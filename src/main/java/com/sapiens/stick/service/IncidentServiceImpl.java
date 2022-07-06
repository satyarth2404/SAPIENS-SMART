package com.sapiens.stick.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapiens.stick.dao.IncidentRepository;
import com.sapiens.stick.entity.Incident;

@Service
public class IncidentServiceImpl implements IncidentService {
	
	private IncidentRepository incidentRepository;
	
	@Autowired
	public IncidentServiceImpl(IncidentRepository theIncidentRepository) {
		incidentRepository = theIncidentRepository;
	}
	
	@Override
	public List<Incident> findAll() {
		return incidentRepository.findAll();
	}

	@Override
	public Incident findById(Long theId) {
		Optional<Incident> result = incidentRepository.findById(theId);
		Incident incident = null;
		if(result.isPresent()) {
			incident = result.get();
		}
		else {
			throw new RuntimeException("Incident with id: "+theId+" is not found");
		}
		return incident;
	}

	@Override
	public void save(Incident theIncident) {
		incidentRepository.save(theIncident);
	}

	@Override
	public void deleteById(Long theId) {
		incidentRepository.deleteById(theId);
	}

}
