package textprocessing;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DataNode {
	
	TST<HashMap<Integer, List<Integer>>> st;
	HashMap<Integer,Set<String>> ht;
	
	
	public DataNode(TST<HashMap<Integer, List<Integer>>> st, HashMap<Integer, Set<String>> ht) {
		super();
		this.st = st;
		this.ht = ht;
	}

	public TST<HashMap<Integer, List<Integer>>> getSt() {
		return st;
	}
	public void setSt(TST<HashMap<Integer, List<Integer>>> st) {
		this.st = st;
	}
	public HashMap<Integer, Set<String>> getHt() {
		return ht;
	}
	public void setHt(HashMap<Integer, Set<String>> ht) {
		this.ht = ht;
	}
}
