package com.game.JoseMosquera.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.JoseMosquera.entity.Venta;

@Repository("ventaRepository")
public interface VentaRepository extends JpaRepository<Venta, Serializable>{

}
