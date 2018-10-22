import java.util.*;

public class DatabaseProcessing {
	// Process data from database

	public DatabaseProcessing() {

	}

	//AYTO einai swsto
	public static void displayData(ArrayList<String> fields, ArrayList<ArrayList<Object>> lists) {
		for (int field = 0; field < fields.size(); field++) {
			System.out.print(fields.get(field) + " "); //Prota typono onomata pedion
		}
		System.out.println();
		int counter = 0;
		for (int line = 0; line < lists.get(0).size(); line++) {
			for (int field = 0; field < lists.size(); field++) {
				System.out.print(lists.get(field).get(line) + " ");
				if (++counter % lists.size() == 0) System.out.println(); //kathe lists.size() stoixeia katebainei grammi
			}
		}
	}

	// apo edw kai katw prepei na skeftoume pws tha to kanoume 
	// dhladh an tha eisagei ta stoixeia ana seira h ana sthlh
	// opote paizei kai na exw kanei malakies
	
	
	public static void inputData(ArrayList<ArrayList<Object>> lists, ArrayList<Object> newline) {  
		lists.add(newline); 
	}
	
	//den thn exw teleiwsei 
	public static void changeData(ArrayList<ArrayList<Object>> lists, int line, String newVariable) {
		int i = 0; 
		boolean boo = true;
		
		do {
			
			if (line == i) { 
			lists.get(i).add(line, newVariable); 	 
			}
			
			i++; 
		} while (boo == true && i < lists.size());
	}
	
	//sorry pou allaksa to onoma ths methodou 
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
	
	public static void swapData(ArrayList<ArrayList<Object>> lists, int a, int b) {
		for (int i = 0; i < lists.size(); i++) {
			Collections.swap(lists.get(i), a, b);
		}
	}

	
}
