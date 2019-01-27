package com.game.JoseMosquera.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.JoseMosquera.entity.Competicion;

@Repository("competicionRepository")
public interface CompeticionRepository extends JpaRepository<Competicion, Serializable>{

}
