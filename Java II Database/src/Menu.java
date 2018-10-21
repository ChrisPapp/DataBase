import java.util.Scanner;
public class Menu {
	//Print main menu and run user's choice
	private Database data;
	private static Scanner inputChoice = new Scanner(System.in);
	
	public Menu(Database data) {
		this.data = data;
	}

	
	public static void printMenu() {
		System.out.println("   *** DATABASE MENU ***\n");
		System.out.println("  1. DISPLAY DATA ");
		System.out.println("  2. INPUT DATA ");
		System.out.println("  3. CHANGE DATA ");
		System.out.println("  4. DELETE DATA ");
		System.out.println("  5. SWAP DATA ");
		System.out.print("  SELECT AN OPTION: "); 
	}
	
	// auto den to exw oloklhlwsei 
	public static void performAction() {
		
		int choice = inputChoice.nextInt();
		
		switch (choice) { 
		case 1: choice = 1; 
			
		case 2: choice = 2; 
		
		case 3: choice = 3; 
		
		case 4: choice = 4; 
			
		}
		
		
	}

	public Database getData() {
		return data;
	}
	
	public void setData(Database data) {
		this.data = data;
	}
}
