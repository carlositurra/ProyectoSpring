package com.generation.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.models.Usuario;
import com.generation.services.UsuarioService;


@Controller
@RequestMapping("/registro")
public class RegistroController { //localhost:8080/registro

    //inyeccion de dependencias
    @Autowired
    UsuarioService usuarioService;
    
    //ruta para desplegar el jsp, ruta por default
    @RequestMapping("")
    public String registro(@ModelAttribute("usuario") Usuario usuario) {  //pasando un objeto vacio con @ModelAttribute
        return "registro.jsp"; //la pagina a desplegar
    }
    
    //ruta para capturar los datos del formulario,  localhost:8080/registro/usuario
    @PostMapping("/usuario/respaldo")
    public String registroUsuario(@RequestParam(value = "nombre") String nombre,
                                    @RequestParam(value = "apellido") String apellido,
                                    @RequestParam(value = "edad") String edad) {
        System.out.println("parametro nombre: " + nombre);
        System.out.println("parametro apellido: " + apellido);
        System.out.println("parametro edad: " + edad);
        return "registro.jsp"; //la pagina a desplegar
    }

    @PostMapping("/usuario")
    public String guardarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario,
        BindingResult resultado,
        Model model) {
        
        if(resultado.hasErrors()){//capturando si existe un error en el ingreso de datos desde el jsp
            model.addAttribute("msgError", "Debe realizar ingreso correcto de los datos");
            return "registro.jsp";
        }else{
            //capturamos el objeto con los atributos llenos
        System.out.println(usuario.getNombre()+" "+usuario.getApellido()+" "+usuario.getEdad());
        
        //enviar el objeto service
        usuarioService.saveUsuario(usuario);
        
        return "index.jsp"; //la pagina a desplegar
        }
        
    }

}
