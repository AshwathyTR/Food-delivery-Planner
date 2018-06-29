import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;



public class Main{
	
	  public static void main(String[] args) {
		  PrintWriter writer;
			try {
				writer = new PrintWriter("log.txt", "UTF-8");
				writer.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				System.setOut(new PrintStream(new File("log.txt")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	        
		  solve s=new solve(foodworld.setUpFoodworld());
		  s.AStar();
	    }
}