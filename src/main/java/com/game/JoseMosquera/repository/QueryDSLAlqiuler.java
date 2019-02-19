package com.game.JoseMosquera.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.game.JoseMosquera.entity.Alquiler;
import com.game.JoseMosquera.entity.QAlquiler;
import com.querydsl.jpa.impl.JPAQuery;


@Repository("queryDSLAlquiler")
public class QueryDSLAlqiuler {

	private QAlquiler qAlquiler = QAlquiler.alquiler;
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Alquiler> listAlquilerPendientes(){
		
		JPAQuery<Alquiler> query = new JPAQuery<Alquiler>(em);
		
		List<Alquiler> alquileresPendientes = query.select(qAlquiler).from(qAlquiler).where(qAlquiler.devuelto.isFalse()).fetch();
		
		return alquileresPendientes;
	}
	
	public List<Alquiler> listAlquilerRealizados(){
	
		
		JPAQuery<Alquiler> query = new JPAQuery<Alquiler>(em);
		
		List<Alquiler> alquileresRealizados = query.select(qAlquiler).from(qAlquiler).where(qAlquiler.devuelto.isTrue()).fetch();
		
		return alquileresRealizados;
	}
}
