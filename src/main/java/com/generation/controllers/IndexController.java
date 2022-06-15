package com.generation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.generation.models.Usuario;

@Controller
/*esto establece que sea controlador @*/
public class IndexController {
    //http://localhost:8080/
    @RequestMapping("/") //anotacion para capturar las rutas
    public String index(Model model){
        model.addAttribute("apellidos", "Iturra Gonzalez");
        model.addAttribute("nombres", "Carlos Patricio Emilio");
        model.addAttribute("edad", "26");
        
        //instancia de usuario
        Usuario usuario = new Usuario("Donato","Loren",12,"Admin1234");
        //pasando el objeto a la vista (JSP)
        model.addAttribute("usuario", usuario);

        return "index.jsp";
    } 
}
