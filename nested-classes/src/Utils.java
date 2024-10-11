import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Utils {

	/**
	 * Get an enumerated version of the given list.
	 * @param list List to enumerate.
	 * @return String enumerating the contents of the list.
	 */
	public static String enumeratedList(List<?> list) {
		StringBuffer sb = new StringBuffer();
		String format = "%d) %s\n";
		for (int i = 0; i < list.size(); i++) {
			sb.append(format.formatted(i, list.get(i)));
		}
		
		return sb.toString();
	}
	
	/**
	 * Prompt and get user input from a given list.
	 * @param <T> Type of the elements in the list.
	 * @param scan Scanner to read user input from.
	 * @param list List to get object from.
	 * @return Element from the list chosen by user.
	 */
	public static <T> T userChooseFromEnumeratedList(Scanner scan, List<? extends T> list) {
		System.out.println(enumeratedList(list));
		int choice;
		do {
			while (!scan.hasNextInt()) {
				scan.nextLine();
			}
			choice = Integer.parseInt(scan.nextLine().strip());
		} while (choice < 0 || choice >= list.size());
		
		return list.get(choice);
	}
	
	/**
	 * Get a positive integer from user input.
	 * @param scan Scanner to read user input.
	 * @param prompt Prompt for the user.
	 * @return A positive integer from user input.
	 */
	public static int userGetPositiveInteger(Scanner scan, String prompt) {
		int val;
		do {
			System.out.print(prompt);
			while (!scan.hasNextInt()) {
				scan.nextLine();
			}
			val = Integer.parseInt(scan.nextLine().strip());
		} while (val <= 0);
		return val;
	}
	
	/**
	 * Get a non-negative integer from user input.
	 * @param scan Scanner to read user input.
	 * @param prompt Prompt for the user.
	 * @return A non-negative integer from user input.
	 */
	public static int userGetNonNegativeInteger(Scanner scan, String prompt) {
		int val;
		do {
			System.out.print(prompt);
			while (!scan.hasNextInt()) {
				scan.nextLine();
			}
			val = Integer.parseInt(scan.nextLine().strip());
		} while (val < 0);
		return val;
	}
	
	/**
	 * Get a valid path from user input.
	 * 
	 * <p>
	 * 	A valid path is one that exists.
	 * </p>
	 * @param scan Scanner to read user input.
	 * @param prompt Prompt for the user.
	 * @return A valid path.
	 */
	public static Path userGetValidPath(Scanner scan, String prompt) {
		Path path = null;
		while (true) {
			System.out.print(prompt);
			try {
				path = Path.of(scan.nextLine());
			} catch (InvalidPathException e) {
				System.out.println("%s is not a valid path.".formatted(path.toString()));
			}
			
			if (Files.notExists(path)) {
				System.out.println("Path %s does not exist".formatted(path.toString()));
			}
			else {
				return path;
			}
		}
	}
	
	/**
	 * Get an integer from user input.
	 * @param scan Scanner to read user input.
	 * @param prompt Prompt for the user.
	 * @return An integer from user input.
	 */
	public static int userGetInteger(Scanner scan, String prompt) {
		System.out.print(prompt);
		while (!scan.hasNextInt()) {
			scan.nextLine();
		}
		
		return Integer.parseInt(scan.nextLine());
	}
}
