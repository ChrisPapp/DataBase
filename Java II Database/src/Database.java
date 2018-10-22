import java.util.ArrayList;
import java.util.Scanner;

public class Database {
	// Fill in our Database with fields and data
	private ArrayList<String> fields = new ArrayList<String>();
	private ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
	
	private Scanner scanner = new Scanner(System.in);

	public Database() {
		inputFields();
		inputData();
		DatabaseProcessing.displayData(fields, list); //For testing purposes
	}

	public void inputFields() { 
		String category = null; 
		
		System.out.println("PLEASE ENTER THE FIELDS OF YOUR DATABASE");
		System.out.println("WHEN YOU ARE DONE JUST ENTER EXIT");
		
		do {
			category = scanner.nextLine(); 	
			
			if ((category.equals("exit") || category.equals("Exit") || category.equals("EXIT")) == false) {
				fields.add(category); 
				list.add(new ArrayList<Object>()); //Prepei na ftiaxnei kai kainouria stili sti lista
			}
		
		} while ((category.equals("exit") || category.equals("Exit") || category.equals("EXIT")) == false);
	}

	// mexri edw trexei
	// auto den douleuei guyz prepei na to ftiaksoume
	// tha sas ekshghsw apo konta
	public void inputData() {
		
		System.out.println("PLEASE ENTER THE DATA IN YOUR DATABASE");
		System.out.println("AFTER YOU ADD A LINE YOU CAN ENTER 'EXIT' TO STOP");
		
		String data = scanner.nextLine();
		while (true) {
			if (!(data.equals("exit") || data.equals("Exit") || data.equals("EXIT"))) {
				for (int field = 0; field < fields.size(); field++) {
					list.get(field).add(data);
					data = scanner.nextLine();
				}
			} else break;
		}
		
		

	}
	

	public ArrayList<String> getFields() {
		return fields;
	}

	public void setFields(ArrayList<String> fields) {
		this.fields = fields;
	}

	public ArrayList<ArrayList<Object>> getList() {
		return list;
	}

	public void setList(ArrayList<ArrayList<Object>> list) {
		this.list = list;
	}
}
