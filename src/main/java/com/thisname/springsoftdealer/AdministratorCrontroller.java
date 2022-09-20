package com.thisname.springsoftdealer;

import com.thisname.springsoftdealer.model.Order;
import com.thisname.springsoftdealer.model.Product;
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
import org.springframework.web.bind.annotation.RequestMapping;


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


}
