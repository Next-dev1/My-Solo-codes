import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String ADMIN_FILE = "admin_accounts.txt";
    private static final String GUEST_USAGE_FILE = "guest_usage.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NotificationManager notificationManager = new NotificationManager();
        EventManager eventManager = new EventManager(notificationManager); // Instantiate EventManager object

        // Load admin accounts
        List<User> adminAccounts = loadAdminAccounts();

        System.out.println("Welcome to the Event Registration System!");

        boolean validChoice = false;
        int choice = 0;
        while (!validChoice) {
            try {
                System.out.println("Are you an admin or a guest?");
                System.out.println("1. Admin");
                System.out.println("2. Guest");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1 || choice == 2) {
                    validChoice = true;
                } else if (choice == 3) {
                    System.out.println("Exiting...");
                    return;
                } else {
                    System.out.println("Invalid choice. Please enter 1 for admin, 2 for guest, or 3 to exit.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        if (choice == 1) {
            boolean loggedIn = false;
            while (!loggedIn) {
                try {
                    System.out.println("1. Login");
                    System.out.println("2. Register");
                    System.out.println("3. Cancel");
                    System.out.print("Enter your choice: ");
                    int adminChoice = Integer.parseInt(scanner.nextLine());

                    if (adminChoice == 1) {
                        // Admin login
                        loggedIn = adminLogin(scanner, adminAccounts);
                    } else if (adminChoice == 2) {
                        // Admin registration
                        adminRegistration(scanner, adminAccounts);
                    } else if (adminChoice == 3) {
                        System.out.println("Exiting admin section...");
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter 1 to login, 2 to register, or 3 to cancel.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
        } else if (choice == 2) {
            // Guest login
            guestLogin(scanner, eventManager);
        } else {
            System.out.println("Invalid choice. Exiting...");
        }
    }

    // Admin login
    // Admin login
private static boolean adminLogin(Scanner scanner, List<User> adminAccounts) {
    while (true) {
        try {
            System.out.println("Enter 'cancel' at any prompt to cancel.");
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            if (username.equalsIgnoreCase("cancel")) {
                System.out.println("Cancellation detected. Exiting admin login...");
                return false;
            }
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            if (password.equalsIgnoreCase("cancel")) {
                System.out.println("Cancellation detected. Exiting admin login...");
                return false;
            }
            for (User admin : adminAccounts) {
                if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                    System.out.println("Welcome, " + username + "! You are logged in as an admin.");
                    admin.setLastLogin(LocalDateTime.now());
                    return true;
                }
            }
            System.out.println("Invalid username or password. Access denied.");
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
        }
    }
}


    // Admin registration
    private static void adminRegistration(Scanner scanner, List<User> adminAccounts) {
        while (true) {
            try {
                System.out.print("Enter a username: ");
                String username = scanner.nextLine();
                if (username.equals("cancel")) {
                    System.out.println("Cancellation detected. Exiting admin registration...");
                    break;
                }
                System.out.print("Enter a password: ");
                String password = scanner.nextLine();
                if (password.equals("cancel")) {
                    System.out.println("Cancellation detected. Exiting admin registration...");
                    break;
                }
                adminAccounts.add(new User(username, password, "admin"));
                saveAdminAccounts(adminAccounts);
                System.out.println("Admin account created successfully.");
                break;
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
            }
        }
    }

    // Guest login
   // Guest login
private static void guestLogin(Scanner scanner, EventManager eventManager) {
    System.out.println("Welcome, guest!");
    // Increment guest usage count
    int guestUsage = incrementGuestUsage();
    System.out.println("Number of times guests logged in: " + guestUsage);

    // Guest functionalities (e.g., event registration)
    // Sample event registration
    System.out.println("Available Events:");
    List<Event> events = eventManager.getEvents(); // Retrieve events from EventManager
    for (int i = 0; i < events.size(); i++) {
        System.out.println((i + 1) + ". " + events.get(i).getName());
    }
    System.out.print("Select an event to register (enter the event number): ");
    try {
        String eventNumberInput = scanner.nextLine();
        if (eventNumberInput != null && !eventNumberInput.isEmpty()) {
            int eventNumber = Integer.parseInt(eventNumberInput);
            if (eventNumber >= 1 && eventNumber <= events.size()) {
                Event selectedEvent = events.get(eventNumber - 1);
                System.out.println("You have selected the event: " + selectedEvent.getName());
                System.out.println("Please provide your personal details:");
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Age: ");
                int age = Integer.parseInt(scanner.nextLine());
                System.out.print("Course/Section: ");
                String courseSection = scanner.nextLine();
                System.out.print("School Email: ");
                String email = scanner.nextLine();
                System.out.print("School ID: ");
                String schoolId = scanner.nextLine();
                // Register the guest for the event (you can add this logic)
                System.out.println("Thank you for registering for the event!");
            } else {
                System.out.println("Invalid event number. Registration canceled.");
            }
        } else {
            System.out.println("Invalid event number. Registration canceled.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid event number.");
    }
}


    // Load admin accounts from file
    private static List<User> loadAdminAccounts() {
        List<User> adminAccounts = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ADMIN_FILE))) {
            adminAccounts = (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions
            e.printStackTrace();
        }
        return adminAccounts;
    }

    // Save admin accounts to file
    private static void saveAdminAccounts(List<User> adminAccounts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ADMIN_FILE))) {
            oos.writeObject(adminAccounts);
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    // Increment guest usage count and save to file
   // Increment guest usage count and save to file
private static int incrementGuestUsage() {
    int guestUsage = 0;
    try (BufferedReader reader = new BufferedReader(new FileReader(GUEST_USAGE_FILE))) {
        String line = reader.readLine();
        if (line != null && !line.isEmpty()) {
            guestUsage = Integer.parseInt(line);
        }
    } catch (IOException e) {
        // Handle exceptions
        e.printStackTrace();
    }
    guestUsage++;
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(GUEST_USAGE_FILE))) {
        writer.write(String.valueOf(guestUsage));
    } catch (IOException e) {
        // Handle exceptions
        e.printStackTrace();
    }
    return guestUsage;
}
}
