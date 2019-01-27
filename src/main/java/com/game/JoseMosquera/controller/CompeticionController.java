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
import com.game.JoseMosquera.model.CompeticionModel;
import com.game.JoseMosquera.service.impl.CompeticionServiceImpl;

@Controller
public class CompeticionController {

	@Autowired
	@Qualifier("competicionServiceImpl")
	private CompeticionServiceImpl competiService;
	
	@GetMapping("/competiciones")
	public ModelAndView showCompeticiones() {
		Logs.LOG.info("Llamada al metodo showCompeticiones() de la clase CompeticionController, retorna a la vista "+Vistas.COMPETIS+" con un listado de competiciones");
		ModelAndView mav = new ModelAndView(Vistas.COMPETIS);
		mav.addObject("competiciones", competiService.listCompeticiones());
		return mav;
	}
	
	@GetMapping("/competicion")
	public ModelAndView competicion(@RequestParam("id") int id) {
		Logs.LOG.info("Llamada al metodo competicion() de la clase CompeticionController, recibe un id '"+id+"'");
		ModelAndView mav = new ModelAndView(Vistas.COMPETI);
		mav.addObject("competicionModel", competiService.competicion(id));
		return mav;
		
	}
	
	@GetMapping("/competicionform")
	public String competicionForm(Model model,
			@RequestParam(name = "id", required = false) int id){
		Logs.LOG.info("Llamada al metodo competicionForm() de la clase CompeticionController");
		CompeticionModel competicionModel = new CompeticionModel();
		
		if(id != 0) {
			Logs.LOG.info("Metodo competicionForm() de la clase CompeticionController, retorna a la vista "+Vistas.EDITCOMPETI+", editar");
			competicionModel = competiService.competicion(id);
			model.addAttribute("competicionModel", competicionModel);
			return Vistas.EDITCOMPETI;
		}
		Logs.LOG.info("Metodo competicionForm() de la clase CompeticionController, retorna a la vista "+Vistas.ADDCOMPETI+", crear");
		model.addAttribute("competicionModel", competicionModel);
		return Vistas.ADDCOMPETI;
	}
	
	@PostMapping("/crearcompeticion")
	public ModelAndView crearCompeticion(@Valid @ModelAttribute("competicionModel") CompeticionModel competicionModel,
			BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			Logs.LOG.info("Error en la validacion de creacion de competicion");
			Logs.LOG.info(competicionModel.toString());
			mav.setViewName(Vistas.ADDCOMPETI);
		}else {
			competiService.addCompeticion(competicionModel);
			Logs.LOG.info("Competicion se creo correctamente");
			Logs.LOG.info(competicionModel.toString());
			mav.setViewName("redirect:/competiciones");
		}
		return mav;
	}
	
	@GetMapping("removecompeti")
	public ModelAndView removeCompeticion(@RequestParam(name = "id", required = true) int id) {
		Logs.LOG.info("Llamada al metodo removeCompeticion() de la clase CompeticionController, retorna al metodo showCompeticiones()");
		competiService.removeCompeticion(id);
		return showCompeticiones();
	}
}
