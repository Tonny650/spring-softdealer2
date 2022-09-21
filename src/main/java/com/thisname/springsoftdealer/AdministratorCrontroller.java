package com.thisname.springsoftdealer;

import com.thisname.springsoftdealer.model.Order;
import com.thisname.springsoftdealer.model.Product;
import com.thisname.springsoftdealer.model.User;
import com.thisname.springsoftdealer.service.IOrderService;
import com.thisname.springsoftdealer.service.IUserService;
import com.thisname.springsoftdealer.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/administrator")
public class AdministratorCrontroller {

    private Logger log = LoggerFactory.getLogger(AdministratorCrontroller.class);

    @Autowired
    private IUserService iuserService;

    @Autowired
    private ProductService productService;

    @Autowired
    private IOrderService iOrderService;

    @GetMapping("")
    public String home(Model model){

        List<Product> products=productService.findAll();
        model.addAttribute("products",products);

        return "administrator/home";
    }

    @GetMapping("/viewUsers")
    public String Users(Model model){

        model.addAttribute("users",iuserService.findAll());

        return "administrator/usuarios";
    }

    @GetMapping("/orders")
    public String Orders(Model model){

        model.addAttribute("orders",iOrderService.fiadAll());

        return "administrator/ordenes";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable Integer id){
        log.info("id orden {}",id);
        Order order = iOrderService.findById(id).get();

        model.addAttribute("details",order.getOrderDetail());

        return "administrator/detalleorden";
    }

    @GetMapping("/access")
    public String access(HttpSession session){
        User user = iuserService.findById( Integer.parseInt(session.getAttribute("idUser").toString()) ).get();
        int admin = 1;
        if (admin >= 5){
            return "redirect:http://localhost/phpmyadmin/index.php?route=/database/structure&db=softdealer";
        }else {
            return "administrator/accessDenied";
        }
    }

}
