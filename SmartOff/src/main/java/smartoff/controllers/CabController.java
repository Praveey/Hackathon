package smartoff.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import smartoff.pojo.BookingPojo;
import smartoff.pojo.CabPojo;
import smartoff.pojo.EmpPojo;
import smartoff.pojo.ParkingPojo;
import smartoff.service.CabService;

@RestController
@RequestMapping("/cabController")
@EnableAutoConfiguration
public class CabController {

	/*@Autowired
	CabService service;*/
	
	@RequestMapping(method=RequestMethod.GET)
	public CabPojo cabInformation(){
		CabPojo cab = new CabPojo();
		cab.setCabNumber("123");
		cab.setDriverContact("984586798");
		cab.setDriverName("abc");
		cab.setScheduletime("1");
		return cab;
		
	} 	

	@RequestMapping(value = ("/bookingHistory"),method=RequestMethod.GET)
	public BookingPojo getBookingHistory(@RequestParam (value="empID", required=true)String empID) {
		BookingPojo emp = new BookingPojo();
		emp.setEmp_ID("22");
		emp.setBookingId("1");
		emp.setTime("2016/12/10");
		emp.setCab_ID("12345");
		return emp;
	}

	@RequestMapping(value= ("/successRequest"),method=RequestMethod.POST)
	public CabPojo processBooking(@RequestBody CabPojo cabPojo){
		//send confirmation mail to employee
		return cabPojo;
		
	} 	
	
}
