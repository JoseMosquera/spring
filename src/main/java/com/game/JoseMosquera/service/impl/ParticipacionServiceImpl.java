package com.game.JoseMosquera.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.converter.ParticipacionConverter;
import com.game.JoseMosquera.entity.Competicion;
import com.game.JoseMosquera.entity.Participacion;
import com.game.JoseMosquera.entity.User;
import com.game.JoseMosquera.model.ParticipacionModel;
import com.game.JoseMosquera.repository.CompeticionRepository;
import com.game.JoseMosquera.repository.ParticipacionRepository;
import com.game.JoseMosquera.repository.UserRepository;
import com.game.JoseMosquera.service.ParticipacionService;

@Service("participacionService")
public class ParticipacionServiceImpl implements ParticipacionService{
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	@Autowired
	@Qualifier("competicionRepository")
	private CompeticionRepository competicionRepository;
	
	@Autowired
	@Qualifier("participacionRepository")
	private ParticipacionRepository participacionRepository;
	
	@Autowired
	@Qualifier("participacionConverter")
	private ParticipacionConverter participacionConverter;
	
	@Override
	public ParticipacionModel addParticipacion(int idCompeticion) {
		Participacion participacion = new Participacion();
		Logs.LOG.info("Llamada al metodo addPArticipacion() de la clase ParticipacionServiceImpl, recibe un id de una competicion: '"+idCompeticion+"'");
		Competicion competicion = competicionRepository.getOne(idCompeticion);
		participacion.setCompeticion(competicion);
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		Logs.LOG.info("Obtiene el nombre del usuario registrado: '"+userName+"'");
		User user = userRepository.findByUsername(userName);
		Logs.LOG.info("Obtiene un user a traves del nombre de usuario obtenido antes: '"+user.toString()+"'");
		participacion.setUser(user);
		Logs.LOG.info("Se genera una participacion: '"+participacion.toString()+"'");
		participacionRepository.save(participacion);
		return participacionConverter.entity2model(participacion);
	}

	@Override
	public List<ParticipacionModel> listAllParticipaciones() {
		Logs.LOG.info("Llamada al metodo listAllParticipaciones() de la clase ParticipacionServiceImpl");
		List<Participacion> participaciones = participacionRepository.findAll();
		Logs.LOG.info("El metodo listAllParticipaciones() recibe una lista de participaciones y la pasa a una lista de participacionesModel");
		List<ParticipacionModel> participacionesModel = new ArrayList<ParticipacionModel>();
		
		for(Participacion participacion : participaciones) {
			participacionesModel.add(participacionConverter.entity2model(participacion));
		}
		return participacionesModel;
	}

	@Override
	public void removeParticipacion(int id) {
		Logs.LOG.info("Llamada al metodo  removeParticipacion() de la clase ParticipacionServiceImpl, recibe un id: '"+id+"' y con el obtiene una participacion que elimina");
		participacionRepository.deleteById(id);
	}
}
