package com.flights.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FlightApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(FlightApplication.class, args);
	    BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
		FlightController fc = new FlightController();
	    int x=1;
	    
	    // main command processing loop
	    while (x==1) {	    	
	        char searchType;
	        String flightNum;
	        String date;
	        String origin;
	        String destination;
	        String inString;
	        
	        System.out.println("Type 1 and Enter to Search by Flight Number and Date.\n" 
	        		+"Type 2 and Enter to Search by Origin and Destination and Date.\n"  
	        		+"Type 3 and Enter to exit.\n");
	        
			try {
				inString = reader.readLine();
				
				if (inString.length() == 0) {
					continue;
				}
				searchType = inString.charAt(0);
				
				// search by flight number and date
				if (searchType == '1') {
					System.out.println("Enter Flight Number");
					flightNum = reader.readLine();
					System.out.println("Enter Date");
					date = reader.readLine();		
					fc.returnFlightByFlightNumberAndDeparture(flightNum, date);
				}
				
				// search by origin and destination and date
				else if (searchType == '2'){
					System.out.println("Enter Origin");
					origin = reader.readLine();
					System.out.println("Enter Destination");
					destination = reader.readLine();
					System.out.println("Enter Date");
					date = reader.readLine();
					fc.returnFlightByOriginAndDestinationAndDeparture(origin, destination, date);
				}
				
				else {
					break;
				}
			}
			
			catch (IOException e) {
				System.out.println("Command not successfully read:  " +e.getMessage());
				continue;
			}
	    }	
		ctx.close();
	}
}
