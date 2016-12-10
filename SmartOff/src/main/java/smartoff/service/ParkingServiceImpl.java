package smartoff.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smartoff.pojo.ParkingPojo;
import smartoff.repo.ParkingRepoImplDB;
	

@Service("parkingService")
public class ParkingServiceImpl implements ParkingService {
	@Autowired
	private ParkingRepoImplDB repo;

	@Autowired
	public ParkingServiceImpl(ParkingRepoImplDB repo){
		this.repo = repo;
	}
	

	
	public Map<String, String> showParkingSpace() {
		// TODO Auto-generated method stub
		return repo.showSpace();
		/*Map<String,String> alloc = new HashMap<String,String>();
		alloc.put("67992_FS", "12");
		alloc.put("434636", "54");
		alloc.put("33", "342");
		alloc.put("3521", "5");
		alloc.put("2424", "35");
		alloc.put("34", "6");
		return alloc;*/
	}

	public Map<String, String> allotParkingSpace(ParkingPojo parkingPojo) {
		// TODO Auto-generated method stub
		return repo.allotSpace(parkingPojo);
		/*Map<String, String> alloc = showParkingSpace();
		alloc.put(parkingPojo.getEmpID(), parkingPojo.getSlotID());
		return alloc;*/
	}



	@Override
	public List<String> displayFreeSpace() {
		// TODO Auto-generated method stub
		return repo.displayFreeSpace();
	}



	@Override
	public String deallocateParkingSpace(ParkingPojo parkingPojo) {
		// TODO Auto-generated method stub
		return repo.deallocateParkingSpace(parkingPojo);
	}

}