package com.parkingslot.bigthinkx.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingslot.bigthinkx.reprository.ParkingSlotRepository;
import com.parkingslot.bigthinkx.entity.ParkingSlot;
import com.parkingslot.bigthinkx.service.ParkingSlotService;

@Service
public class  ParkingSlotServiceImpl implements ParkingSlotService  {
    @Autowired
    private ParkingSlotRepository parkingSlotRepository;
    

    
    public  void createParkingLot(int  numberOfSlots) {
        for (int i = 1; i <= numberOfSlots; i++) {
            ParkingSlot parkingSlot = new ParkingSlot();
            parkingSlot.setSlotNumber(i);
            parkingSlotRepository.save(parkingSlot);
        }
    }

 
    public ParkingSlot parkCar(String registrationNumber, String color) {
        List<ParkingSlot> emptyParkingSlots = parkingSlotRepository.findEmptyParkingSlots();
        if (emptyParkingSlots.isEmpty()) {
            throw new RuntimeException("Parking lot is full");
        }
        ParkingSlot parkingSlot = emptyParkingSlots.get(0);
        parkingSlot.setRegistrationNumber(registrationNumber);
        parkingSlot.setColor(color);
        parkingSlotRepository.save(parkingSlot);
        return parkingSlot;
    }

   
    public ParkingSlot leaveParkingSlot(Integer slotNumber) {
        ParkingSlot parkingSlot = parkingSlotRepository.findBySlotNumber(slotNumber);
        if (parkingSlot == null) {
            throw new RuntimeException("No such parking slot found");
        }
        parkingSlot.setRegistrationNumber(null);
        parkingSlot.setColor(null);
        parkingSlotRepository.save(parkingSlot);
        return parkingSlot;
    }

  
    public List<ParkingSlot> getAllParkingSlots() {
        return parkingSlotRepository.findAll();
    }

   
    public List<ParkingSlot> getParkingSlotsByColor(String color) {
        return parkingSlotRepository.findByColor(color);
    }

  
    public ParkingSlot getParkingSlotByRegistrationNumber(String registrationNumber) {
        return parkingSlotRepository.findByRegistrationNumber(registrationNumber);
    }
	


	@Override
	public ParkingSlot leaveParkingSlot(int slotNumber) {
		// TODO Auto-generated method stub
		 ParkingSlot parkingSlot = parkingSlotRepository.findBySlotNumber(slotNumber);
		    if (parkingSlot == null) {
		        throw new RuntimeException("No such parking slot found");
		    }
		    parkingSlot.setRegistrationNumber(null);
		    parkingSlot.setColor(null);
		    parkingSlotRepository.save(parkingSlot);
		    return parkingSlot;
	}
}

