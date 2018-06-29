import java.util.ArrayList;
import java.util.Arrays;

public class foodworld{
	
	public static boolean isNeighbour(String pos1, String pos2){
		int pos1x = (int)pos1.charAt(0);
		int pos1y = (int)pos1.charAt(1);
		
		int pos2x = (int)pos2.charAt(0);
		int pos2y = (int)pos2.charAt(1);
		
		if((pos1x == pos2x) &&( pos2y == (pos1y+1)))
			return true;
		if((pos1y == pos2y)&& (pos2x ==(pos1x+1))  )
			return true;
		if((pos1x == pos2x) &&( pos2y == (pos1y-1)))
			return true;
		if((pos1y == pos2y)&& (pos2x ==(pos1x-1))  )
			return true;
		return false;
		
	}
	
	public static ArrayList<String> generateBoard(int dim){
		ArrayList<String> board = new ArrayList<String>();
		for(int i=0;i<dim;i++){
			for(int j=0;j<dim;j++){
				board.add(Integer.toString(i)+Integer.toString(j));
			}
		}
		return board;
	}
	
	public static Domain setUpRelaxedWorld(State init){
		
		
		ArrayList<String> board = generateBoard(3);
		ArrayList<String> entities = board;
		
		
		State initial=init;
		
		ArrayList<Condition> goal=new ArrayList<Condition>();
		goal.add(new Condition("foodDelivered",new ArrayList<String>(Arrays.asList("11"))));
		goal.add(new Condition("foodDelivered",new ArrayList<String>(Arrays.asList("12"))));
		goal.add(new Condition("Agent",new ArrayList<String>(Arrays.asList("21"))));
		goal.add(new Condition("HasFood",new ArrayList<String>()));
		
		ArrayList<Condition> preconds = new ArrayList<Condition>();
		
		ArrayList<Condition> addEffects = new ArrayList<Condition>();
		addEffects.add(new Condition("Agent",new ArrayList<String>(Arrays.asList("y"))));
		
		ArrayList<Condition> removeEffects = new ArrayList<Condition>();
		
		ActionTemplate act1=new ActionTemplate("Move",new ArrayList<String>(Arrays.asList("x","y")),preconds,addEffects,removeEffects);
		
		preconds = new ArrayList<Condition>();
		   addEffects = new ArrayList<Condition>();
		addEffects.add(new Condition("HasFood",new ArrayList<String>()));
		
		removeEffects = new ArrayList<Condition>();
		
		ActionTemplate act2=new ActionTemplate("pickUpFood",new ArrayList<String>(Arrays.asList("x")),preconds,addEffects,removeEffects);
		
		preconds = new ArrayList<Condition>();
		   addEffects = new ArrayList<Condition>();
	    addEffects.add(new Condition("foodDelivered",new ArrayList<String>(Arrays.asList("x"))));
		
		removeEffects = new ArrayList<Condition>();
		
		ActionTemplate act3=new ActionTemplate("deliverFood",new ArrayList<String>(Arrays.asList("x")),preconds,addEffects,removeEffects);
		
		Domain foodworld=new Domain(new ArrayList<ActionTemplate>(Arrays.asList(act1,act2,act3)),initial,goal,entities);
		return foodworld;
	}
	
	
public static Domain setUpFoodworld(){
		
	ArrayList<String> board = generateBoard(3);
	ArrayList<String> entities = board;
	
	ArrayList<Condition> init=new ArrayList<Condition>();
	ArrayList<String> rest = new ArrayList<String>(Arrays.asList("02","21"));
	ArrayList<String> houses = new ArrayList<String>(Arrays.asList("11","12"));
	ArrayList<String> agent = new ArrayList<String>(Arrays.asList("00"));
	
	for(int i=0;i<board.size();i++){
		if(rest.contains(board.get(i)))
			init.add(new Condition("Restaurant",new ArrayList<String>(Arrays.asList(board.get(i)))));
		else if(houses.contains(board.get(i)))
			init.add(new Condition("House",new ArrayList<String>(Arrays.asList(board.get(i)))));
		else if(agent.contains(board.get(i)))
			init.add(new Condition("Agent",new ArrayList<String>(Arrays.asList(board.get(i)))));
			}
	
	for(int i=0;i<entities.size();i++){
		for(int j=0;j<entities.size();j++){
			
			if(isNeighbour(entities.get(i),entities.get(j))){
				init.add(new Condition("Neighbour",new ArrayList<String>(Arrays.asList(entities.get(i),entities.get(j)))));
			}
		}
	}
	State initial=new State(init);
	
	ArrayList<Condition> goal=new ArrayList<Condition>();
	goal.add(new Condition("foodDelivered",new ArrayList<String>(Arrays.asList("11"))));
	goal.add(new Condition("foodDelivered",new ArrayList<String>(Arrays.asList("12"))));
	goal.add(new Condition("Agent",new ArrayList<String>(Arrays.asList("21"))));
	goal.add(new Condition("HasFood",new ArrayList<String>()));
	
	ArrayList<Condition> preconds = new ArrayList<Condition>();
	preconds.add(new Condition("Agent",new ArrayList<String>(Arrays.asList("x"))));
	preconds.add(new Condition("Neighbour",new ArrayList<String>(Arrays.asList("x","y"))));
	
	ArrayList<Condition> addEffects = new ArrayList<Condition>();
	addEffects.add(new Condition("Agent",new ArrayList<String>(Arrays.asList("y"))));
	
	ArrayList<Condition> removeEffects = new ArrayList<Condition>();
	removeEffects.add(new Condition("Agent",new ArrayList<String>(Arrays.asList("x"))));
	
	ActionTemplate act1=new ActionTemplate("Move",new ArrayList<String>(Arrays.asList("x","y")),preconds,addEffects,removeEffects);
	
	preconds = new ArrayList<Condition>();
	preconds.add(new Condition("Agent",new ArrayList<String>(Arrays.asList("x"))));
	preconds.add(new Condition("Restaurant",new ArrayList<String>(Arrays.asList("x"))));
	
    addEffects = new ArrayList<Condition>();
	addEffects.add(new Condition("HasFood",new ArrayList<String>()));
	
	removeEffects = new ArrayList<Condition>();
	
	ActionTemplate act2=new ActionTemplate("pickUpFood",new ArrayList<String>(Arrays.asList("x")),preconds,addEffects,removeEffects);
	
	preconds = new ArrayList<Condition>();
	preconds.add(new Condition("Agent",new ArrayList<String>(Arrays.asList("x"))));
	preconds.add(new Condition("House",new ArrayList<String>(Arrays.asList("x"))));
	preconds.add(new Condition("HasFood",new ArrayList<String>()));
	
    addEffects = new ArrayList<Condition>();
    addEffects.add(new Condition("foodDelivered",new ArrayList<String>(Arrays.asList("x"))));
	
	removeEffects = new ArrayList<Condition>();
	removeEffects.add(new Condition("HasFood",new ArrayList<String>()));
	
	ActionTemplate act3=new ActionTemplate("deliverFood",new ArrayList<String>(Arrays.asList("x")),preconds,addEffects,removeEffects);
	
	Domain foodworld=new Domain(new ArrayList<ActionTemplate>(Arrays.asList(act1,act2,act3)),initial,goal,entities);
	return foodworld;
	}

public static Domain setUpSimpleWorld(){
	
	ArrayList<String> board = generateBoard(3);
	ArrayList<String> entities = board;
	
	ArrayList<Condition> init=new ArrayList<Condition>();
	ArrayList<String> rest = new ArrayList<String>(Arrays.asList("02","21"));
	ArrayList<String> houses = new ArrayList<String>(Arrays.asList("11","12"));
	ArrayList<String> agent = new ArrayList<String>(Arrays.asList("00"));
	
	for(int i=0;i<board.size();i++){
		if(rest.contains(board.get(i)))
			init.add(new Condition("Restaurant",new ArrayList<String>(Arrays.asList(board.get(i)))));
		else if(houses.contains(board.get(i)))
			init.add(new Condition("House",new ArrayList<String>(Arrays.asList(board.get(i)))));
		else if(agent.contains(board.get(i)))
			init.add(new Condition("Agent",new ArrayList<String>(Arrays.asList(board.get(i)))));
			}
	
	for(int i=0;i<entities.size();i++){
		for(int j=0;j<entities.size();j++){
			
			if(isNeighbour(entities.get(i),entities.get(j))){
				init.add(new Condition("Neighbour",new ArrayList<String>(Arrays.asList(entities.get(i),entities.get(j)))));
			}
		}
	}
	State initial=new State(init);
	
	ArrayList<Condition> goal=new ArrayList<Condition>();
	goal.add(new Condition("HasFood",new ArrayList<String>()));
	goal.add(new Condition("Agent",new ArrayList<String>(Arrays.asList("21"))));
	
	ArrayList<Condition> preconds = new ArrayList<Condition>();
	preconds.add(new Condition("Agent",new ArrayList<String>(Arrays.asList("x"))));
	preconds.add(new Condition("Neighbour",new ArrayList<String>(Arrays.asList("x","y"))));
	
	ArrayList<Condition> addEffects = new ArrayList<Condition>();
	addEffects.add(new Condition("Agent",new ArrayList<String>(Arrays.asList("y"))));
	
	ArrayList<Condition> removeEffects = new ArrayList<Condition>();
	removeEffects.add(new Condition("Agent",new ArrayList<String>(Arrays.asList("x"))));
	
	ActionTemplate act1=new ActionTemplate("Move",new ArrayList<String>(Arrays.asList("x","y")),preconds,addEffects,removeEffects);
	
	preconds = new ArrayList<Condition>();
	preconds.add(new Condition("Agent",new ArrayList<String>(Arrays.asList("x"))));
	preconds.add(new Condition("Restaurant",new ArrayList<String>(Arrays.asList("x"))));
	
    addEffects = new ArrayList<Condition>();
	addEffects.add(new Condition("HasFood",new ArrayList<String>()));
	
	removeEffects = new ArrayList<Condition>();
	
	ActionTemplate act2=new ActionTemplate("pickUpFood",new ArrayList<String>(Arrays.asList("x")),preconds,addEffects,removeEffects);
	
	preconds = new ArrayList<Condition>();
	preconds.add(new Condition("Agent",new ArrayList<String>(Arrays.asList("x"))));
	preconds.add(new Condition("House",new ArrayList<String>(Arrays.asList("x"))));
	preconds.add(new Condition("HasFood",new ArrayList<String>()));
	
    addEffects = new ArrayList<Condition>();
    addEffects.add(new Condition("foodDelivered",new ArrayList<String>(Arrays.asList("x"))));
	
	removeEffects = new ArrayList<Condition>();
	removeEffects.add(new Condition("HasFood",new ArrayList<String>()));
	
	ActionTemplate act3=new ActionTemplate("deliverFood",new ArrayList<String>(Arrays.asList("x")),preconds,addEffects,removeEffects);
	
	Domain foodworld=new Domain(new ArrayList<ActionTemplate>(Arrays.asList(act1,act2,act3)),initial,goal,entities);
	return foodworld;
	}
}