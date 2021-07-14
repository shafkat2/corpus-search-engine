package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import textprocessing.TST;

public class Algorithm {
		
	

	TST<HashMap<Integer,List<Integer>>> st;
	HashMap<Integer,List<String>> wordCount;
	
	public Algorithm(TST<HashMap<Integer, List<Integer>>> st, HashMap<Integer, List<String>> wordCount) {
		super();
		this.st = st;
		this.wordCount = wordCount;
	}



	

		
	public List<String> websiteLink(String word,List<String> ListOfSites){
		
		List<String> answer = new ArrayList<String>();
		
		HashMap<Integer,List<Integer>> temp = st.get(word); 
		for (Map.Entry mapElement : temp.entrySet()) {
            String key = (String)mapElement.getKey();

            int value = ((int)mapElement.getKey());
  
        
		}
   
		
		
		
		return answer;
		
		
		
		
	}
	
	public List<String> suggestedWords(String word){
		
		List<String> answer = new ArrayList<String>();
		
		//code here return answer
		
		
		return answer;
		
		
		
		
	}
	
	public List<String> LRUCache(String word){
		
		
		List<String> answer = new ArrayList<String>();
		
		//code here return answer
		
		
		return answer;
		
		
		
		
	}
}
