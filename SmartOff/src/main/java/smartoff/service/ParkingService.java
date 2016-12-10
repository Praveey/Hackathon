package smartoff.service;


import java.util.List;
import java.util.Map;

import smartoff.pojo.ParkingPojo;

public interface ParkingService {
	
	Map<String,String> showParkingSpace();
	Map<String,String> allotParkingSpace(ParkingPojo parkingPojo);
	List<String> displayFreeSpace();
	String deallocateParkingSpace(ParkingPojo parkingPojo);
}