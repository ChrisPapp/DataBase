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
    choice = inputChoice.nextInt() - 1;
    if (choice >= 0 && choice < database.getTables().size()) {
      tableMenu.runWith(database.getTables().get(choice));
    } else if (choice == database.getTables().size()) {
      // Back
      showAgain = false;
    } else {
      System.out.println("Out of Bounds");
    }
  }

}
