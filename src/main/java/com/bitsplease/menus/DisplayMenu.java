package com.bitsplease.menus;

import com.bitsplease.dbms.Database;

public class DisplayMenu extends AbstractMenu{
	
    protected Database database;
    protected static boolean style;

	public DisplayMenu(Database database) {
	    this.database = database;
	}
	
	@Override
	protected void printMenu() {
		System.out.println(
				"1. Display Entire Data \n" + "2. Display Shortcut \n");
	}
	
	@Override
	protected void performAction() {
		readChoice();
		switch (choice) {
		case 1:
			style = true;
			break;
		case 2:
			style = false;
			break;
		default:
			error.printWrong("Out of Bounds");
			
		}
	}

}
