package com.game.JoseMosquera.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.entity.User;
import com.game.JoseMosquera.model.UserModel;

@Component("userConverter")
public class UserConverter {

	private DozerBeanMapper dozer = new DozerBeanMapper();
	
	public UserModel entity2model(User user) {
		Logs.LOG.info("Llamada al metodo entity2model de la clase UserConverter, recibe una entidad: '"+user.toString()+"'");
		UserModel userModel = dozer.map(user, UserModel.class);
		userModel.setUser_Id(user.getUser_id());
		Logs.LOG.info("Retorno del metodo entity2model de la clase UserConverter, devuelve un modelo: '"+userModel.toString()+"'");
		return userModel;
	}
	
	public User model2entity(UserModel userModel) {
		Logs.LOG.info("Llamada al metodo model2entity de la clase UserConverter, recibe un modelo: '"+userModel.toString()+"'");
		User user = dozer.map(userModel, User.class);
		user.setUser_id(userModel.getUser_Id());
		Logs.LOG.info("Retorno del metodo model2entity de la clase UserConverter, devuelve una entidad: '"+user.toString()+"'");
		return user;
	}
}
