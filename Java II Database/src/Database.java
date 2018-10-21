import java.util.ArrayList;
import java.util.Scanner;

public class Database {
	// Fill in our Database with fields and data
	private ArrayList<String> fields = new ArrayList<String>();
	private ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
	
	private Scanner inputField = new Scanner(System.in);
	private Scanner inputNewData = new Scanner(System.in);  

	public Database() {
		inputFields();
		inputData();
	}

	public void inputFields() { 
		String category = null; 
		
		System.out.println("PLEASE ENTER THE FIELDS OF YOUR DATABASE");
		System.out.println("WHEN YOU ARE DONE JUST ENTER EXIT");
		
		do {
			category = inputField.next(); 	
			
			if ((category.equals("exit") || category.equals("Exit") || category.equals("EXIT")) == false) {
				fields.add(category); 
			}
		
		} while ((category.equals("exit") || category.equals("Exit") || category.equals("EXIT")) == false);
	}

	// mexri edw trexei
	// auto den douleuei guyz prepei na to ftiaksoume
	// tha sas ekshghsw apo konta
	public void inputData() {
		int i = 0; 
		String storeData = null; 
		
		System.out.println("PLEASE ENTER STORE THE DATA IN YOUR DATABASE");
		System.out.println("WHEN YOU ARE DONE JUST ENTER EXIT");
		
		while (i < fields.size()) {
			storeData = inputNewData.next();			
			//list.add();
			while ((storeData.equals("exit") || storeData.equals("Exit") || storeData.equals("EXIT")) == false) {
				list.get(i).add(storeData);
			}
			i++; 
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
