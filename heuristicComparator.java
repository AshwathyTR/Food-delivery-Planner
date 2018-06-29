import java.util.Comparator;

public class heuristicComparator implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		return (o1.heuristic+o1.costSoFar) - (o2.heuristic + o2.costSoFar);

	}

}
