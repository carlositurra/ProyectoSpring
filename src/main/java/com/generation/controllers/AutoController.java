package com.generation.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.generation.models.Auto;
import com.generation.services.AutoService;

@Controller
@RequestMapping("/auto")
public class AutoController {
	@Autowired
	AutoService autoService;
	
	@RequestMapping("")
	public String inicio(@ModelAttribute("auto") Auto auto) {
		
		return "auto.jsp";
	}
	
	@PostMapping("/guardar")
	public String guardarAuto(@Valid @ModelAttribute("auto") Auto auto,
			BindingResult resultado,
			Model model) {
		
		
		if(resultado.hasErrors()) {
			model.addAttribute("msgError","Datos erroneos");
			return "autos.jsp";
		}else {
			//enviamos el obeto a persistir en base datos
			autoService.guardarAuto(auto);
			//obtener un alista de autos
			List<Auto> listaAutos = autoService.findAll();
			//pasamos la lista de autos al jsp
			model.addAttribute("autosCapturados", listaAutos);
			return "mostrarAutos.jsp";
		}
		
		
	}

	//solo mostrar el listado de autos
	@RequestMapping("/mostrar")
	public String mostrar(Model model){
		//obtener un alista de autos
		List<Auto> listaAutos = autoService.findAll();
		//pasamos la lista de autos al jsp
		model.addAttribute("autosCapturados", listaAutos);

		return "mostrarAutos.jsp";
	}

	@RequestMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long id, Model model) {
		System.out.println("el id es: "+id);
		Auto auto = autoService.buscarId(id);
model.addAttribute("auto", auto); //pasar al jsp

		return "editarAuto.jsp";//redireccionar a otra url o path
		
	}

	//localhost:8080/auto/actualizar/{id}  --> //actualizar para la persistencia en la bd

	@PostMapping("/actualizar/{id}")
	public String actualizarAuto(@PathVariable("id") Long id,@Valid @ModelAttribute("auto") Auto auto,
			BindingResult resultado,
			Model model) {
		System.out.println("el id al actualizar es: "+id);
		
		if(resultado.hasErrors()) {
			model.addAttribute("msgError","Datos erroneos");
			return "editarAuto.jsp";
		}else {
			auto.setId(id);// agregar el id al objeto
			
			//enviamos el obeto a persistir en base datos
			autoService.guardarAuto(auto);
			//obtener un alista de autos
			List<Auto> listaAutos = autoService.findAll();
			//pasamos la lista de autos al jsp
			model.addAttribute("autosCapturados", listaAutos);
			return "mostrarAutos.jsp";
		}

	/*Edicion:
		capturar el id
		buscar el objeto completo a bd
		pasar el objeto completo al JSP
		mostrar los datos en el JSP
		modicar un dato, menos el ID
		pasar el objeto modificado al controlador
		en la nueva ruta(controlador), capturamos el objeto
		actualizar el objeto en base datos
 */
}
}