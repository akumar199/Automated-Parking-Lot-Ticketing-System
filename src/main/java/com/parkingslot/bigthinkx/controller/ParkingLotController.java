//package com.parkingslot.bigthinkx.controller;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.parkingslot.bigthinkx.entity.ParkingSlot;
//import com.parkingslot.bigthinkx.service.ParkingSlotServiceImpl;
//
//@RestController
//@RequestMapping("/parking")
//public class ParkingLotController {
//
//	@Autowired
//	private ParkingSlotServiceImpl parkingLotService;
//
//	@PostMapping("/create")
//	public ResponseEntity<String> createParkingLot(
//			@RequestParam(name = "numberOfSlots", required = false, defaultValue = "10") int numberOfSlots) {
//		parkingLotService.createParkingLot(numberOfSlots);
//		return ResponseEntity.ok("Parking lot created with capacity " + numberOfSlots);
//	}
//
//	@PostMapping("/park")
//	public ResponseEntity<String> parkCar(@RequestParam String registrationNumber, @RequestParam String color) {
//		ParkingSlot parkingSlot = parkingLotService.parkCar(registrationNumber, color);
//		return ResponseEntity.ok(
//				"Car with registration number " + registrationNumber + " parked at slot number " + parkingSlot.getSlotNumber());
//	}
//
//	@PostMapping("/leave")
//	public ResponseEntity<String> leaveParkingLot(@RequestParam int slotNumber) {
//		ParkingSlot parkingSlot = parkingLotService.leaveParkingSlot(slotNumber);
//		return ResponseEntity.ok("Slot number " + parkingSlot.getSlotNumber() + " is now empty");
//	}
//
//	@GetMapping("/status")
//	public ResponseEntity<List<ParkingSlot>> getParkingLotStatus() {
//		List<ParkingSlot> parkingSlots = parkingLotService.getAllParkingSlots();
//		return ResponseEntity.ok(parkingSlots);
//	}
//
//	/*
//	 * @GetMapping("/registration_numbers_for_cars_with_colour") public
//	 * ResponseEntity<List<String>> getRegistrationNumbersByColor(@RequestParam
//	 * String color) { List<String> registrationNumbers =
//	 * parkingLotService.getRegistrationNumbersByColor(color); return
//	 * ResponseEntity.ok(registrationNumbers); }
//	 */
//
//	/*
//	 * @GetMapping("/slot_numbers_for_cars_with_colour") public
//	 * ResponseEntity<List<Integer>> getSlotNumbersByColor(@RequestParam String
//	 * color) { List<Integer> slotNumbers =
//	 * parkingLotService.getSlotNumbersByColor(color); return
//	 * ResponseEntity.ok(slotNumbers); }
//	 */
//	@GetMapping("/slot_number_for_registration_number")
//	public ResponseEntity<Integer> getSlotNumberByRegistrationNumber(@RequestParam String carNumber) {
//		ParkingSlot parkingSlot = parkingLotService.getParkingSlotByRegistrationNumber(carNumber);
//		if (parkingSlot == null) {
//			return ResponseEntity.notFound().build();
//		}
//		return ResponseEntity.ok(parkingSlot.getSlotNumber());
//	}
//}
