package com.game.JoseMosquera.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.game.JoseMosquera.entity.Alquiler;
import com.game.JoseMosquera.entity.Juego;
import com.game.JoseMosquera.entity.User;
import com.game.JoseMosquera.model.AlquilerModel;
import com.game.JoseMosquera.model.JuegoModel;
import com.game.JoseMosquera.model.UserModel;

@Component("alquilerConverter")
public class AlquilerConverter {
	
	@Autowired
	@Qualifier("juegoConverter")
	private JuegoConverter juegoConverter;
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;

	public AlquilerModel entity2model(Alquiler alquiler) {
		AlquilerModel alquilerModel = new AlquilerModel();
		Juego juego = alquiler.getJuego();
		alquilerModel.setAlquiler_id(alquiler.getAlquiler_id());
		alquilerModel.setJuegoModel(juegoConverter.entity2model(juego));
		User user = alquiler.getUser();
		alquilerModel.setUserModel(userConverter.entity2model(user));
		alquilerModel.setFecha(alquiler.getFecha());
		alquilerModel.setDevuelto(alquiler.isDevuelto());
		return alquilerModel;
	}
	
	public Alquiler model2entity(AlquilerModel alquilerModel) {
		Alquiler alquiler = new Alquiler();
		JuegoModel juegoModel = alquilerModel.getJuegoModel();
		alquiler.setAlquiler_id(alquilerModel.getAlquiler_id());
		alquiler.setJuego(juegoConverter.model2entity(juegoModel));
		UserModel userModel = alquilerModel.getUserModel();
		alquiler.setUser(userConverter.model2entity(userModel));
		alquiler.setFecha(alquilerModel.getFecha());
		alquiler.setDevuelto(alquilerModel.isDevuelto());
		return alquiler;
	}
}
