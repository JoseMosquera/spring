package com.game.JoseMosquera.service;

import java.util.List;

import com.game.JoseMosquera.model.CategoriaModel;

public interface CategoriaService {

	public abstract List<CategoriaModel> listAllCategorias();
	public abstract CategoriaModel addCategoria(CategoriaModel categoriaModel);
	public abstract void removeCategoria(int id);
	public abstract CategoriaModel categoria(int id);
}
