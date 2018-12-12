package com.bitsplease.dbms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import com.bitsplease.menus.DisplayMenu;

public class Table implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fill in our Table with fields and table
	private String name;

	private ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

	public Table() {
		inputName();
		inputFields();
		inputData();
	}

	public Table(String name, ArrayList<ArrayList<String>> list) {
		super();
		this.name = name;
		this.list = list;
	}

	private void inputName() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the name of this Table");
		setName(scanner.nextLine());
	}

	public void inputFields() {
		Scanner scanner = new Scanner(System.in);
		String category;
		do {
			System.out.println("PLEASE ENTER THE FIELD #" + (list.size() + 1) + " OF YOUR DATABASE");
			System.out.println("OR IF YOU ARE DONE JUST ENTER EXIT");

			category = scanner.nextLine();
			if (!(TableProcessing.equalsExit(category))) { // If category is not
															// "exit"
				list.add(new ArrayList<String>()); // Add a new List
				list.get(list.size() - 1).add(category); // Add category to the last
															// position
			} else
				break;

		} while (true);
	}

	public void inputData() {
		Scanner scanner = new Scanner(System.in);
		String data;
		System.out.println("PLEASE ENTER THE DATA IN YOUR DATABASE\n");
		while (true) {
			System.out.println("PRESS ENTER TO ADD A LINE OR ENTER 'EXIT' TO STOP");
			String answer = scanner.nextLine(); // Read user's answer
			if (!(TableProcessing.equalsExit(answer))) {
				for (int field = 0; field < list.size(); field++) {
					System.out.println("ADD DATA TO: " + list.get(field).get(0));
					data = scanner.nextLine();
					System.out.println();
					list.get(field).add(data);
					// The first line has the field names
					System.out.println("Added table to:" + list.get(field).get(0));
					System.out.println(); // Empty Line
				}
			} else {
				System.out.println("DATA INSERTION STOPPED");
				break;
			}
		}
	}

	@Override
	public boolean equals(Object o) {
	  if ( o == this) { // Compare hash codes
	    return true;
	  }
		// If given Object is not a Table return false
		if (!(o instanceof Table)) {
			return false;
		}
		// If I am still here, that means o is a Table
		Table table = (Table) o;
		// I hereby declare that two Tables are equal
		// only and only if their names are equal
		return table.getName().equals(this.getName());

	}

	public void print() {
		if (Printer.isMarginVariable()) {
			Printer.displayData(this.getList());
		} else {
			Printer.printShortcut(this.getList());
		}
	}

	public ArrayList<ArrayList<String>> getList() {
		return list;
	}

	public void setList(ArrayList<ArrayList<String>> list) {
		this.list = list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
