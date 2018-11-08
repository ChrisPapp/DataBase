package Menus;

import Dbms.*;

public class MainMenu extends AbstractMenu {
	public MainMenu(Database data, boolean showAgain) {
		super(data, showAgain);
	}

	public void performAction() {

		int choice = inputChoice.nextInt();

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
