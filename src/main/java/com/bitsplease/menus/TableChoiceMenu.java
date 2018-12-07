package com.bitsplease.menus;

import com.bitsplease.dbms.Database;

public class TableChoiceMenu extends RemoveTableMenu {

	private TableMenu tableMenu;

	public TableChoiceMenu(Database database) {
		super(database);
		tableMenu = new TableMenu();
	}

	@Override
	protected void performAction() {
		readChoice();
		choice--;
		if (choice >= 0 && choice < database.getTables().size()) {
			tableMenu.runWith(database.getTables().get(choice));
		} else if (choice == database.getTables().size()) {
			// Back
			showAgain = false;
		} else if (choice == -2) {
			error.printWrong("This is not a number");
		} else {
			error.printWrong("Out of Bounds");
		}

	}
}
