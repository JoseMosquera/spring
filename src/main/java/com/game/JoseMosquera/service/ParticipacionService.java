package com.game.JoseMosquera.service;

import java.util.List;

import com.game.JoseMosquera.model.ParticipacionModel;

public interface ParticipacionService {

	public abstract List<ParticipacionModel> listAllParticipaciones();
	public abstract ParticipacionModel addParticipacion(int idCompeticion);
	public abstract void removeParticipacion(int id);
}
