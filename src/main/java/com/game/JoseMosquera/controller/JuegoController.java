package com.game.JoseMosquera.controller;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.constant.Vistas;
import com.game.JoseMosquera.model.AlquilerModel;
import com.game.JoseMosquera.model.CategoriaModel;
import com.game.JoseMosquera.model.JuegoModel;
import com.game.JoseMosquera.model.PlataformaModel;
import com.game.JoseMosquera.model.VentaModel;
import com.game.JoseMosquera.service.CategoriaService;
import com.game.JoseMosquera.service.PlataformaService;
import com.game.JoseMosquera.service.impl.AlquilerServiceImpl;
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
	
	@Autowired
	@Qualifier("alquilerService")
	private AlquilerServiceImpl alquilerService;

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
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/juegoventa")
	public ModelAndView juegoventa(int id) {
		Logs.LOG.info("Llamada al metodo juego() de la clase JuegoController, recibe un id '"+id+"'");
		ModelAndView mav = new ModelAndView(Vistas.JUEGOVENTA);
		
		mav.addObject("juego", juegoService.juego(id));
		return mav;		
	}
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/juegoalquiler")
	public ModelAndView juegoalquiler(int id) {
		Logs.LOG.info("Llamada al metodo juego() de la clase JuegoController, recibe un id '"+id+"'");
		ModelAndView mav = new ModelAndView(Vistas.JUEGOALQUILER);
		
		mav.addObject("juego", juegoService.juego(id));
		return mav;		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/crearjuego")
	public ModelAndView crearJuego(@Valid @ModelAttribute("juegoModel") JuegoModel juegoModel,
			BindingResult bindingResult,
			@RequestParam("imagen") MultipartFile file) throws IOException {
		Logs.LOG.info("Llamada al metodo crearJuego() de la clase JuegoController, recibe un juegoModel:'"+juegoModel.toString()+"'");
		
		ModelAndView mav = new ModelAndView();
		

		List<CategoriaModel> listAllCategorias = categoriaService.listAllCategorias();
		List<PlataformaModel> listAllPlataformas = plataformaService.listAllPlataformas();
		
		Logs.LOG.info("--------------------------------------------------");
		String caratula = file.getOriginalFilename();
		Logs.LOG.info("Variable caratula: "+caratula);
		Logs.LOG.info("--------------------------------------------------");
		int localizacionExtension = caratula.indexOf(".");
		Logs.LOG.info("Localizacion de la extension del archivo: "+localizacionExtension);
		Logs.LOG.info("--------------------------------------------------");
		String extension = caratula.substring(localizacionExtension+1);
		Logs.LOG.info("Nombre de la extension del archivo: "+extension);
		Logs.LOG.info("--------------------------------------------------");
		
		
		if(bindingResult.hasErrors()) {
			Logs.LOG.info("Error en la validacion de creacion de juego");
			Logs.LOG.info(juegoModel.toString());
			Logs.LOG.info(bindingResult.hasErrors());
			mav.addObject("listAllCategorias", listAllCategorias);
			mav.addObject("listAllPlataformas", listAllPlataformas);
			mav.setViewName(Vistas.ADDJUEGO);
		} else if (!(extension.equals("jpg") || extension.equals("png") || extension.equals("jpeg"))){
			Logs.LOG.info("Dentro de la condicion error de extension: "+extension);
			String imagenerror = "error";
			mav.addObject("imagenerror", imagenerror);
			mav.addObject("listAllCategorias", listAllCategorias);
			mav.addObject("listAllPlataformas", listAllPlataformas);
			mav.setViewName(Vistas.ADDJUEGO);
		} else {
			String nombreImagen = juegoService.saveFile(file, extension);
			juegoModel.setCaratula(nombreImagen);
			juegoService.addJuego(juegoModel);
			Logs.LOG.info("Juego se creo correctamente");
			Logs.LOG.info(juegoModel.toString());
			mav.setViewName("redirect:/juegosventa");
		}
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/removejuego")
	public ModelAndView removeJuego(@RequestParam(name = "id", required = true) int id) {
		Logs.LOG.info("Llamada al metodo removeJuego() de la clase JuegoController, retorna al metodo showJuegosVentas()");
		juegoService.removeJuego(id);
		return showJuegosVentas();
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/ventasuccess")
	public ModelAndView ventaSuccess(@RequestParam(name = "id", required = true) int idJuego) {
		ModelAndView mav = new ModelAndView();
		VentaModel ventaModel = ventaService.addVenta(idJuego);
		Logs.LOG.info("Venta se creo correctamente");
		Logs.LOG.info(ventaModel.toString());
		
		mav.setViewName("redirect:/juegosventa");

		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/alquilersuccess")
	public ModelAndView alquilerSuccess(@RequestParam(name = "id", required = true) int idJuego) {
		ModelAndView mav = new ModelAndView();
		AlquilerModel alquilerModel = alquilerService.alquilarJuego(idJuego);
		Logs.LOG.info("Alquiler se creo correctamente");
		Logs.LOG.info(alquilerModel.toString());
		
		mav.setViewName("redirect:/juegosalq");

		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/alquilerespendientes")
	public ModelAndView alquileresPendientes() {
		Logs.LOG.info("Llamada al metodo alquileresPendientes() de la clase JuegoController, retorna a la vista "+Vistas.ALQPEND+" con un listado de juegos");
		ModelAndView mav = new ModelAndView(Vistas.ALQPEND);
		
		mav.addObject("alquileres", alquilerService.listAllAlquileresPendientes());
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/alquilerespendientesadmin")
	public ModelAndView alquileresPendientesAdmin() {
		Logs.LOG.info("Llamada al metodo alquileresPendientesAdmin() de la clase JuegoController, retorna a la vista "+Vistas.ALQPENDAD+" con un listado de juegos");
		ModelAndView mav = new ModelAndView(Vistas.ALQPENDAD);
		
		mav.addObject("alquileres", alquilerService.listAllAlquileresPendientesAdmin());
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/alquileresrealizados")
	public ModelAndView alquileresRealizados() {
		Logs.LOG.info("Llamada al metodo alquileresRealizados() de la clase JuegoController, retorna a la vista "+Vistas.ALQREA+" con un listado de juegos");
		ModelAndView mav = new ModelAndView(Vistas.ALQREA);
		
		mav.addObject("alquileres", alquilerService.listAllAlquileresRealizados());
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/alquileresrealizadosadmin")
	public ModelAndView alquileresRealizadosAdmin() {
		Logs.LOG.info("Llamada al metodo alquileresRealizadosAdmin() de la clase JuegoController, retorna a la vista "+Vistas.ALQREAAD+" con un listado de juegos");
		ModelAndView mav = new ModelAndView(Vistas.ALQREAAD);
		
		mav.addObject("alquileres", alquilerService.listAllAlquileresRealizadosAdmin());
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/comprasrealizadas")
	public ModelAndView compreasRealizadas() {
		Logs.LOG.info("Llamada al metodo compreasRealizadas() de la clase JuegoController, retorna a la vista "+Vistas.VENTARE+" con un listado de juegos");
		ModelAndView mav = new ModelAndView(Vistas.VENTARE);
		
		mav.addObject("ventas", ventaService.listAllVentas());
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/devolveralquiler")
	public ModelAndView devolverAlquiler(@RequestParam(name = "id", required = true) int idAquiler) {
		ModelAndView mav = new ModelAndView();
		AlquilerModel alquilerModel = alquilerService.devolverJuego(idAquiler);
		Logs.LOG.info("Alquiler devuleto correctamente");
		Logs.LOG.info(alquilerModel.toString());
		
		mav.setViewName("redirect:/alquilerespendientesadmin");

		return mav;
	}
}