package textprocessing;

import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LRUCache {
	 
    // store keys of cache
    private Deque<String> doublyQueue;
 
    // store references of key in cache
    private HashMap<String,List<String>> hashMap;
 
    // maximum capacity of cache
    private final int CACHE_SIZE;
 
    LRUCache(int capacity) {
        doublyQueue = new LinkedList<>();
        hashMap = new HashMap<String,List<String>>();
        CACHE_SIZE = capacity;
    }
 
    /* Refer the page within the LRU cache */
    public void refer(String page,List<String> WebPages) {
        if (!hashMap.containsKey(page)) {
            if (doublyQueue.size() == CACHE_SIZE) {
                String last = doublyQueue.removeLast();
                hashMap.remove(last);
            }
        }
        else {/* The found page may not be always the last element, even if it's an
               intermediate element that needs to be removed and added to the start
               of the Queue */
            doublyQueue.remove(page);
        }
        doublyQueue.push(page);
        hashMap.put(page,WebPages);
    }
    
    public HashMap<String,List<String>> getHashSet(String page) {
    	return hashMap;
   
    }
 
    // display contents of cache
    public void display() {
        Iterator<String> itr = doublyQueue.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
    }
}