package com.thisname.springsoftdealer;

import com.thisname.springsoftdealer.model.Order;
import com.thisname.springsoftdealer.model.OrderDetail;
import com.thisname.springsoftdealer.model.Product;
import com.thisname.springsoftdealer.model.User;
import com.thisname.springsoftdealer.service.IOrderDetailService;
import com.thisname.springsoftdealer.service.IOrderService;
import com.thisname.springsoftdealer.service.IUserService;
import com.thisname.springsoftdealer.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    //Para almacenar los detalles de la orden
    private List<OrderDetail> details = new ArrayList<OrderDetail>();
    //Datos de la orden
    Order order = new Order();

    @Autowired
    private IUserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping("")
    public String index(Model model, HttpSession session) {

        log.info("Session: {}", session.getAttribute("idUser"));

        model.addAttribute("products", productService.findAll());

        //Secion
        model.addAttribute("sesion", session.getAttribute("idUser"));

        return "usuarios/Home";
    }

    @GetMapping("productohome/{id}")
    public String productHome(@PathVariable Integer id, Model model){
        Product product = new Product();
        Optional<Product> productOptional = productService.get(id);
        product = productOptional.get();
        model.addAttribute("product", product);

        log.info("Id producto eniado como parametro: {}",id);
        return "usuarios/productohome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer lot, Model model){

        OrderDetail orderDetail = new OrderDetail();
        Product product = new Product();
        double sumaTotal = 0;

        Optional<Product> optionalProduct = productService.get(id);
        product = optionalProduct.get();
        orderDetail.setLot(lot);
        orderDetail.setPrice(product.getPrice());
        orderDetail.setName(product.getName());
        orderDetail.setTotal(product.getPrice()*lot);
        orderDetail.setProduct(product);

        Integer idProduct = product.getId();
        boolean ingresados=details.stream().anyMatch(p -> p.getProduct().getId() ==idProduct);

        if (!ingresados){
            details.add(orderDetail);
        }

        sumaTotal = details.stream().mapToDouble(dt->dt.getTotal()).sum();

        order.setTotal(sumaTotal);

        model.addAttribute("cart",details);
        model.addAttribute("order",order);

        return "usuarios/carrito";
    }
    @GetMapping("/delete/cart/{id}")
    public String delateProduct(@PathVariable Integer id, Model model){
        List<OrderDetail> neworder = new ArrayList<OrderDetail>();

        for(OrderDetail orderDetail: details){
            if (orderDetail.getProduct().getId()!=id){
                neworder.add(orderDetail);
            }
        }
        details= neworder;

        double sumaTotal = 0;
        sumaTotal = details.stream().mapToDouble(dt->dt.getTotal()).sum();

        order.setTotal(sumaTotal);

        model.addAttribute("cart",details);
        model.addAttribute("order",order);

        return "usuarios/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model, HttpSession session) {

        model.addAttribute("cart",details);
        model.addAttribute("order",order);

        model.addAttribute("sesion",session.getAttribute("idUser"));

        return "/usuarios/carrito";
    }

    @GetMapping("/order")
    public String order(Model model, HttpSession session){

        User user = userService.findById( Integer.parseInt(session.getAttribute("idUser").toString())).get();

        model.addAttribute("cart",details);
        model.addAttribute("order",order);
        model.addAttribute("user",user);

        return "usuarios/resumenorden";
    }

    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session){
        Date creationDate = new Date();
        order.setCreationDate(creationDate);
        order.setName(orderService.generateOrderNumber());
        //User
        User user = userService.findById( Integer.parseInt(session.getAttribute("idUser").toString()) ).get();
        order.setUser(user);
        orderService.save(order);

        /**Details Save*/

        for (OrderDetail dt:details){
            dt.setOrder(order);
            orderDetailService.save(dt);
        }

        //Limpiar list
        order = new Order();
        details.clear();

        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchProduct(@RequestParam String nameProduct, Model model, HttpSession session){
        log.info("Nombre del Producto: {}",nameProduct);

        model.addAttribute("sesion", session.getAttribute("idUser"));

        List<Product> products = productService.findAll().stream().filter(p -> p.getName().contains(nameProduct)).collect(Collectors.toList());

        model.addAttribute("products",products);


        return "usuarios/Home";
    }

}
