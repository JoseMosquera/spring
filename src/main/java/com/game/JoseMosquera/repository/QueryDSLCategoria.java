package com.game.JoseMosquera.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.entity.Categoria;
import com.game.JoseMosquera.entity.Juego;
import com.game.JoseMosquera.entity.QCategoria;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("queryDSLCategoria")
public class QueryDSLCategoria {
	
	private QCategoria qCategoria = QCategoria.categoria;
	
	@PersistenceContext
	private EntityManager em;
	
	public Categoria findCategoriaByName(String name) {
		
		JPAQuery<Juego> query = new JPAQuery<Juego>(em);
		Logs.LOG.info("Llamada al medoto findCategoriaByName() de la clase QueryDSLCategoria(), recibe un nombre: '"+name+"'");
		
		Categoria categoria= query.select(qCategoria).from(qCategoria).where(qCategoria.nombre.eq(name)).fetchOne();
		Logs.LOG.info("Retorno del medoto findCategoriaByName() de la clase QueryDSLCategoria(), retorna una categoria: '"+categoria.toString()+"'");
		
		return categoria;
	}
}
