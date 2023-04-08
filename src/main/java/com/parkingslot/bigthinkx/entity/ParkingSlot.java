package com.parkingslot.bigthinkx.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parking_slot")
public class ParkingSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private  int numberOfSlots;
    
    @Column(name = "slot_number", unique = true, nullable = false)
    private Integer slotNumber;
    
    @Column(name = "registration_number")
    private String registrationNumber;
    
    @Column(name = "color")
    private String color;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(Integer slotNumber) {
		this.slotNumber = slotNumber;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ParkingSlot(Long id, Integer slotNumber, String registrationNumber, String color, int numberOfSlots ) {
		super();
		this.id = id;
		this.slotNumber = slotNumber;
		this.registrationNumber = registrationNumber;
		this.color = color;
		this.numberOfSlots=numberOfSlots;
	}

	public ParkingSlot() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getNumberOfSlots() {
		return numberOfSlots;
	}

	public void setNumberOfSlots(int numberOfSlots) {
		this.numberOfSlots = numberOfSlots;
	}
    
    // Constructors, getters, and setters
}
