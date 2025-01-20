package shelter; 

import database.DatabaseConnection; // Import database connection utilities
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Shelter {
    private String name;
    private List<Pet> pets;

    public Shelter(String name) {
        this.name = name;
        this.pets = new ArrayList<>();
    }

    // Existing methods for in-memory operations
    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public void displayPets() {
        if (pets.isEmpty()) {
            System.out.println("No pets available for adoption.");
        } else {
            System.out.println("Pets in " + name + ":");
            pets.forEach(System.out::println);
        }
    }

    public void comparePets() {
        if (pets.size() < 2) {
            System.out.println("Not enough pets to compare.");
            return;
        }
        Pet oldest = Collections.max(pets, Comparator.comparingInt(Pet::getAge));
        Pet youngest = Collections.min(pets, Comparator.comparingInt(Pet::getAge));
        System.out.println("Oldest Pet: " + oldest);
        System.out.println("Youngest Pet: " + youngest);
    }

    public void searchPetByName(String name) {
        System.out.println("Searching for pets with name: " + name);
        pets.stream()
                .filter(pet -> pet.getName().equalsIgnoreCase(name))
                .forEach(System.out::println);
    }

    public void sortPetsByAge() {
        pets.sort(Comparator.comparingInt(Pet::getAge));
        System.out.println("Pets sorted by age:");
        pets.forEach(System.out::println);
    }

    public Pet adoptPet(int index) {
        if (index >= 0 && index < pets.size()) {
            return pets.remove(index);
        } else {
            System.out.println("Invalid choice. Try again.");
            return null;
        }
    }

    // Database methods
    public void addPetToDatabase(String name, int age, String type) {
        String query = "INSERT INTO pets_simple (name, age, type) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, type);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Pet added successfully to the database!");
            }
        } catch (SQLException e) {
            System.out.println("Error adding pet to database: " + e.getMessage());
        }
    }

    public void displayPetsFromDatabase() {
        String query = "SELECT * FROM pets_simple";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            System.out.println("Pets in the database:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                int age = resultSet.getInt("age");
                System.out.println("ID: " + id + ", Name: " + name + ", Type: " + type + ", Age: " + age);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving pets from database: " + e.getMessage());
        }
    }

    public void updatePetAgeInDatabase(int id, int newAge) {
        String query = "UPDATE pets_simple SET age = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, newAge);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Pet's age updated successfully!");
            } else {
                System.out.println("Pet with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating pet's age: " + e.getMessage());
        }
    }

    public void deletePetFromDatabase(int id) {
        String query = "DELETE FROM pets_simple WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Pet deleted successfully!");
            } else {
                System.out.println("Pet with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting pet from database: " + e.getMessage());
        }
    }
}










