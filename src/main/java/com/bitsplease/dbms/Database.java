package com.bitsplease.dbms;

import java.util.ArrayList;

/**
 * This hold an ArrayList of tables
 */
public class Database {

	private ArrayList<Table> tables;

	public Database() {
		setTables(new ArrayList<Table>());
	}

	public ArrayList<Table> getTables() {
		return tables;
	}

	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}

}
