package com.game.JoseMosquera.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.JoseMosquera.entity.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Serializable>{
	
}
