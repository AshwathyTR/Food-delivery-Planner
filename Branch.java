import java.util.ArrayList;

public class Branch{
	Action act;
	ArrayList<String> param;
	
	public Branch(Action act, ArrayList<String> param){
		this.act=act;
		this.param=param;
	}
}