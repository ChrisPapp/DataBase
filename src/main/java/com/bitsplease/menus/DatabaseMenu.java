package com.bitsplease.menus;

import com.bitsplease.dbms.Database;
import com.bitsplease.dbms.Table;

public class DatabaseMenu extends AbstractMenu {
	protected Database database;
	private SettingsMenu settingsMenu;
	private TableChoiceMenu tableChoiceMenu;
	private RemoveTableMenu removeTableMenu;

	public DatabaseMenu(Database database) {
		this.database = database;
		removeTableMenu = new RemoveTableMenu(database);
		tableChoiceMenu = new TableChoiceMenu(database);
		settingsMenu = new SettingsMenu();
	}


  @Override
	protected void printMenu() {
		System.out.println("1. Edit a Table \n" + "2. Create new Table \n"
				+ "3. Load Table \n" + "4. Remove Table \n" + "5. Settings \n");
	}

	@Override
	protected void performAction() {
		choice = inputChoice.nextInt();
		switch (choice) {
			case 1 :
				tableChoiceMenu.run();
				break;
			case 2 :
				database.getTables().add(new Table());
				break;
			case 3:
			  System.out.println("Under Development");
			  break;
			case 4 :
				removeTableMenu.run();
				break;
			case 5 :
				settingsMenu.run();
			default :
				System.out.println("Out of bounds");
				break;
		}

	}

}
