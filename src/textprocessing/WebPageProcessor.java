package textprocessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebPageProcessor {



	ArrayList<String> listOfPages;
	ArrayList<String> listOfText;
	
	public WebPageProcessor(ArrayList<String> pages,ArrayList<String> text) {
		super();
		this.listOfPages = pages;
		this.listOfText = text;
		
	}
	
	public void ProcessPage() throws IOException {
		 
		 
		
		for (int i =0 ; i<listOfPages.size();i++) {
			File in = new File("C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment4\\src\\W3C Web Pages\\"+listOfPages.get(i));
			File output = new File(listOfText.get(i));
			FileWriter writer = new FileWriter("C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment4\\src\\W3C Web Pages\\textfiles\\"+output);
			Document doc = Jsoup.parse(in,null);
			Elements essay = doc.select("html");
			essay.forEach(el -> 
				 {
					try {
						writer.write(el.wholeText());
						writer.write('\n');
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
					);
		}
		
		
	}
	public ArrayList<String> findNumber() throws FileNotFoundException {
		
		ArrayList<String> number = new ArrayList<String>();
		for (int i =0 ; i<listOfText.size();i++) {
		    Scanner sc = new Scanner(new File("C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment4\\src\\W3C Web Pages\\Text\\"+listOfText.get(i)));
		    while (sc.hasNextLine()) {
		    	String stringNew = sc.nextLine();
		    	
	        }
			
		}
		
		return number;
	}
	
	public ArrayList<String> findURL() throws FileNotFoundException {
		
		ArrayList<String> url = new ArrayList<String>();
		for (int i =0 ; i<listOfPages.size();i++) {
		    Scanner sc = new Scanner(new File("C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment4\\src\\W3C Web Pages"+listOfPages.get(i)));
		    while (sc.hasNextLine()) {
		    	String stringNew = sc.nextLine();
		    	
	        }
			
		}
		
		return url;
	}
	
}
