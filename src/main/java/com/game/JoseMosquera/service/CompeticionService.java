package com.game.JoseMosquera.service;

import java.util.List;

import com.game.JoseMosquera.model.CompeticionModel;

public interface CompeticionService {

	public abstract List<CompeticionModel> listCompeticiones();
	public abstract CompeticionModel addCompeticion(CompeticionModel competicionModel);
	public abstract void removeCompeticion(int id);
	public abstract CompeticionModel competicion(int id);
}
