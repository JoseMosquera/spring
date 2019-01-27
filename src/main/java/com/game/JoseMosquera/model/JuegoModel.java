package com.game.JoseMosquera.model;

import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class JuegoModel {

	private int juego_id;
	
	@NotNull
	@Size(min = 3,max = 50)

	private String titulo;
	
	@NotNull
	@Size(min = 500,max = 1000)
	private String descripcion;
	
	@NotNull
	private float precio;
	
	@NotNull
	private int stock;
	
	@NotNull
	private String tipo;
	
	private String caratula;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lanzamiento;
	
	@NotNull
	private String[] categoriasArray;

	public String[] getCategoriasArray() {
		return categoriasArray;
	}
	
	@NotNull
	private String[] plataformasArray;

	public String[] getPlataformasArray() {
		return plataformasArray;
	}

	public void setPlataformasArray(String[] plataformasArray) {
		this.plataformasArray = plataformasArray;
	}

	public void setCategoriasArray(String[] categoriasArray) {
		this.categoriasArray = categoriasArray;
	}

	public int getJuego_id() {
		return juego_id;
	}

	public void setJuego_id(int juego_id) {
		this.juego_id = juego_id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCaratula() {
		return caratula;
	}

	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}

	public Date getLanzamiento() {
		return lanzamiento;
	}

	public void setLanzamiento(Date lanzamiento) {
		this.lanzamiento = lanzamiento;
	}

	public JuegoModel(int juego_id, String titulo, String descripcion, float precio, int stock, String tipo,
			String caratula, Date lanzamiento, String[] categoriasArray, String[] plataformasArray) {
		super();
		this.juego_id = juego_id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.tipo = tipo;
		this.caratula = caratula;
		this.lanzamiento = lanzamiento;
		this.categoriasArray = categoriasArray;
		this.plataformasArray = plataformasArray;
	}

	public JuegoModel() {
		super();
	}

	@Override
	public String toString() {
		return "JuegoModel [juego_id=" + juego_id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", precio="
				+ precio + ", stock=" + stock + ", tipo=" + tipo + ", caratula=" + caratula + ", lanzamiento="
				+ lanzamiento + ", categoriasArray=" + Arrays.toString(categoriasArray) + ", plataformasArray="
				+ Arrays.toString(plataformasArray) + "]";
	}
}
