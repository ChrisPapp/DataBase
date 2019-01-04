package com.bitsplease.dbms;

import java.util.ArrayList;

public class BasicArithmeticOperations {

	private static double sum = 0;
	private static double product = 1;
	private static double average = 0;
	
	public static void sumOfAll(ArrayList<ArrayList<String>> lists, int askedColumn) {
		for (int i = 1; i < lists.get(askedColumn).size(); i++) {
			double temp = Double.parseDouble((String) lists.get(askedColumn).get(i));
			sum += temp;
		}
	}

	public static void productOfAll(ArrayList<ArrayList<String>> lists, int askedColumn) {
		for (int i = 1; i < lists.get(askedColumn).size(); i++) {
			double temp = Double.parseDouble((String) lists.get(askedColumn).get(i));
			product *= temp;
		}
	}

	public static void averageOfAll(ArrayList<ArrayList<String>> lists, int askedColumn) {
		average = sum / (lists.get(askedColumn).size() - 1);
	}

	public static void displayMore(int askedColumn) {
		System.out.printf("Column %s \n", TableProcessing.numToCode(askedColumn));
		System.out.printf("Sum: %f \nProduct: %f \nAverage: %f \n", sum, product, average);
	}

}
