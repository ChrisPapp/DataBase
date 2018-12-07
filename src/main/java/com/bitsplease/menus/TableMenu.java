package com.bitsplease.menus;

import java.util.ArrayList;
import java.util.Scanner;
import com.bitsplease.dbms.TableArithmetics;
import com.bitsplease.dbms.Table;
import com.bitsplease.dbms.TableProcessing;
import com.bitsplease.utilities.MemoryCard;

public class TableMenu extends AbstractMenu {
	private static Scanner inputOperation = new Scanner(System.in);
	private Table table;

	public Table getTable() {
		return table;
	}

	public void runWith(Table table) {
		// Menu will run with this table
		this.table = table;
		run();
	}

	public void performAction() {
		readChoice();
		switch (choice) {
		case 1:
			table.print();
			break;
		case 2:
			table.inputData();
			break;
		case 3:
			System.out.println("WHICH CELL DO YOU WANT TO CHANGE? (Type position)");
			Scanner input = new Scanner(System.in);
			String cell = input.nextLine();
			TableProcessing.changeData(table.getList(), cell);
			break;
		case 4:
			TableProcessing.removeLine(table.getList());
			break;
		case 5:
			TableProcessing.removeColumn(table.getList());
			break;
		case 6:
			System.out.print(" GIVE OPERATION: ");
			String askedOperation = inputOperation.nextLine();
			TableArithmetics.startingOperationsBetweenColumns(table.getList(), askedOperation);
			break;
		case 7:
			search();
			break;
		case 8:
			sort();
			break;
		case 9:
			MemoryCard.save(table);
			break;
		case 0:
			showAgain = false;
			break;
		case -1:
			error.printWrong("This is not a number");
			break;
		default:
			error.printWrong("Out of Bounds");
			break;
		}

	}

	private void sort() {
		System.out.println("Which field (1 - " + (table.getList().size()) + ")");
		TableProcessing.rotate(table.getList()); // Line Mode
		// Print fields as a column
		TableProcessing.printColumn(table.getList().get(0));
		TableProcessing.rotate(table.getList()); // Column Mode
		Scanner input = new Scanner(System.in);
		int column = input.nextInt() - 1;
		TableProcessing.sort(table.getList(), column, 1, table.getList().get(column).size() - 1);
	}

	private void search() {
		System.out.println("What are you looking for?");
		Scanner input = new Scanner(System.in);
		String item = input.nextLine();
		ArrayList<String> cells = new ArrayList<String>(TableProcessing.find(table.getList(), item));
		if (cells.size() == 0) {
			System.out.println("The item was not found");
		} else {
			System.out.println("The item was found in these cells:");
			TableProcessing.printColumn(cells);
		}
		// input.close();
	}

	@Override
	protected final void printMenu() {
		System.out.println(
				"\n*** DATABASE MENU ***\n" + "  1. DISPLAY DATA \n" + "  2. INPUT DATA \n" + "  3. CHANGE DATA\n"
						+ "  4. REMOVE LINE\n" + "  5. REMOVE COLUMN\n" + "  6. OPERATIONS BETWEEN COLUMNS \n"
						+ "  7. SEARCH \n" + "  8. SORT \n" + "  9. SAVE \n" + "  0. BACK \n" + "  SELECT AN OPTION: ");
	}
}
