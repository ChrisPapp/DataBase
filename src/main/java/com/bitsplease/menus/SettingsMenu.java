package com.bitsplease.menus;

import com.bitsplease.dbms.Database;

public class SettingsMenu extends AbstractMenu {
	
	protected Database database;
	private DisplayMenu displayMenu;

  public SettingsMenu(Database database) {
      this.database = database;
	  DisplayMenu displayMenu = new DisplayMenu(database);
  }
  	
  @Override
  protected void printMenu() {
    System.out.println(
        "1. Data Display Settings \n" + "2. Audio Settings \n" + "3. Back \n");

  }

  @Override
  protected void performAction() {
    readChoice();
    switch (choice) {
    case 1:
      System.out.println("Under Development");
      break;
    case 2:
      System.out.println("Under Development");
      break;
    case 3:
      showAgain = false;
      break;
    case -1:
      error.printWrong("This is not a number");
      break;
    default:
      error.printWrong("Out of bounds");
    }

  }

}
