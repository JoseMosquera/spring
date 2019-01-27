package com.game.JoseMosquera.controller;

import java.util.List;

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
import com.game.JoseMosquera.model.JuegoModel;
import com.game.JoseMosquera.model.PlataformaModel;
import com.game.JoseMosquera.model.VentaModel;
import com.game.JoseMosquera.service.CategoriaService;
import com.game.JoseMosquera.service.PlataformaService;
import com.game.JoseMosquera.service.impl.JuegoServiceImpl;
import com.game.JoseMosquera.service.impl.VentaServiceImpl;

@Controller
public class JuegoController {
	
	@Autowired
	@Qualifier("juegoServiceImpl")
	private JuegoServiceImpl juegoService;
	
	@Autowired
	@Qualifier("categoriaService")
	private CategoriaService categoriaService;
	
	@Autowired
	@Qualifier("plataformaService")
	private PlataformaService plataformaService;
	
	@Autowired
	@Qualifier("ventaService")
	private VentaServiceImpl ventaService;

	@GetMapping("/juegosventa")
	public ModelAndView showJuegosVentas() {
		Logs.LOG.info("Llamada al metodo showJuegosVentas() de la clase JuegoController, retorna a la vista "+Vistas.JUEGOSV+" con un listado de juegos");
		ModelAndView mav = new ModelAndView(Vistas.JUEGOSV);
		
		mav.addObject("juegos", juegoService.listJuegosVenta());
		return mav;
	}
	
	@GetMapping("/juegosalq")
	public ModelAndView showJuegosAlq() {
		Logs.LOG.info("Llamada al metodo showJuegosAlq() de la clase JuegoController, retorna a la vista "+Vistas.JUEGOSA+" con un listado de juegos");
		ModelAndView mav = new ModelAndView(Vistas.JUEGOSA);
		
		mav.addObject("juegos", juegoService.listJuegoAlquiler());
		return mav;
	}
	
	@GetMapping("/juego")
	public ModelAndView juego(int id) {
		Logs.LOG.info("Llamada al metodo juego() de la clase JuegoController, recibe un id '"+id+"'");
		ModelAndView mav = new ModelAndView(Vistas.JUEGO);
		
		mav.addObject("juego", juegoService.juego(id));
		return mav;		
	}
	
	@GetMapping("/juegoform")
	public String juegoForm(Model model,
			@RequestParam(name = "id", required = false) int id) {
		Logs.LOG.info("Llamada al metodo juegoForm() de la clase JuegoController");
		JuegoModel juegoModel = new JuegoModel();
		List<CategoriaModel> listAllCategorias = categoriaService.listAllCategorias();
		List<PlataformaModel> listAllPlataformas = plataformaService.listAllPlataformas();
		
		if(id!=0) {
			Logs.LOG.info("Metodo juegoForm() de la clase JuegoController, retorna a la vista "+Vistas.EDITJUEGO+", editar");
			juegoModel = juegoService.juego(id);
			model.addAttribute("juegoModel", juegoModel);
			model.addAttribute("listAllCategorias", listAllCategorias);
			model.addAttribute("listAllPlataformas", listAllPlataformas);
			return Vistas.EDITJUEGO;
		}
		Logs.LOG.info("Metodo juegoForm() de la clase JuegoController, retorna a la vista "+Vistas.ADDJUEGO+", crear");
		model.addAttribute("juegoModel", juegoModel);
		model.addAttribute("listAllCategorias", listAllCategorias);
		model.addAttribute("listAllPlataformas", listAllPlataformas);
		Logs.LOG.info(juegoModel.toString());
		return Vistas.ADDJUEGO;
	}
	
	@PostMapping("/crearjuego")
	public ModelAndView crearJuego(@Valid @ModelAttribute("juegoModel") JuegoModel juegoModel,
			BindingResult bindingResult) {
		Logs.LOG.info("Llamada al metodo crearJuego() de la clase JuegoController, recibe un juegoModel:'"+juegoModel.toString()+"'");
		
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			Logs.LOG.info("Error en la validacion de creacion de juego");
			Logs.LOG.info(juegoModel.toString());
			mav.setViewName(Vistas.ADDJUEGO);
		} else {
			juegoService.addJuego(juegoModel);
			Logs.LOG.info("Noticia se creo correctamente");
			Logs.LOG.info(juegoModel.toString());
			mav.setViewName("redirect:/juegosventa");
		}
		return mav;
	}
	
	@GetMapping("/removejuego")
	public ModelAndView removeJuego(@RequestParam(name = "id", required = true) int id) {
		Logs.LOG.info("Llamada al metodo removeJuego() de la clase JuegoController, retorna al metodo showJuegosVentas()");
		juegoService.removeJuego(id);
		return showJuegosVentas();
	}
	
	@GetMapping("/ventasuccess")
	public ModelAndView competicionSuccess(@RequestParam(name = "id", required = true) int idJuego) {
		ModelAndView mav = new ModelAndView();
		VentaModel ventaModel = ventaService.addVenta(idJuego);
		Logs.LOG.info("Participacion se creo correctamente");
		Logs.LOG.info(ventaModel.toString());
		
		mav.setViewName("redirect:/juegosventa");

		return mav;
	}
}