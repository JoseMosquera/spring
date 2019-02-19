package com.game.JoseMosquera.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.game.JoseMosquera.entity.Juego;
import com.game.JoseMosquera.entity.QJuego;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("queryDSLJuego")
public class QueryDSLJuego {

	private QJuego qJuego = QJuego.juego;
	
	@PersistenceContext
	private EntityManager em;

	
	public List<Juego> listJuegosVenta(){
		
		JPAQuery<Juego> query = new JPAQuery<Juego>(em);
		
		List<Juego> juegosVentas = query.select(qJuego).from(qJuego).where(qJuego.tipo.eq("venta")).fetch();
		
		return juegosVentas;
	}
	
	public List<Juego> listJuegosAlq(){
		
		JPAQuery<Juego> query = new JPAQuery<Juego>(em);
		
		List<Juego> juegosAlq = query.select(qJuego).from(qJuego).where(qJuego.tipo.eq("alquiler")).fetch();
		
		return juegosAlq;
	}
}
