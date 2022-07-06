package com.sapiens.stick.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapiens.stick.entity.Incident;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
	
}
