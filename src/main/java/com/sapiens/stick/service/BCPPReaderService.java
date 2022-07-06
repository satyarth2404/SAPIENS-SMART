package com.sapiens.stick.service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import com.sapiens.stick.entity.Incident;

@RestController
public class BCPPReaderService {
	
	List<Incident> incidents = new ArrayList<>();
	
	
//	@RequestMapping("/addInc")
//	public List<Incident> readIncident(String path) throws Exception {
//		Incident incident;
//		String[] nextLine;
//		try {
//			FileReader file = new FileReader("C:/Users/I559344/Documents/export.csv");
//			CSVReader csvReader = new CSVReaderBuilder(file).build();
//			
//			int count = 0;
//			while((nextLine = csvReader.readNext()) != null){
//				if(count == 0)
//					continue;
//			/*	incident.setId(Long.parseLong(nextLine[1]));
//				incident.setComponent(nextLine[2]);
//				incident.setCustomer(nextLine[5]);
//				incident.setDescription(nextLine[4]);
//				incident.setStatus(nextLine[3]);
//				incident.setPriority(nextLine[6]);
//				incident.setProcessor(nextLine[7]);
//				incident.setIrt(count++); */
//				incident = new Incident(Long.parseLong(nextLine[1]),nextLine[2],nextLine[3],
//										nextLine[4],nextLine[5],nextLine[7],nextLine[6],count++);
//				
//				incidents.add(incident);
//			}
//			return incidents;
//		}
//		catch(Exception e) {
//			System.out.println(e.getStackTrace().toString() + " "+incidents.size());
//		throw e;
//		}
//	}  
	
	@RequestMapping("loadInc")
	public List<Incident> readInc(String path) throws Exception {
			String fileName = "C:/Users/I559344/Documents/incidents.csv";
			try(ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(fileName), CsvPreference.STANDARD_PREFERENCE))
		    {
		      // the header elements are used to map the values to the bean
		      final String[] headers = beanReader.getHeader(true);
		      //final String[] headers = new String[]{"Incident ID", "Component", "Status", "Description", "Customer","Priority","Processor",null};
		      //final String[] headers = new String[]{"CustomerId","CustomerName","Country","PinCode","Email"};
		      final CellProcessor[] processors = getProcessors();
		 
		      Incident incident;
		      
		      while ((incident = beanReader.read(Incident.class, headers, processors)) != null) {
		    	  incidents.add(incident);
		    	  System.out.println(incident.getId());
		      }
		      for(Incident inc: incidents) {
		    	  System.out.println(inc.toString());
		      }
		      System.out.println(Integer.toString(incidents.size()));
		    }
			return incidents;
		}
		private static CellProcessor[] getProcessors() {
	    
		 
		    final CellProcessor[] processors = new CellProcessor[] {
		        new NotNull(new ParseLong()), // CustomerId
		        new NotNull(), // CustomerName
		        new NotNull(), // Country
		        new NotNull(), // PinCode
		        new NotNull(), // Email
		        new NotNull(),
		        new Optional(),
		        new Optional(new ParseInt())
	        
	        
	    };
	    return processors;
	  }
	
	@Scheduled(cron = "${cron.expression}") 
	@RequestMapping("/irtAck")
	public void scheduleIrt() {
		if(incidents != null)
		System.out.println("IRT Acknowledged using Cron at "
				+ new Date());
	}
		
	}

