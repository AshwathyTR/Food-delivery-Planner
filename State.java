import java.util.ArrayList;

public class State{
	ArrayList<Condition> satisfied;
	int heuristic;
	
	public State(ArrayList<Condition> satisfied){
		this.satisfied=satisfied;
	}
	
	public boolean isSatisfied(Condition c){
		return satisfied.contains(c);
	}
	
	public void addConditions(ArrayList<Condition> conditions){
		satisfied.removeAll(conditions); // So that there are no duplicates
		satisfied.addAll(conditions);
	}
	
	public void removeConditions(ArrayList<Condition> conditions){
		satisfied.removeAll(conditions); 
	}
	
	public State duplicate(){
		ArrayList<Condition> newConditions=new ArrayList<Condition>();
		for(int i=0;i<satisfied.size();i++){
			newConditions.add(satisfied.get(i).duplicate());
		}
		State newState=new State(newConditions);
		return newState;
	}
	
	public boolean checkGoal(ArrayList<Condition> goals){
		for(int goal=0;goal<goals.size();goal++){
			if(!isSatisfied(goals.get(goal)))
				return false;
		
		}
		return true;
	}
	
	public void display(){
		System.out.println("State is");
		for(int i=0;i<satisfied.size();i++){
			satisfied.get(i).display();
		}
	}
	
	}