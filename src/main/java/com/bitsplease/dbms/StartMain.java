package com.bitsplease.dbms;

import com.bitsplease.gui.Window;
import com.bitsplease.menus.DatabaseMenu;

public class StartMain { // StartMain is responsible for calling all the methods needed
  private static Window window;
	public static void main(String[] args) {
		System.out.println("****** BITSPLEASE ****** ");
		System.out.println("WELCOME TO THE BITSPLEASE DATABASE \n");
		window = new Window();
		new DatabaseMenu(new Database()).run();
	}
  public static Window getWindow() {
    return window;
  }
}
