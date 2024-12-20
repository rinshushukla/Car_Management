package com.example.demo.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.example.demo.ExceptionHanler.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Car;
import com.example.demo.Repository.CarRepository;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;

	public List<Car> getAllCars(Pageable pageable, String sortBy, String sortDir) {

		Sort sort = null;

		// Validating sortBy value
		if (!Objects.isNull(sortBy)) {
			if (Objects.isNull(sortDir)) {
				throw new CustomException("sortDir value is required");
			}
			if (!Arrays.asList("color", "name", "model", "year", "price", "fuelType").contains(sortBy)) {
				throw new CustomException(
						"sortBy value should be either : color | name | model | year | price | fuelType");
			}
			if (!Arrays.asList("AESC", "DESC").contains(sortDir)) {
				throw new CustomException("sortDir value should be either : AESC | DESC ");
			}
			if (Objects.equals(sortDir, "AESC")) {
				sort = Sort.by(sortBy).ascending();
			} else {
				sort = Sort.by(sortBy).descending();
			}
			pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
		}

		Page<Car> cars = carRepository.findAll(pageable);

		return cars.stream().toList();
	}

	public Optional<Car> getCarById(Long id) {
		return carRepository.findById(id);
	}

	public List<Car> getCarByname(String name) {
		return carRepository.findCarByname(name);

	}

	public List<Car> getCarBymodel(String model) {
		return carRepository.findCarBymodel(model);

	}

	public List<Car> getCarByyear(Integer year) {
		return carRepository.findCarByyear(year);

	}

	public Car saveCar(Car car) {
		return carRepository.save(car);
	}

	public List<Car> getCarsByFilters(Pageable pageable, String name, String model, Integer year, String color, String fuelType) {
	    Specification<Car> specification = Specification.where(null);

	    if (name != null && !name.isEmpty()) {
	        specification = specification.and((root, query, criteriaBuilder) -> 
	            criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name.toUpperCase() + "%"));
	    }

	    if (model != null && !model.isEmpty()) {
	        specification = specification.and((root, query, criteriaBuilder) -> 
	            criteriaBuilder.like(criteriaBuilder.upper(root.get("model")), "%" + model.toUpperCase() + "%"));
	    }

	    if (year != null) {
	        specification = specification.and((root, query, criteriaBuilder) -> 
	            criteriaBuilder.equal(root.get("year"), year));
	    }

	    if (color != null && !color.isEmpty()) {
	        specification = specification.and((root, query, criteriaBuilder) -> 
	            criteriaBuilder.like(criteriaBuilder.upper(root.get("color")), "%" + color.toUpperCase() + "%"));
	    }

	    if (fuelType != null && !fuelType.isEmpty()) {
	        specification = specification.and((root, query, criteriaBuilder) -> 
	            criteriaBuilder.like(criteriaBuilder.upper(root.get("fuelType")), "%" + fuelType.toUpperCase() + "%"));
	    }

	    return carRepository.findAll(specification, pageable).getContent();
	}
	public List<Car> saveAllCars(List<Car> cars) {
		return carRepository.saveAll(cars);
	}

	public void deleteCar(Long id) {
		carRepository.deleteById(id);
	}

	@Override
	public List<Car> FindByColour(String color) {
		// TODO Auto-generated method stub
		return null;
	}



//	@Override
//	public List<Car> FindByColour(String color) {
//		// TODO Auto-generated method stub
//		carRepository.find
//		
//	}
}
