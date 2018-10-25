import java.util.*;

public class DatabaseProcessing {
	// Process data from database

	public DatabaseProcessing() {

	}

	public static void displayData(ArrayList<ArrayList<Object>> lists) {
		ArrayList<Object> fieldFrame = new ArrayList<Object>();
		ArrayList<Object> dataFrame = new ArrayList<Object>();
		
		//prospathisa na kanw ayto po eipame me to excel alla me arithmous gia arxxh
		//mexri na vroume tropo na to kanoume me grammata
		//thelei akoma douleia gia stoixish alla kati ginetai
		
		int counter = 0;
		fieldFrame.add(0, " ");
		for (int i=1; i<lists.size(); i++) { //to prwto kouti einai keno opws sto excel
			fieldFrame.add(++counter);
		}
		counter = 0;
		for (int i=0; i<lists.get(0).size(); i++) {
			dataFrame.add(counter++);
		}
		for(int i=0; i<fieldFrame.size(); i++) {
			System.out.print(" |	" + fieldFrame.get(i) + "	");
		}
		System.out.println();
		counter = 0;
		
		for (int line = 0; line < lists.get(0).size(); line++) {
			System.out.print(dataFrame.get(line));
			for (int field = 0; field < lists.size(); field++) {
				System.out.print("|	" + lists.get(field).get(line) + "	");
				if (++counter % lists.size() == 0) System.out.println(); //kathe lists.size() stoixeia katebainei grammi
			}
			System.out.println();
		}
	}
	
	public static void inputData(ArrayList<ArrayList<Object>> lists, ArrayList<Object> newline) {  
		lists.add(newline); 
	}
	
	public static void changeData(ArrayList<ArrayList<Object>> lists, int line, String newVariable) {
		int i = 0; 
		boolean boo = true;
		
		do {
			
			if (line == i) { 
			lists.get(i).add(line, newVariable); 
			boo = false;
			}
			i++; 
		} while (boo == true && i < lists.size());
	}
	 
	public static void deleteData(ArrayList<ArrayList<Object>> lists, int line) {
		int i = 0; 
		boolean boo = true;
		
		do {
			
			if (line == i) { 
				lists.get(i).remove(line); // h lists.remove(line); 
				boo = false; 
			}
			i++; 
		} while (boo == true && i < lists.size());
	}
	
	public static void removeLine(ArrayList<ArrayList<Object>> lists) {
		System.out.println("Which line do you want to remove");
		Scanner input = new Scanner(System.in);
		int line = Integer.parseInt(input.nextLine());
		input.close();
		for (int i = 0; i < lists.size(); i++) {
			lists.get(i).remove(line);
		}
	}
	
	public static void swapData(ArrayList<ArrayList<Object>> lists, int a, int b) {
		for (int i = 0; i < lists.size(); i++) {
			Collections.swap(lists.get(i), a, b);
		}
	}

	
}
