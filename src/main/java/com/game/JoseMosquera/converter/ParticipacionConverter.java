package com.game.JoseMosquera.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.game.JoseMosquera.entity.Competicion;
import com.game.JoseMosquera.entity.Participacion;
import com.game.JoseMosquera.entity.User;
import com.game.JoseMosquera.model.CompeticionModel;
import com.game.JoseMosquera.model.ParticipacionModel;
import com.game.JoseMosquera.model.UserModel;

@Component("participacionConverter")
public class ParticipacionConverter {

	@Autowired
	@Qualifier("competicionConverter")
	private CompeticionConverter competicionConverter;
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;

	public ParticipacionModel entity2model(Participacion participacion) {
		ParticipacionModel participacionModel = new ParticipacionModel();
		Competicion competicion = participacion.getCompeticion();
		participacionModel.setCompeticionModel(competicionConverter.entity2model(competicion));
		User user = participacion.getUser();
		participacionModel.setUserModel(userConverter.entity2model(user));
		participacionModel.setParticipacion_id(participacion.getParticipacion_id());
		participacionModel.setPosicion(participacion.getPosicion());
		return participacionModel;
	}
	
	public Participacion model2entity(ParticipacionModel participacionModel) {
		Participacion participacion = new Participacion();
		CompeticionModel competicionModel = participacionModel.getCompeticionModel();
		participacion.setCompeticion(competicionConverter.model2entity(competicionModel));
		UserModel userModel = participacionModel.getUserModel();
		participacion.setUser(userConverter.model2entity(userModel));
		participacion.setParticipacion_id(participacionModel.getParticipacion_id());
		participacion.setPosicion(participacionModel.getPosicion());
		return participacion;
	}
}
