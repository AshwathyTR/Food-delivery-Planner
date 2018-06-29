import java.util.ArrayList;

public class Domain{
	ArrayList<ActionTemplate> acts;
	State initial;
	ArrayList<Condition> goal;
	ArrayList<String> entities;
	ArrayList<Action> allActs;
	
	public Domain(ArrayList<ActionTemplate> availableActs,State initial,	ArrayList<Condition> goal, ArrayList<String> entities){
		this.acts=availableActs;
		this.initial=initial;
		this.goal=goal;
		this.entities=entities;
		this.allActs=computeAllActs();
	}
	
	public ArrayList<Action> computeAllActs(){
		ArrayList<Action> allActs = new ArrayList<Action>();
		for(int act=0;act<acts.size();act++){
			int numParams=acts.get(act).params.size();
			ArrayList<ArrayList<String>> paramCombinations = utils.getSubsets(entities,numParams);
			for(int i=0;i<paramCombinations.size();i++)
				allActs.add(new Action(acts.get(act),paramCombinations.get(i)));
			
		}
		return allActs;
	}
	
}