# Concurrent Cinema Reservation System

## Overview

This Java project simulates a cinema seat reservation system with **concurrent access** using multiple threads. It features three separate theatres, each with 20 seats, and 100 customers (threads) attempting to reserve seats concurrently. The system uses `ReentrantLock` to ensure thread-safe operations, preventing race conditions and deadlocks during seat reservations.

## Features

* Three theatres, each with 20 seats
* 100 customer threads making reservations concurrently
* Seat reservation uses `ReentrantLock` for fine-grained thread control
* Prevents double booking and ensures atomic seat locking
* Displays final seat reservation status for all theatres

## Technologies Used

* Java
* `java.util.concurrent` package (Locks and ThreadPool)
* Multithreading and synchronization

## How It Works

Each `Customer` is a runnable thread that:

1. Randomly picks one of three theatres.
2. Randomly selects 1–3 unique seats to reserve.
3. Tries to reserve the selected seats using `tryLock()` on each one.
4. If all selected seat locks are acquired, the reservation succeeds.
5. If any lock fails, previously acquired locks are released and the reservation fails.

## How to Run

1. **Clone the Repository**

   ```bash
   git clone https://github.com/ColdTea15/CinemaReservationSystemConcurrent.git
   cd CinemaReservationSystemConcurrent
   ```

2. **Compile and Run**

   ```bash
   javac Cinema.java CinemaReservationSystem.java Customer.java
   java CinemaReservationSystem
   ```

## Output Sample

```
Customer 8 successfully reserved seats in Theatre 2: [3, 9]
Customer 15 failed to reserve seats in Theatre 1: [4, 5, 6]
...
Theatre 1 status:
Seat 1: Reserved
Seat 2: Available
...
```

## File Structure

```
.
├── Cinema.java                # Cinema and Theatre classes with seat locking logic
├── Customer.java              # Thread task representing a customer
├── CinemaReservationSystem.java # Entry point; manages thread pool and output
└── README.md                  # Project documentation
```

## License

This project is licensed under the MIT License.

## Author

* **GitHub**: [ColdTea15](https://github.com/ColdTea15)
