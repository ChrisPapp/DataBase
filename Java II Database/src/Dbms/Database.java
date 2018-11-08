package Dbms;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
	// Fill in our Database with fields and data
	private ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
	
	private Scanner scanner = new Scanner(System.in);

	public Database() {
		inputFields();
		inputData();
	}

	public void inputFields() { 
		String category;
		
		do {
			
			System.out.println("PLEASE ENTER THE FIELD #" + (list.size() + 1) + " OF YOUR DATABASE");
			System.out.println("OR IF YOU ARE DONE JUST ENTER EXIT");
			
			category = scanner.nextLine(); 	
			if ((category.equals("exit") || category.equals("Exit") || category.equals("EXIT")) == false) {
				list.add(new ArrayList<Object>()); //Add a new List
				list.get(list.size() - 1).add(category); //Add category to the last position
			} else break;
		
		} while (true);
	}

	public void inputData() {
		String data;
		
		System.out.println("PLEASE ENTER THE DATA IN YOUR DATABASE\n");
		
		while (true) {
			System.out.println("PRESS ENTER TO ADD A LINE OR ENTER 'EXIT' TO STOP");
			String answer = scanner.nextLine(); //Read user's answer
			if (!(answer.equals("exit") || answer.equals("Exit") || answer.equals("EXIT"))) {
				for (int field = 0; field < list.size(); field++) {
					System.out.println("ADD DATA TO: " + list.get(field).get(0));
					data = scanner.nextLine();
					while (data.length() > 20) {
						System.out.print("*TOO BIG FOR '" + list.get(field).get(0) + "'*\nENTER AGAIN: ");
						data = scanner.nextLine();
						System.out.println();
					}
					list.get(field).add(data);
					System.out.println("Added data to:" + list.get(field).get(0)); //The first line has the field names
					System.out.println(); //Empty Line
				}
			} else {
				System.out.println("DATA INSERTION STOPPED");
				break;
			}
		}
		
		

	}
	

	public ArrayList<ArrayList<Object>> getList() {
		return list;
	}

	public void setList(ArrayList<ArrayList<Object>> list) {
		this.list = list;
	}
}
