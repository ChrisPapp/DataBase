package com.bitsplease.menus;

import java.util.Scanner;

import com.bitsplease.utilities.Wrong;

public abstract class AbstractMenu implements Runnable {
	// Print main menu and run user's choice
	protected Scanner inputChoice = new Scanner(System.in);
	protected static int choice;
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
			performAction();
			// After an action is completed, the menu is called again
		}
	}

	/**
	 * Takes user input. If the user does not enter an integer it prints a message
	 */
	public void readChoice() {
		try {
			choice = Integer.parseInt(inputChoice.nextLine());
		} catch (NumberFormatException e) {
			choice = -1;
		}
	}

	protected abstract void printMenu();

	protected abstract void performAction();

}
