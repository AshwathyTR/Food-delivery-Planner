
public class Node {
    int depth;
	State current;
	Node parent;
	Action relation;
	boolean isLeaf;
	int heuristic;
	int costSoFar;
	
	public Node() {
		current=null;
		parent=null;
		relation=null;
		depth=0;
		isLeaf=true;
		heuristic=Integer.MAX_VALUE - 10;
		costSoFar=0;
		
	}
	
	public void computeHeuristic(){
	     Domain problem = foodworld.setUpRelaxedWorld(current);
	     solve s=new solve(problem);
	     if(s.Greedy() != null)
	    	 heuristic =s.Greedy().size();
	    
	}

}
