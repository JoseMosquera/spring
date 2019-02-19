package com.game.JoseMosquera.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.converter.CategoriaConverter;
import com.game.JoseMosquera.converter.JuegoConverter;
import com.game.JoseMosquera.entity.Juego;
import com.game.JoseMosquera.model.JuegoModel;
import com.game.JoseMosquera.repository.CategoriaRepository;
import com.game.JoseMosquera.repository.JuegoRepository;
import com.game.JoseMosquera.repository.QueryDSLJuego;
import com.game.JoseMosquera.service.JuegoService;

@Service("juegoServiceImpl")
public class JuegoServiceImpl implements JuegoService{

	@Autowired
	@Qualifier("juegoRepository")
	private JuegoRepository juegoRepository;
	
	@Autowired
	@Qualifier("queryDSLJuego")
	private QueryDSLJuego queryDSLJuego;
	
	@Autowired
	@Qualifier("juegoConverter")
	private JuegoConverter juegoConverter;
	
	@Autowired
	@Qualifier("categoriaRepository")
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	@Qualifier("categoriaConverter")
	private CategoriaConverter categoriaConverter;
	
	private String upload_folder = ".//src//main//resources//static//imgs//";

    public String saveFile(MultipartFile file, String extension) throws IOException {
        if(!file.isEmpty()){
            byte[] bytes = file.getBytes();
            Date date = new Date();
            String nombre = Long.toString(date.getTime());
            nombre += "."+extension;
            Path path = Paths.get(upload_folder + nombre);
            Files.write(path,bytes);
            return nombre;
        }
        return null;
    }
	
	@Override
	public List<JuegoModel> listJuegosVenta() {
		Logs.LOG.info("Llamada al metodo listJuegosVentas() de la clase JuegoServiceImpl");
		List<Juego> juegosVentas = queryDSLJuego.listJuegosVenta();
		Logs.LOG.info("El metodo listJuegosVentas() recibe una lista de juegosVentas y la pasa a una lista de JuegosVentasModel");
		List<JuegoModel> juegosVentasModel = new ArrayList<JuegoModel>();
		
		for(Juego juego : juegosVentas) {
			juegosVentasModel.add(juegoConverter.entity2model(juego));
		}
		
		return juegosVentasModel;
	}

	@Override
	public List<JuegoModel> listJuegoAlquiler() {
		Logs.LOG.info("Llamada al metodo listJuegosAlq() de la clase JuegoServiceImpl");
		List<Juego> juegosVentas = queryDSLJuego.listJuegosAlq();
		Logs.LOG.info("El metodo listJuegosAlq() recibe una lista de juegosAlq y la pasa a una lista de JuegosAlqModel");
		List<JuegoModel> juegosVentasModel = new ArrayList<JuegoModel>();
		
		for(Juego juego : juegosVentas) {
			juegosVentasModel.add(juegoConverter.entity2model(juego));
		}
		
		return juegosVentasModel;
	}

	@Override
	public JuegoModel addJuego(JuegoModel juegoModel) {
		Logs.LOG.info("Llamada al metodo addJuego() de la clase JuegoServiceImpl, recibe un juegoModel: '"+juegoModel.toString()+"'");
		Juego juego = juegoRepository.save(juegoConverter.model2entity(juegoModel));
		return juegoConverter.entity2model(juego);
	}

	@Override
	public void removeJuego(int id) {
		Logs.LOG.info("Llamada al metodo  removeJuego() de la clase JuegoServiceImpl, recibe un id: '"+id+"' y con el obtiene un juego que elimina");
		juegoRepository.deleteById(id);
		
	}

	@Override
	public JuegoModel juego(int id) {
		Juego juego = juegoRepository.getOne(id);
		Logs.LOG.info("Llamada al metodo juego() de la clase JuegoServiceImpl, recibe un id: '"+id+"' y con el obtiene un juego: '"+juego.toString()+"'");
		return juegoConverter.entity2model(juego);
	}
}
