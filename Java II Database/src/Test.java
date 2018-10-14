import java.util.*;

public class Test {
	private static Scanner scanner = new Scanner(System.in);
	
	public Test(String testString) {
		
	}
	
	public static boolean isRealNumber(String testString) {
		return(testString.matches("[+-]?(\\d+|\\d*\\.?\\d+)"));
	}
	
}
