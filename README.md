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

#### Main Menu Options:
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

## 💡 Usage Examples

### Registering a Rider
```
Enter rider name: Rajesh Kumar
Enter email: rajesh.kumar@email.com
Enter phone: 98765-43210
Enter latitude: 28.6139
Enter longitude: 77.2090
```

### Requesting a Ride
```
Enter rider ID: RIDER1
Pickup Location:
  Latitude: 28.6139
  Longitude: 77.2090
Drop-off Location:
  Latitude: 28.7041
  Longitude: 77.1025
Enter vehicle type: CAR
```

### Completing a Ride
```
Enter ride ID: RIDE-1
✓ Ride completed successfully!
--- Fare Receipt ---
Receipt ID: RECEIPT-1
Ride ID: RIDE-1
Amount: ₹125.50
Generated At: 2024-01-15 14:30:00
```

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

## 📊 Fare Calculation

### Default Fare Strategy
- **BIKE**: ₹2.0 per km
- **AUTO**: ₹3.5 per km
- **CAR**: ₹5.0 per km
- **BUS**: ₹1.5 per km

### Peak Hour Fare Strategy
- Applies **1.5x multiplier** during peak hours:
  - Morning: 7:00 AM - 9:00 AM
  - Evening: 5:00 PM - 7:00 PM
- Uses default rates during non-peak hours

## 🔍 Search Functionality

All model classes (`Rider`, `Driver`, `Ride`, `FareReceipt`) implement the `Searchable` interface, allowing:
- Search by name
- Search by email
- Search by phone
- Search by ID
- Search by status (for rides)
- Search by vehicle type (for rides)

## 🧪 Testing

Run the `Demo` class to see a comprehensive demonstration of all features:
- Automatic rider and driver registration
- Multiple ride requests
- Strategy switching
- Ride completion and receipt generation
- Search functionality
- Statistics and summaries

## 📝 Code Quality

- **Clean Code**: Meaningful variable names, proper comments
- **Error Handling**: Comprehensive exception handling
- **Validation**: Input validation at multiple layers
- **Documentation**: JavaDoc comments for public methods
- **Type Safety**: Strong typing throughout

## 🔧 Extensibility

### Adding New Matching Strategies
1. Implement `RideMatchingStrategy` interface
2. Override `findDriver()` method
3. Inject into `RideService` constructor

### Adding New Fare Strategies
1. Implement `FareCalculationStrategy` interface
2. Override `calculateFare()` method
3. Inject into `RideService` constructor

### Example: Adding a Premium Fare Strategy
```java
public class PremiumFareStrategy implements FareCalculationStrategy {
    @Override
    public double calculateFare(Ride ride) {
        // Premium pricing logic
        return baseFare * 2.0; // 2x multiplier
    }
}
```

## 📈 Future Enhancements

- Database persistence layer
- RESTful API endpoints
- Real-time notifications
- Payment gateway integration
- Rating and review system
- Route optimization
- Multi-city support
- Admin dashboard
- Analytics and reporting

## 👥 Contributing

This is a demonstration project showcasing:
- Object-Oriented Design
- Design Patterns (Strategy, Service Layer, Repository)
- SOLID Principles
- Clean Architecture
- Java Best Practices

## 📄 License

This project is for educational and demonstration purposes.

## 🙏 Acknowledgments

- Design patterns inspired by Gang of Four (GoF)
- Architecture principles from Clean Architecture by Robert C. Martin

---

**Built with ❤️ using Java and Design Patterns**

