package shelter;

import java.util.Scanner;

public class PetAdoptionPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Shelter shelter = new Shelter("Happy Tails Shelter");

        // Initial in-memory pets
        shelter.addPet(new Pet("Bobik", 3, "Dog"));
        shelter.addPet(new Pet("Barsik", 2, "Cat"));
        shelter.addPet(new Pet("Murzik", 10, "Cat"));
        shelter.addPet(new Pet("Dymok", 6, "Cat"));
        shelter.addPet(new Pet("Jack", 2, "Dog"));
        shelter.addPet(new Pet("Bugz Bunny", 1, "Rabbit"));

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. View all pets");
            System.out.println("2. Compare pets by age");
            System.out.println("3. Search pet by name");
            System.out.println("4. Sort pets by age");
            System.out.println("5. Adopt a pet");
            System.out.println("6. Add a new pet");
            System.out.println("7. Add pet to database");
            System.out.println("8. View pets from database");
            System.out.println("9. Update pet's age in database");
            System.out.println("10. Delete pet from database");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> shelter.displayPets();
                case 2 -> shelter.comparePets();
                case 3 -> {
                    System.out.print("Enter the name of the pet to search: ");
                    String name = scanner.nextLine();
                    shelter.searchPetByName(name);
                }
                case 4 -> shelter.sortPetsByAge();
                case 5 -> {
                    shelter.displayPets();
                    System.out.print("Enter the number of the pet you want to adopt: ");
                    int index = scanner.nextInt() - 1;
                    Pet adoptedPet = shelter.adoptPet(index);
                    if (adoptedPet != null) {
                        System.out.println("You adopted " + adoptedPet.getName());
                    }
                }
                case 6 -> {
                    System.out.print("Enter pet name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter pet type: ");
                    String newType = scanner.nextLine();
                    System.out.print("Enter pet age: ");
                    int newAge = scanner.nextInt();
                    shelter.addPet(new Pet(newName, newAge, newType));
                    System.out.println("New pet added successfully.");
                }
                case 7 -> {
                    System.out.print("Enter pet name: ");
                    String dbName = scanner.nextLine();
                    System.out.print("Enter pet type: ");
                    String dbType = scanner.nextLine();
                    System.out.print("Enter pet age: ");
                    int dbAge = scanner.nextInt();
                    shelter.addPetToDatabase(dbName, dbAge, dbType);
                }
                case 8 -> shelter.displayPetsFromDatabase();
                case 9 -> {
                    System.out.print("Enter pet ID to update: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter new age: ");
                    int newAge = scanner.nextInt();
                    shelter.updatePetAgeInDatabase(id, newAge);
                }
                case 10 -> {
                    System.out.print("Enter pet ID to delete: ");
                    int id = scanner.nextInt();
                    shelter.deletePetFromDatabase(id);
                }
                case 11 -> {
                    System.out.println("Exiting. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}






