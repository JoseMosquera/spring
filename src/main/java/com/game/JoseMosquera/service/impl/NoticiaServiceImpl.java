package com.game.JoseMosquera.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.converter.NoticiaConverter;
import com.game.JoseMosquera.entity.Noticia;
import com.game.JoseMosquera.model.NoticiaModel;
import com.game.JoseMosquera.repository.NoticiaRepository;
import com.game.JoseMosquera.repository.QueryDSLNoticia;
import com.game.JoseMosquera.service.NoticiaService;

@Service("noticiaServiceImpl")
public class NoticiaServiceImpl implements NoticiaService{
	
	@Autowired
	@Qualifier("noticiaRepository")
	private NoticiaRepository noticiaRepository;
	
	@Autowired
	@Qualifier("noticiaConverter")
	private NoticiaConverter noticiaConverter;
	
	@Autowired
	@Qualifier("queryDSLNoticia")
	private QueryDSLNoticia qNoticia;

	@Override
	public List<NoticiaModel> listAllNoticias() {
		Logs.LOG.info("Llamada al metodo listAllNoticias() de la clase NoticiaServiceImpl");
		List<Noticia> noticias = noticiaRepository.findAll();
		Logs.LOG.info("El metodo listAllNoticias() recibe una lista de noticias y la pasa a una lista de noticiasModel");
		List<NoticiaModel> noticiasModel = new ArrayList<NoticiaModel>();
		for(Noticia noticia : noticias) {
			noticiasModel.add(noticiaConverter.entity2model(noticia));
		}		
		return noticiasModel;
	}

	@Override
	public List<NoticiaModel> listNoticiasDestacadas() {
		Logs.LOG.info("Llamada al metodo listNoticiasDestacadas() de la clase NoticiaServiceImpl");
		List<Noticia> noticias = qNoticia.findNoticiasDestacadas();
		Logs.LOG.info("El metodo listNoticiasDestacadas() recibe una lista de noticiasDestacadas y la pasa a una lista de noticiasDestacadasModel");
		List<NoticiaModel> noticiasModel = new ArrayList<NoticiaModel>();
		for(Noticia noticia : noticias) {
			noticiasModel.add(noticiaConverter.entity2model(noticia));
		}		
		return noticiasModel;
	}

	@Override
	public NoticiaModel addNoticia(NoticiaModel noticiaModel) {
		Logs.LOG.info("Llamada al metodo addNoticia() de la clase NoticiaServiceImpl, recibe una noticiaModel: '"+noticiaModel.toString()+"'");
		Noticia noticia =  noticiaRepository.save(noticiaConverter.model2entity(noticiaModel));
		return noticiaConverter.entity2model(noticia);
	}

	@Override
	public NoticiaModel noticia(int id) {
		Noticia noticia = noticiaRepository.getOne(id);
		Logs.LOG.info("Llamada al metodo noticia() de la clase NoticiaServiceImpl, recibe un id: '"+id+"' y con el obtiene una noticia: '"+noticia.toString()+"'");
		return noticiaConverter.entity2model(noticia);
	}

	@Override
	public void removeNoticia(int id) {
		Logs.LOG.info("Llamada al metodo  removeNoticia() de la clase NoticiaServiceImpl, recibe un id: '"+id+"' y con el obtiene una noticia que elimina");
		noticiaRepository.delete(noticiaRepository.getOne(id));
	}
}
