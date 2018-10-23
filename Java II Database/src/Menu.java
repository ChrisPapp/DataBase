import java.util.Scanner;

public class Menu {
	// Print main menu and run user's choice
	private static Database data;
	private static Scanner inputChoice = new Scanner(System.in);

	public Menu(Database data) {
		Menu.data = data;
		menu();
	}

	private void menu() {
		printMenu();
		performAction();
		menu(); //Afou ginei i performAction() ksanakalei to Menu

	}

	public static void printMenu() {
		System.out.println("   *** DATABASE MENU ***\n");
		System.out.println("  1. DISPLAY DATA ");
		System.out.println("  2. INPUT DATA ");
		System.out.println("  3. CHANGE DATA ");
		System.out.println("  4. REMOVE LINE ");
		System.out.print("  SELECT AN OPTION: ");
	}

	public static void performAction() {

		int choice = inputChoice.nextInt();

		switch (choice) {
		case 1:
			DatabaseProcessing.displayData(data.getList());
			break;
		case 2:
			data.inputData();
			break;
		case 3:
			System.out.println("Work In Progress...");
			break;
		case 4:
			DatabaseProcessing.removeLine(data.getList());

		}

	}

	public Database getData() {
		return data;
	}

	public void setData(Database data) {
		Menu.data = data;
	}
}
