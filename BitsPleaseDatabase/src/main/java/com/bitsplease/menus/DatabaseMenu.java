package com.bitsplease.menus;

import com.bitsplease.dbms.Database;
import com.bitsplease.dbms.Table;

public class DatabaseMenu extends AbstractMenu {
  private Database database;
  private TableMenu tableMenu;

  public DatabaseMenu(Database database) {
    this.database = database;
    tableMenu = new TableMenu();
  }

  @Override
  protected void printMenu() {
    int i = 0;
    for (i = 0; i < database.getTables().size(); i++) {
      System.out
          .println((i + 1) + ". " + database.getTables().get(i).getName());
    }
    System.out.println((i + 1) + ". Create new Table");
  }

  @Override
  protected void performAction() {
    choice = inputChoice.nextInt() - 1;
    if (choice == database.getTables().size()) {
      database.getTables().add(new Table());
    } else if (choice >= 0 && choice < database.getTables().size()) {
      tableMenu.runWith(database.getTables().get(choice));
    } else {
      System.out.println("Out of bounds");
    }

  }

}
