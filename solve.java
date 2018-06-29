import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class solve {
    Node n;
    Domain problem;

    public solve() {
		n = new Node();
		problem=blockworld.setUpBlockworld();
		n.current=problem.initial;
		
	}
    
    public solve(Domain problem) {
		n = new Node();
		this.problem=problem;
		n.current=problem.initial;
		
	}

    public ArrayList<Action> backtrace(Node n){
    	ArrayList<Action> sln=new ArrayList<Action>();
    	while(n!=null && n.parent!= null){
    		sln.add(0,n.relation);
    		n=n.parent;
    	}
    	return sln;
    }
    
    public Vector<Node> expand(Node n){
    	ArrayList<Action> branches=new ArrayList<Action>();
    	for(int i=0;i<problem.allActs.size();i++){
    		if(problem.allActs.get(i).isActionPossible(n.current))
    			branches.add(problem.allActs.get(i));
    	}
    	if(branches.size()!=0)
    		n.isLeaf=false;
    	Collections.shuffle(branches);
    	
    	Vector<Node> q=new Vector<Node>();
    	boolean success=false;
        Node child;
        State succState;
        for(int i=0;i<branches.size();i++){
        	child=new Node();
        	succState=n.current.duplicate();
        	child.parent=n;
        	child.relation=branches.get(i);
        	child.current=succState;
        	child.depth=n.depth+1;
        	child.costSoFar=n.costSoFar+1;
        	success=branches.get(i).performAction(succState);
        	if(!success) child.current=null;
        	q.add(child);
        }
        
        
       return q; 
    }
    
   
	public ArrayList<Action> BFS(){
		Queue<Node> q = new LinkedList<Node>();
		ArrayList<Action> solution;
		q.add(n);
		int counter=0;
		boolean flag=false;
		Vector<Node> children;
		int i;
		int depth=0;
		while(!flag && depth<=12){
			counter++;
			n=q.poll();
           
			if(n==null || n.current==null)
				continue;
			
			if(depth!=n.depth){
	            	depth=n.depth;
	            }
			
			if(n.current.checkGoal(problem.goal)) {
				flag=true;
				solution = backtrace(n);
				for( i=0;i<solution.size();i++)
					solution.get(i).display();
					
				return solution;}
			else{
				 children = expand(n);
				 for(i=0;i<children.size();i++)
					 q.add(children.get(i));
			}
		}
		return null;
	}
	
	 public ArrayList<Action> AStar(){
		 Queue<Node> q = new PriorityQueue<Node>(10,new heuristicComparator());
			ArrayList<Action> solution;
			q.add(n);
			int counter=0;
			boolean flag=false;
			Vector<Node> children;

			while(!flag  ){
				counter++;
				n=q.poll();
				if(n.current==null || n==null)
					continue;
				
				if(n.current.checkGoal(problem.goal)) {
					flag=true;
					solution = backtrace(n);
					for( int i=0;i<solution.size();i++)
						solution.get(i).display();
					return solution;}
				else{
					 children = expand(n);
					 for(int child=0;child<children.size();child++)
						 {
						 Node childNode=children.get(child);
						 if(childNode.current !=null)
						{
							 childNode.computeHeuristic();
							 q.add(children.get(child));
						}
						 }
					 
					 
				}
			}
			return null;
		 
		 
	 }
	 
	 public ArrayList<Action> Greedy(){
			ArrayList<Action> solution;
			int counter=0;
			boolean flag=false;
			Vector<Node> children;
			Node bestChild;
			int i;
			while(!flag ){
				counter++;
				if(n==null || n.current==null)
					continue;
				if(n.current.checkGoal(problem.goal)) {
					flag=true;
					solution = backtrace(n);
					return solution;}
				else{
					 children = expand(n);
					 bestChild=null;
					 for(i=0;i<children.size();i++)
						 bestChild= getBetter(bestChild,children.get(i));
					 n=bestChild;
					 }
			}
			return null;
		}
	 
	 public Node getBetter(Node n1,Node n2){
		 int n1Count=0;
		 int n2Count=0;
		 if(n1 == null)
			 return n2;
		 if(n2 == null)
			 return n1;
		 for(int i=0;i<problem.goal.size();i++){
			 n1Count = (n1.current.isSatisfied(problem.goal.get(i)))? n1Count+1: n1Count;
			 n2Count = (n2.current.isSatisfied(problem.goal.get(i)))? n2Count+1: n2Count;
		 }
		 //System.out.println("!!!!!!!!getBetter: !!!!!!!!!!!!");
		// n1.current.display();
		 //n2.current.display();
		 //System.out.println("!!!!!!!! n1:"+n1Count+" n2:"+n2Count+" !!!!!!!!!!!!");
		 
		 return ((n1Count>n2Count)?n1:n2);
		 
	 }
		
	 
}
