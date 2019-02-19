package com.game.JoseMosquera.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.converter.AlquilerConverter;
import com.game.JoseMosquera.entity.Alquiler;
import com.game.JoseMosquera.entity.Juego;
import com.game.JoseMosquera.entity.User;
import com.game.JoseMosquera.model.AlquilerModel;
import com.game.JoseMosquera.repository.AlquilerRepository;
import com.game.JoseMosquera.repository.JuegoRepository;
import com.game.JoseMosquera.repository.QueryDSLAlqiuler;
import com.game.JoseMosquera.repository.UserRepository;
import com.game.JoseMosquera.service.AlquilerService;

@Service("alquilerService")
public class AlquilerServiceImpl implements AlquilerService{
	
	@Autowired
	@Qualifier("alquilerRepository")
	private AlquilerRepository alquilerRepository;
	
	@Autowired
	@Qualifier("alquilerConverter")
	private AlquilerConverter alquilerConverter;
	
	@Autowired
	@Qualifier("queryDSLAlquiler")
	private QueryDSLAlqiuler queryDSLAlquiler;
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("juegoRepository")
	private JuegoRepository juegoRepository;

	@Override
	public List<AlquilerModel> listAllAlquileresRealizados() {
		Logs.LOG.info("Llamada al metodo listAllVentas() de la clase VentaServiceImpl");
		List<Alquiler> alquileres = queryDSLAlquiler.listAlquilerRealizados();
		Logs.LOG.info("El metodo listAllVentas() recibe una lista de ventas y la pasa a una lista de ventasModel");
		List<AlquilerModel> alquileresModel = new ArrayList<AlquilerModel>();
		
		if(SecurityContextHolder.getContext().getAuthentication().getName() != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(userName);
		
			for(Alquiler alquiler : alquileres) {
				if(alquiler.getUser() == user) {
					alquileresModel.add(alquilerConverter.entity2model(alquiler));
				}
			}
		}
		return alquileresModel;
	}
	
	@Override
	public List<AlquilerModel> listAllAlquileresPendientes() {
		Logs.LOG.info("Llamada al metodo listAllVentas() de la clase VentaServiceImpl");
		List<Alquiler> alquileres = queryDSLAlquiler.listAlquilerPendientes();
		Logs.LOG.info("El metodo listAllVentas() recibe una lista de ventas y la pasa a una lista de ventasModel");
		List<AlquilerModel> alquileresModel = new ArrayList<AlquilerModel>();

		if(SecurityContextHolder.getContext().getAuthentication().getName() != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(userName);
		
			for(Alquiler alquiler : alquileres) {
				if(alquiler.getUser() == user) {
					alquileresModel.add(alquilerConverter.entity2model(alquiler));
				}
			}
		}
		return alquileresModel;
	}

	@Override
	public AlquilerModel alquilarJuego(int idJuego) {
		Juego juego = juegoRepository.getOne(idJuego);
		if(juego.getStock()>0) {
			Alquiler alquiler= new Alquiler();
			Logs.LOG.info("Llamada al metodo alquilarJuego() de la clase AlquilerServiceImpl, recibe un id de un juego: '"+idJuego+"'");
			alquiler.setJuego(juego);
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			Logs.LOG.info("Obtiene el nombre del usuario registrado: '"+userName+"'");
			User user = userRepository.findByUsername(userName);
			Logs.LOG.info("Obtiene un user a traves del nombre de usuario obtenido antes: '"+user.toString()+"'");
			alquiler.setUser(user);
			int stock = (juego.getStock() - 1);
			juego.setStock(stock);
			Date fecha = new Date();
			alquiler.setFecha(fecha);
			alquiler.setDevuelto(false);
			Logs.LOG.info("Se genera una venta: '"+alquiler.toString()+"'");
			alquilerRepository.save(alquiler);
			return alquilerConverter.entity2model(alquiler);
		}
		return null;
	}

	@Override
	public AlquilerModel devolverJuego(int idAquiler) {
		Logs.LOG.info("Llamada al metodo devolverJuego() de la clase AlquilerServiceImpl, recibe un id de un alquiler: '"+idAquiler+"'");
		Alquiler alquiler = alquilerRepository.getOne(idAquiler);
		Logs.LOG.info("Busca un alquiler con el id pasado anteriormente: '"+alquiler.toString()+"'");
		alquiler.setDevuelto(true);
		alquilerRepository.save(alquiler);
		Logs.LOG.info("Se asigna el valor devuelto a true: '"+alquiler.toString()+"'");
		return alquilerConverter.entity2model(alquiler);
	}

	@Override
	public void removeAlquiler(int id) {
		Logs.LOG.info("Llamada al metodo  removeAlquiler() de la clase AlquilerServiceImpl, recibe un id: '"+id+"' y con el obtiene un alquiler que elimina");
		alquilerRepository.deleteById(id);
	}

	@Override
	public List<AlquilerModel> listAllAlquileresRealizadosAdmin() {
		Logs.LOG.info("Llamada al metodo listAllVentas() de la clase VentaServiceImpl");
		List<Alquiler> alquileres = queryDSLAlquiler.listAlquilerRealizados();
		Logs.LOG.info("El metodo listAllVentas() recibe una lista de ventas y la pasa a una lista de ventasModel");
		List<AlquilerModel> alquileresModel = new ArrayList<AlquilerModel>();
		
		for(Alquiler alquiler : alquileres) {
				alquileresModel.add(alquilerConverter.entity2model(alquiler));
		}
		
		return alquileresModel;
	}

	@Override
	public List<AlquilerModel> listAllAlquileresPendientesAdmin() {
		List<Alquiler> alquileres = queryDSLAlquiler.listAlquilerPendientes();
		Logs.LOG.info("El metodo listAllVentas() recibe una lista de ventas y la pasa a una lista de ventasModel");
		List<AlquilerModel> alquileresModel = new ArrayList<AlquilerModel>();
		
		for(Alquiler alquiler : alquileres) {
				alquileresModel.add(alquilerConverter.entity2model(alquiler));
		}
		
		return alquileresModel;
	}

}
