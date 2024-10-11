import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DungeonBuilder {
	// List to store monsters 
    private List<Monster> monsters;
     
    // Constructor for the dungeon builder
    public DungeonBuilder(List<Monster> monsters) {
        this.monsters = monsters;
    }
    
    // Method that asks user whether the users wants to build manually or get a random dungeon
    public Dungeon buildDungeon(Scanner scan) {
        System.out.println("Manual or Random Dungeon? (Enter 'M' for manual, 'R' for random): ");
        
        // String that takes the users input and turns it into uppercase
        String choice = scan.nextLine().strip().toUpperCase();
        
      // Deal with the users input depending on what they enter
        if (choice.equals("M")) {
            return createManualDungeon(scan);
        } else {
            return createRandomDungeon(scan);
        }
    }

    // Private method that creates a dungeon manually by taking the users input  
    private Dungeon createManualDungeon(Scanner scan) {
    	
    	// Ask user for the number of floors
        int numFloors = Utils.userGetPositiveInteger(scan, "Number of floors: ");
        
        // Make an object for the dungeon
        Dungeon dungeon = new Dungeon();
        
        // For loop that loops through the number of floors
        for (int i = 0; i < numFloors; i++) {
        	// Make a new floor for the dungeon
            Dungeon.DungeonFloor floor = dungeon.new DungeonFloor();
            // Ask user how many encounters they want on that floor
            int numEncounters = Utils.userGetPositiveInteger(scan, "Number of encounters for floor " + (i + 1) + ": ");
            
            // Loop through the encounters
            for (int j = 0; j < numEncounters; j++) {
            	
            	// Add new encounter for the current floor
                Encounter encounter = new Encounter();
                
                // Ask user what monsters they want for the encounter
                System.out.println("Choose monsters for encounter " + (j + 1) + ": ");
                
                // Add the chosen monster to the encounter    
                Monster chosenMonster = Utils.userChooseFromEnumeratedList(scan, monsters);
                encounter.addMonster(chosenMonster);
                // Add that encounter to the dungeon floor
                floor.addEncounter(encounter);
            }
            
            
           // Add the floor to the dungeon, making sure everything else is already added in
            dungeon.addDungeonFloor(floor);
        }

        return dungeon;
    }
    
    // Private method to create a random dungeon for the user
    private Dungeon createRandomDungeon(Scanner scan) {
        // Ask the user for the number of floors
        int numFloors = Utils.userGetPositiveInteger(scan, "Number of floors: ");
        // Create a new dungeon 
        Dungeon dungeon = new Dungeon();
        // Initialise a random number generator
        Random random = new Random();

        // Loop over the number of floors
        for (int i = 0; i < numFloors; i++) {
            // Create a new floor for the dungeon
            Dungeon.DungeonFloor floor = dungeon.new DungeonFloor();
            // Ask the user how many encounters should be on this floor, even though it's random
            int numEncounters = Utils.userGetPositiveInteger(scan, "Number of encounters for floor " + (i + 1) + ": ");

            // Loop over the number of encounters
            for (int j = 0; j < numEncounters; j++) {
                // Create a new encounter
                Encounter encounter = new Encounter();
                // Randomly choose the number of monsters for the encounter making sure theres at least 1
                int numMonsters = random.nextInt(monsters.size()) + 1;  
                // Loop over the number of monsters we need to add
                for (int k = 0; k < numMonsters; k++) {
                    // Randomly select a monster from the list of available monsters
                    Monster randomMonster = monsters.get(random.nextInt(monsters.size()));
                    // Add the randomly chosen monster to the encounter
                    encounter.addMonster(randomMonster);
                }
                // After adding all the monsters, add the encounter to the floor
                floor.addEncounter(encounter);
            }

            // After adding all encounters, add the floor to the dungeon
            dungeon.addDungeonFloor(floor);
        }

        // Return the users random dungeon
        return dungeon;
    }

    // Method to load a list of monsters from a file
    public List<Monster> getMonstersFromFile(Path path) {
        try {
            // Try to get the monsters from the file 
            return Monster.monstersFromFile(path);
        } catch (Exception e) {
            // If something goes wrong print stack trace 
            e.printStackTrace();
            // Return an empty list if there was an error
            return new ArrayList<>();
        }
    }
}

