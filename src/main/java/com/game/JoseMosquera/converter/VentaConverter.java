package com.game.JoseMosquera.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.game.JoseMosquera.entity.Juego;
import com.game.JoseMosquera.entity.User;
import com.game.JoseMosquera.entity.Venta;
import com.game.JoseMosquera.model.JuegoModel;
import com.game.JoseMosquera.model.UserModel;
import com.game.JoseMosquera.model.VentaModel;

@Component("ventaConverter")
public class VentaConverter {

	@Autowired
	@Qualifier("juegoConverter")
	private JuegoConverter juegoConverter;
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;

	public VentaModel entity2model(Venta venta) {
		VentaModel ventaModel = new VentaModel();
		Juego juego = venta.getJuego();
		ventaModel.setVenta_id(venta.getVenta_id());
		ventaModel.setJuegoModel(juegoConverter.entity2model(juego));
		User user = venta.getUser();
		ventaModel.setUserModel(userConverter.entity2model(user));
		ventaModel.setFecha(venta.getFecha());
		return ventaModel;
	}
	
	public Venta model2entity(VentaModel ventaModel) {
		Venta venta = new Venta();
		JuegoModel juegoModel = ventaModel.getJuegoModel();
		venta.setVenta_id(ventaModel.getVenta_id());
		venta.setJuego(juegoConverter.model2entity(juegoModel));
		UserModel userModel = ventaModel.getUserModel();
		venta.setUser(userConverter.model2entity(userModel));
		venta.setFecha(ventaModel.getFecha());
		return venta;
	}
}
