

  package com.parkingslot.bigthinkx.service;
  
  import java.util.List;
  
  import com.parkingslot.bigthinkx.entity.ParkingSlot;
  
  public interface ParkingSlotService  { 
	  
  void createParkingLot(int numberOfSlots);
  
  ParkingSlot parkCar(String registrationNumber, String color);
  
  ParkingSlot leaveParkingSlot(int slotNumber);
  
  List<ParkingSlot> getAllParkingSlots();
  
 
  
  ParkingSlot getParkingSlotByRegistrationNumber(String registrationNumber);
  }
