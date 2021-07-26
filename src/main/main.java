package main;

import java.io.File;
import textprocessing.webcrawler;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import textprocessing.DataNode;
import textprocessing.LRUCache;
import textprocessing.Utils;
import textprocessing.WebPageProcessor;

public class main {
	public static void crawlerF( String urlToCrawl) {
		
		String sourceUrl = urlToCrawl;
		List<String> urlsList = new ArrayList<String>();
		
		// Crawling until we get an expected NO_OF_URLS
		webcrawler.crawUrls(sourceUrl, 100, urlsList);
		
		System.out.println(urlsList.size());
		for (String url : urlsList) {
			System.out.println(url);
			try {
				Document doc = Jsoup.connect(url).get();
				
				String html = doc.html();
				String title = doc.title();
				
				// Some title contains this special character so it causes error writing the html onto harddisk
				// Use this trick to avoid the error
				if (title.contains("|")) {
					title = title.split("\\|")[0];
					
				}
				Utils.writeStringToFile("C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment5\\src\\W3C Web Pages\\" + title.trim() + ".htm", html);
			} catch (Exception e) {
				continue;
			}
		}
	}
	
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
		
		System.out.println("Do you want to crawl a website provide valid url please -- :)");
		Scanner sc=new Scanner(System.in);
		String url = sc.nextLine();
		if (url.contentEquals( "No")) {
		}else {
			crawlerF(url);
		}

		
		
		WebPageProcessor Processor = new WebPageProcessor(listofPages,listofText);
		DataNode run = Processor.trieFormed();
		Algorithm algo = new Algorithm(run.getSt(),run.getHt());
		LRUCache cache = new LRUCache(3);
		
		System.out.println("Word-? searches for the pages");
		System.out.println("Word-! shows your suggestion for the word");
		System.out.println("cache-c shows cached data");
		String query = "";

		
		while(query != "exit") {
			System.out.println("");
			System.out.println("Please query for the relavent information");
			System.out.println("");
			query = sc.nextLine();
			String [] query_arrary = query.split("-");
			
			if (query_arrary[query_arrary.length-1].contentEquals("?")){
				
				HashMap<String,List<String>> maps = cache.getHashSet();
				List<String> answer = new ArrayList<String>();
				if (maps.containsKey(query_arrary[0]) ) {
					
					answer  = maps.get(query_arrary[0]);
					
				}else {
					answer = algo.websiteLink(query_arrary[0], listofPages);
					cache.refer(query_arrary[0], answer);
				
				}
	
				if (answer.size() == 0) {
					System.out.println("Nothing found");
				}else {
					for (int i = 0; i< answer.size()  ; i++) {
						System.out.println(answer.get(i));
					}
				}
				
			}else if(query_arrary[query_arrary.length-1].contentEquals( "!")) {
				
				List<String> suggestedWords = algo.suggestedWords(query_arrary[0]);
				for (int i = 0; i< suggestedWords.size()  ; i++) {
					System.out.println(suggestedWords.get(i));
				}
				
			}else if(query_arrary[query_arrary.length-1].contentEquals( "c") && query_arrary[0].contentEquals("cache")) {
					
					cache.display();
			}
			

		}
		sc.close();
	}

}
