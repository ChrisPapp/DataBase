package com.bitsplease.menus;

public class SettingsMenu extends AbstractMenu {

  @Override
  protected void printMenu() {
    System.out.println(
        "1. Data Display Settings \n" + "2. Audio Settings \n" + "3. Back \n");

  }

  @Override
  protected void performAction() {
    choice = inputChoice.nextInt();
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
    }

  }

}
