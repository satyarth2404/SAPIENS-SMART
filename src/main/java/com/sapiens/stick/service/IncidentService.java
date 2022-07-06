package com.sapiens.stick.service;

import java.util.List;

import com.sapiens.stick.entity.Incident;

public interface IncidentService {
	
	public List<Incident> findAll();
	
	public Incident findById(Long theId);
	
	public void save(Incident theIncident);
	
	public void deleteById(Long theId);
}
