# Java Design Patterns - Complete Guide with Code Examples

## Understanding Design Patterns: The Language of Software Architecture

Design patterns are like blueprints for solving common software design problems. Think of them as proven recipes that experienced developers have refined over decades of building software. Just as a chef doesn't reinvent how to make bread every time they bake, software developers use design patterns to solve recurring design challenges without starting from scratch.

**The fundamental insight:** Every piece of software faces similar challenges - how to create objects efficiently, how to structure relationships between classes, how to handle changing requirements, and how to coordinate complex behaviors. Design patterns capture the wisdom of how to handle these challenges elegantly.

**Why patterns matter in Java:** Java's object-oriented nature makes it particularly well-suited for design patterns. The language's features like interfaces, inheritance, and polymorphism provide the tools needed to implement these patterns effectively. Understanding patterns will transform how you think about software design and help you write more maintainable, flexible code.

## Creational Patterns: Mastering Object Creation

Creational patterns focus on how objects are created, ensuring that object creation is flexible and doesn't create unnecessary dependencies in your code. These patterns become essential as your applications grow more complex and you need sophisticated ways to manage object lifecycles.

### Singleton Pattern: Ensuring One Instance Rules Them All

The Singleton pattern ensures that a class has only one instance and provides global access to that instance. This pattern is like having a single CEO in a company - there can be only one, and everyone needs to be able to reach them when necessary.

**When you need Singleton:** Database connections, logging services, configuration managers, cache systems, and thread pools are common candidates. These are resources that should exist only once in your application and need to be accessible from multiple parts of your code.

```java
/**
 * Thread-safe Singleton implementation using enum (recommended approach)
 * This leverages Java's guarantee that enum values are created only once
 * and are inherently thread-safe.
 */
public enum DatabaseConnection {
    INSTANCE;
    
    private Connection connection;
    
    // This method will be called only once when the enum is first accessed
    DatabaseConnection() {
        try {
            // Initialize the database connection
            this.connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb", "user", "password");
            System.out.println("Database connection created");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public void executeQuery(String query) {
        // Example method showing how the singleton can encapsulate behavior
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            // Process results...
        } catch (SQLException e) {
            System.err.println("Query execution failed: " + e.getMessage());
        }
    }
}

// Usage example - notice how we get the same instance everywhere
class OrderService {
    public void processOrder(int orderId) {
        // Same instance will be returned every time
        DatabaseConnection.INSTANCE.executeQuery("SELECT * FROM orders WHERE id = " + orderId);
    }
}

class UserService {
    public void getUserProfile(int userId) {
        // This is the exact same database connection instance
        DatabaseConnection.INSTANCE.executeQuery("SELECT * FROM users WHERE id = " + userId);
    }
}
```

**Traditional implementation with lazy initialization and double-checked locking:**

```java
/**
 * Classic Singleton with double-checked locking
 * This shows the complexity involved in making a traditional singleton thread-safe
 */
public class ConfigurationManager {
    // volatile ensures that changes to instance are visible across all threads
    private static volatile ConfigurationManager instance;
    private Properties configuration;
    
    // Private constructor prevents direct instantiation
    private ConfigurationManager() {
        configuration = new Properties();
        loadConfiguration();
        System.out.println("Configuration manager initialized");
    }
    
    public static ConfigurationManager getInstance() {
        // First check - if instance exists, return it (no synchronization needed)
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                // Second check - maybe another thread created the instance while we were waiting
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }
    
    private void loadConfiguration() {
        // Simulate loading configuration from file
        configuration.setProperty("app.name", "MyApplication");
        configuration.setProperty("app.version", "1.0.0");
        configuration.setProperty("database.url", "localhost:3306");
    }
    
    public String getProperty(String key) {
        return configuration.getProperty(key);
    }
    
    public void setProperty(String key, String value) {
        configuration.setProperty(key, value);
    }
}
```

**Understanding the complexity:** The double-checked locking pattern shows why the enum approach is preferred. The traditional approach requires careful handling of thread safety, volatile keywords, and synchronization blocks. The enum approach leverages Java's built-in guarantees to achieve the same result with much simpler code.

### Factory Pattern: Delegating Object Creation

