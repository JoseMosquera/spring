package com.game.JoseMosquera.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.entity.Competicion;
import com.game.JoseMosquera.model.CompeticionModel;

@Component("competicionConverter")
public class CompeticionConverter {

	private DozerBeanMapper dozer = new DozerBeanMapper();
	
	public CompeticionModel entity2model(Competicion competicion) {
		Logs.LOG.info("Llamada al metodo entity2model de la clase JuegoConverter, recibe una entidad: '"+competicion.toString()+"'");
		CompeticionModel competicionModel = dozer.map(competicion, CompeticionModel.class);
		Logs.LOG.info("Retorno del metodo entity2model de la clase JuegoConverter, devuelve un modelo: '"+competicionModel.toString()+"'");
		return competicionModel;
	}
	
	public Competicion model2entity(CompeticionModel competicionModel) {
		Logs.LOG.info("Llamada al metodo model2entity de la clase JuegoConverter, recibe un modelo: '"+competicionModel.toString()+"'");
		Competicion competicion = dozer.map(competicionModel, Competicion.class);
		Logs.LOG.info("Retorno del metodo model2entity de la clase JuegoConverter, devuelve una entidad: '"+competicion.toString()+"'");
		return competicion;
	}
}
