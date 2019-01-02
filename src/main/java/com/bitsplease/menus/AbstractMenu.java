package com.bitsplease.menus;

import java.util.Scanner;

import com.bitsplease.dbms.StartMain;
import com.bitsplease.utilities.Wrong;

public abstract class AbstractMenu extends Thread {
  // Print main menu and run user's choice
  protected Scanner inputChoice = new Scanner(System.in);
  private static int choice;
  protected boolean showAgain;
  protected Wrong error = new Wrong("buzzer");

  /**
   * Runs the menu until showAgain becomes equal to false
   */
  public void run() {
    // If showAgain is true, then this will run again
    // after an action is performed
    showAgain = true;
    while (showAgain) {
      printMenu();
      readChoice();
      performAction();

      // After an action is completed, the menu is called again
    }
  }

  /**
   * Takes user input. If the user does not enter an integer it prints a message
   */
  public void readChoice() {
    try {
      setChoice(Integer.parseInt(inputChoice.nextLine()));
    } catch (NumberFormatException e) {
      setChoice(-1);
    }
  }
  
  public void readChoiceFromGUI() {
    try {
      setChoice(Integer.parseInt(StartMain.getWindow().getInputField().getCurrentText()));
    } catch (NumberFormatException e) {
      setChoice(-1);
    }
  }

  protected abstract void printMenu();

  protected abstract void performAction();

  public int getChoice() {
    return choice;
  }

  public void setChoice(int choice) {
    AbstractMenu.choice = choice;
  }

}
