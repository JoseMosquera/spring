package com.game.JoseMosquera.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.converter.CompeticionConverter;
import com.game.JoseMosquera.entity.Competicion;
import com.game.JoseMosquera.model.CompeticionModel;
import com.game.JoseMosquera.repository.CompeticionRepository;
import com.game.JoseMosquera.service.CompeticionService;

@Service("competicionServiceImpl")
public class CompeticionServiceImpl implements CompeticionService{

	@Autowired
	@Qualifier("competicionRepository")
	private CompeticionRepository competiRepository;
	
	@Autowired
	@Qualifier("competicionConverter")
	private CompeticionConverter competiConverter;
	
	@Override
	public List<CompeticionModel> listCompeticiones() {
		Logs.LOG.info("Llamada al metodo listCompeticiones() de la clase CompeticionServiceImpl");
		List<Competicion> competiciones = competiRepository.findAll();
		Logs.LOG.info("El metodo listCompeticiones() recibe una lista de Competicion y la pasa a una lista de CompeticionModel");
		List<CompeticionModel> competicionesModel = new ArrayList<CompeticionModel>();
		
		for(Competicion competicion : competiciones) {
			competicionesModel.add(competiConverter.entity2model(competicion));
		}
		return competicionesModel;
	}

	@Override
	public CompeticionModel addCompeticion(CompeticionModel competicionModel) {
		Logs.LOG.info("Llamada al metodo addCompeticion() de la clase CompeticionServiceImpl, recibe una competicionModel: '"+competicionModel.toString()+"'");
		Competicion competicion = competiRepository.save(competiConverter.model2entity(competicionModel));
		return competiConverter.entity2model(competicion);
	}

	@Override
	public void removeCompeticion(int id) {
		Logs.LOG.info("Llamada al metodo  removeCompeticion() de la clase CompeticionServiceImpl, recibe un id: '"+id+"' y con el obtiene una competicion que elimina");
		competiRepository.deleteById(id);
		
	}

	@Override
	public CompeticionModel competicion(int id) {
		Competicion competicion = competiRepository.getOne(id);
		Logs.LOG.info("Llamada al metodo competicion() de la clase CompeticionServiceImpl, recibe un id: '"+id+"' y con el obtiene un juego: '"+competicion.toString()+"'");
		return competiConverter.entity2model(competicion);
	}
}
