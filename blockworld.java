import java.util.ArrayList;
import java.util.Arrays;

public class blockworld{
	
	public static Domain setUpBlockworld(){
		
		ArrayList<String> entities = new ArrayList<String>(Arrays.asList("A","B","C","Table"));
		ArrayList<Condition> init=new ArrayList<Condition>();
		init.add(new Condition("On",new ArrayList<String>(Arrays.asList("A","Table"))));
		init.add(new Condition("On",new ArrayList<String>(Arrays.asList("B","Table"))));
		init.add(new Condition("On",new ArrayList<String>(Arrays.asList("C","Table"))));
		init.add(new Condition("Block",new ArrayList<String>(Arrays.asList("A"))));
		init.add(new Condition("Block",new ArrayList<String>(Arrays.asList("B"))));
		init.add(new Condition("Block",new ArrayList<String>(Arrays.asList("C"))));
		init.add(new Condition("Clear",new ArrayList<String>(Arrays.asList("A"))));
		init.add(new Condition("Clear",new ArrayList<String>(Arrays.asList("B"))));
		init.add(new Condition("Clear",new ArrayList<String>(Arrays.asList("C"))));
		for(int i=0;i<entities.size();i++){
			for(int j=0;j<entities.size();j++){
				if(!(entities.get(i).equals(entities.get(j)))){
					init.add(new Condition("notEquals",new ArrayList<String>(Arrays.asList(entities.get(i),entities.get(j)))));
				}
			}
		}
		State initial=new State(init);
		
		ArrayList<Condition> goal=new ArrayList<Condition>();
		goal.add(new Condition("On",new ArrayList<String>(Arrays.asList("A","B"))));
		goal.add(new Condition("On",new ArrayList<String>(Arrays.asList("B","C"))));
		
		ArrayList<Condition> preconds = new ArrayList<Condition>();
		preconds.add(new Condition("On",new ArrayList<String>(Arrays.asList("b","x"))));
		preconds.add(new Condition("Clear",new ArrayList<String>(Arrays.asList("b"))));
		preconds.add(new Condition("Clear",new ArrayList<String>(Arrays.asList("y"))));
		preconds.add(new Condition("Block",new ArrayList<String>(Arrays.asList("b"))));
		preconds.add(new Condition("notEquals",new ArrayList<String>(Arrays.asList("b","x"))));
		preconds.add(new Condition("notEquals",new ArrayList<String>(Arrays.asList("b","y"))));
		preconds.add(new Condition("notEquals",new ArrayList<String>(Arrays.asList("x","y"))));
		
		ArrayList<Condition> addEffects = new ArrayList<Condition>();
		addEffects.add(new Condition("On",new ArrayList<String>(Arrays.asList("b","y"))));
		addEffects.add(new Condition("Clear",new ArrayList<String>(Arrays.asList("x"))));
		
		ArrayList<Condition> removeEffects = new ArrayList<Condition>();
		removeEffects.add(new Condition("On",new ArrayList<String>(Arrays.asList("b","x"))));
		removeEffects.add(new Condition("Clear",new ArrayList<String>(Arrays.asList("y"))));
		
		ActionTemplate act1=new ActionTemplate("Move",new ArrayList<String>(Arrays.asList("b","x","y")),preconds,addEffects,removeEffects);
		
		preconds = new ArrayList<Condition>();
		preconds.add(new Condition("On",new ArrayList<String>(Arrays.asList("b","x"))));
		preconds.add(new Condition("Clear",new ArrayList<String>(Arrays.asList("b"))));
		preconds.add(new Condition("Block",new ArrayList<String>(Arrays.asList("b"))));
		preconds.add(new Condition("notEquals",new ArrayList<String>(Arrays.asList("b","x"))));
		
	    addEffects = new ArrayList<Condition>();
		addEffects.add(new Condition("On",new ArrayList<String>(Arrays.asList("b","Table"))));
		addEffects.add(new Condition("Clear",new ArrayList<String>(Arrays.asList("x"))));
		
		removeEffects = new ArrayList<Condition>();
		removeEffects.add(new Condition("On",new ArrayList<String>(Arrays.asList("b","x"))));
		
		ActionTemplate act2=new ActionTemplate("MoveToTable",new ArrayList<String>(Arrays.asList("b","x")),preconds,addEffects,removeEffects);
		
		Domain blockworld=new Domain(new ArrayList<ActionTemplate>(Arrays.asList(act1,act2)),initial,goal,entities);
		return blockworld;
	}
	
	
public static Domain setUpRelaxedWorld(){
		
		ArrayList<String> entities = new ArrayList<String>(Arrays.asList("A","B","C","Table"));
		ArrayList<Condition> init=new ArrayList<Condition>();
		init.add(new Condition("On",new ArrayList<String>(Arrays.asList("A","Table"))));
		init.add(new Condition("On",new ArrayList<String>(Arrays.asList("B","Table"))));
		init.add(new Condition("On",new ArrayList<String>(Arrays.asList("C","Table"))));
		init.add(new Condition("Block",new ArrayList<String>(Arrays.asList("A"))));
		init.add(new Condition("Block",new ArrayList<String>(Arrays.asList("B"))));
		init.add(new Condition("Block",new ArrayList<String>(Arrays.asList("C"))));
		init.add(new Condition("Clear",new ArrayList<String>(Arrays.asList("A"))));
		init.add(new Condition("Clear",new ArrayList<String>(Arrays.asList("B"))));
		init.add(new Condition("Clear",new ArrayList<String>(Arrays.asList("C"))));
		for(int i=0;i<entities.size();i++){
			for(int j=0;j<entities.size();j++){
				if(!(entities.get(i).equals(entities.get(j)))){
					init.add(new Condition("notEquals",new ArrayList<String>(Arrays.asList(entities.get(i),entities.get(j)))));
				}
			}
		}
		State initial=new State(init);
		
		ArrayList<Condition> goal=new ArrayList<Condition>();
		goal.add(new Condition("On",new ArrayList<String>(Arrays.asList("A","B"))));
		goal.add(new Condition("On",new ArrayList<String>(Arrays.asList("B","C"))));
		
		ArrayList<Condition> preconds = new ArrayList<Condition>();
		
		
		ArrayList<Condition> addEffects = new ArrayList<Condition>();
		addEffects.add(new Condition("On",new ArrayList<String>(Arrays.asList("b","y"))));
		addEffects.add(new Condition("Clear",new ArrayList<String>(Arrays.asList("x"))));
		
		ArrayList<Condition> removeEffects = new ArrayList<Condition>();
		removeEffects.add(new Condition("On",new ArrayList<String>(Arrays.asList("b","x"))));
		removeEffects.add(new Condition("Clear",new ArrayList<String>(Arrays.asList("y"))));
		
		ActionTemplate act1=new ActionTemplate("Move",new ArrayList<String>(Arrays.asList("b","x","y")),preconds,addEffects,removeEffects);
		
		preconds = new ArrayList<Condition>();
		
	    addEffects = new ArrayList<Condition>();
		addEffects.add(new Condition("On",new ArrayList<String>(Arrays.asList("b","Table"))));
		addEffects.add(new Condition("Clear",new ArrayList<String>(Arrays.asList("x"))));
		
		removeEffects = new ArrayList<Condition>();
		removeEffects.add(new Condition("On",new ArrayList<String>(Arrays.asList("b","x"))));
		
		ActionTemplate act2=new ActionTemplate("MoveToTable",new ArrayList<String>(Arrays.asList("b","x")),preconds,addEffects,removeEffects);
		
		Domain relaxedworld=new Domain(new ArrayList<ActionTemplate>(Arrays.asList(act1,act2)),initial,goal,entities);
		return relaxedworld;
	}
}