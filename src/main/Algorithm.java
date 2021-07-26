package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

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
	
	public List<String> suggestedWords(String word) {

		TreeMap<Integer, TreeMap<Integer, TreeSet<String>>> costMap = new TreeMap<>();

		Set<Entry<Integer, Set<String>>> entrySet = wordCount.entrySet();
		Iterator<Entry<Integer, Set<String>>> it = entrySet.iterator();
		while (it.hasNext()) {
			Entry<Integer, Set<String>> next = it.next();
			int freq = next.getKey();
			Set<String> wordSet = next.getValue();
			for (String w : wordSet) {
				// word - input word
				// w - suggested word
				int cost = editDistance(word, w);
				TreeMap<Integer, TreeSet<String>> similarWords = costMap.getOrDefault(cost,
						new TreeMap<>(Collections.reverseOrder()));
				TreeSet<String> set = similarWords.getOrDefault(freq, new TreeSet<>());
				set.add(w);
				similarWords.put(freq, set);
				costMap.put(cost, similarWords);
			}
		}
		return processSuggestedWords(costMap);
	}
	
	private List<String> processSuggestedWords(TreeMap<Integer, TreeMap<Integer, TreeSet<String>>> costMap) {
		List<String> suggestedWords = new ArrayList<String>();
		int cnt = 0;
		int maximumSuggestedWords = 10;
		for (int cost : costMap.keySet()) {
			if (cost == 0) {
				continue;
			}
			TreeMap<Integer, TreeSet<String>> map = costMap.get(cost);
			Set<Entry<Integer, TreeSet<String>>> entrySet = map.entrySet();
			Iterator<Entry<Integer, TreeSet<String>>> it = entrySet.iterator();
			while (it.hasNext()) {
				Entry<Integer, TreeSet<String>> next = it.next();
				for (String s : next.getValue()) {
					suggestedWords.add(s);
					cnt++;
					if (cnt == maximumSuggestedWords) {
						break;
					}
				}
				if (cnt == maximumSuggestedWords) {
					break;
				}
			}
			if (cnt == maximumSuggestedWords) {
				break;
			}
		}
		return suggestedWords;
	}
	
	public int editDistance(String s, String t) {
		s = s.toLowerCase();
		t = t.toLowerCase();
		int len1 = s.length();
		int len2 = t.length();

		int cost[][] = new int[len1 + 1][len2 + 1];
		for (int i = 0; i <= len1; i++) {
			for (int j = 0; j <= len2; j++) {
				if (i == 0 && j == 0) {
					cost[i][j] = 0;
					continue;
				}
				if (i == 0) {
					cost[i][j] = cost[i][j - 1] + 1;
					continue;
				}
				if (j == 0) {
					cost[i][j] = cost[i - 1][j] + 1;
					continue;
				}
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					cost[i][j] = cost[i - 1][j - 1];
					continue;
				}
				int min = Math.min(cost[i - 1][j], Math.min(cost[i - 1][j - 1], cost[i][j - 1]));
				cost[i][j] = min + 1;
			}
		}
		return cost[len1][len2];
	}
	
}
