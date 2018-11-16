package com.bitsplease.menus;

import java.util.Scanner;

import com.bitsplease.dbms.Database;

public abstract class AbstractMenu {
  // Print main menu and run user's choice
  protected static Database data;
  protected Scanner inputChoice = new Scanner(System.in);
  protected static int choice;
  protected boolean showAgain;

  public AbstractMenu(Database data, boolean showAgain) {
    AbstractMenu.data = data;
    this.showAgain = showAgain;
    menu(showAgain);
  }

  private void menu(boolean showAgain) { // If showAgain is true, then this will
                                         // run again
    // after an action is performed
    printMenu();
    performAction();
    if (showAgain == true)
      menu(showAgain); // After an action is completed, the menu is called again

  }

  protected abstract void printMenu();

  protected abstract void performAction();

  public Database getData() {
    return data;
  }

  public void setData(Database data) {
    AbstractMenu.data = data;
  }
}
