package com.game.JoseMosquera.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.constant.Vistas;
import com.game.JoseMosquera.model.ParticipacionModel;
import com.game.JoseMosquera.repository.ParticipacionRepository;
import com.game.JoseMosquera.service.impl.ParticipacionServiceImpl;

@Controller
public class ParticipacionController {
	
	@Autowired
	@Qualifier("participacionService")
	private ParticipacionServiceImpl participacionService;
	
	@Autowired
	@Qualifier("participacionRepository")
	private ParticipacionRepository participacionRepository;

	@PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_USER')")
	@GetMapping("/competicionsuccess")
	public ModelAndView competicionSuccess(@RequestParam(name = "id", required = true) int idCompeticion) {
		ModelAndView mav = new ModelAndView();
		ParticipacionModel participacionModel = participacionService.addParticipacion(idCompeticion);
		Logs.LOG.info("Participacion se creo correctamente");
		Logs.LOG.info(participacionModel.toString());
		
		mav.setViewName("redirect:/competiciones");

		return mav;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/competicionesfuturas")
	public ModelAndView competicionesPendientes() {
		Logs.LOG.info("Llamada al metodo competicionesPendientes() de la clase ParticipacionController, retorna a la vista "+Vistas.COMPETISFUT+" con un listado de participaciones");
		ModelAndView mav = new ModelAndView(Vistas.COMPETISFUT);
		
		mav.addObject("participaciones", participacionService.listAllCompeticionesPendientes());
		return mav;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/competicionespasadas")
	public ModelAndView competicionesRealizados() {
		Logs.LOG.info("Llamada al metodo competicionesRealizados() de la clase ParticipacionController, retorna a la vista "+Vistas.COMPETISPA+" con un listado de participaciones");
		ModelAndView mav = new ModelAndView(Vistas.COMPETISPA);
		
		mav.addObject("participaciones", participacionService.listAllCompeticionesRealizadas());
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/competicionesfuturasadmin")
	public ModelAndView competicionesPendientesAdmin() {
		Logs.LOG.info("Llamada al metodo competicionesPendientesAdmin() de la clase ParticipacionController, retorna a la vista "+Vistas.COMPETISFUTAD+" con un listado de participaciones");
		ModelAndView mav = new ModelAndView(Vistas.COMPETISFUTAD);
		
		mav.addObject("participaciones", participacionService.listAllCompeticionesPendientesAdmin());
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/posicionform")
	public String posicionForm(Model model, @RequestParam(name = "id", required = true) int id) {
		Logs.LOG.info("Llamada al metodo posicionForm() de la clase ParticipacionController, recibe un id: "+id+" retorna a la vista "+Vistas.POSFORM);
		ParticipacionModel participacionModel = participacionService.participacion(id);
		Logs.LOG.info("Con el id obtiene el modelo: "+participacionModel.toString());
		model.addAttribute("participacionModel", participacionModel);
		model.addAttribute("id", id);
		return Vistas.POSFORM;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/asignarposicion")
	public ModelAndView asignarPosicion(@ModelAttribute("posicion") int posicion,
			@ModelAttribute("id") int id) {
		Logs.LOG.info("Llamada al metodo asignarPosicion() de la clase ParticipacionController, recibe un id:'"+id+"' y una posicion: '"+posicion);
		participacionService.asignarPosicion(posicion, id);
		return competicionesPendientesAdmin();
	}
}
