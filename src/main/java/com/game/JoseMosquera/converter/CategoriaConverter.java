package com.game.JoseMosquera.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.entity.Categoria;
import com.game.JoseMosquera.model.CategoriaModel;


@Component("categoriaConverter")
public class CategoriaConverter {

	private DozerBeanMapper dozer = new DozerBeanMapper();
	
	public CategoriaModel entity2model(Categoria categoria) {
		Logs.LOG.info("Llamada al metodo entity2model de la clase CategoriaConverter, recibe una entidad: '"+categoria.toString()+"'");
		CategoriaModel categoriaModel = dozer.map(categoria, CategoriaModel.class);
		Logs.LOG.info("Retorno del metodo entity2model de la clase CategoriaConverter, devuelve un modelo: '"+categoriaModel.toString()+"'");
		return categoriaModel;
	}
	
	public Categoria model2entity(CategoriaModel categoriaModel) {
		Logs.LOG.info("Llamada al metodo model2entity de la clase CategoriaConverter, recibe un modelo: '"+categoriaModel.toString()+"'");
		Categoria categoria = dozer.map(categoriaModel, Categoria.class);
		Logs.LOG.info("Retorno del metodo model2entity de la clase CategoriaConverter, devuelve una entidad: '"+categoria.toString()+"'");
		return categoria;
	}
}
