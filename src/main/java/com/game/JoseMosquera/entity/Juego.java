package com.game.JoseMosquera.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "juegos")
public class Juego {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "juego_id")
	private int juego_id;
	
	@Column(name= "titulo", unique = false, nullable = false, length = 100)
	private String titulo;
	
	@Column(name= "descripcion", unique = false, nullable = false, length = 1000)
	private String descripcion;
	
	@Column(name= "precio")
	private float precio;
	
	@Column(name= "stock")
	private int stock;
	
	@Column(name= "tipo")
	private String tipo;
	
	@Column(name= "caratula")
	private String caratula;
	
	@Column(name= "lanzamiento")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lanzamiento;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "categoria_juego", joinColumns = @JoinColumn(name = "juego_id", referencedColumnName = "juego_id"),
				inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "categoria_id"))
	private List<Categoria> categoriasList;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "plataforma_juego", joinColumns = @JoinColumn(name = "juego_id", referencedColumnName = "juego_id"),
				inverseJoinColumns = @JoinColumn(name = "plataforma_id", referencedColumnName = "plataforma_id"))
	private List<Plataforma> plataformasList;
	
	@OneToMany(mappedBy = "juego", cascade = CascadeType.PERSIST)
	private Set<Venta> ventas;
	
	@OneToMany(mappedBy = "juego", cascade = CascadeType.PERSIST)
	private Set<Alquiler> alquileres;
	

	public Set<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}

	public Set<Alquiler> getAlquileres() {
		return alquileres;
	}

	public void setAlquileres(Set<Alquiler> alquileres) {
		this.alquileres = alquileres;
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

	public List<Categoria> getCategorias() {
		return categoriasList;
	}

	public void setCategorias(List<Categoria> categoriasList) {
		this.categoriasList = categoriasList;
	}
	

	public List<Plataforma> getPlataformas() {
		return plataformasList;
	}

	public void setPlataformas(List<Plataforma> plataformasList) {
		this.plataformasList = plataformasList;
	}

	public Juego(int juego_id, String titulo, String descripcion, float precio, int stock, String tipo, String caratula,
			Date lanzamiento, List<Categoria> categoriasList, List<Plataforma> plataformasList, Set<Venta> ventas,
			Set<Alquiler> alquileres) {
		super();
		this.juego_id = juego_id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.tipo = tipo;
		this.caratula = caratula;
		this.lanzamiento = lanzamiento;
		this.categoriasList = categoriasList;
		this.plataformasList = plataformasList;
		this.ventas = ventas;
		this.alquileres = alquileres;
	}

	public Juego() {
		super();
	}

	@Override
	public String toString() {
		return "Juego [juego_id=" + juego_id + ", titulo=" + titulo + ", descriopcion=" + descripcion + ", precio="
				+ precio + ", stock=" + stock + ", tipo=" + tipo + ", caratula=" + caratula + ", lanzamiento="
				+ lanzamiento + ", categoriasList=" + categoriasList + ", plataformasList=" + plataformasList + "]";
	}
}
