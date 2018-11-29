package com.bitsplease.menus;

import java.util.Scanner;

public abstract class AbstractMenu implements Runnable {
  // Print main menu and run user's choice
  protected Scanner inputChoice = new Scanner(System.in);
  protected static int choice;
  protected boolean showAgain = true;

  public void run() {
    // If showAgain is true, then this will run again
    // after an action is performed
    while (showAgain) {
      printMenu();
      performAction();
      // After an action is completed, the menu is called again
    }
  }

  protected abstract void printMenu();

  protected abstract void performAction();

}
