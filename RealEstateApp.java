import java.util.ArrayList;
import java.util.List;

// Property Class
class Property {
    private String propertyId;
    private String address;
    private double price;
    private String propertyType;
    private boolean isAvailable;

    public Property(String propertyId, String address, double price, String propertyType) {
        this.propertyId = propertyId;
        this.address = address;
        this.price = price;
        this.propertyType = propertyType;
        this.isAvailable = true;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public String getAddress() {
        return address;
    }

    public double getPrice() {
        return price;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Property ID: " + propertyId + ", Address: " + address + ", Price: " + price + ", Type: " + propertyType + ", Available: " + isAvailable;
    }
}

// User Class
class User {
    private String userId;
    private String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void viewProperties(List<Property> properties) {
        System.out.println("Available Properties:");
        for (Property property : properties) {
            if (property.isAvailable()) {
                System.out.println(property);
            }
        }
    }
}

// Agent Class
class Agent extends User {
    private List<Property> propertiesListed;

    public Agent(String userId, String name) {
        super(userId, name);
        this.propertiesListed = new ArrayList<>();
    }

    public void addProperty(Property property) {
        propertiesListed.add(property);
        System.out.println("Property added successfully by agent " + getName());
    }

    public void updatePropertyAvailability(String propertyId, boolean isAvailable) {
        for (Property property : propertiesListed) {
            if (property.getPropertyId().equals(propertyId)) {
                property.setAvailable(isAvailable);
                System.out.println("Property availability updated successfully.");
                return;
            }
        }
        System.out.println("Property not found.");
    }
}

// Real Estate System
class RealEstateSystem {
    private List<Property> properties;
    private List<User> users;

    public RealEstateSystem() {
        properties = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void registerUser(User user) {
        users.add(user);
        System.out.println("User registered: " + user.getName());
    }

    public void listProperty(Agent agent, Property property) {
        agent.addProperty(property);
        properties.add(property);
    }

    // Getter for the properties list
    public List<Property> getProperties() {
        return properties;
    }

    public void showProperties() {
        System.out.println("Properties in the System:");
        for (Property property : properties) {
            System.out.println(property);
        }
    }
}

// Main Class
public class RealEstateApp {
    public static void main(String[] args) {
        RealEstateSystem system = new RealEstateSystem();
        Agent agent = new Agent("A101", "Alice");
        User user = new User("U202", "Bob");

        // Registering Users
        system.registerUser(agent);
        system.registerUser(user);

        // Listing Properties
        Property property1 = new Property("P001", "123 Main St", 250000.0, "Apartment");
        Property property2 = new Property("P002", "456 Elm St", 300000.0, "House");

        system.listProperty(agent, property1);
        system.listProperty(agent, property2);

        // User viewing available properties
        user.viewProperties(system.getProperties()); // Use getter method to access properties

        // Agent updating property availability
        agent.updatePropertyAvailability("P001", false);

        // Display all properties
        system.showProperties();
    }
}