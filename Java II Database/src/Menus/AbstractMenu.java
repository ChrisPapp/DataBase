package Menus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Dbms.*;

public abstract class AbstractMenu {
	// Print main menu and run user's choice
	protected static Database data;
	protected Scanner inputChoice = new Scanner(System.in);
	private Scanner inputFile;
	protected static int choice;
	protected boolean showAgain;

	public AbstractMenu(Database data, String path, boolean showAgain) { //Experimental
		AbstractMenu.data = data;
		this.showAgain = showAgain;
		try {
			inputFile = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		menu(showAgain);
	}
	
	public AbstractMenu(Database data, boolean showAgain) {
		AbstractMenu.data = data;
		this.showAgain = showAgain;
		menu(showAgain);
	}

	private void menu(boolean showAgain) { // If showAgain is true, then this will run again
											// after an action is performed
		printMenu();
		performAction();
		if (showAgain == true)
			menu(showAgain); // After an action is completed, the menu is called again

	}

	private void printMenuExperimantal() {
		while(inputFile.hasNextLine()) {
			System.out.println(inputFile.nextLine());
		}
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
