package com.game.JoseMosquera.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NoticiaModel {

	private int noticia_id;
	
	@NotNull
	@Size(min = 10, max = 100)
	private String titulo;
	
	@NotNull
	@Size(min = 10, max = 100)
	private String tipo;
	
	@NotNull
	@Size(min = 500, max = 1000)
	private String descripcion;
	
	@NotNull
	@Size(min = 2000, max = 5000)
	private String contenido;
	
	@NotNull
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

	public NoticiaModel() {
		super();
	}

	public NoticiaModel(@NotNull int noticia_id, @NotNull @Size(min = 10, max = 100) String titulo,
			@NotNull @Size(min = 10, max = 100) String tipo, @NotNull @Size(min = 500, max = 1000) String descripcion,
			@NotNull @Size(min = 2000, max = 5000) String contenido, @NotNull boolean destacada) {
		super();
		this.noticia_id = noticia_id;
		this.titulo = titulo;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.contenido = contenido;
		this.destacada = destacada;
	}

	@Override
	public String toString() {
		return "NoticiaModel [noticia_id=" + noticia_id + ", titulo=" + titulo + ", tipo=" + tipo + ", descripcion="
				+ descripcion + ", contenido=" + contenido + ", destacada=" + destacada + "]";
	}
}
