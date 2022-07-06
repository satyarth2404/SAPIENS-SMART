package com.sapiens.stick;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import com.sapiens.stick.entity.Incident;
import com.sapiens.stick.service.BCPPReaderService;

@SpringBootApplication
@EnableScheduling
public class StickApplication {
	private static List<Incident> incidents = new ArrayList<>();
	private static final Logger logger = LoggerFactory.getLogger(StickApplication.class);
	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = SpringApplication.run(StickApplication.class, args);
		BCPPReaderService bcpReader = applicationContext.getBean(BCPPReaderService.class); 
	csvtoIncBean();
	}
	public static String csvtoIncBean()throws IOException {
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
		return "Hello";
	}
	private static CellProcessor[] getProcessors() {
	    
	 
	    final CellProcessor[] processors = new CellProcessor[] {
	        new NotNull(new ParseLong()), // Incident ID
	        new NotNull(), // Component
	        new NotNull(), // Status
	        new NotNull(), // Description
	        new NotNull(), // Customer
	        new NotNull(), // Priority
	        new Optional(),// Processor
	        new Optional(new ParseInt()) // IRT Flag
	        
	        
	    };
	    return processors;
	  }
}
