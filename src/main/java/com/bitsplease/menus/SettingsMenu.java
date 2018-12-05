package com.bitsplease.menus;

public class SettingsMenu extends AbstractMenu {

  @Override
  protected void printMenu() {
    System.out.println(
        "1. Data Display Settings \n" + "2. Audio Settings \n" + "3. Back \n");

  }

  @Override
  protected void performAction() {
    readChoice();
    switch (choice) {
    case 1:
      System.out.println("Under Development");
      break;
    case 2:
      System.out.println("Under Development");
      break;
    case 3:
      showAgain = false;
      break;
    case -1:
      error.printWrong("This is not a number");
      break;
    default:
      error.printWrong("Out of bounds");
    }

  }

}
