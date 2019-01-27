package com.game.JoseMosquera.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.entity.Noticia;
import com.game.JoseMosquera.model.NoticiaModel;

@Component("noticiaConverter")
public class NoticiaConverter {

	private DozerBeanMapper dozer = new DozerBeanMapper();
	
	public NoticiaModel entity2model(Noticia noticia) {
		Logs.LOG.info("Llamada al metodo entity2model de la clase NoticiaConverter, recibe una entidad: '"+noticia.toString()+"'");
		NoticiaModel noticiaModel = dozer.map(noticia, NoticiaModel.class);
		Logs.LOG.info("Retorno del metodo entity2model de la clase NoticiaConverter, devuelve un modelo: '"+noticiaModel.toString()+"'");
		return noticiaModel;
	}
	
	public Noticia model2entity(NoticiaModel noticiaModel) {
		Logs.LOG.info("Llamada al metodo model2entity de la clase NoticiaConverter, recibe un modelo: '"+noticiaModel.toString()+"'");
		Noticia noticia = dozer.map(noticiaModel, Noticia.class);
		Logs.LOG.info("Retorno del metodo model2entity de la clase NoticiaConverter, devuelve una entidad: '"+noticia.toString()+"'");
		return noticia;
	}
}
