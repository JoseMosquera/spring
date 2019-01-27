package com.game.JoseMosquera.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.constant.Vistas;
import com.game.JoseMosquera.service.impl.NoticiaServiceImpl;

@Controller
public class IndexController {
	
	@Autowired
	@Qualifier("noticiaServiceImpl")
	private NoticiaServiceImpl noticiaServiceImpl;
	
	@GetMapping("/index")
	public ModelAndView listNoticiasD() {
		Logs.LOG.info("Llamada al metodo listNoticiasD() de la clase NoticiaController, retorna a la vista "+Vistas.NOTICIA+" con un listado de noticias destacadas");
		ModelAndView mav = new ModelAndView(Vistas.INDEX);
		mav.addObject("noticias", noticiaServiceImpl.listNoticiasDestacadas());
		return mav;
	}
}
