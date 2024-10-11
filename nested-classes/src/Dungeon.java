import java.util.ArrayList;
import java.util.List;

public class Dungeon {
	
	// List to store the dungeons floors
    private List<DungeonFloor> floors;

    // Constructor that initialises dungeon
    public Dungeon() {
        this.floors = new ArrayList<>();
    }
    
    // Method that adds new floors to the dungeon
    public void addDungeonFloor(DungeonFloor floor) {
        floors.add(floor);
    }
     
   // Getter for the number of floors
    public int getNumFloors() {
        return floors.size();
    }
    
    // Getter to access a specific floor
    public DungeonFloor getFloor(int index) {
        return floors.get(index);
    }

    // The nested DungeonFloor class
    public class DungeonFloor {
        private List<Encounter> encounters;
        
     // Constructor that initialises the dungeons floor
        public DungeonFloor() {
            this.encounters = new ArrayList<>();
        }
        
      // Method that adds an encounter to a floor
        public void addEncounter(Encounter encounter) {
            encounters.add(encounter);
        }
        
      // Getter to access the encounters
        public List<Encounter> getEncounters() {
            return encounters;
        }
      // Print info about the dungeon
        public void print() {
        	// Use for loop to go through the encounters before printing
            for (Encounter encounter : encounters) {
            	// Get the print method for the encounters and pass through the outputs stream
                encounter.print(System.out);
            }
        }
    }
}
