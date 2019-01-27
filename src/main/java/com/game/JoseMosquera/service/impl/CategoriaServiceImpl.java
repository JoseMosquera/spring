package com.game.JoseMosquera.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.converter.CategoriaConverter;
import com.game.JoseMosquera.entity.Categoria;
import com.game.JoseMosquera.model.CategoriaModel;
import com.game.JoseMosquera.repository.CategoriaRepository;
import com.game.JoseMosquera.service.CategoriaService;

@Service("categoriaService")
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	@Qualifier("categoriaRepository")
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	@Qualifier("categoriaConverter")
	private CategoriaConverter categoriaConverter;
	
	@Override
	public List<CategoriaModel> listAllCategorias() {
		Logs.LOG.info("Llamada al metodo listAllCategorias() de la clase CategoriaServiceImpl");
		List<Categoria> categorias = categoriaRepository.findAll();
		Logs.LOG.info("El metodo listAllCategorias() recibe una lista de Categorias y la pasa a una lista de CategoriasModel");
		List<CategoriaModel> categoriasModel = new ArrayList<CategoriaModel>();
		
		for(Categoria categoria : categorias) {
			categoriasModel.add(categoriaConverter.entity2model(categoria));
		}
		Logs.LOG.info("Metodo listAllCategorias() retorna una lista de categorias "+categorias);
		return categoriasModel;
	}

	@Override
	public CategoriaModel addCategoria(CategoriaModel categoriaModel) {
		Logs.LOG.info("Llamada al metodo addCategoria() de la clase CategoriaServiceImpl, recibe una categoriaModel: '"+categoriaModel.toString()+"'");
		Categoria categoria = categoriaRepository.save(categoriaConverter.model2entity(categoriaModel));
		return categoriaConverter.entity2model(categoria);
	}

	@Override
	public void removeCategoria(int id) {
		Logs.LOG.info("Llamada al metodo  removeCategoria() de la clase CategoriaServiceImpl, recibe un id: '"+id+"' y con el obtiene una categoria que elimina");
		categoriaRepository.deleteById(id);
	}
	
	@Override
	public CategoriaModel categoria(int id) {
		Categoria categoria = categoriaRepository.getOne(id);
		Logs.LOG.info("Llamada al metodo competicion() de la clase CompeticionServiceImpl, recibe un id: '"+id+"' y con el obtiene un juego: '"+categoria.toString()+"'");
		return categoriaConverter.entity2model(categoria);
	}
}
