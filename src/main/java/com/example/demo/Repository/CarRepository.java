package com.example.demo.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.Car;

public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

	List<Car> findCarByname(String name);

	List<Car> findCarBymodel(String name);

	List<Car> findCarByyear(Integer name);

	@Query("SELECT c FROM Car c WHERE " + "(:name IS NULL OR UPPER(c.name) LIKE CONCAT('%', :name, '%')) AND "
			+ "(:model IS NULL OR UPPER(c.model) LIKE CONCAT('%', :model, '%')) AND "
			+ "(:year IS NULL OR c.year = :year) AND "
			+ "(:color IS NULL OR UPPER(c.color) LIKE CONCAT('%', :color, '%')) AND "
			+ "(:fuelType IS NULL OR UPPER(c.fuelType) LIKE CONCAT('%', :fuelType, '%'))")
	List<Car> findCarsByFilters(@Param("name") String name, @Param("model") String model, @Param("year") Integer year,
			@Param("color") String color, @Param("fuelType") String fuelType);

}
