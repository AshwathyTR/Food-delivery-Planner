import java.util.ArrayList;

public class Action {
	ActionTemplate template;
	ArrayList<String> params;
	ArrayList<Condition> preconditions;
	ArrayList<Condition> addEffects;
	ArrayList<Condition> removeEffects;
	
	public Action(ActionTemplate template,ArrayList<String> params){
		this.template=template;
		this.params=params;
		preconditions=new ArrayList<Condition>();
		preconditions.addAll(template.getRealConditions(template.preconditions, params));
		addEffects=new ArrayList<Condition>();
		addEffects.addAll(template.getRealConditions(template.addEffects, params));
		removeEffects=new ArrayList<Condition>();
		removeEffects.addAll(template.getRealConditions(template.removeEffects, params));
	}
	

	public boolean isActionPossible(State fromState) {
		for (int i = 0; i < preconditions.size(); i++) {
			if (!fromState.isSatisfied(preconditions.get(i)))
				{return false;}
		}
		return true;
	}

	public boolean performAction(State fromState) {
		if (isActionPossible(fromState)) {
			fromState.addConditions(addEffects);
			fromState.removeConditions(removeEffects);
		}
		return true;
	}
	
	public State simulateAction(State fromState, ArrayList<String> parameters) {
		    State newState=fromState.duplicate();
			newState.addConditions(addEffects);
			newState.removeConditions(removeEffects);
			return newState;
		
	}
	
	public void display(){
		System.out.print("Action:");
		System.out.print(template.name+" ");
		for(int i=0;i<params.size();i++){
			System.out.print(params.get(i)+" ");
		}
		System.out.println("");
	}
	
	
}