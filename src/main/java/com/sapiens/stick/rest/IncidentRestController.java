package com.sapiens.stick.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapiens.stick.entity.Incident;
import com.sapiens.stick.service.IncidentService;

@RestController
@RequestMapping("/api")
public class IncidentRestController {
	
	
	private IncidentService incidentService;
	
	@Autowired
	public IncidentRestController(IncidentService theIncidentService) {
		incidentService = theIncidentService;
	}
	
	@GetMapping("/incidents")
	public List<Incident> findAll(){
		return incidentService.findAll();
	}
	
	@GetMapping("/incident/{incidentId}")
	public Incident findById(@PathVariable Long incidentId) {
	Incident incident;
	try {
		 incident = incidentService.findById(incidentId);
	}
	catch(Exception e) {
		throw new RuntimeException("Incident with id:"+incidentId+"  not found");
	}
	return incident;
	}
	
	@PostMapping("/incidents")
	public String postIncident(@RequestBody Incident theIncident) {
		theIncident.setId(0L);
		incidentService.save(theIncident);
		return "Incident "+theIncident.getId()+" is posted";
	}
	
	@PutMapping("/incidents")
	public Incident updateIncident(@RequestBody Incident theIncident) {
		incidentService.save(theIncident);
		return theIncident;
	}
	
	@DeleteMapping("/incidents/{incidentId}")
	public List<Incident> deleteById(Long incidentId){
		Incident incident = incidentService.findById(incidentId);
		
		if(incident == null)
			throw new RuntimeException("Incident with id: "+incidentId+" not found");
		incidentService.deleteById(incidentId);
		return incidentService.findAll();
	}
}
