package com.bitsplease.menus;

import com.bitsplease.dbms.Database;
import com.bitsplease.gui.ButtonList;

public class RemoveTableMenu extends AbstractMenu {

  protected Database database;

  public RemoveTableMenu(Database database, DatabaseMenu databaseMenu) {
    super(databaseMenu);
    this.database = database;
    createOptions();
    buttons = new ButtonList(options, this);
  }

  protected void createOptions() {
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
    } 
    calledFrom.run();
  }

}
