package com.game.JoseMosquera.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.entity.Categoria;
import com.game.JoseMosquera.entity.Juego;
import com.game.JoseMosquera.entity.Plataforma;
import com.game.JoseMosquera.model.JuegoModel;
import com.game.JoseMosquera.repository.QueryDSLCategoria;
import com.game.JoseMosquera.repository.QueryDSLPlataforma;

@Component("juegoConverter")
public class JuegoConverter {
	
	@Autowired
	@Qualifier("queryDSLCategoria")
	private QueryDSLCategoria queryDSLCategoria;
	
	@Autowired
	@Qualifier("queryDSLPlataforma")
	private QueryDSLPlataforma queryDSLPlataforma;
	
	public JuegoModel entity2model(Juego juego) {
		Logs.LOG.info("Llamada al metodo entity2model de la clase JuegoConverter, recibe una entidad: '"+juego.toString()+"'");
		JuegoModel juegoModel = new JuegoModel();
		juegoModel.setJuego_id(juego.getJuego_id());
		juegoModel.setTitulo(juego.getTitulo());
		juegoModel.setDescripcion(juego.getDescripcion());
		juegoModel.setLanzamiento(juego.getLanzamiento());
		juegoModel.setCaratula(juego.getCaratula());
		juegoModel.setPrecio(juego.getPrecio());
		juegoModel.setStock(juego.getStock());
		juegoModel.setTipo(juego.getTipo());
		
		List<Categoria> categorias = juego.getCategorias();
		String [] categoriasArray = new String[categorias.size()];
		
		for(int i=0; i<categorias.size(); i++) {
			Categoria categoria = categorias.get(i);
			categoriasArray[i] = categoria.getNombre();
		}
		juegoModel.setCategoriasArray(categoriasArray);
		
		List<Plataforma> plataformas = juego.getPlataformas();
		String [] plataformasArray = new String[plataformas.size()];
		
		for(int i=0; i<plataformas.size(); i++) {
			Plataforma plataforma = plataformas.get(i);
			plataformasArray[i] = plataforma.getNombre();
		}
		juegoModel.setPlataformasArray(plataformasArray);
		Logs.LOG.info("Retorno del metodo entity2model de la clase JuegoConverter, devuelve un modelo: '"+juegoModel.toString()+"'");
		return juegoModel;
	}
	
	public Juego model2entity(JuegoModel juegoModel) {
		Logs.LOG.info("Llamada al metodo model2entity de la clase JuegoConverter, recibe un modelo: '"+juegoModel.toString()+"'");
		Juego juego = new Juego();
		juego.setJuego_id(juegoModel.getJuego_id());
		juego.setTitulo(juegoModel.getTitulo());
		juego.setDescripcion(juegoModel.getDescripcion());
		juego.setLanzamiento(juegoModel.getLanzamiento());
		juego.setCaratula(juegoModel.getCaratula());
		juego.setPrecio(juegoModel.getPrecio());
		juego.setStock(juegoModel.getStock());
		juego.setTipo(juegoModel.getTipo());
		
		String [] categoriasArray = juegoModel.getCategoriasArray();
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		for (int i = 0; i < categoriasArray.length; i++) {
			String name = categoriasArray[i];
			categorias.add(queryDSLCategoria.findCategoriaByName(name));
		}
		juego.setCategorias(categorias);
		
		String [] plataformasArray = juegoModel.getPlataformasArray();
		List<Plataforma> plataformas = new ArrayList<Plataforma>();
		
		for (int i = 0; i < plataformasArray.length; i++) {
			String name = plataformasArray[i];
			plataformas.add(queryDSLPlataforma.findPlataformaByName(name));
		}
		juego.setPlataformas(plataformas);
		Logs.LOG.info("Retorno del metodo model2entity de la clase JuegoConverter, devuelve una entidad: '"+juego.toString()+"'");
		return juego;
	}
}
