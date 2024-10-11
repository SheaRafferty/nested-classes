

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Encounter class.
 * 
 * <p>
 * 	Encounters are the main obstacle for
 *  the hero as they stroll the dungeon. 
 *  
 *  They consist of a list of monsters the hero 
 *  will need to battle to pass the encounter.
 * </p>
 */
public class Encounter implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9085056247294595629L;
	/**
	 * List of monsters in the encounter.
	 */
	private List<Monster> monsters;
	
	/**
	 * Instantiate an encounter with an
	 * empty list of monsters.
	 */
	public Encounter() {
		monsters = new ArrayList<>();
	}
	
	/**
	 * Instantiate an encounter with a given list
	 * of monsters.
	 * @param monsters List of monsters in the encounter.
	 */
	public Encounter(List<Monster> monsters) {
		this.monsters = monsters;
	}
	
	/**
	 * Add a monster to the encounter.
	 * @param m Monster to add.
	 */
	public void addMonster(Monster m) {
		// A duplicate monster is being added.
		if (monsters.contains(m)) {
			// Get number of m in the list
			int count = 0;
			for (Monster monster : monsters) {
				if (monster.equals(m)) {
					count++;
				}
			}
			m = new Monster(m,count+1);
		}
		
		monsters.add(m);
	}
	
	/**
	 * Remove a monster from the encounter.
	 * @param m Monster to remove.
	 */
	public void removeMonster(Monster m) {
		monsters.remove(m);
	}
	
	/**
	 * Get the number of monsters in the encounter.
	 * @return Number of monsters.
	 */
	public int getNumMonsters() {
		return monsters.size();
	}
	
	/**
	 * Get the list of monsters.
	 * @return Copy of the list of monsters.
	 */
	public List<Monster> getMonsters() {
		//return Collections.unmodifiableList(monsters);
		return List.copyOf(monsters);
	}
	
	/**
	 * Print the monsters in the encounter.
	 * @param out PrintStream to print to.
	 */
	public void print(PrintStream out) {
		for (Monster m : monsters) {
			m.print(out);
		}
	}
	
	@Override
	public String toString() { 
		return Utils.enumeratedList(monsters);
	}
}
