

import java.io.PrintStream;

/**
 * Hero class.
 * 
 */
public class Hero {

	/*
	 * Name of the hero.
	 */
	private String name;
	/*
	 * Attack value.
	 */
	private int atk;
	/*
	 * Defense value.
	 */
	private int def;
	/*
	 * Maximum allowed HP.
	 */
	private int maxHP;
	/*
	 * Current amount of HP
	 */
	private int currentHP;
	
	/**
	 * Instantiate a Hero with given name, atk, def, and hp.
	 * @param name Name of the hero.
	 * @param hp Health points
	 * @param atk Attack value.
	 * @param def Defense value.
	 */
	public Hero(String name, int maxHP, int atk, int def) {
		this.name = name;
		this.atk = atk;
		this.def = def;
		this.maxHP = maxHP;
		
		currentHP = maxHP;
	}

	public String getName() {
		return name;
	}

	public int getAtk() {
		return atk;
	}

	public int getDef() {
		return def;
	}

	public int getMaxHP() {
		return maxHP;
	}
	
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	
	public int getCurrentHP() {
		return currentHP;
	}
	
	/**
	 * Decrease current HP by an amount.
	 * 
	 * <p>
	 * 	If new HP value would be less than 0, then set it to 0.
	 * </p>
	 * @param amount Amount to decrease HP.
	 */
	public void decreaseHP(int amount) {
		int newHP = currentHP - amount;
		if (newHP < 0) {
			currentHP = 0;
		}
		else {
			currentHP = newHP; 
		}
	}
	
	/**
	 * Increase current HP by an amount.
	 * 
	 * <p>
	 *  If new HP value would be greater than maxHP, then set it to maxHP.
	 * </p>
	 * @param amount Amount to increase HP.
	 */
	public void increaseHP(int amount) {
		int newHP = currentHP + amount;
		if (newHP > maxHP) {
			currentHP = maxHP;
		}
		else {
			currentHP = newHP;
		}
	}
	
	/**
	 * Decrease defense by an amount.
	 * <p>
	 *  If new defense value would be less than 0, then set it to 0.
	 * </p>
	 * @param amount Amount to decrease defense.
	 */
	public void decreaseDef(int amount) {
		int newDef = def - amount;
		if (newDef < 0) {
			def = 0;
		}
		else {
			def = newDef;
		}
	}

	/**
	 * Print the hero's stats to the print stream.
	 * @param out PrintStream object to print to.
	 */
	public void print(PrintStream out) {
		String formatted = """
				Hero name: %s HP: %d/%d
				Atk: %d  Def: %d
				""".formatted(name, currentHP, maxHP,atk,def);
		out.println(formatted);
	}
}
