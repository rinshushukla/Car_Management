package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	 @NotNull(message = "Name cannot be null")
	    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	    private String name;

	    @NotNull(message = "Model cannot be null")
	    @Size(min = 2, max = 50, message = "Model must be between 2 and 50 characters")
	    private String model;

	    @NotNull(message = "Year cannot be null")
	    @Min(value = 1886, message = "Year must be greater than or equal to 1886") // Cars were first made in 1886
	    @Max(value = 2100, message = "Year must be less than or equal to 2100") // Ensure the year is reasonable
	    private Integer year;

	    @NotNull(message = "Price cannot be null")
	    @Positive(message = "Price must be a positive number")
	    private Double price;

	    @NotNull(message = "Color cannot be null")
	    @Size(min = 3, max = 20, message = "Color must be between 3 and 20 characters")
	    private String color;

	    @NotNull(message = "Fuel Type cannot be null")
	    @Pattern(regexp = "^(PETROL|DIESEL|ELECTRIC|HYBRID|CNG)$", message = "Fuel type must be one of the following: PETROL, DIESEL, ELECTRIC, HYBRID,CNG")
	    private String fuelType;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	
		

}
