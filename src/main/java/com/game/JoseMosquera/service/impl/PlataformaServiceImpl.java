package com.game.JoseMosquera.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.converter.PlataformaConverter;
import com.game.JoseMosquera.entity.Plataforma;
import com.game.JoseMosquera.model.PlataformaModel;
import com.game.JoseMosquera.repository.PlataformaRepository;
import com.game.JoseMosquera.service.PlataformaService;

@Repository("plataformaService")
public class PlataformaServiceImpl implements PlataformaService{
	
	@Autowired
	@Qualifier("plataformaRepository")
	private PlataformaRepository plataformaRepository;
	
	@Autowired
	@Qualifier("plataformaConverter")
	private PlataformaConverter plataformaConverter;

	@Override
	public List<PlataformaModel> listAllPlataformas() {
		Logs.LOG.info("Llamada al metodo listAllPlataformas() de la clase PlataformaServiceImpl");
		List<Plataforma> plataformas = plataformaRepository.findAll();
		Logs.LOG.info("El metodo listAllPlataformas() recibe una lista de Plataformas y la pasa a una lista de PlataformasModel");
		List<PlataformaModel> plataformasModel = new ArrayList<PlataformaModel>();
		
		for(Plataforma plataforma : plataformas) {
			plataformasModel.add(plataformaConverter.entity2model(plataforma));
		}
		
		return plataformasModel;
	}

	@Override
	public PlataformaModel addPlataforma(PlataformaModel plataformaModel) {
		Logs.LOG.info("Llamada al metodo addPlataforma() de la clase PlataformaServiceImpl, recibe una plataformaModel: '"+plataformaModel.toString()+"'");
		Plataforma plataforma = plataformaRepository.save(plataformaConverter.model2entity(plataformaModel));
		return plataformaConverter.entity2model(plataforma);
	}

	@Override
	public void removePlataforma(int id) {
		Logs.LOG.info("Llamada al metodo  removePlataforma() de la clase PlataformaServiceImpl, recibe un id: '"+id+"' y con el obtiene una plataforma que elimina");
		plataformaRepository.deleteById(id);
		
	}
	
	@Override
	public PlataformaModel plataforma(int id) {
		Plataforma plataforma = plataformaRepository.getOne(id);
		Logs.LOG.info("Llamada al metodo plataforma() de la clase PlataformaServiceImpl, recibe un id: '"+id+"' y con el obtiene un juego: '"+plataforma.toString()+"'");
		return plataformaConverter.entity2model(plataforma);
	}
}
