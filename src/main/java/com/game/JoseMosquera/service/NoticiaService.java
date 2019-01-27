package com.game.JoseMosquera.service;

import java.util.List;

import com.game.JoseMosquera.model.NoticiaModel;

public interface NoticiaService {

	public abstract List<NoticiaModel> listAllNoticias();
	public abstract List<NoticiaModel> listNoticiasDestacadas();
	public abstract NoticiaModel addNoticia(NoticiaModel noticiaModel);
	public abstract void removeNoticia(int id);
	public abstract NoticiaModel noticia(int id);
}
