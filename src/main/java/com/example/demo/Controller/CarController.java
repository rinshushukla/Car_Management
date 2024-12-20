package com.example.demo.Controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Car;
import com.example.demo.ExceptionHanler.ErrorResponse;
import com.example.demo.ExceptionHanler.SuccessResponse;
import com.example.demo.Service.CarService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api/car")
public class CarController {

	@Autowired
	private CarService carService;

	// Create a new car
	@PostMapping("/create")
	public ResponseEntity<Object> createCar(@Valid @RequestBody Car car) {
		try {
			// Capitalize string fields before saving
			car.setName(capitalizeString(car.getName()));
			car.setModel(capitalizeString(car.getModel()));
			car.setColor(capitalizeString(car.getColor()));
			car.setFuelType(capitalizeString(car.getFuelType()));

			// Save the car
			Car savedCar = carService.saveCar(car);

			// Success Response with Car Data and Timestamp
			SuccessResponse successResponse = new SuccessResponse("Car saved successfully", LocalDateTime.now(),
					savedCar);

			return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
		} catch (Exception e) {
			// Error Handling with Detailed Response

			ErrorResponse errorResponse = new ErrorResponse("Internal Server Error", LocalDateTime.now(),
					e.getMessage(), "CAR_SAVE_FAILURE");
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//dsds

	// For Adding Array Of Car Data

//	public List<Car> saveAllCars(List<Car> cars) {
//        return carRepository.saveAll(cars);
//    }

	// Return the original value if null or empty
	private String capitalizeString(String value) {
		// TODO Auto-generated method stub
		if (value != null && !value.isEmpty()) {
			return value.toUpperCase();
		}
		return value;

	}

	// Get all cars
	@GetMapping
	public List<Car> getAllCars(Pageable pageable,  @RequestParam(value = "sortBy", required = false) String sortBy, @RequestParam(value = "sortDir", required = false) String sortDir) {
		return carService.getAllCars(pageable, sortBy, sortDir);
	}

	// Get car by ID
	@GetMapping("/{id}")
	public ResponseEntity<Car> getCarById(@PathVariable Long id) {
		Optional<Car> car = carService.getCarById(id);
		return car.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/search")
	public ResponseEntity<List<Car>> getFilteredCars(
	        Pageable pageable,
	        @RequestParam(required = false) String name,
	        @RequestParam(required = false) String model,
	        @RequestParam(required = false) Integer year,
	        @RequestParam(required = false) String color,
	        @RequestParam(required = false) String fuelType) {
	    List<Car> cars = carService.getCarsByFilters(pageable, name, model, year, color, fuelType);
	    return ResponseEntity.ok(cars);
	}


//	@GetMapping("/")
//	public ResponseEntity<?> getCarsBYModel(@RequestParam String model) {
//		String reqString = model.toUpperCase().trim();
//		List<Car> cars = carService.getCarBymodel(reqString);
//		if (cars.isEmpty()) {
//			// Return a custom message with status 404 (Not Found)
//			return ResponseEntity.status(404).body("No cars found with the Model: " + reqString);
//		}
//		return ResponseEntity.ok(cars);
//	}
//
//	@GetMapping("/")
//	public ResponseEntity<?> getCarsByYear(@RequestParam Integer year) {
//		
//		List<Car> cars = carService.getCarByyear(year);
//		if (cars.isEmpty()) {
//			// Return a custom message with status 404 (Not Found)
//			return ResponseEntity.status(404).body("No cars found with the Year: " + year);
//		}
//		return ResponseEntity.ok(cars);
//	}

	// Update a car
	@PutMapping("/{id}")
	public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody JsonNode carDetails) {
		Optional<Car> carOptional = carService.getCarById(id);
		if (carOptional.isPresent()) {
			Car car = carOptional.get();

			// Dynamically update fields if they exist in the provided JSON object
			if (carDetails.has("name") && !carDetails.get("name").isNull()) {
				car.setName(carDetails.get("name").asText());
			}
			if (carDetails.has("model") && !carDetails.get("model").isNull()) {
				car.setModel(carDetails.get("model").asText());
			}
			if (carDetails.has("year") && !carDetails.get("year").isNull()) {
				car.setYear(carDetails.get("year").asInt());
			}
			if (carDetails.has("price") && !carDetails.get("price").isNull()) {
				car.setPrice(carDetails.get("price").asDouble());
			}
			if (carDetails.has("color") && !carDetails.get("color").isNull()) {
				car.setColor(carDetails.get("color").asText());
			}
			if (carDetails.has("fuelType") && !carDetails.get("fuelType").isNull()) {
				car.setFuelType(carDetails.get("fuelType").asText());
			}

			// Save and return the updated car
			return ResponseEntity.ok(carService.saveCar(car));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

//	@PostMapping("/updatCar/{id}")
//	public ResponseEntity<String> updateCardetails(@PathVariable Integer id, @RequestBody String req){
//		JSONObject object = new JSONObject();
//		
//		return null;
//		
//	}

	// Delete a car
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteCar(@PathVariable Long id) {
		Optional<Car> carOptional = carService.getCarById(id);
		Map<String, Object> response = new HashMap<>();

		if (carOptional.isPresent()) {
			carService.deleteCar(id);

			response.put("message", "Car details deleted successfully");
			response.put("carId", id);
			return ResponseEntity.ok(response);
		} else {
			// If the car does not exist, return a NOT_FOUND status with a custom message
			response.put("message", "Car with ID " + id + " does not exist");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}
}
