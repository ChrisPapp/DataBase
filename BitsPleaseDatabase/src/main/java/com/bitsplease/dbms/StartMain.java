package com.bitsplease.dbms;

import com.bitsplease.menus.MainMenu;

public class StartMain {
  // StartMain is responsible for calling all the methods needed
  public static void main(String[] args) {
    System.out.println("****** BITSPLEASE ****** ");
    System.out.println("WELCOME TO THE BITSPLEASE DATABASE \n");

    new MainMenu(new Database(), true);
  }

}