The Factory pattern encapsulates object creation logic, allowing you to create objects without specifying their exact classes. Think of it like ordering a car from a dealership - you specify what type of car you want, but you don't need to know the intricate details of how it's manufactured.

**The design principle:** This pattern follows the Open/Closed Principle - your code is open for extension (you can add new product types) but closed for modification (existing code doesn't need to change).

```java
/**
 * Abstract base class for all notification types
 * This defines the contract that all notifications must follow
 */
abstract class Notification {
    protected String recipient;
    protected String message;
    
    public Notification(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }
    
    // Template method - defines the notification process
    public final void send() {
        if (validateRecipient()) {
            deliverMessage();
            logNotification();
        }
    }
    
    // Abstract method - each notification type implements this differently
    protected abstract void deliverMessage();
    
    protected boolean validateRecipient() {
        return recipient != null && !recipient.trim().isEmpty();
    }
    
    protected void logNotification() {
        System.out.println("Notification sent to: " + recipient);
    }
}

/**
 * Concrete implementation for email notifications
 */
class EmailNotification extends Notification {
    private String subject;
    
    public EmailNotification(String recipient, String message, String subject) {
        super(recipient, message);
        this.subject = subject;
    }
    
    @Override
    protected void deliverMessage() {
        System.out.println("Sending email to: " + recipient);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
        // In real implementation, this would integrate with email service
    }
    
    @Override
    protected boolean validateRecipient() {
        // Email-specific validation
        return super.validateRecipient() && recipient.contains("@");
    }
}

/**
 * Concrete implementation for SMS notifications
 */
class SMSNotification extends Notification {
    public SMSNotification(String recipient, String message) {
        super(recipient, message);
    }
    
    @Override
    protected void deliverMessage() {
        System.out.println("Sending SMS to: " + recipient);
        // Truncate message if it's too long for SMS
        String smsMessage = message.length() > 160 ? 
            message.substring(0, 157) + "..." : message;
        System.out.println("Message: " + smsMessage);
        // In real implementation, this would integrate with SMS service
    }
    
    @Override
    protected boolean validateRecipient() {
        // Phone number validation
        return super.validateRecipient() && recipient.matches("\\+?[0-9\\-\\s()]+");
    }
}

/**
 * Concrete implementation for push notifications
 */
class PushNotification extends Notification {
    private String appId;
    
    public PushNotification(String recipient, String message, String appId) {
        super(recipient, message);
        this.appId = appId;
    }
    
    @Override
    protected void deliverMessage() {
        System.out.println("Sending push notification to device: " + recipient);
        System.out.println("App ID: " + appId);
        System.out.println("Message: " + message);
        // In real implementation, this would integrate with push notification service
    }
}

/**
 * The Factory class that encapsulates object creation logic
 * This is where the magic happens - client code doesn't need to know
 * about concrete classes or their constructors
 */
class NotificationFactory {
    
    public enum NotificationType {
        EMAIL, SMS, PUSH
    }
    
    /**
     * Factory method that creates appropriate notification objects
     * Notice how the client doesn't need to know about constructors
     * or specific implementation details
     */
    public static Notification createNotification(NotificationType type, 
                                                String recipient, 
                                                String message, 
                                                String... additionalParams) {
        
        switch (type) {
            case EMAIL:
                String subject = additionalParams.length > 0 ? 
                    additionalParams[0] : "Notification";
                return new EmailNotification(recipient, message, subject);
                
            case SMS:
                return new SMSNotification(recipient, message);
                
            case PUSH:
                String appId = additionalParams.length > 0 ? 
                    additionalParams[0] : "default-app";
                return new PushNotification(recipient, message, appId);
                
            default:
                throw new IllegalArgumentException("Unsupported notification type: " + type);
        }
    }
    
    /**
     * Alternative factory method that determines notification type
     * based on recipient format - this shows how factories can
     * encapsulate complex decision logic
     */
    public static Notification createSmartNotification(String recipient, String message) {
        if (recipient.contains("@")) {
            return createNotification(NotificationType.EMAIL, recipient, message, "Smart Notification");
        } else if (recipient.matches("\\+?[0-9\\-\\s()]+")) {
            return createNotification(NotificationType.SMS, recipient, message);
        } else {
            // Assume it's a device ID for push notifications
            return createNotification(NotificationType.PUSH, recipient, message, "smart-app");
        }
    }
}

// Usage example showing the power of the factory pattern
class NotificationService {
    public void sendWelcomeMessage(String recipient) {
        // Client code is clean and doesn't depend on concrete classes
        Notification notification = NotificationFactory.createSmartNotification(
            recipient, 
            "Welcome to our service! We're glad to have you."
        );
        
        notification.send();
    }
    
    public void sendOrderConfirmation(String email, String orderDetails) {
        // Explicitly choosing notification type when needed
        Notification notification = NotificationFactory.createNotification(
            NotificationFactory.NotificationType.EMAIL,
            email,
            "Your order has been confirmed. Details: " + orderDetails,
            "Order Confirmation"
        );
        
        notification.send();
    }
}
```

**Key benefits demonstrated:** The factory pattern allows you to add new notification types without modifying existing client code. If you need to add Slack notifications, you'd create a new SlackNotification class and update the factory - all existing code continues to work unchanged.

### Builder Pattern: Constructing Complex Objects Step by Step

The Builder pattern is essential when you need to create objects with many optional parameters or complex initialization logic. It's like assembling a custom computer - you choose each component step by step rather than trying to specify everything at once.

**The problem it solves:** Java constructors with many parameters become unwieldy and error-prone. The Builder pattern provides a fluent interface that makes object construction clear and flexible.

```java
/**
 * User class demonstrating the complexity that builders solve
 * Notice how many fields are optional - this would lead to
 * constructor explosion without the builder pattern
 */
class User {
    // Required fields
    private final String username;
    private final String email;
    
    // Optional fields with sensible defaults
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String phoneNumber;
    private final String address;
    private final boolean isActive;
    private final List<String> roles;
    private final Map<String, String> preferences;
    
    // Private constructor - only the builder can create User instances
    private User(UserBuilder builder) {
        // Required fields validation
        if (builder.username == null || builder.username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (builder.email == null || !builder.email.contains("@")) {
            throw new IllegalArgumentException("Valid email is required");
        }
        
        this.username = builder.username;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;
        this.isActive = builder.isActive;
        this.roles = new ArrayList<>(builder.roles); // Defensive copy
        this.preferences = new HashMap<>(builder.preferences); // Defensive copy
    }
    
    // Getters (no setters - the object is immutable once created)
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    public boolean isActive() { return isActive; }
    public List<String> getRoles() { return new ArrayList<>(roles); } // Defensive copy
    public Map<String, String> getPreferences() { return new HashMap<>(preferences); } // Defensive copy
    
    @Override
    public String toString() {
        return String.format("User{username='%s', email='%s', firstName='%s', lastName='%s', " +
                "age=%d, isActive=%b, roles=%s}", 
                username, email, firstName, lastName, age, isActive, roles);
    }
    
    /**
     * Static nested Builder class
     * This pattern keeps the builder closely associated with the class it builds
     */
    public static class UserBuilder {
        // Required parameters
        private String username;
        private String email;
        
        // Optional parameters with default values
        private String firstName = "";
        private String lastName = "";
        private int age = 0;
        private String phoneNumber = "";
        private String address = "";
        private boolean isActive = true;
        private List<String> roles = new ArrayList<>();
        private Map<String, String> preferences = new HashMap<>();
        
        // Constructor takes only required parameters
        public UserBuilder(String username, String email) {
            this.username = username;
            this.email = email;
        }
        
        // Fluent interface methods for optional parameters
        // Each method returns 'this' to enable method chaining
        
        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        
        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        
        public UserBuilder age(int age) {
            if (age < 0 || age > 150) {
                throw new IllegalArgumentException("Age must be between 0 and 150");
            }
            this.age = age;
            return this;
        }
        
        public UserBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        
        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }
        
        public UserBuilder isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }
        
        public UserBuilder addRole(String role) {
            this.roles.add(role);
            return this;
        }
        
        public UserBuilder roles(List<String> roles) {
            this.roles = new ArrayList<>(roles); // Defensive copy
            return this;
        }
        
        public UserBuilder addPreference(String key, String value) {
            this.preferences.put(key, value);
            return this;
        }
        
        public UserBuilder preferences(Map<String, String> preferences) {
            this.preferences = new HashMap<>(preferences); // Defensive copy
            return this;
        }
        
        // The build method creates and returns the final object
        public User build() {
            return new User(this);
        }
    }
}

/**
 * Advanced builder example showing how to handle complex validation
 * and business rules during the building process
 */
class DatabaseConfig {
    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;
    private final int maxConnections;
    private final int timeoutSeconds;
    private final boolean sslEnabled;
    private final String sslCertificatePath;
    
    private DatabaseConfig(DatabaseConfigBuilder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.database = builder.database;
        this.username = builder.username;
        this.password = builder.password;
        this.maxConnections = builder.maxConnections;
        this.timeoutSeconds = builder.timeoutSeconds;
        this.sslEnabled = builder.sslEnabled;
        this.sslCertificatePath = builder.sslCertificatePath;
    }
    
    // Getters...
    public String getConnectionString() {
        return String.format("jdbc:mysql://%s:%d/%s", host, port, database);
    }
    
    public static class DatabaseConfigBuilder {
        // Required
        private String host;
        private String database;
        private String username;
        private String password;
        
        // Optional with defaults
        private int port = 3306;
        private int maxConnections = 10;
        private int timeoutSeconds = 30;
        private boolean sslEnabled = false;
        private String sslCertificatePath = "";
        
        public DatabaseConfigBuilder(String host, String database, String username, String password) {
            this.host = host;
            this.database = database;
            this.username = username;
            this.password = password;
        }
        
        public DatabaseConfigBuilder port(int port) {
            if (port < 1 || port > 65535) {
                throw new IllegalArgumentException("Port must be between 1 and 65535");
            }
            this.port = port;
            return this;
        }
        
        public DatabaseConfigBuilder maxConnections(int maxConnections) {
            if (maxConnections < 1) {
                throw new IllegalArgumentException("Max connections must be positive");
            }
            this.maxConnections = maxConnections;
            return this;
        }
        
        public DatabaseConfigBuilder timeoutSeconds(int timeoutSeconds) {
            if (timeoutSeconds < 1) {
                throw new IllegalArgumentException("Timeout must be positive");
            }
            this.timeoutSeconds = timeoutSeconds;
            return this;
        }
        
        public DatabaseConfigBuilder enableSSL(String certificatePath) {
            this.sslEnabled = true;
            this.sslCertificatePath = certificatePath;
            return this;
        }
        
        public DatabaseConfig build() {
            // Complex validation that happens only at build time
            if (sslEnabled && (sslCertificatePath == null || sslCertificatePath.trim().isEmpty())) {
                throw new IllegalStateException("SSL certificate path is required when SSL is enabled");
            }
            
            return new DatabaseConfig(this);
        }
    }
}

// Usage examples demonstrating the power and flexibility of the Builder pattern
class UserManagementExample {
    public void demonstrateBuilderUsage() {
        // Simple user creation with only required fields
        User basicUser = new User.UserBuilder("john_doe", "john@example.com")
                .build();
        
        System.out.println("Basic user: " + basicUser);
        
        // Complex user creation with many optional fields
        User completeUser = new User.UserBuilder("jane_smith", "jane@example.com")
                .firstName("Jane")
                .lastName("Smith")
                .age(28)
                .phoneNumber("+1-555-0123")
                .address("123 Main St, Anytown, USA")
                .addRole("USER")
                .addRole("ADMIN")
                .addPreference("theme", "dark")
                .addPreference("language", "en")
                .addPreference("notifications", "enabled")
                .build();
        
        System.out.println("Complete user: " + completeUser);
        
        // Creating different configurations is easy and readable
        DatabaseConfig devConfig = new DatabaseConfig.DatabaseConfigBuilder(
                "localhost", "dev_db", "dev_user", "dev_pass")
                .port(3307)
                .maxConnections(5)
                .build();
        
        DatabaseConfig prodConfig = new DatabaseConfig.DatabaseConfigBuilder(
                "prod-server.company.com", "prod_db", "prod_user", "secure_pass")
                .port(3306)
                .maxConnections(50)
                .timeoutSeconds(60)
                .enableSSL("/path/to/certificate.pem")
                .build();
        
        System.out.println("Dev connection: " + devConfig.getConnectionString());
        System.out.println("Prod connection: " + prodConfig.getConnectionString());
    }
}
```

**Why this pattern is powerful:** The Builder pattern makes your code self-documenting. When you read `new User.UserBuilder("john", "john@email.com").age(25).addRole("ADMIN").build()`, you immediately understand what's being created and with what properties. This clarity becomes invaluable in large codebases.

## Structural Patterns: Organizing Object Relationships

Structural patterns focus on how classes and objects are composed to form larger structures. These patterns help you build flexible architectures where components can work together efficiently while remaining loosely coupled.

### Adapter Pattern: Making Incompatible Interfaces Work Together

The Adapter pattern allows classes with incompatible interfaces to work together. Think of it like a power adapter when you travel to different countries - it allows your device to work with different electrical outlets without changing the device itself.

**The real-world scenario:** You often need to integrate third-party libraries or legacy code that doesn't match your application's interface. The Adapter pattern bridges these gaps without modifying existing code.

```java
/**
 * Legacy payment system that we can't modify
 * This represents existing code that we need to integrate with
 */
class LegacyPaymentProcessor {
    public void makePayment(String cardNumber, String expiryDate, double amount) {
        System.out.println("Legacy system processing payment:");
        System.out.println("Card: " + cardNumber + ", Expiry: " + expiryDate + 
                          ", Amount: $" + amount);
        
        // Simulate payment processing
        if (amount > 0) {
            System.out.println("Payment of $" + amount + " processed successfully");
        } else {
            System.out.println("Payment failed: Invalid amount");
        }
    }
}

/**
 * Modern payment interface that our application expects
 * This is the interface our new code is designed around
 */
interface ModernPaymentGateway {
    PaymentResult processPayment(PaymentRequest request);
}

/**
 * Modern payment request structure
 */
class PaymentRequest {
    private final String creditCardNumber;
    private final String expirationDate;
    private final String cvv;
    private final double amount;
    private final String currency;
    private final String customerEmail;
    
    public PaymentRequest(String creditCardNumber, String expirationDate, 
                         String cvv, double amount, String currency, String customerEmail) {
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.amount = amount;
        this.currency = currency;
        this.customerEmail = customerEmail;
    }
    
    // Getters
    public String getCreditCardNumber() { return creditCardNumber; }
    public String getExpirationDate() { return expirationDate; }
    public String getCvv() { return cvv; }
    public double getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public String getCustomerEmail() { return customerEmail; }
}

/**
 * Modern payment result structure
 */
class PaymentResult {
    private final boolean successful;
    private final String transactionId;
    private final String message;
    
    public PaymentResult(boolean successful, String transactionId, String message) {
        this.successful = successful;
        this.transactionId = transactionId;
        this.message = message;
    }
    
    public boolean isSuccessful() { return successful; }
    public String getTransactionId() { return transactionId; }
    public String getMessage() { return message; }
    
    @Override
    public String toString() {
        return String.format("PaymentResult{successful=%b, transactionId='%s', message='%s'}", 
                           successful, transactionId, message);
    }
}

/**
 * The Adapter that bridges the gap between old and new interfaces
 * This class implements the modern interface but delegates to the legacy system
 */
class LegacyPaymentAdapter implements ModernPaymentGateway {
    private final LegacyPaymentProcessor legacyProcessor;
    
    public LegacyPaymentAdapter(LegacyPaymentProcessor legacyProcessor) {
        this.legacyProcessor = legacyProcessor;
    }
    
    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        try {
            // Validate the modern request
            if (request.getAmount() <= 0) {
                return new PaymentResult(false, null, "Invalid amount");
            }
            
            if (!request.getCurrency().equals("USD")) {
                return new PaymentResult(false, null, "Legacy system only supports USD");
            }
            
            // Transform modern request format to legacy format
            String cardNumber = request.getCreditCardNumber();
            String expiryDate = request.getExpirationDate();
            double amount = request.getAmount();
            
            // Call the legacy system with transformed data
            legacyProcessor.makePayment(cardNumber, expiryDate, amount);
            
            // Transform legacy response to modern format
            String transactionId = "TXN_" + System.currentTimeMillis();
            return new PaymentResult(true, transactionId, "Payment processed successfully");
            
        } catch (Exception e) {
            // Handle any exceptions from the legacy system
            return new PaymentResult(false, null, "Payment failed: " + e.getMessage());
        }
    }
}

/**
 * New payment system that follows modern patterns
 */
class ModernPaymentProcessor implements ModernPaymentGateway {
    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        System.out.println("Modern system processing payment:");
        System.out.println("Processing payment for: " + request.getCustomerEmail());
        System.out.println("Amount: " + request.getAmount() + " " + request.getCurrency());
        
        // Modern validation and processing logic
        if (request.getCvv() == null || request.getCvv().length() != 3) {
            return new PaymentResult(false, null, "Invalid CVV");
        }
        
        // Simulate modern payment processing
        String transactionId = "MOD_" + System.currentTimeMillis();
        return new PaymentResult(true, transactionId, "Payment processed via modern system");
    }
}

/**
 * Payment service that works with any payment gateway
 * This shows how the adapter allows us to treat all payment systems uniformly
 */
class PaymentService {
    private final ModernPaymentGateway paymentGateway;
    
    public PaymentService(ModernPaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }
    
    public boolean processOrderPayment(String cardNumber, String expiryDate, String cvv,
                                     double amount, String customerEmail) {
        // Create a modern payment request
        PaymentRequest request = new PaymentRequest(
            cardNumber, expiryDate, cvv, amount, "USD", customerEmail
        );
        
        // Process payment using whatever gateway was injected
        PaymentResult result = paymentGateway.processPayment(request);
        
        System.out.println("Payment result: " + result);
        
        if (result.isSuccessful()) {
            System.out.println("Order confirmed. Transaction ID: " + result.getTransactionId());
        } else {
            System.out.println("Order failed. Reason: " + result.getMessage());
        }
        
        return result.isSuccessful();
    }
}

// Usage example showing how the adapter pattern enables seamless integration
class AdapterPatternDemo {
    public void demonstrateAdapter() {
        // Using the legacy system through the adapter
        LegacyPaymentProcessor legacySystem = new LegacyPaymentProcessor();
        ModernPaymentGateway adaptedLegacySystem = new LegacyPaymentAdapter(legacySystem);
        
        PaymentService legacyPaymentService = new PaymentService(adaptedLegacySystem);
        legacyPaymentService.processOrderPayment(
            "1234-5678-9012-3456", "12/25", "123", 
            99.99, "customer@example.com"
        );
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Using the modern system directly
        ModernPaymentGateway modernSystem = new ModernPaymentProcessor();
        PaymentService modernPaymentService = new PaymentService(modernSystem);
        modernPaymentService.processOrderPayment(
            "9876-5432-1098-7654", "06/26", "456", 
            149.99, "modern.customer@example.com"
        );
    }
}
```

**The key insight:** The Adapter pattern allows you to use legacy systems in modern architectures without rewriting them. Your new code doesn't need to know or care about the differences in the underlying systems - it just works with a consistent interface.

### Observer Pattern: Staying Informed of Changes

The Observer pattern defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified automatically. Think of it like a news subscription - when news breaks, all subscribers are automatically informed.

**When you need Observer:** User interfaces that need to update when data changes, event systems, model-view architectures, and any scenario where multiple objects need to react to state changes in another object.

```java
import java.util.*;

/**
 * Subject interface - defines the contract for objects that can be observed
 */
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

/**
 * Observer interface - defines the contract for objects that want to be notified
 */
interface Observer {
    void update(String symbol, double price, double change);
}

/**
 * Concrete subject - represents a stock that can be observed
 * This is the object whose state changes trigger notifications
 */
class Stock implements Subject {
    private final String symbol;
    private double currentPrice;
    private double previousPrice;
    private List<Observer> observers;
    
    public Stock(String symbol, double initialPrice) {
        this.symbol = symbol;
        this.currentPrice = initialPrice;
        this.previousPrice = initialPrice;
        this.observers = new ArrayList<>();
    }
