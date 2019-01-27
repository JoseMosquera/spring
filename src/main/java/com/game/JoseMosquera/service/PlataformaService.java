package com.game.JoseMosquera.service;

import java.util.List;

import com.game.JoseMosquera.model.PlataformaModel;

public interface PlataformaService {

	public abstract List<PlataformaModel> listAllPlataformas();
	public abstract PlataformaModel addPlataforma(PlataformaModel plataformaModel);
	public abstract void removePlataforma(int id);
	public abstract PlataformaModel plataforma(int id);
}
