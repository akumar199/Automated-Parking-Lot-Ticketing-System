package com.parkingslot.bigthinkx.reprository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.parkingslot.bigthinkx.entity.ParkingSlot;

@EnableJpaRepositories
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    
	 @Query("SELECT p FROM ParkingSlot p WHERE p.registrationNumber IS NULL")
	    List<ParkingSlot> findEmptyParkingSlots();
    ParkingSlot findBySlotNumber(Integer slotNumber);
    
    List<ParkingSlot> findByColor(String color);
    
    ParkingSlot findByRegistrationNumber(String registrationNumber);
    
   
}
    
   