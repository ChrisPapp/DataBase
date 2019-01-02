package com.bitsplease.menus;

import com.bitsplease.dbms.Printer;
import com.bitsplease.gui.ButtonList;

/**
 * Contains the options for displaying.
 * 
 * @author The BitsPlease Project
 *
 */

public class DisplayMenu extends AbstractMenu {

  public DisplayMenu(SettingsMenu settingsMenu) {
    super(settingsMenu);
    options = new String[] {"Display Entire Data", "Display Fixed Space Between Data"};
    buttons = new ButtonList(options, this);
  }

  @Override
  public void performAction() {
    switch (getChoice()) {
    case 1:
      Printer.setMarginVariable(true);
      calledFrom.run();
      break;
    case 2:
      Printer.setMarginVariable(false);
      calledFrom.run();
      break;
    default:
      error.printWrong("Out of Bounds");

    }
  }

}
