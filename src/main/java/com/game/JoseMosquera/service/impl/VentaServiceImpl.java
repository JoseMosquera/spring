package com.game.JoseMosquera.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.converter.VentaConverter;
import com.game.JoseMosquera.entity.Juego;
import com.game.JoseMosquera.entity.User;
import com.game.JoseMosquera.entity.Venta;
import com.game.JoseMosquera.model.VentaModel;
import com.game.JoseMosquera.repository.JuegoRepository;
import com.game.JoseMosquera.repository.UserRepository;
import com.game.JoseMosquera.repository.VentaRepository;
import com.game.JoseMosquera.service.VentaService;

@Service("ventaService")
public class VentaServiceImpl implements VentaService{
	
	@Autowired
	@Qualifier("ventaRepository")
	private VentaRepository ventaRepository;
	
	@Autowired
	@Qualifier("ventaConverter")
	private VentaConverter ventaConverter;
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("juegoRepository")
	private JuegoRepository juegoRepository;

	@Override
	public List<VentaModel> listAllVentas() {
		Logs.LOG.info("Llamada al metodo listAllVentas() de la clase VentaServiceImpl");
		List<Venta> ventas = ventaRepository.findAll();
		Logs.LOG.info("El metodo listAllVentas() recibe una lista de ventas y la pasa a una lista de ventasModel");
		List<VentaModel> ventasModel = new ArrayList<VentaModel>();
		
		for(Venta venta : ventas) {
			ventasModel.add(ventaConverter.entity2model(venta));
		}
		return ventasModel;
	}

	@Override
	public VentaModel addVenta(int idJuego) {
		Venta venta = new Venta();
		Logs.LOG.info("Llamada al metodo addVenta() de la clase VentaServiceImpl, recibe un id de un juego: '"+idJuego+"'");
		Juego juego = juegoRepository.getOne(idJuego);
		venta.setJuego(juego);
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		Logs.LOG.info("Obtiene el nombre del usuario registrado: '"+userName+"'");
		User user = userRepository.findByUsername(userName);
		Logs.LOG.info("Obtiene un user a traves del nombre de usuario obtenido antes: '"+user.toString()+"'");
		venta.setUser(user);
		int stock = (juego.getStock() - 1);
		juego.setStock(stock);
		Date fecha = new Date();
		venta.setFecha(fecha);
		Logs.LOG.info("Se genera una venta: '"+venta.toString()+"'");
		ventaRepository.save(venta);
		return ventaConverter.entity2model(venta);
	}

	@Override
	public void removeVenta(int id) {
		Logs.LOG.info("Llamada al metodo  removeVenta() de la clase VentaServiceImpl, recibe un id: '"+id+"' y con el obtiene una venta que elimina");
		ventaRepository.deleteById(id);
		
	}

}
