package com.parkingslot.bigthinkx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.parkingslot.bigthinkx.entity.ParkingSlot;
import com.parkingslot.bigthinkx.service.ParkingSlotServiceImpl;

@SpringBootTest(classes= {ParkinglotControllersTest.class})
public class ParkinglotControllersTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ParkingSlotServiceImpl parkingLotService;

    @Test
    public void testCreateParkingLot() {
        ResponseEntity<String> response = restTemplate.postForEntity("/parking/create?numberOfSlots=5", null, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Parking lot created with capacity 5", response.getBody());
    }

    @Test
    public void testParkCar() {
        ResponseEntity<String> createResponse = restTemplate.postForEntity("/parking/create?numberOfSlots=1", null, String.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        ResponseEntity<String> parkResponse = restTemplate.postForEntity("/parking/park/ABC123/Red", null, String.class);
        assertEquals(HttpStatus.OK, parkResponse.getStatusCode());
        assertEquals("Car with registration number ABC123 parked at slot number 1", parkResponse.getBody());
    }

    @Test
    public void testLeaveParkingLot() {
        ResponseEntity<String> createResponse = restTemplate.postForEntity("/parking/create?numberOfSlots=1", null, String.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        ResponseEntity<String> parkResponse = restTemplate.postForEntity("/parking/park/ABC123/Red", null, String.class);
        assertEquals(HttpStatus.OK, parkResponse.getStatusCode());
        ResponseEntity<String> leaveResponse = restTemplate.postForEntity("/parking/leave/1", null, String.class);
        assertEquals(HttpStatus.OK, leaveResponse.getStatusCode());
        assertEquals("Slot number 1 is now empty", leaveResponse.getBody());
    }
}

   
