package com.bitsplease.menus;

import com.bitsplease.dbms.Database;

public class RemoveTableMenu extends AbstractMenu {

	protected Database database;

	public RemoveTableMenu(Database database) {
		this.database = database;
	}

	@Override
	protected void printMenu() {
		for (int i = 0; i < database.getTables().size(); i++) {
			System.out.println((i + 1) + ". " + database.getTables().get(i).getName());
		}
		System.out.println((database.getTables().size() + 1) + ". Back");
	}

	@Override
	protected void performAction() {
		readChoice();
		choice--;
		if (choice >= 0 && choice < database.getTables().size()) {
			database.getTables().remove(choice);
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
