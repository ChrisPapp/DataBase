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
				list.add(new ArrayList<Object>()); //bazei mia kainouria lista
				list.get(list.size() - 1).add(category); //bazei stin teleytaia thesi tis megalisS listas to category
			} else break;
		
		} while (true);
	}

	public void inputData() {
		String data;
		
		System.out.println("PLEASE ENTER THE DATA IN YOUR DATABASE");
		
		while (true) {
			System.out.println("PRESS ENTER TO ADD A LINE OR ENTER 'EXIT' TO STOP");
			String answer = scanner.nextLine(); //diabazei mia fora
			if (!(answer.equals("exit") || answer.equals("Exit") || answer.equals("EXIT"))) {
				for (int field = 0; field < list.size(); field++) {
					System.out.println("ADD DATA TO: " + list.get(field).get(0));
					data = scanner.nextLine();
					list.get(field).add(data);
					System.out.println("Added data to:" + list.get(field).get(0)); //get(0) stin proti grammi einai to onoma
					System.out.println(); //Adeia Grammi
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
