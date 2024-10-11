

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

/**
 * A monster the Hero can encounter on their
 * dungeon stroll.
 */
public class Monster implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2513667880225814347L;

	/**
	 * Create a list of monsters from a given Path.
	 * @param monsterFilePath Path to monster file.
	 * @return MonsterList parsed from the file.
	 * @throws IOException if there is an error with the file.
	 */
	public static List<Monster> monstersFromFile(Path monsterFilePath) throws IOException {
		List<Monster> monsters = new ArrayList<>();
		try (BufferedReader br = Files.newBufferedReader(monsterFilePath)) {
			String line = null;
			while ( (line = br.readLine()) != null) {
				Monster m = new Monster();
				try {
					m.parse(line);
					monsters.add(m);
				} catch (NumberFormatException e) {
					continue;
				}
			}
		} 
		
		return monsters;
	}
	
	/**
	 * Create a Monster from user input.
	 * @param scan Scanner to read user input.
	 * @return Newly created monster.
	 */
	public static Monster userCreateMonster(Scanner scan) {
		String name;
		int maxHP, armorClass, atk;
		
		System.out.print("Monster name: ");
		name = scan.nextLine();
		
		do {
			System.out.print("HP: ");
			while (!scan.hasNextInt()) {
				scan.nextLine();
			}
			maxHP = Integer.parseInt(scan.nextLine());
		} while (maxHP <= 0);
		
		do {
			System.out.print("AC: ");
			while (!scan.hasNextInt()) {
				scan.nextLine();
			}
			armorClass = Integer.parseInt(scan.nextLine());
		} while (armorClass <= 0 || armorClass > 20);
		
		do {
			System.out.print("ATK: ");
			while (!scan.hasNextInt()) {
				scan.nextLine();
			}
			atk = Integer.parseInt(scan.nextLine());
		} while (atk < 0);
		return new Monster(name, maxHP, armorClass, atk);
	}

	/**
	 * Name of the monster.
	 */
	private String name;
	/**
	 * Maximum HP of the monster.
	 */
	private int maxHP;
	/**
	 * Armor class of the monster.
	 * 
	 * Armor class decides how difficult a monster is to hit.
	 */
	private int armorClass;
	/**
	 * Attack value of the monster.
	 */
	private int atk;
	/**
	 * Unique Universal Identifier 
	 */
	private UUID identifier;
	
	/**
	 * Instantiate a monster.
	 * @param name Name of the monster.
	 * @param maxHP MaxHP of the monster.
	 * @param armorClass Armor class of the monster.
	 * @param atk Attack of the monster.
	 */
	public Monster(String name, int maxHP, int armorClass, int atk) {
		this.name = name;
		this.maxHP = maxHP;
		this.armorClass = armorClass;
		this.atk = atk;
		this.identifier = UUID.randomUUID();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getArmorClass() {
		return armorClass;
	}

	public void setArmorClass(int armorClass) {
		this.armorClass = armorClass;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	/**
	 * Print monster name, armor class and maxHP as a formatted String
	 * to a PrintStream.
	 * @param out PrintStream to print to.
	 */
	public void print(PrintStream out) {
		String format = """
				%-10s AC:%d HP:%d
				""".formatted(name, armorClass, maxHP);
		out.println(format);
	}
	
	protected Monster() {
		this.name = null;
		this.maxHP = 0;
		this.armorClass = 0;
		this.atk = 0;
		this.identifier = UUID.randomUUID();
	}
	
	protected Monster(Monster monster, int nth) {
		this.name = "%s%d".formatted(monster.name,nth);
		this.maxHP = monster.getMaxHP();
		this.armorClass = monster.getArmorClass();
		this.atk = monster.getAtk();
		this.identifier = monster.identifier;
	}
	
	/**
	 * Parses a given String to create a monster.
	 * @param str String to parse.
	 * @throws NumberFormatException if given String cannot be parsed into a Monster.
	 */
	protected void parse(String str) throws NumberFormatException {
		String[] splits = str.split(",");
		
		String name = splits[0];
		int maxHP = Integer.parseInt(splits[1]);
		int armorClass = Integer.parseInt(splits[2]);
		int atk = Integer.parseInt(splits[3]);
		
		this.name = name;
		this.maxHP = maxHP;
		this.armorClass = armorClass;
		this.atk = atk;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			// o is the same object
			return true;
		}
		else if (o instanceof Monster m) {
			// o is a Monster
			return Objects.equals(identifier, m.identifier);
		}
		else {
			// o is not a Monster
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "%-10s AC:%d HP:%d".formatted(name, armorClass, maxHP);
	}
	
	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
	}
}
