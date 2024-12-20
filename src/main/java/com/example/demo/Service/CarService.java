package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {
	public List<Car> getAllCars(Pageable pageable, String sortBy, String sortDir);

	public Optional<Car> getCarById(Long id);

	public List<Car> getCarByname(String name);

	public List<Car> getCarBymodel(String model);

	public List<Car> getCarByyear(Integer year);

	public Car saveCar(Car car);

	public List<Car> getCarsByFilters(Pageable pageable,String name, String model, Integer year, String color, String fuelType);

	public List<Car> saveAllCars(List<Car> cars);

	public void deleteCar(Long id);
	
	public List<Car> FindByColour(String color);

}
