package com.bitsplease.menus;

import java.util.ArrayList;
import java.util.Scanner;

import com.bitsplease.dbms.BasicArithmeticOperations;
import com.bitsplease.dbms.TableArithmetics;

public class TableOperationMenu extends TableMenu {	
	public TableOperationMenu() {		
	}
	
	@Override
	protected void performAction() {
		Scanner scanner = new Scanner(System.in);
		
		int choice = -1;
		choice = scanner.nextInt();
		switch (choice) {
		case 1: 
			System.out.print("GIVE THE COLUMN NUMBER:");
			int askedColumn = -1;
			try {
				askedColumn = scanner.nextInt() - 1;
			} catch (Exception e) {
				error.printWrong("Out of bounds");
			}
			BasicArithmeticOperations.sumOfAll(getTable().getList(), askedColumn);
			BasicArithmeticOperations.productOfAll(getTable().getList(), askedColumn);
			BasicArithmeticOperations.averageOfAll(getTable().getList(), askedColumn);
			BasicArithmeticOperations.displayMore(askedColumn);
			break;
		case 2:
			System.out.print("GIVE THE MATHEMATICAL OPERATION \n (e.g. (columnName + columnName) / 2 = newColumnName):");
			String mathOperation;
			mathOperation = scanner.nextLine();
			try {
				TableArithmetics.startingOperationsBetweenColumns(getTable().getList(), mathOperation);
			} catch (Exception e) {
				System.out.println("ERROR! Something has occured! Check the mathematical operation again!");
			}
		}
	}
	
	@Override
	protected void printMenu() {
		System.out.println("\nMATHEMATICAL OPERATION MENU \n" + "1. SUM & PRODUCT & AVERAGE \n" + "2. OPERATIONS BETWEEN COLUMNS");
	}
}
