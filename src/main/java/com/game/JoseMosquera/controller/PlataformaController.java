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
import com.game.JoseMosquera.model.PlataformaModel;
import com.game.JoseMosquera.service.impl.PlataformaServiceImpl;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
public class PlataformaController {

	@Autowired
	@Qualifier("plataformaService")
	private PlataformaServiceImpl plataformaService;

	@GetMapping("/plataformas")
	public ModelAndView showPlataformas() {
		Logs.LOG.info("Llamada al metodo showPlataforma() de la clase PlataformaController, retorna a la vista "+Vistas.PLATAFORMAS+" con un listado de plataformas");
		ModelAndView mav = new ModelAndView(Vistas.PLATAFORMAS);
		mav.addObject("plataformas", plataformaService.listAllPlataformas());
		return mav;
	}

	@GetMapping("/plataformaform")
	public String categoriaForm(Model model,
			@RequestParam(name = "id", required = false) int id){
		Logs.LOG.info("Llamada al metodo plataformaForm() de la clase PlataformaController");
		PlataformaModel plataformaModel = new PlataformaModel();
		
		if(id != 0) {
			Logs.LOG.info("Metodo plataformaForm() de la clase PlataformaController, retorna a la vista "+Vistas.EDITPLATAFORMA+", editar");
			plataformaModel = plataformaService.plataforma(id);
			model.addAttribute("plataformaModel", plataformaModel);
			return Vistas.EDITPLATAFORMA;
		}
		Logs.LOG.info("Metodo plataformaForm() de la clase PlataformaController, retorna a la vista "+Vistas.ADDPLATAFORMA+", crear");
		model.addAttribute("plataformaModel", plataformaModel);
		return Vistas.ADDPLATAFORMA;
	}

	@PostMapping("/crearplataforma")
	public ModelAndView crearCompeticion(@Valid @ModelAttribute("plataformaModel") PlataformaModel plataformaModel,
			BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			Logs.LOG.info("Error en la validacion de creacion de plataforma");
			Logs.LOG.info(plataformaModel.toString());
			mav.setViewName(Vistas.ADDPLATAFORMA);
		}else {
			plataformaService.addPlataforma(plataformaModel);
			Logs.LOG.info("Plataforma se creo correctamente");
			Logs.LOG.info(plataformaModel.toString());
			mav.setViewName("redirect:/plataformas");
		}
		return mav;
	}

	@GetMapping("removeplataforma")
	public ModelAndView removePlataforma(@RequestParam(name = "id", required = true) int id) {
		Logs.LOG.info("Llamada al metodo removePlataforma() de la clase PlataformaController, retorna al metodo showPlataformas()");
		plataformaService.removePlataforma(id);
		return showPlataformas();
	}
}
