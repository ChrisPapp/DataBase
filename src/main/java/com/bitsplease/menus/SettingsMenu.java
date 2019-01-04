package com.bitsplease.menus;

import com.bitsplease.gui.ButtonList;

public class SettingsMenu extends AbstractMenu {

  private DisplayMenu displayMenu;

  public SettingsMenu(DatabaseMenu databaseMenu) {
    super(databaseMenu);
    displayMenu = new DisplayMenu(this);
    options = new String[] { "Data Display Settings", "Audio Settings",
        "Back" };
    buttons = new ButtonList(options, this);

  }

  @Override
  public void performAction() {
    switch (getChoice()) {
    case 1:
      displayMenu.run();
      break;
    case 2:
      System.out.println("Under Development");
      this.showButtons();
      break;
    case 3:
      calledFrom.run();
      break;
    case -1:
      error.printWrong("This is not a number");
      break;
    default:
      error.printWrong("Out of bounds");
    }

  }

}
