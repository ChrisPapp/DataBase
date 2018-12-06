package com.bitsplease.menus;

import java.util.Scanner;

import com.bitsplease.dbms.Database;
import com.bitsplease.dbms.PlaySound;
import com.bitsplease.dbms.Table;
import com.bitsplease.utilities.MemoryCard;

public class DatabaseMenu extends AbstractMenu {
  protected Database database;
  private SettingsMenu settingsMenu;
  private TableChoiceMenu tableChoiceMenu;
  private RemoveTableMenu removeTableMenu;

  public DatabaseMenu(Database database) {
    this.database = database;
    removeTableMenu = new RemoveTableMenu(database);
    tableChoiceMenu = new TableChoiceMenu(database);
    settingsMenu = new SettingsMenu(database);
  }

  @Override
  protected void printMenu() {
    System.out.println(
        "1. Edit a Table \n" + "2. Create new Table \n" + "3. Load Table \n"
            + "4. Remove Table \n" + "5. Settings \n" + "6. Exit \n");
  }

  @Override
  protected void performAction() {
    readChoice();
    switch (choice) {
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
      new PlaySound().play("antegeia.wav");
      System.exit(0);
    case -1:
      error.printWrong("This is not a number");
      break;
    default:
      System.out.println("Out of bounds");
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
