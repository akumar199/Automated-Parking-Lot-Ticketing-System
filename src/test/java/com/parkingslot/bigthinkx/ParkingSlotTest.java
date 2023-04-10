package com.parkingslot.bigthinkx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.parkingslot.bigthinkx.entity.ParkingSlot;
import com.parkingslot.bigthinkx.reprository.ParkingSlotRepository;
import com.parkingslot.bigthinkx.service.ParkingSlotServiceImpl;

public class ParkingSlotTest {

    private ParkingSlotServiceImpl parkingLotService;

    @Mock
    private ParkingSlotRepository parkingSlotRepository;

	
	  @BeforeEach public void setUp() { MockitoAnnotations.initMocks(this);
	  parkingLotService = new ParkingSlotServiceImpl(parkingSlotRepository);
	  parkingLotService.setParkingSlotRepository(parkingSlotRepository); }
	 

    @Test
    public void testCreateParkingLot() {
        int numberOfSlots = 10;
        parkingLotService.createParkingLot(numberOfSlots);
        Mockito.verify(parkingSlotRepository, Mockito.times(numberOfSlots)).save(Mockito.any(ParkingSlot.class));
    }

    @Test
    public void testParkCar() {
        String registrationNumber = "ABC-123";
        String color = "Red";
        ParkingSlot emptyParkingSlot = new ParkingSlot();
        emptyParkingSlot.setSlotNumber(1);
        Mockito.when(parkingSlotRepository.findEmptyParkingSlots()).thenReturn(List.of(emptyParkingSlot));
        ParkingSlot parkingSlot = parkingLotService.parkCar(registrationNumber, color);
        assertNotNull(parkingSlot);
        assertEquals(emptyParkingSlot, parkingSlot);
        assertEquals(registrationNumber, parkingSlot.getRegistrationNumber());
        assertEquals(color, parkingSlot.getColor());
        Mockito.verify(parkingSlotRepository, Mockito.times(1)).save(Mockito.any(ParkingSlot.class));
    }

    @Test
    public void testParkCarParkingLotFull() {
        Mockito.when(parkingSlotRepository.findEmptyParkingSlots()).thenReturn(List.of());
        assertThrows(RuntimeException.class, () -> parkingLotService.parkCar("ABC-123", "Red"));
    }

    @Test
    public void testLeaveParkingSlot() {
        int slotNumber = 1;
        ParkingSlot parkingSlot = new ParkingSlot();
        parkingSlot.setSlotNumber(slotNumber);
        parkingSlot.setRegistrationNumber("ABC-123");
        parkingSlot.setColor("Red");
        Mockito.when(parkingSlotRepository.findBySlotNumber(slotNumber)).thenReturn(parkingSlot);
        parkingLotService.leaveParkingSlot(slotNumber);
        assertNotNull(parkingSlot);
        assertEquals(slotNumber, parkingSlot.getSlotNumber());
        assertEquals(null, parkingSlot.getRegistrationNumber());
        assertEquals(null, parkingSlot.getColor());
        Mockito.verify(parkingSlotRepository, Mockito.times(1)).save(Mockito.any(ParkingSlot.class));
    }

    @Test
    public void testLeaveParkingSlotNotFound() {
        int slotNumber = 1;
        Mockito.when(parkingSlotRepository.findBySlotNumber(slotNumber)).thenReturn(null);
        assertThrows(RuntimeException.class, () -> parkingLotService.leaveParkingSlot(slotNumber));
    }

    @Test
    public void testGetAllParkingSlots() {
        Mockito.when(parkingSlotRepository.findAll()).thenReturn(List.of(new ParkingSlot()));
        List<ParkingSlot> parkingSlots = parkingLotService.getAllParkingSlots();
        assertNotNull(parkingSlots);
        assertEquals(1, parkingSlots.size());
    }

}
