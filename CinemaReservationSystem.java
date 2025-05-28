import java.util.concurrent.*;
public class CinemaReservationSystem {
    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        ExecutorService executor = Executors.newFixedThreadPool(100);

        for (int i = 1; i <= 100; i++) {
            executor.execute(new Customer(cinema, i));
        }

        executor.shutdown();

        // Wait for all threads to finish
        while (!executor.isTerminated()) {
            // Busy-wait loop
        }

        // Print final status of all theatres
        cinema.printAllTheatresStatus();
    }
}

