package com.game.JoseMosquera.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.game.JoseMosquera.entity.Participacion;
import com.game.JoseMosquera.entity.QParticipacion;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("queryDSLParticipacion")
public class QueryDSLParticipacion {
	
	private QParticipacion qParticipacion = QParticipacion.participacion;
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Participacion> listCompeticionesFuturas(){
		
		JPAQuery<Participacion> query = new JPAQuery<Participacion>(em);
		
		List<Participacion> competicionesfuturas = query.select(qParticipacion).from(qParticipacion).where(qParticipacion.posicion.isNull()).fetch();
		
		return competicionesfuturas;
	}
	
	public List<Participacion> listCompeticionesPasadas(){
	
		
		JPAQuery<Participacion> query = new JPAQuery<Participacion>(em);
		
		List<Participacion> competicionespasadas = query.select(qParticipacion).from(qParticipacion).where(qParticipacion.posicion.isNotNull()).fetch();
		
		return competicionespasadas;
	}
}
