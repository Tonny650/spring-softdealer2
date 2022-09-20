package com.thisname.springsoftdealer.contoller;

import com.thisname.springsoftdealer.model.Product;
import com.thisname.springsoftdealer.model.User;
import com.thisname.springsoftdealer.service.IUserService;
import com.thisname.springsoftdealer.service.ProductService;
import com.thisname.springsoftdealer.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private UploadFileService upload;



    @GetMapping("")
    public String show(Model model){
        model.addAttribute("productsList",productService.findAll());
        return "products/show";
    }

    @GetMapping("/create")
    public String crate(){

        return "products/create";
    }

      @PostMapping("/save")
    public String save(Product product, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
        LOGGER.info("Este es el objeto producto {}",product);

        User user = iUserService.findById( Integer.parseInt(session.getAttribute("idUser").toString()) ).get();
        product.setUser(user);

        /**Images*/
        if (product.getId()==null){ //Cuando se crea un nuevo producto
            String nameImage = upload.saveImage(file);
            product.setImage(nameImage);
        }

        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Product product = new Product();
        Optional<Product> optionalProduct = productService.get(id);
        product = optionalProduct.get();

        LOGGER.info("Producto buscado: {}",product);
        model.addAttribute("product",product);

        return "products/edit";
    }

    @PostMapping("/update")
    public String update(Product product, @RequestParam("img") MultipartFile file) throws IOException {

        Product p = new Product();
        p = productService.get(product.getId()).get();


        if (file.isEmpty()){ /**Cuando se edita el producto pero no se cambia la img*/
            product.setImage(p.getImage());

        }else { /**Cuando se Edita tambien la img*/

            //Eliminar cundo no sea la img por defecto

            if (!p.getImage().equals("default.jpg")){
                upload.deleteImage(p.getImage());
            }

            String nameImage = upload.saveImage(file);
            product.setImage(nameImage);
        }

        product.setUser(p.getUser());
        productService.update(product);
        return "redirect:/products";
    }

    @GetMapping("/delate/{id}")
    public String delete(@PathVariable Integer id){

        Product p = new Product();
        p = productService.get(id).get();

        //Eliminar cundo no sea la img por defecto
        if (!p.getImage().equals("default.jpg")){
            upload.deleteImage(p.getImage());
        }

        productService.delete(id);
        return "redirect:/products";
    }

}
