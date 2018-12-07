package com.bitsplease.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.filechooser.FileSystemView;

import com.bitsplease.dbms.Table;

/**
 * Saves and Loads tables from Documents directory
 * 
 * @author ChrisPapp
 *
 */
public class MemoryCard {
	private static String filepath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath()
			+ "\\BitsPleaseTables";

	/**
	 * Saves a table to Documents (Windows) or Home (Linux)
	 * 
	 * @param table
	 *            to be saved
	 */
	public static void save(Table table) {
		makeDirectory();
		File file = new File(filepath, table.getName());
		FileOutputStream fos;
		ObjectOutputStream oos;
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(table);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Makes BitsPleaseTables directory to Documents on Windows or Home on Linux, if
	 * it is not already there
	 */
	public static void makeDirectory() {
		File file = new File(filepath);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("BitsPleaseTables directory created");
			} else {
				System.out.println("Directory creation failed");
			}
		}
	}

	/**
	 * Loads a table that's already saved
	 * 
	 * @param name
	 *            Name of table to be loaded
	 * @return required table if it exists, null if it does not
	 */
	public static Table load(String name) {
		FileInputStream fis;
		ObjectInputStream ois;
		try {
			File file = new File(filepath, name);
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			Table table = (Table) ois.readObject();
			ois.close();
			return table;
		} catch (FileNotFoundException e) {
			System.out.println("Error 404: Not found");
		} catch (IOException e) {
			System.out.println("IO Exception");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
		}
		return null;
	}
}
