import java.util.*;

class Customer implements Runnable {
    private final Cinema cinema;
    private final int id;
    private static final Random random = new Random();

    public Customer(Cinema cinema, int id) {
        this.cinema = cinema;
        this.id = id;
    }
    //System.out.println("Customer " + id + " is trying to reserve seats in Theatre " + theatreNumber + ": " + Arrays.toString(seatNumbers));

    @Override
    public void run() {
        int theatreNumber = random.nextInt(3) + 1; // Randomly choose a theatre (1 to 3)
        Theatre theatre = cinema.getTheatre(theatreNumber);

        // Ensure unique seat numbers
        Set<Integer> seatSet = new HashSet<>();
        int seatsToReserve = random.nextInt(3) + 1;
        while (seatSet.size() < seatsToReserve) {
            seatSet.add(random.nextInt(20) + 1); // Adjust for 1-based index
        }
        int[] seatNumbers = seatSet.stream().mapToInt(Number::intValue).toArray();

        if (theatre.reserveSeats(seatNumbers)) {
            try {
                Thread.sleep(random.nextInt(501) + 500);
                System.out.println("Customer " + id 
                + " successfully reserved seats in Theatre " 
                + theatreNumber + ": " + Arrays.toString(seatNumbers));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Customer " + id 
            + " failed to reserve seats in Theatre " 
            + theatreNumber + ": " + Arrays.toString(seatNumbers));
        }
    }
}
