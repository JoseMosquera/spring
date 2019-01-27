package com.game.JoseMosquera.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.model.ParticipacionModel;
import com.game.JoseMosquera.service.impl.ParticipacionServiceImpl;

@Controller
public class ParticipacionController {
	
	@Autowired
	@Qualifier("participacionService")
	private ParticipacionServiceImpl participacionService;

	@GetMapping("/competicionsuccess")
	public ModelAndView competicionSuccess(@RequestParam(name = "id", required = true) int idCompeticion) {
		ModelAndView mav = new ModelAndView();
		ParticipacionModel participacionModel = participacionService.addParticipacion(idCompeticion);
		Logs.LOG.info("Participacion se creo correctamente");
		Logs.LOG.info(participacionModel.toString());
		
		mav.setViewName("redirect:/competiciones");

		return mav;
	}
}
