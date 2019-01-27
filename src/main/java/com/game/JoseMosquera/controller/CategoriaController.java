package com.game.JoseMosquera.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.constant.Vistas;
import com.game.JoseMosquera.model.CategoriaModel;
import com.game.JoseMosquera.service.impl.CategoriaServiceImpl;

@Controller
public class CategoriaController {

	@Autowired
	@Qualifier("categoriaService")
	private CategoriaServiceImpl categoriaService;
	
	@GetMapping("/categorias")
	public ModelAndView showCategorias() {
		Logs.LOG.info("Llamada al metodo showCategorias() de la clase CategoriaController, retorna a la vista "+Vistas.CATEGORIAS+" con un listado de competiciones");
		ModelAndView mav = new ModelAndView(Vistas.CATEGORIAS);
		mav.addObject("categorias", categoriaService.listAllCategorias());
		return mav;
	}
	
	@GetMapping("/categoria")
	public ModelAndView categoria(@RequestParam("id") int id) {
		Logs.LOG.info("Llamada al metodo categoria() de la clase CategoriaController, recibe un id '"+id+"'");
		ModelAndView mav = new ModelAndView(Vistas.CATEGORIA);
		mav.addObject("categoriaModel", categoriaService.categoria(id));
		return mav;
		
	}
	
	@GetMapping("/categoriaform")
	public String categoriaForm(Model model,
			@RequestParam(name = "id", required = false) int id){
		Logs.LOG.info("Llamada al metodo categoriaForm() de la clase CategoriaController");
		CategoriaModel categoriaModel = new CategoriaModel();
		
		if(id != 0) {
			Logs.LOG.info("Metodo categoriaForm() de la clase CategoriaController, retorna a la vista "+Vistas.EDITCATEGORIA+", editar");
			categoriaModel = categoriaService.categoria(id);
			model.addAttribute("categoriaModel", categoriaModel);
			return Vistas.EDITCATEGORIA;
		}
		Logs.LOG.info("Metodo categoriaForm() de la clase CategoriaController, retorna a la vista "+Vistas.ADDCATEGORIA+", crear");
		model.addAttribute("categoriaModel", categoriaModel);
		return Vistas.ADDCATEGORIA;
	}
	
	@PostMapping("/crearcategoria")
	public ModelAndView crearCompeticion(@Valid @ModelAttribute("categoriaModel") CategoriaModel categoriaModel,
			BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			Logs.LOG.info("Error en la validacion de creacion de categoria");
			Logs.LOG.info(categoriaModel.toString());
			mav.setViewName(Vistas.ADDCATEGORIA);
		}else {
			categoriaService.addCategoria(categoriaModel);
			Logs.LOG.info("Categoria se creo correctamente");
			Logs.LOG.info(categoriaModel.toString());
			mav.setViewName("redirect:/categorias");
		}
		return mav;
	}
	
	@GetMapping("removecategoria")
	public ModelAndView removeCategoria(@RequestParam(name = "id", required = true) int id) {
		Logs.LOG.info("Llamada al metodo removeCategoria() de la clase CategoriaController, retorna al metodo showCategorias()");
		categoriaService.removeCategoria(id);
		return showCategorias();
	}
}
