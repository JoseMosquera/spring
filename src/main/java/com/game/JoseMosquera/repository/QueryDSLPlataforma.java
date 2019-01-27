package com.game.JoseMosquera.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.entity.Juego;
import com.game.JoseMosquera.entity.Plataforma;
import com.game.JoseMosquera.entity.QPlataforma;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("queryDSLPlataforma")
public class QueryDSLPlataforma {

private QPlataforma qPlataforma = QPlataforma.plataforma;
	
	@PersistenceContext
	private EntityManager em;
	
	public Plataforma findPlataformaByName(String name) {
		
		JPAQuery<Juego> query = new JPAQuery<Juego>(em);
		Logs.LOG.info("Llamada al medoto findPlataformaByName() de la clase QueryDSLPlataforma(), recibe un nombre: '"+name+"'");
		
		Plataforma plataforma= query.select(qPlataforma).from(qPlataforma).where(qPlataforma.nombre.eq(name)).fetchOne();
		Logs.LOG.info("Retorno del medoto findPlataformaByName() de la clase QueryDSLPlataforma(), retorna una categoria: '"+plataforma.toString()+"'");
		
		return plataforma;
	}
}
