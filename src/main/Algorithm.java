package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import textprocessing.TST;

public class Algorithm {
		
	

	TST<HashMap<Integer,List<Integer>>> st;
	HashMap<Integer,Set<String>> wordCount;
	
	public Algorithm(TST<HashMap<Integer, List<Integer>>> st, HashMap<Integer, Set<String>> wordCount) {
		super();
		this.st = st;
		this.wordCount = wordCount;
	}



	public List<String> websiteLink(String word,List<String> ListOfSites){
		
		List<String> answer = new ArrayList<String>();
		
		if (st.get(word) != null){
			int maxValue = 0;
			HashMap<Integer,List<Integer>> temp = st.get(word); 
			for (Map.Entry mapElement : temp.entrySet()) {
				int key = (int)mapElement.getKey();
				maxValue = Math.max(maxValue, key);
				   
			}
			for(int index: temp.get(maxValue)) {
				
				answer.add(ListOfSites.get(index));
			}
			
		}
   
		
		return answer;
		
		
		
		
	}
	
	public List<String> suggestedWords(String word){
		
		List<String> answer = new ArrayList<String>();
		
		//code here return answer
		
		
		return answer;
		
		
		
		
	}
	
}
