package textprocessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
public class WebPageProcessor {



	ArrayList<String> listOfPages;
	ArrayList<String> listOfText;
	
	
	public WebPageProcessor(ArrayList<String> pages,ArrayList<String> text) {
		super();
		this.listOfPages = pages;
		this.listOfText = text;
		
	}
	
	public DataNode trieFormed() throws FileNotFoundException{
		TST<HashMap<Integer, List<Integer>>> st = new TST<HashMap<Integer, List<Integer>>>();
		HashMap<Integer,Set<String>> ht = new HashMap<Integer,Set<String>>();
		for( int i = 0; i < listOfText.size(); i++) {
			
			HashMap<String, Integer> counter = new HashMap<String, Integer>();
			
			Scanner sc = new Scanner(new File("C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment5\\src\\W3C Web Pages\\Text\\"+listOfText.get(i)));
			while (sc.hasNextLine()) {
		    	String lines = sc.nextLine();
		    	StringTokenizer baka = new StringTokenizer(lines);
		    	while(baka.hasMoreTokens()) {
		    		
					String Token = baka.nextToken();
					
					if (ht.containsKey(Token.length())){
						ht.get(Token.length()).add(Token);
					}else {
						Set<String> hs = new HashSet<String>();
						hs.add(Token);
						ht.put(Token.length(), hs);
						}
					
					if (counter.containsKey(Token)) {
						counter.put(Token,counter.get(Token)+1);
						
					}else {
						counter.put(Token,1);
						}
					
					}

	        	}
			
			Iterator<Entry<String, Integer>> hmIterator = counter.entrySet().iterator();
	        
			while (hmIterator.hasNext()) {
	        	 
				Map.Entry mapElement = (Map.Entry)hmIterator.next();
	            if (st.get((String) mapElement.getKey()) == null) {
	            	HashMap<Integer, List<Integer>> norm = new HashMap<Integer, List<Integer>>();
	            	List<Integer> listofwebsite = new ArrayList<Integer>();
	            	listofwebsite.add(i);
	            	norm.put(counter.get((String) mapElement.getKey()),listofwebsite);
	            	st.put((String) mapElement.getKey(),norm);
	            	
	            }else {
	            	
	            	if (st.get((String) mapElement.getKey()).containsKey(counter.get((String) mapElement.getKey()))) {
	            		st.get((String) mapElement.getKey()).get(counter.get((String) mapElement.getKey())).add(i);
	            	}else {
	            		List<Integer> listofwebsite = new ArrayList<Integer>();
		            	listofwebsite.add(i);
	            		st.get((String) mapElement.getKey()).put(counter.get((String) mapElement.getKey()),listofwebsite);
	            	}
	            }

	        }
	    }
			
		return new DataNode(st,ht);
	}
	
	
	public void ProcessPage() throws IOException {
		 
		 
	
		for (int i =0 ; i<listOfPages.size();i++) {
			File in = new File("C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment5\\src\\W3C Web Pages\\"+listOfPages.get(i));
			File output = new File(listOfText.get(i));
			FileWriter writer = new FileWriter("C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment5\\src\\W3C Web Pages\\text\\"+output);
			Document doc = Jsoup.parse(in,null);
			doc.outputSettings().prettyPrint(false);
			Elements essay = doc.children();
			essay.forEach(el -> 
				 {
					
						try {
							writer.write(el.wholeText());
						
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					
					
				 }
				
			);
			writer.close();
		}
		
		
	}
	public ArrayList<String> findEmail() throws IOException {
		String regex =  "([A-Za-z0-9-_.]+@[A-Za-z0-9-_]+(?:\\.[A-Za-z0-9]+)+)";
		Pattern p = Pattern.compile(regex);
		FileWriter writer = new FileWriter("C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment5\\src\\email.txt");
		ArrayList<String> number = new ArrayList<String>();
		for (int i =0 ; i<listOfText.size();i++) {
			
			
		    Scanner sc = new Scanner(new File("C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment5\\src\\W3C Web Pages\\Text\\"+listOfText.get(i)));
		    while (sc.hasNextLine()) {
		    	String stringNew = sc.nextLine();
		    	
		    	Matcher m = p.matcher(stringNew);
		    	while(m.find()) {
		    		writer.write(m.group());
		    		writer.write("\n");
		    	}
	        }
			sc.close();
		}
		writer.close();
		return number;
	}
	
	public ArrayList<String> findNumber() throws IOException {
		String regex = "(\\()?(\\d){3}(\\))?[ -]?(\\d){3}-(\\d){4}";
		Pattern p = Pattern.compile(regex);
		FileWriter writer = new FileWriter("C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment5\\src\\number.txt");
		ArrayList<String> number = new ArrayList<String>();
		for (int i =0 ; i<listOfText.size();i++) {
		    Scanner sc = new Scanner(new File("C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment5\\src\\W3C Web Pages\\Text\\"+listOfText.get(i)));
		    while (sc.hasNextLine()) {
		    	String stringNew = sc.nextLine();
		    	Matcher m = p.matcher(stringNew);
		    	while(m.find()) {
		    		writer.write(m.group(0));
		    		writer.write("\n");
		    	}
	        }
			sc.close();
			
		}
		writer.close();
		return number;
	}
	
	
	public ArrayList<String> findURL() throws IOException {
		
		
		String patterna = "((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}([-a-zA-Z0-9@:%._\\+~#?&//=]*)";
		String patternb = "((http|https)://)(www.w3.org)([-a-zA-Z0-9@:%._\\+~#?&//=]*)";
		String patternc = "((http|https)://)(www.w3.[org])([-a-zA-Z0-9@:%._\\+~#?&//=]*)/#([-a-zA-Z0-9@:%._\\+~#?&//=]*)";
		FileWriter writera = new FileWriter("C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment5\\src\\questiona.txt");
		FileWriter writerb = new FileWriter("C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment5\\src\\questionb.txt");
		FileWriter writerc  = new FileWriter("C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment5\\src\\questionc.txt");
		Pattern a = Pattern.compile(patterna);
		Pattern b = Pattern.compile(patternb);
		Pattern c = Pattern.compile(patternc);
		
		ArrayList<String> url = new ArrayList<String>();
		for (int i =0 ; i<listOfPages.size();i++) {
			
		    Scanner sc = new Scanner(new File("C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment5\\src\\W3C Web Pages\\"+listOfPages.get(i)));
		    while (sc.hasNextLine()) {
		    	String stringNew = sc.nextLine();
		    	Matcher ma = a.matcher(stringNew);
		    	Matcher mb = b.matcher(stringNew);
		    	Matcher mc = c.matcher(stringNew);
		    	while(ma.find()) {
		    		writera.write(ma.group(0));
		    		writera.write("\n");
		    	}
		    	while(mb.find()) {
		    		writerb.write(mb.group(0));
		    		writerb.write("\n");
		    	}
		    	while(mc.find()) {
		    		writerc.write(mc.group(0));
		    		writerc.write("\n");
		    	}
		    	
	        }
		    sc.close();
			
		}
		writera.close();
		writerb.close();
		writerc.close();
		
		return url;
	}
	
}
