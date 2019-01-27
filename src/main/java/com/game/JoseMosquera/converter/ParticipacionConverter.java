package com.game.JoseMosquera.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.entity.Participacion;
import com.game.JoseMosquera.model.ParticipacionModel;

@Component("participacionConverter")
public class ParticipacionConverter {

private DozerBeanMapper dozer = new DozerBeanMapper();
	
	public ParticipacionModel entity2model(Participacion participacion) {
		Logs.LOG.info("Llamada al metodo entity2model() de la clase ParticipacionConverter, recibe una entidad: '"+participacion.toString()+"'");
		ParticipacionModel participacionModel = dozer.map(participacion, ParticipacionModel.class);
		Logs.LOG.info("Retorno del metodo entity2model() de la clase ParticipacionConverter, devuelve un modelo: '"+participacionModel.toString()+"'");
		return participacionModel;
	}
	
	public Participacion model2entity(ParticipacionModel participacionModel) {
		Logs.LOG.info("Llamada al metodo model2entity() de la clase ParticipacionConverter, recibe un modelo: '"+participacionModel.toString()+"'");
		Participacion participacion = dozer.map(participacionModel, Participacion.class);
		Logs.LOG.info("Retorno del metodo model2entity() de la clase ParticipacionConverter, devuelve una entidad: '"+participacion.toString()+"'");
		return participacion;
	}
}
