import java.util.ArrayList;

public class ActionTemplate {
	String name;
	ArrayList<String> params;
	ArrayList<Condition> preconditions;
	ArrayList<Condition> addEffects;
	ArrayList<Condition> removeEffects;
	double cost;
	
	public ActionTemplate(String name,ArrayList<String> params,ArrayList<Condition> preconditions,	ArrayList<Condition> addEffects,	ArrayList<Condition> removeEffects){
		this.name=name;
		this.params=params;
		this.preconditions=preconditions;
		this.addEffects=addEffects;
		this.removeEffects=removeEffects;
		
	}
	

	public Condition getRealCondition(Condition abstractCondition,
			ArrayList<String> parameters) {
		ArrayList<String> realParams=new ArrayList<String>();
		for(int i=0;i<params.size();i++){
			if(abstractCondition.parameters.contains(params.get(i))){
				realParams.add(parameters.get(i));
			}
		}
		Condition real = new Condition(abstractCondition.name, realParams);
		return real;
	}

	public ArrayList<Condition> getRealConditions(
			ArrayList<Condition> abstractConditions,
			ArrayList<String> parameters) {
		ArrayList<Condition> real = new ArrayList<Condition>();
		for (int i = 0; i < abstractConditions.size(); i++) {
			real.add(getRealCondition(abstractConditions.get(i), parameters));
		}
		return real;
	}
	
	
}