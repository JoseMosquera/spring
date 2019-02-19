package com.game.JoseMosquera.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.entity.Plataforma;
import com.game.JoseMosquera.model.PlataformaModel;

@Component("plataformaConverter")
public class PlataformaConverter {

	private DozerBeanMapper dozer = new DozerBeanMapper();
	
	public PlataformaModel entity2model(Plataforma plataforma) {
		Logs.LOG.info("Llamada al metodo entity2model de la clase PlataformaConverter, recibe una entidad: '"+plataforma.toString()+"'");
		PlataformaModel plataformaModel = dozer.map(plataforma, PlataformaModel.class);
		Logs.LOG.info("Retorno del metodo entity2model de la clase PlataformaConverter, devuelve un modelo: '"+plataformaModel.toString()+"'");
		return plataformaModel;
	}
	
	public Plataforma model2entity(PlataformaModel plataformaModel) {
		Logs.LOG.info("Llamada al metodo model2entity de la clase PlataformaConverter, recibe un modelo: '"+plataformaModel.toString()+"'");
		Plataforma plataforma = dozer.map(plataformaModel, Plataforma.class);
		Logs.LOG.info("Retorno del metodo model2entity de la clase PlataformaConverter, devuelve una entidad: '"+plataforma.toString()+"'");
		return plataforma;
	}
}