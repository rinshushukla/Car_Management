# 🚗 Car Management System

Welcome to the **Car Management System**! 🚘  
This application is built using **Spring Boot** and provides a set of REST APIs to manage cars, including creating, updating, deleting, and fetching car details. The system allows users to store and retrieve car details, with validations on fields like price, year, and fuel type. 

## Features 🌟
- **Create Car:** Add a new car to the system. 🆕
- **Get Cars:** Retrieve a list of cars, with filters for name, model, and year. 🔍
- **Update Car:** Modify car details, including name, model, price, color, fuel type, and year. ✏️
- **Delete Car:** Remove a car from the system by its ID. 🗑️
- **Search by Name/Model/Year:** Filter cars based on their name, model, and year. 🔎

## Technologies Used ⚙️
- **Java 17** (or higher) ☕
- **Spring Boot 2.x** 🚀
- **Spring Data JPA** (for database interaction) 🗃️
- **H2 Database** (used as an in-memory database for simplicity) 💾
- **Maven** (for dependency management and building the project) 🔧
- **Validation annotations** for input validation ✅

## Setup Instructions 🛠️

### Prerequisites 📋

Before setting up the project, ensure that you have the following installed:

- **Java 17** (or higher) ☕
- **Maven** (for dependency management) 📦
- **IDE (IntelliJ IDEA, Eclipse, etc.) 💻**

### Steps to Run the Project 🏃‍♂️

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/car-management-system.git
   cd car-management-system

2. **Build the project: Use Maven to build the project.**
    ```bash
		mvn clean install
3.**Run the application: Start the Spring Boot application:**
    ```bash
	mvn spring-boot:run
 
4.**Access the API: Once the application is running, you can access the API at:**
  ```bash
http://localhost:8080



