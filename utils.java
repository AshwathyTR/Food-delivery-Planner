import java.util.ArrayList;

public class utils{
	
	public static ArrayList<ArrayList<String>> getSubsets(ArrayList<String> labels,int num){
	ArrayList<ArrayList<String>> subsets = new ArrayList<>();
	if(num==1){
		for(int i=0;i<labels.size();i++){
			ArrayList<String> subset= new ArrayList<String>();
			subset.add(labels.get(i));
			subsets.add(subset);
		}
		return subsets;
	}
	else{
		ArrayList<ArrayList<String>> allcombs = getSubsets(labels,num-1);
		for(int i=0;i<labels.size();i++){
			for(int j=0;j<allcombs.size();j++){
				ArrayList<String> subset= new ArrayList<String>();
				subset.add(labels.get(i));
				subset.addAll(allcombs.get(j));
				subsets.add(subset);
			}
			
		}
			
	}
	return subsets;
	

	}


}