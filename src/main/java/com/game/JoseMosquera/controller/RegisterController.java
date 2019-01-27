package com.game.JoseMosquera.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.game.JoseMosquera.constant.Vistas;
import com.game.JoseMosquera.model.UserModel;
import com.game.JoseMosquera.service.UserService;

@Controller
public class RegisterController {
	
	private static final Log LOG = LogFactory.getLog(LoginController.class);
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	@GetMapping("/register")
	public String shorRegister(Model model) {
		model.addAttribute("userModel", new UserModel());
		return Vistas.REGISTER;
	}
	
	@PostMapping("/registersucces")
	public ModelAndView registerSucces(@Valid @ModelAttribute("userModel") UserModel userModel,
			BindingResult bindingResult) {
		LOG.info("Metodo registerSucces(): recibe un userModel: '"+userModel.toString()+"'");
		
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			LOG.info("Error en la calidacion de creacion de usuario");
			mav.setViewName(Vistas.REGISTER);
		} else {
			LOG.info("Usuario se creo correctamente");
			userService.addUser(userModel);
			mav.setViewName(Vistas.LOGIN);
		}
		return mav;
	}
}