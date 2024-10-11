import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create scanner
        Scanner scan = new Scanner(System.in);
        
        // Declare a DungeonBuilder
        DungeonBuilder builder;

        // Ask the user if they want to load monsters from a file
        System.out.println("Do you want to load monsters from a file? (Y/N): ");
        
        // Read the user's input, make sure it's uppercase and doesn't have extra spaces
        String choice = scan.nextLine().strip().toUpperCase();
        
        // Declare a list to hold the monsters
        List<Monster> monsters;

        // If the user said yes, load the monsters in from the file
        if (choice.equals("Y")) {
            // Ask the user to enter the path to the monster file
            Path monsterFilePath = Utils.userGetValidPath(scan, "Enter monster file path: ");
            // Load monsters from the file using DungeonBuilder's method
            monsters = new DungeonBuilder(null).getMonstersFromFile(monsterFilePath);
        } else {
            // If the user said no, use some default monsters 
            monsters = List.of(
                new Monster("Wolf", 50, 5, 5),
                new Monster("Rat King", 30, 3, 3),
                new Monster("Great Lizard", 40, 4, 4),
                new Monster("Lesser Lizard", 100, 10, 10),
                new Monster("King Penguin", 64, 6, 46),
                new Monster("Killer Wasp", 86, 6, 6),
                new Monster("Bandit", 30, 2, 3)
            );
        }

        // Create the DungeonBuilder with the chosen monsters
        builder = new DungeonBuilder(monsters);
        // Build the dungeon by asking the user for more input (manual or random)
        Dungeon dungeon = builder.buildDungeon(scan);
        // Tell the user that the dungeon has been successfully created
        System.out.println("Dungeon created successfully! Here's your dungeon:");
        // Loop through all floors and print each one of them
        for (int i = 0; i < dungeon.getNumFloors(); i++) {
            // Print the floor number (1-based, so we add 1 to the index)
            System.out.println("Floor " + (i + 1) + ": ");
            // Get the current floor from the dungeon
            Dungeon.DungeonFloor floor = dungeon.getFloor(i);
            // Print all the encounters and monsters on this floor
            floor.print();
        }

        // Close scanner 
        scan.close();
    }
}

