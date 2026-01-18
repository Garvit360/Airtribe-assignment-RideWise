# RideWise - Ride Sharing Management System

A comprehensive Java-based ride-sharing application that demonstrates object-oriented design principles, design patterns, and clean architecture. The system allows riders to request rides, matches them with drivers using configurable strategies, and calculates fares dynamically.

## 🚀 Features

### Core Functionality

- **Rider Management**: Register and manage riders with location tracking
- **Driver Management**: Register drivers, track availability, and monitor completed rides
- **Ride Booking**: Request rides with automatic driver matching
- **Fare Calculation**: Dynamic fare calculation with multiple pricing strategies
- **Ride Lifecycle**: Complete ride lifecycle management (REQUESTED → ASSIGNED → COMPLETED/CANCELLED)
- **Search Functionality**: Search across riders, drivers, and rides using the Searchable interface
- **Receipt Generation**: Automatic fare receipt generation upon ride completion

### Advanced Features

- **Strategy Pattern Implementation**:
  - **Ride Matching Strategies**: Nearest Driver, Least Active Driver
  - **Fare Calculation Strategies**: Default Fare, Peak Hour Fare (1.5x multiplier)
- **Real-time Location Tracking**: GPS-based distance calculation using Haversine formula
- **Driver Activity Tracking**: Monitor driver performance and distribute rides fairly
- **Peak Hour Pricing**: Automatic fare adjustment during peak hours (7-9 AM, 5-7 PM)

## 📋 Prerequisites

- **Java**: JDK 8 or higher
- **IDE**: IntelliJ IDEA, Eclipse, or any Java IDE (optional)
- **Build Tool**: Maven or Gradle (optional, can run directly)

## 🏗️ Architecture

### Design Patterns Used

1. **Strategy Pattern**:
   - `RideMatchingStrategy` - Swappable driver matching algorithms
   - `FareCalculationStrategy` - Swappable fare calculation algorithms

2. **Service Layer Pattern**:
   - Separation of business logic from presentation layer
   - `RiderService`, `DriverService`, `RideService`

3. **Repository Pattern**:
   - `DataStore<T>` - Generic data storage abstraction

4. **Interface Segregation**:
   - `Searchable` interface for searchable entities

### Package Structure

```
org.example/
├── Main.java                 # Console application entry point
├── Demo.java                 # Comprehensive feature demonstration
├── model/                    # Domain entities
│   ├── Rider.java
│   ├── Driver.java
│   ├── Ride.java
│   ├── FareReceipt.java
│   ├── Location.java
│   ├── RideStatus.java       # Enum: REQUESTED, ASSIGNED, COMPLETED, CANCELLED
│   └── VehicleType.java      # Enum: BIKE, AUTO, CAR, BUS
├── service/                  # Business logic layer
│   ├── RiderService.java
│   ├── DriverService.java
│   └── RideService.java
├── strategy/                 # Strategy pattern implementations
│   ├── RideMatchingStrategy.java
│   ├── NearestDriverStrategy.java
│   ├── LeastActiveDriverStrategy.java
│   ├── FareCalculationStrategy.java
│   ├── DefaultFareStrategy.java
│   └── PeakHourFareStrategy.java
├── exception/                # Custom exceptions
│   ├── NoDriverAvailableException.java
│   ├── RideNotFoundException.java
│   ├── RiderNotFoundException.java
│   ├── DriverNotFoundException.java
│   └── InvalidDataException.java
├── util/                     # Utility classes
│   ├── DataStore.java
│   ├── IdGenerator.java
│   └── Validator.java
└── Interface/                # Interfaces
    └── Searchable.java
```

## 🚦 Getting Started

### Running the Application

#### Option 1: Run Main Application (Interactive Menu)

```bash
# Compile
javac -d out src/main/java/org/example/**/*.java

# Run
java -cp out org.example.Main
```

#### Option 2: Run Demo (Automated Demonstration)

```bash
java -cp out org.example.Demo
```

### Using the Application

#### Main Menu Options

1. **Add Rider** - Register a new rider with name, email, phone, and location
2. **Add Driver** - Register a new driver with availability status
3. **View Available Drivers** - List all currently available drivers
4. **View All Drivers** - Display all registered drivers
5. **View All Riders** - Display all registered riders
6. **Request Ride** - Book a ride with pickup/dropoff locations and vehicle type
7. **Complete Ride** - Complete an assigned ride and generate receipt
8. **Cancel Ride** - Cancel an assigned ride
9. **View All Rides** - Display all rides with their status
10. **Search Riders** - Search riders by name, email, phone, or ID
11. **Search Drivers** - Search drivers by name, email, phone, or ID
12. **Search Rides** - Search rides by ID, rider name, driver name, status, or vehicle type
13. **Change Matching Strategy** - Switch between Nearest/Least Active driver strategies
14. **Change Fare Strategy** - Switch between Default/Peak Hour fare strategies
15. **View Ride Details** - View detailed information about a specific ride

## 🎯 Design Principles

### SOLID Principles

- **Single Responsibility Principle (SRP)**: Each class has a single, well-defined responsibility
- **Open/Closed Principle (OCP)**: Open for extension (new strategies), closed for modification
- **Liskov Substitution Principle (LSP)**: Strategy implementations are interchangeable
- **Interface Segregation Principle (ISP)**: Focused interfaces like `Searchable`
- **Dependency Inversion Principle (DIP)**: High-level modules depend on abstractions (interfaces)

### Additional Principles

- **Composition over Inheritance**: Strategies are composed, not inherited
- **Fail-Fast Validation**: Input validation at service layer
- **Separation of Concerns**: Clear separation between model, service, and presentation layers
