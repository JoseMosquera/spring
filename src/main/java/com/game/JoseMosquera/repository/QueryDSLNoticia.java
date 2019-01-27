package com.game.JoseMosquera.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.game.JoseMosquera.entity.Noticia;
import com.game.JoseMosquera.entity.QNoticia;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("queryDSLNoticia")
public class QueryDSLNoticia {

	private QNoticia qNoticia = QNoticia.noticia;
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Noticia> findNoticiasDestacadas() {
		JPAQuery<Noticia> query = new JPAQuery<Noticia>(em);
		
		List<Noticia> noticiasDestacadas = query.select(qNoticia).from(qNoticia).where(qNoticia.destacada.isTrue()).fetch();
		
		return noticiasDestacadas;
	}
}
