package Project;

import java.util.ArrayList;
import java.util.Scanner;

class Train {
    String name;
    String time;
    int passengerStrength;
    int trainNumber;

    public Train(String name, String time, int passengerStrength, int trainNumber) {
        this.name = name;
        this.time = time;
        this.passengerStrength = passengerStrength;
        this.trainNumber = trainNumber;
    }
}

class Booking {
    String passengerName;
    int trainNumber;
    String time;

    public Booking(String passengerName, int trainNumber, String time) {
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
        this.time = time;
    }
}

class ReservationSystem {

    private ArrayList<Train> availableTrains = new ArrayList<>();
    private ArrayList<Booking> bookedSeats = new ArrayList<>();

    public ReservationSystem() {
        availableTrains.add(new Train("Mumbai - Delhi", "13:05", 50, 1010));
        availableTrains.add(new Train("Delhi - Jaipur", "7:00", 50, 2013));
        availableTrains.add(new Train("Prayagraj - Delhi", "10:00", 50, 3045));
        availableTrains.add(new Train("Varanasi - Mumbai", "11:20", 50, 2003));
        availableTrains.add(new Train("Rajgir - New Delhi", "8:00", 50, 8621));
        availableTrains.add(new Train("New Delhi - Lucknow", "12:25", 50, 4562));
        availableTrains.add(new Train("Kolkata - Amritsar", "12:10", 50, 5683));
        availableTrains.add(new Train("Jaipur - Jodhpur", "22:50", 50, 3211));
        availableTrains.add(new Train("Bandra - Jodhpur", "19:00", 50, 5689));
        availableTrains.add(new Train("Hydrabad - Vishakapatnam", "17:15", 50, 9564));
        availableTrains.add(new Train("Patna - Shalimar", "20:40", 50, 2371));
        availableTrains.add(new Train("Dehradun - Mumbai", "5:00", 50, 1864));
    }

    public void displayAvailableTrains() {
        System.out.println("\nAvailable Trains:\n");

        System.out.printf("%-25s %-8s %-20s %-12s%n",
                "Train Name", "Time", "Passenger Strength", "Train Number");

        System.out.println("---------------------------------------------------------------------");

        for (Train train : availableTrains) {
            System.out.printf("%-25s %-8s %-20d %-12d%n",
                    train.name,
                    train.time,
                    train.passengerStrength,
                    train.trainNumber);
        }
    }

    public void checkSeatAvailability(int trainNumber) {
        for (Train train : availableTrains) {
            if (train.trainNumber == trainNumber) {

                int bookedCount = 0;
                for (Booking booking : bookedSeats) {
                    if (booking.trainNumber == trainNumber) {
                        bookedCount++;
                    }
                }

                int availableSeats = train.passengerStrength - bookedCount;
                System.out.println("Available seats on Train " + train.trainNumber + ": " + availableSeats);
                return;
            }
        }
        System.out.println("Train not found.");
    }

    public void bookTicket(int trainNumber, String passengerName) {
        for (Train train : availableTrains) {
            if (train.trainNumber == trainNumber) {

                int bookedCount = 0;
                for (Booking booking : bookedSeats) {
                    if (booking.trainNumber == trainNumber) {
                        bookedCount++;
                    }
                }

                if (bookedCount < train.passengerStrength) {
                    bookedSeats.add(new Booking(passengerName, train.trainNumber, train.time));
                    System.out.println("Ticket booked successfully for " + passengerName);
                } else {
                    System.out.println("Sorry, the train is fully booked.");
                }
                return;
            }
        }
        System.out.println("Train not found.");
    }

    public void cancelTicket(String passengerName) {
        for (Booking booking : bookedSeats) {
            if (booking.passengerName.equalsIgnoreCase(passengerName)) {
                bookedSeats.remove(booking);
                System.out.println("Ticket canceled successfully for " + passengerName);
                return;
            }
        }
        System.out.println("Passenger not found or no booking exists.");
    }

    public void displayBookedTickets() {
        if (bookedSeats.isEmpty()) {
            System.out.println("No tickets booked yet.");
            return;
        }

        System.out.printf("%-20s %-12s %-8s%n",
                "Passenger Name", "Train Number", "Time");

        System.out.println("------------------------------------------");

        for (Booking booking : bookedSeats) {
            System.out.printf("%-20s %-12d %-8s%n",
                    booking.passengerName,
                    booking.trainNumber,
                    booking.time);
        }
    }
}

class Main {
    public static void main(String[] args) {

        ReservationSystem reservationSystem = new ReservationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nRailway Reservation System Menu:");
            System.out.println("1. Display Available Trains");
            System.out.println("2. Check Seat Availability");
            System.out.println("3. Book a Ticket");
            System.out.println("4. Cancel a Ticket");
            System.out.println("5. Display Booked Tickets");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    reservationSystem.displayAvailableTrains();
                    break;

                case 2:
                    System.out.print("Enter Train Number: ");
                    int trainNumber = scanner.nextInt();
                    reservationSystem.checkSeatAvailability(trainNumber);
                    break;

                case 3:
                    System.out.print("Enter Train Number: ");
                    trainNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Passenger Name: ");
                    String passengerName = scanner.nextLine();
                    reservationSystem.bookTicket(trainNumber, passengerName);
                    break;

                case 4:
                    System.out.print("Enter Passenger Name to Cancel: ");
                    passengerName = scanner.nextLine();
                    reservationSystem.cancelTicket(passengerName);
                    break;

                case 5:
                    reservationSystem.displayBookedTickets();
                    break;

                case 6:
                    System.out.println("Exiting Railway Reservation System. Thank you!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}