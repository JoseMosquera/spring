package com.game.JoseMosquera.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.game.JoseMosquera.model.NoticiaModel;
import com.game.JoseMosquera.service.impl.NoticiaServiceImpl;

@Controller
public class NoticiaController {
	
	@Autowired
	@Qualifier("noticiaServiceImpl")
	private NoticiaServiceImpl noticiaServiceImpl;

	@GetMapping("/noticias")
	public ModelAndView listNoticias() {
		Logs.LOG.info("Llamada al metodo listNoticias() de la clase NoticiaController, retorna a la vista "+Vistas.NOTICIA+" con un listado de noticias");
		ModelAndView mav = new ModelAndView(Vistas.NOTICIAS);
		
		mav.addObject("noticias", noticiaServiceImpl.listAllNoticias());
		return mav;
	}
	
	@GetMapping("/noticiasD")
	public ModelAndView listNoticiasD() {
		Logs.LOG.info("Llamada al metodo listNoticiasD() de la clase NoticiaController, retorna a la vista "+Vistas.NOTICIA+" con un listado de noticias destacadas");
		ModelAndView mav = new ModelAndView(Vistas.NOTICIAS);
		mav.addObject("noticias", noticiaServiceImpl.listNoticiasDestacadas());
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/noticia")
	public ModelAndView noticia(int id) {
		Logs.LOG.info("Llamada al metodo noticia() de la clase NoticiaController, recibe un id '"+id+"'");
		ModelAndView mav = new ModelAndView(Vistas.NOTICIA);
		
		mav.addObject("noticia", noticiaServiceImpl.noticia(id));
		return mav;		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/noticiaform")
	public String noticiaForm(Model model,
			@RequestParam (name = "id", required = false) int id) {
		Logs.LOG.info("Llamada al metodo noticiaForm() de la clase NoticiaController");
		NoticiaModel noticiaModel = new NoticiaModel();
		
		if(id != 0) {
			Logs.LOG.info("Metodo noticiaForm() de la clase NoticiaController, retorna a la vista "+Vistas.EDITNOTICIA+", editar");
			noticiaModel = noticiaServiceImpl.noticia(id);
			model.addAttribute("noticiaModel", noticiaModel);
			return Vistas.EDITNOTICIA;
		}
		Logs.LOG.info("Metodo noticiaForm() de la clase NoticiaController, retorna a la vista "+Vistas.ADDNOTICIA+", crear");
		model.addAttribute("noticiaModel", noticiaModel);
		Logs.LOG.info(noticiaModel.toString());
		return Vistas.ADDNOTICIA;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/crearnoticia")
	public ModelAndView crearNoticia(@Valid @ModelAttribute("noticiaModel") NoticiaModel noticiaModel,
			BindingResult bindingResult) {
		Logs.LOG.info("Llamada al metodo crearNoticia() de la clase NoticiaController, recibe un noticiaModel: '"+noticiaModel.toString()+"'");
		
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			Logs.LOG.info("Error en la validacion de creacion de noticia");
			Logs.LOG.info(noticiaModel.toString());
			mav.setViewName(Vistas.ADDNOTICIA);
		} else {
			noticiaServiceImpl.addNoticia(noticiaModel);
			Logs.LOG.info("Noticia se creo correctamente");
			Logs.LOG.info(noticiaModel.toString());
			mav.setViewName("redirect:/noticias");
		}
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/removenoticia")
	public ModelAndView removeNoticia(@RequestParam(name = "id", required = true) int id) {
		Logs.LOG.info("Llamada al metodo removeNoticia() de la clase NoticiaController, retorna al metodo listNoticias()");
		noticiaServiceImpl.removeNoticia(id);
		return listNoticias();
	}
}
