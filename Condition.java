import java.util.ArrayList;


public class Condition {
	String name;
	ArrayList<String> parameters;

	public Condition(String name,ArrayList<String> params) {
		this.name = name;
		parameters = params;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		else if (obj == null)
			return false;

		else if (obj instanceof Condition) {
			Condition c = (Condition) obj;
			if (c.name == name && c.parameters.equals(parameters))
				return true;
		}

		return false;

	}
	
	public Condition duplicate(){
		ArrayList<String> newParams=new ArrayList<String>();
		for(int i=0;i<parameters.size();i++){
			newParams.add(parameters.get(i));
		}
		Condition newCondition=new Condition(name,newParams);
		return newCondition;
	}
	
	public void display(){
		System.out.print(name+" ");
		for(int i=0;i<parameters.size();i++){
			System.out.print(parameters.get(i)+" ");
		}
		System.out.println("");
	}

}