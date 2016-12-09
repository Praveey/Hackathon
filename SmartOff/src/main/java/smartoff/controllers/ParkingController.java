package smartoff.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import smartoff.pojo.ParkingPojo;
import smartoff.service.ParkingService;

@RestController
@RequestMapping("/hello-world")
@EnableAutoConfiguration
public class ParkingController {
	@Autowired
	ParkingService service;

	@RequestMapping(method=RequestMethod.GET)
	public Map<String,String> showParkingSpace(@RequestParam(value="name", required=false, defaultValue="Stranger") String name){
		System.out.println("In");
		return service.showParkingSpace();
		
	} 	

	@RequestMapping(value= ("/allocate"),method=RequestMethod.POST)
	public Map<String,String> allocateParkingSpace(@RequestBody ParkingPojo parkingpojo){
	return service.allotParkingSpace(parkingpojo);
		
	} 	
	
	
	
}