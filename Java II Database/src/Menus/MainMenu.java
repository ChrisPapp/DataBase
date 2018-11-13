package Menus;

import java.awt.Toolkit;

import java.util.Scanner;

import Dbms.*;

public class MainMenu extends AbstractMenu {
	public MainMenu(Database data, boolean showAgain) {
		super(data, showAgain);
	}

	public void performAction() {

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
			System.out.println("WHICH CELL DO YOU WANT TO CHANGE? (Type position)");
			Scanner input = new Scanner(System.in);
			String cell = input.nextLine();
			DatabaseProcessing.changeData(data.getList() , cell);
			break;
		case 4:
			DatabaseProcessing.removeLine(data.getList());

		}

	}

	@Override
	protected void printMenu() {
		System.out.println("*** DATABASE MENU ***\n" +
				"  1. DISPLAY DATA \n" +
				"  2. INPUT DATA \n" +
				"  3. CHANGE DATA\n" +
				"  4. REMOVE LINE\n" +
				"  SELECT AN OPTION:");

	}
}
