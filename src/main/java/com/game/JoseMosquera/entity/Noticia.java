package com.game.JoseMosquera.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "noticias")
public class Noticia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "noticia_id", unique = true, nullable = false)
	private int noticia_id;
	
	@Column(name = "titulo", unique = true, nullable = false, length = 100)
	private String titulo;
	
	@Column(name = "tipo", unique = true, nullable = false, length = 100)
	private String tipo;
	
	@Column(name = "descripcion", nullable = false, length = 1000)
	private String descripcion;
	
	@Column(name = "contenido", nullable = false, length = 5000)
	private String contenido;
	
	@Column(name = "destacada", nullable = false)
	private boolean destacada;

	public int getNoticia_id() {
		return noticia_id;
	}

	public void setNoticia_id(int noticia_id) {
		this.noticia_id = noticia_id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public boolean isDestacada() {
		return destacada;
	}

	public void setDestacada(boolean destacada) {
		this.destacada = destacada;
	}

	public Noticia(int noticia_id, String titulo, String tipo, String descripcion, String contenido,
			boolean destacada) {
		super();
		this.noticia_id = noticia_id;
		this.titulo = titulo;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.contenido = contenido;
		this.destacada = destacada;
	}

	public Noticia() {
		super();
	}

	@Override
	public String toString() {
		return "Noticia [noticia_id=" + noticia_id + ", titulo=" + titulo + ", tipo=" + tipo + ", descripcion="
				+ descripcion + ", contenido=" + contenido + ", destacada=" + destacada + "]";
	}
}
