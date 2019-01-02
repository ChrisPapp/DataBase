package com.bitsplease.menus;

import java.util.Scanner;

import com.bitsplease.dbms.Database;
import com.bitsplease.dbms.Table;
import com.bitsplease.gui.ButtonList;
import com.bitsplease.utilities.MemoryCard;
import com.bitsplease.utilities.PlaySound;

public class DatabaseMenu extends AbstractMenu {
  protected Database database;
  private SettingsMenu settingsMenu;
  private TableChoiceMenu tableChoiceMenu;
  private RemoveTableMenu removeTableMenu;

  public DatabaseMenu(Database database) {
    super(null);
    options = new String[] { "Edit a Table", "Create new Table", "Load Table",
        "Remove Table", "Settings", "Exit" };
    buttons = new ButtonList(options, this);
    this.database = database;
    removeTableMenu = new RemoveTableMenu(database, this);
    tableChoiceMenu = new TableChoiceMenu(database, this);
    settingsMenu = new SettingsMenu(this);
  }

  @Override
  public void performAction() {
    switch (getChoice()) {
    case 1:
      tableChoiceMenu.run();
      break;
    case 2:
      database.getTables().add(new Table());
      break;
    case 3:
      load();
      break;
    case 4:
      removeTableMenu.run();
      break;
    case 5:
      settingsMenu.run();
      break;
    case 6:
      System.out.println("Exiting... Thanks for choosing BitsPlease");
      System.exit(0);
    case -1:
      error.printWrong("This is not a number");
      break;
    default:
      error.printWrong("Out of bounds");
      break;
    }

  }

  private void load() {
    System.out.println("Enter Table name:");
    Scanner scanner = new Scanner(System.in);
    String name = scanner.next();
    Table suspectTable = MemoryCard.load(name);
    if (suspectTable != null) {
      if (database.getTables().contains(suspectTable)) {
        System.out.println("Overwriting " + suspectTable.getName() + "\n");
        int index = database.getTables().indexOf(suspectTable);
        // Overwrite table
        database.getTables().set(index, suspectTable);
      } else {
        database.getTables().add(suspectTable);
      }
    }
  }

}
