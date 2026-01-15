📘 Secure Rental & Booking System

 DAO with EntityManager & Spring Security

📌 Project Overview

This project is a Spring Boot REST API developed for managing a Car Rental & Booking System.
It allows:

Public users to browse available vehicles

Registered users to view bookings

Administrators to manage vehicles and bookings securely

The application demonstrates:

JPA entity relationships (One-to-Many)

EntityManager-based DAO layer

Business logic in Service layer

Spring Security with Basic Authentication

RESTful API design

🛠 Technologies Used

Java 17

Spring Boot

Spring Web

Spring Data JPA (Hibernate)

Spring Security

H2 In-Memory Database

Maven

📂 Project Architecture
controller  →  service  →  dao (EntityManager)  →  database


Packages:

entity – JPA entities

dao – Persistence logic using EntityManager

service – Business logic

controller – REST API endpoints

security – Spring Security configuration


🧩 Mapping & Persistence
1️⃣ Booking Entity & Foreign Key Naming
Booking Entity Code
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private LocalDate bookingDate;
    private Integer numberOfDays;

    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonIgnore
    private Vehicle vehicle;

    // getters and setters
}

🔍 Explanation

@ManyToOne defines the relationship:
Many bookings belong to one vehicle

@JoinColumn(name = "car_id")
✅ Ensures the foreign key column in the database is named car_id

@JsonIgnore
✅ Prevents infinite JSON serialization loops

2️⃣ JPQL Query: Most Expensive Vehicle
DAO Implementation
@Repository
public class VehiculeDAO {

    @PersistenceContext
    private EntityManager em;

    public Vehicle findMostExpensiveVehicle() {
        return em.createQuery(
            "SELECT v FROM Vehicle v ORDER BY v.dailyRate DESC",
            Vehicle.class
        )
        .setMaxResults(1)
        .getSingleResult();
    }
}

🔍 Explanation

JPQL uses entity names, not table names

ORDER BY v.dailyRate DESC → highest price first

setMaxResults(1) → returns only the most expensive vehicle

🔐 Task 2: The Security Layer
Security Configuration Requirements

✔ Permit all access to GET /api/vehicles
✔ Require ADMIN role for DELETE /api/vehicles/**
✔ Use HTTP Basic Authentication

✅ SecurityFilterChain Configuration
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/api/vehicles").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/vehicles/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager users() {
        return new InMemoryUserDetailsManager(
            User.withUsername("user")
                .password("{noop}1234")
                .roles("USER")
                .build(),
            User.withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build()
        );
    }
}

🔍 Explanation

httpBasic() → enables Basic Authentication

permitAll() → public access to vehicle listing

hasRole("ADMIN") → restricts delete operations

In-memory users used for simplicity (exam requirement)

🌐 Task 3: API Design
1️⃣ Specialized Endpoint

GET /api/vehicles/{id}/bookings

Controller Implementation
@RestController
@RequestMapping("/api/vehicles")
public class VehiculeController {

    private final BookingService bookingService;

    public VehiculeController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/{id}/bookings")
    public List<Booking> getBookings(@PathVariable Long id) {
        return bookingService.getBookingsByVehicle(id);
    }
}

2️⃣ DAO Method Used
public List<Booking> findBookingsByVehicle(Long vehicleId) {
return em.createQuery(
"SELECT b FROM Booking b WHERE b.vehicle.id = :id",
Booking.class
)
.setParameter("id", vehicleId)
.getResultList();
}

🔍 Explanation

Retrieves all bookings linked to a specific vehicle

Uses JPQL navigation: b.vehicle.id

⭐ Bonus: Prevent Infinite JSON Loop

Two valid solutions:

Option 1 (Used)
@JsonIgnore
@ManyToOne
@JoinColumn(name = "car_id")
private Vehicle vehicle;

Option 2
@JsonBackReference


✅ Prevents:

Vehicle → Booking → Vehicle → Booking → ...

🧪 Database Access (H2)

URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:vehicledb

Username: sa

Password: (empty)

🎯 Conclusion

This project fulfills :

✔ JPA One-to-Many relationship
✔ Cascade delete behavior
✔ DAO with EntityManager
✔ JPQL queries
✔ Business rules in Service layer
✔ Secure REST API with Spring Security
