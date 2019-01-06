package com.bitsplease.menus;

import javax.swing.JOptionPane;

import com.bitsplease.dbms.Database;
import com.bitsplease.dbms.StartMain;
import com.bitsplease.dbms.Table;
import com.bitsplease.gui.ButtonList;
import com.bitsplease.utilities.MemoryCard;
import com.bitsplease.utilities.PlaySound;

public class DatabaseMenu extends AbstractMenu {
  protected Database database;
  private SettingsMenu settingsMenu;

  public DatabaseMenu(Database database) {
    super(null);
    options = new String[] { "Edit a Table", "Create new Table", "Load Table",
        "Remove Table", "Exit" };
    buttons = new ButtonList(options, this);
    this.database = database;
    settingsMenu = new SettingsMenu(this);
  }

  @Override
  public void performAction() {
    switch (getChoice()) {
    case 1:
      // Make a new Menu with the current database options
      new TableChoiceMenu(database, this).run();
      break;
    case 2:
      Table table = new Table();
      if (table.isConstructionCompleted()) {
        database.getTables().add(table);
      }
      this.showButtons();
      break;
    case 3:
      load();
      this.showButtons();
      break;
    case 4:
      // Make a new Menu with the current database options
      new RemoveTableMenu(database, this).run();
      break;
    case 5:
      new PlaySound().play("exit");
      System.exit(0);
      break;
    default:
      error.printWrong("Out of bounds");
      break;
    }

  }

  private void load() {
    String name = JOptionPane.showInputDialog(StartMain.getWindow(),
        "Enter Table Name", "Awesome Table Loader",
        JOptionPane.QUESTION_MESSAGE);
    if (name == null) {
      return;
    }
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
