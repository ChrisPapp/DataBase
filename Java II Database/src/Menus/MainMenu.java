package Menus;

import java.awt.Toolkit;

import java.util.Scanner;

import Dbms.*;

public class MainMenu extends AbstractMenu {
	private static Scanner inputOperation = new Scanner(System.in);
	
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
				if ((choice < 1)||(choice > 6)) {
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
		case 5:
			System.out.println(" SELECT COLUMN: ");
			int askedColumn = inputChoice.nextInt() - 1;
			if (DataProcessingMore.areAllNumbers(data.getList(), askedColumn)) {
				DataProcessingMore.sumOfAll(data.getList(), askedColumn);
				DataProcessingMore.productOfAll(data.getList(), askedColumn);
				DataProcessingMore.averageOfAll(data.getList(), askedColumn);
				DataProcessingMore.displayMore();
				break;
			} else {
				System.out.println("Check your data and change the ones that are not numbers!");
			}
		case 6:
			System.out.println(" GIVE OPERATION ");
			String askedOperation = inputOperation.nextLine();
			DataProcessingMore.extraordinaryOption(data.getList(), askedOperation);
		}

	}

	@Override
	protected void printMenu() {
		System.out.println("*** DATABASE MENU ***\n" +
				"  1. DISPLAY DATA \n" +
				"  2. INPUT DATA \n" +
				"  3. CHANGE DATA\n" +
				"  4. REMOVE LINE\n" +
				"  5. MORE CALCULATIONS \n" +
				"  6. OPERATIONS BETWEEN COLUMNS \n" +
				"  SELECT AN OPTION:");

	}
}
