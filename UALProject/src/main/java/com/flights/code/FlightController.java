package com.flights.code;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/flights")
public class FlightController {
	
	LinkedList<Flight> flightList = new LinkedList<Flight>();

	public FlightController() {
		JSONParser parser = new JSONParser();
		loadFlights(parser);
	}
	
	@RequestMapping(method = RequestMethod.GET, params = {"flightNumber", "departure"})
	public ResponseEntity<LinkedList<Flight>> returnFlightByFlightNumberAndDeparture(@RequestParam String flightNumber, @RequestParam String departure) {
		LinkedList<Flight> jsonRtn = new LinkedList<Flight>(); 

		for (int i=0; i < flightList.size(); i++ ) {
			if ( (flightList.get(i).getFlightNumber().equalsIgnoreCase(flightNumber))   &&   (flightList.get(i).getDeparture().equalsIgnoreCase(departure)) ) {
				jsonRtn.add(flightList.get(i));
				printFlightDetails(i);		
				return new ResponseEntity<LinkedList<Flight>>(jsonRtn, HttpStatus.OK);
			}
		}

		System.out.println("\n\nFlight Not Found");
		return new ResponseEntity<LinkedList<Flight>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.GET, params = {"origin", "destination", "departure"})
	public ResponseEntity<LinkedList<Flight>> returnFlightByOriginAndDestinationAndDeparture(@RequestParam String origin, 
			@RequestParam String destination, @RequestParam String departure) {
		LinkedList<Flight> jsonRtn = new LinkedList<Flight>(); 

		for (int i=0; i < flightList.size(); i++ ) {
			if ( (flightList.get(i).getOrigin().equalsIgnoreCase(origin))   && 
				 (flightList.get(i).getDestination().equalsIgnoreCase(destination)) &&   
				 (flightList.get(i).getDeparture().equalsIgnoreCase(departure)) ) {
				jsonRtn.add(flightList.get(i));
				printFlightDetails(i);		
				return new ResponseEntity<LinkedList<Flight>>(jsonRtn, HttpStatus.OK);
			}
		}
		
		System.out.println("\n\nFlight Not Found");
		return new ResponseEntity<LinkedList<Flight>>(HttpStatus.NOT_FOUND);
	}

	private void printFlightDetails(int i) {
		System.out.println(" FlightNumber:  " +flightList.get(i).getFlightNumber());
		System.out.println("      Carrier:  " +flightList.get(i).getCarrier());
		System.out.println("       Origin:  " +flightList.get(i).getOrigin());
		System.out.println("    Departure:  " +flightList.get(i).getDeparture());
		System.out.println("  Destination:  " +flightList.get(i).getDestination());
		System.out.println("      Arrival:  " +flightList.get(i).getArrival());
		System.out.println("     Aircraft:  " +flightList.get(i).getAircraft());
		System.out.println("     Distance:  " +flightList.get(i).getDistance());
		System.out.println("  Travel Time:  " +flightList.get(i).getTravelTime());
		System.out.println("       Status:  " +flightList.get(i).getStatus() +"\n\n");
	}

	private void loadFlights(JSONParser parser) {
		try {
			Gson g = new Gson(); 
			JSONArray obj = (JSONArray) parser.parse(new FileReader("flight-sample.json"));

			for (int i=0; i < obj.size(); i++) {
				flightList.add(g.fromJson(obj.get(i).toString(), Flight.class));
			}

		}
		catch(FileNotFoundException e) { e.printStackTrace(); }
		catch(IOException e) { e.printStackTrace(); }
		catch(ParseException e) { e.printStackTrace(); }
		catch(Exception e) { e.printStackTrace(); }
	}
}
