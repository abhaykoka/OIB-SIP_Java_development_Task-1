import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineReservation {
    private Map<String, String> users; // For storing usernames and passwords
    private Map<String, String> reservations; // For storing reservation data

    public OnlineReservation() {
        users = new HashMap<>();
        reservations = new HashMap<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Welcome to the Online Reservation System ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Please choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    register(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using the Online Reservation System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void login(Scanner scanner) {
        System.out.println("\n=== Login ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("Login successful!");
            reservationMenu(scanner, username);
        } else {
            System.out.println("Incorrect username or password. Please try again.");
        }
    }

    private void register(Scanner scanner) {
        System.out.println("\n=== Register ===");
        System.out.print("Choose a username: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already taken. Please try a different one.");
            return;
        }

        System.out.print("Choose a password: ");
        String password = scanner.nextLine();
        users.put(username, password);
        System.out.println("Registration successful! You can now log in.");
    }

    private void reservationMenu(Scanner scanner, String username) {
        while (true) {
            System.out.println("\n=== Reservation Menu ===");
            System.out.println("1. Make a Reservation");
            System.out.println("2. Cancel a Reservation");
            System.out.println("3. Logout");
            System.out.print("Please choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    makeReservation(scanner, username);
                    break;
                case 2:
                    cancelReservation(scanner, username);
                    break;
                case 3:
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void makeReservation(Scanner scanner, String username) {
        if (reservations.containsKey(username)) {
            System.out.println("You already have a reservation. Please cancel it before making a new one.");
            return;
        }

        System.out.print("Enter reservation details: ");
        String reservationDetails = scanner.nextLine();
        reservations.put(username, reservationDetails);
        System.out.println("Reservation made successfully!");
    }

    private void cancelReservation(Scanner scanner, String username) {
        if (reservations.containsKey(username)) {
            System.out.println("Your current reservation: " + reservations.get(username));
            System.out.print("Are you sure you want to cancel this reservation? (Y/N): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("Y")) {
                reservations.remove(username);
                System.out.println("Reservation canceled successfully.");
            } else {
                System.out.println("Reservation not canceled.");
            }
        } else {
            System.out.println("You have no reservations to cancel.");
        }
    }

    public static void main(String[] args) {
        OnlineReservation reservationSystem = new OnlineReservation();
        reservationSystem.start();
    }
}
