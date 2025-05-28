import java.util.concurrent.locks.*;

class Cinema {
    private final int theatresCount = 3;
    private final int seatsCount = 20;
    private final Theatre[] theatres;

    public Cinema() {
        theatres = new Theatre[theatresCount];
        for (int i = 0; i < theatresCount; i++) {
            theatres[i] = new Theatre(seatsCount);
        }
    }

    public Theatre getTheatre(int theatreNumber) {
        return theatres[theatreNumber - 1]; // Adjust for 1-based index
    }

    public void printAllTheatresStatus() {
        for (int i = 0; i < theatresCount; i++) {
            System.out.println("Theatre " + (i + 1) + " status:");
            theatres[i].printSeatsStatus();
            System.out.println();
        }
    }
}

class Theatre {
    private final int seatsCount;
    private final ReentrantLock[] seats;
    private final boolean[] seatStatus;

    public Theatre(int seatsCount) {
        this.seatsCount = seatsCount;
        seats = new ReentrantLock[seatsCount];
        seatStatus = new boolean[seatsCount];
        for (int i = 0; i < seatsCount; i++) {
            seats[i] = new ReentrantLock();
            seatStatus[i] = false;
        }
    }

    public boolean reserveSeats(int[] seatNumbers) {
        for (int seat : seatNumbers) {
            if (!seats[seat - 1].tryLock()) { // Adjust for 1-based index
                // Release already locked seats
                for (int s : seatNumbers) {
                    if (seats[s - 1].isHeldByCurrentThread()) {
                        seats[s - 1].unlock();
                    }
                }
                return false;
            }
        }
        // Mark seats as reserved
        for (int seat : seatNumbers) {
            seatStatus[seat - 1] = true; // Adjust for 1-based index
        }
        return true;
    }

    public void printSeatsStatus() {
        for (int i = 0; i < seatsCount; i++) {
            System.out.println("Seat " + (i + 1) + ": " + (seatStatus[i] ? "Reserved" : "Available"));
        }
    }
}
