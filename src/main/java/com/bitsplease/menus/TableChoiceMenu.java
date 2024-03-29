package com.bitsplease.menus;

import com.bitsplease.dbms.Database;
import com.bitsplease.dbms.StartMain;
import com.bitsplease.dbms.Table;

public class TableChoiceMenu extends RemoveTableMenu {

  private TableMenu tableMenu;

  public TableChoiceMenu(Database database, DatabaseMenu databaseMenu) {
    super(database, databaseMenu);
    tableMenu = new TableMenu(this);
  }

  @Override
  public void performAction() {
    setChoice(getChoice() - 1);
    if (getChoice() >= 0 && getChoice() < database.getTables().size()) {
      Table table = database.getTables().get(getChoice());
      // Update Window
      StartMain.getWindow().getContentPane().revalidate();
      StartMain.getWindow().getContentPane().repaint();
      tableMenu.runWith(table);
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
