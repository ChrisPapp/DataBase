package com.bitsplease.menus;

import com.bitsplease.dbms.Database;
import com.bitsplease.gui.ButtonList;

public class RemoveTableMenu extends AbstractMenu {

	protected Database database;

	public RemoveTableMenu(Database database, DatabaseMenu databaseMenu) {
	  super(databaseMenu);
		this.database = database;
		this.updateOptions();
    buttons = new ButtonList(options, this);
	}

	protected void updateOptions() {
	  options = new String[database.getTables().size() + 1];
		for (int i = 0; i < database.getTables().size(); i++) {
			options[i] = database.getTables().get(i).getName();
		}
		options[options.length - 1] = "Back";
	}

	@Override
  public void performAction() {
		setChoice(getChoice() - 1);
		if (getChoice() >= 0 && getChoice() < database.getTables().size()) {
			database.getTables().remove(getChoice());
		} else if (getChoice() == database.getTables().size()) {
			// Back
			calledFrom.run();
		} else if (getChoice() == -2) {
			error.printWrong("This is not a number");
		} else {
			error.printWrong("Out of Bounds");
		}
	}

}
