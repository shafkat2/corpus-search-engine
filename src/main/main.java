package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import textprocessing.DataNode;
import textprocessing.WebPageProcessor;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		ArrayList<String> listofPages = new ArrayList<String>();
		ArrayList<String> listofText = new ArrayList<String>();
		File[] files = new File("C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment5\\src\\W3C Web Pages").listFiles();
		
		for (File file : files) {
			if (file.isFile()) {
				listofPages.add(file.getName());
				listofText.add(file.getName().split("htm")[0]+"txt");
				
				
			}
		}
		WebPageProcessor Processor = new WebPageProcessor(listofPages,listofText);
		DataNode run = Processor.trieFormed();
		Algorithm algo = new Algorithm(run.getSt(),run.getHt());
		
		Scanner sc=new Scanner(System.in); 
		String query = sc.next();
		System.out.print("Please query for the relaven information");
		System.out.print("Word-? searches for the pages");
		System.out.print("Word-! shows your suggestion for the word");
		System.out.print("Word-c shows cached data");
		
		while(query != "exit") {
			
			String [] query_arrary = query.split("-");
			if (query_arrary[query_arrary.length-1] == "?"){
				List<String> answer = algo.websiteLink("University", listofPages);
				for (int i = 0; i< answer.size()  ; i++) {
					System.out.println(answer.get(i));
				}
			}else if(query_arrary[query_arrary.length-1] == "!") {
				
				// suggested edit distance word
				
			}else if(query_arrary[query_arrary.length-1] == "c") {
			
				// show LRU cache edit distance word
			}
		

		}
	}

}
