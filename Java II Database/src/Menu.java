import java.util.Scanner;
import java.awt.Toolkit;
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
		menu(); //After an action is completed, the menu is called again

	}

	public static void printMenu() {
		System.out.println();
		System.out.println("*** DATABASE MENU ***\n");
		System.out.println("  1. DISPLAY DATA ");
		System.out.println("  2. INPUT DATA ");
		System.out.println("  3. CHANGE DATA ");
		System.out.println("  4. REMOVE LINE ");
		System.out.print("  SELECT AN OPTION: ");
	}

	public static void performAction() {
		int choice=0;
		boolean flag = true;
		char ch = inputChoice.next().charAt(0);
		do {
			if (!(Character.isDigit(ch))) {
				System.out.println("\n WRONG ");
				Toolkit.getDefaultToolkit().beep();
				printMenu();
				ch = inputChoice.next().charAt(0);
			} else {
				choice = Character.getNumericValue(ch);
				if ((choice < 1)||(choice > 4)) {
					System.out.println("\n WRONG ");
					Toolkit.getDefaultToolkit().beep();
					printMenu();
					ch = inputChoice.next().charAt(0);
				} else {
					flag = false;
				}
			}
		}
		while (flag);




		switch (choice) {
		case 1:
			DatabaseProcessing.displayData(data.getList());
			break;
		case 2:
			data.inputData();
			break;
		case 3:
			DatabaseProcessing.changeData(data.getList());
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
