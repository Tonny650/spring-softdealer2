package com.thisname.springsoftdealer.contoller;


import com.thisname.springsoftdealer.model.Order;
import com.thisname.springsoftdealer.model.User;
import com.thisname.springsoftdealer.service.IOrderService;
import com.thisname.springsoftdealer.service.IUserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/user")
public class UserController {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;

    @GetMapping("/singUp")
    public String crete(){
        return "usuarios/registro";
    }

    @PostMapping("/save")
    public String save(User user){
        logger.info("User Sing Up: {}",user);
        user.setType("USER");
        user.setPassword( passwordEncoder.encode( user.getPassword() ) );
        userService.save(user);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){

        return "usuarios/login";
    }

    @GetMapping("/SingOn")
    public String singOn(User Userp, HttpSession session){
        logger.info("Sing On : {}",Userp);

        Optional<User> user = userService.findById(Integer.parseInt(session.getAttribute("idUser").toString()));

        if (user.isPresent()) {
            session.setAttribute("idUser",user.get().getId());
            if (user.get().getType().equals("admin")){
                return "redirect:/administrator";
            }else{
                return "redirect:/";
            }
        }else {
            logger.info("User no existe");
        }

        return "redirect:/";
    }

    @GetMapping("/shopping")
    public String getShopping(Model model, HttpSession session){
       model.addAttribute("sesion",session.getAttribute("idUser"));

        User usuario = userService.findById( Integer.parseInt(session.getAttribute("idUser").toString()) ).get();
        List<Order> orders = orderService.findByUser(usuario);

        logger.info("order : {}",orders);
        model.addAttribute("order",orders);

        return "usuarios/compras";
    }

    @GetMapping("/details/{id}")
    public String shoppingDetails(@PathVariable Integer id,HttpSession session, Model model){

        model.addAttribute("sesion",session.getAttribute("idUser"));
        logger.info("ID Shopping details: {}",id);
        Optional<Order> order = orderService.findById(id);

        model.addAttribute("details",order.get().getOrderDetail());

        return "usuarios/detallecompra";
    }

     @GetMapping("/toClose")
    public String signOff(HttpSession session){
        session.removeAttribute("idUser");

        return "redirect:/";
     }


}
