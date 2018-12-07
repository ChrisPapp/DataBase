package com.bitsplease.menus;

import com.bitsplease.dbms.Printer;

/**
 * Contains the options for displaying.
 * 
 * @author The BitsPlease Project
 *
 */

public class DisplayMenu extends AbstractMenu {

	@Override
	protected void printMenu() {
		System.out.println("1. Display Entire Data \n" + "2. Display Shortcut \n");
	}

	@Override
	protected void performAction() {
		readChoice();
		switch (choice) {
		case 1:
			Printer.setMarginVariable(true);
			showAgain = false;
			break;
		case 2:
			Printer.setMarginVariable(false);
			showAgain = false;
			break;
		default:
			error.printWrong("Out of Bounds");

		}
	}

}
