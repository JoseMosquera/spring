package com.game.JoseMosquera.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.constant.Vistas;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String showLogin(Model model, 
			@RequestParam(name="error", required=false) String error,
			@RequestParam(name="logout", required=false) String logout) {
		Logs.LOG.info("Metodo showLogin(): puede recibir un error: '"+error+"' y/o logout: '"+logout+"'");
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		Logs.LOG.info("Metodo showLogin retorna la vista: "+Vistas.LOGIN);
		return Vistas.LOGIN;
	}
	
	@GetMapping({"/loginsucces", "/"})
	public ModelAndView loginCheck() {
		Logs.LOG.info("Metodo loginCheck retorna a la vista: "+Vistas.INDEX);
		
		ModelAndView mav = new ModelAndView(Vistas.INDEX);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		mav.addObject("username", user.getUsername());
		return mav;
	}
}