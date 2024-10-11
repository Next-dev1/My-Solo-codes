import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Define Event class to represent each event
class Event implements Serializable {
    private String name;
    private String description;
    private String date;
    private String time;
    private String location;
    private User organizer; // Added organizer field

    public Event(String name, String description, String date, String time, String location, User organizer) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
        this.organizer = organizer;
    }

    // Getters and setters
    // You can also add methods for additional functionalities
    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public User getOrganizer() {
        return organizer;
    }
}

// Define User class to represent users
class User implements Serializable {
    private String username;
    private String password;
    private String role; // Role of the user: "admin" or "guest"
    private LocalDateTime lastLogin; // Time of last login
    private int guestUsage; // Number of times a guest logged in
    // Add more user-related fields as needed

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and setters
    // You can also add methods for additional functionalities

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getGuestUsage() {
        return guestUsage;
    }

    public void incrementGuestUsage() {
        guestUsage++;
    }
}

// Define Notification class
class Notification {
    private String message;
    private User recipient;
    private Event event;
    private LocalDateTime timestamp;

    public Notification(String message, User recipient, Event event, LocalDateTime timestamp) {
        this.message = message;
        this.recipient = recipient;
        this.event = event;
        this.timestamp = timestamp;
    }

    // Getters and setters
    // You can also add methods for additional functionalities
    public String getMessage() {
        return message;
    }
}

// Define NotificationManager class
class NotificationManager {
    public void sendNotification(Notification notification) {
        // Implement logic to send notification (e.g., via email, push notification)
        System.out.println("Notification sent: " + notification.getMessage());
    }

    // Additional methods for managing notifications
}

// Define EventManager class to manage events
class EventManager {
    private List<Event> events;
    private NotificationManager notificationManager;
    private final String FILE_PATH = "events.txt";

    public EventManager(NotificationManager notificationManager) {
        this.events = new ArrayList<>();
        this.notificationManager = notificationManager;
        loadEventsFromFile(); // Load events from file when EventManager is initialized
    }

    public void addEvent(Event event) {
        events.add(event);
        saveEventsToFile(); // Save events to file after adding a new event

        // Schedule notifications for the new event
        scheduleEventNotifications(event);
    }

    private void scheduleEventNotifications(Event event) {
        // Implement logic to schedule notifications for the event
        // For example, you could send a notification one day before the event
        LocalDateTime notificationTime = LocalDateTime.parse(event.getDate() + "T" + event.getTime())
                .minusDays(1); // Send notification one day before the event
        Notification notification = new Notification("Upcoming event: " + event.getName(), event.getOrganizer(), event, notificationTime);
        notificationManager.sendNotification(notification);
    }

    private void loadEventsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            events = (List<Event>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    private void saveEventsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(events);
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    public List<Event> getEvents() {
        return events;
    }

    // Additional methods for managing events (e.g., filtering, searching)
}

