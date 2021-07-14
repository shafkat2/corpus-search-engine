package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import textprocessing.WebPageProcessor;

public class crawlerRun {
	public static void main(String[] args) throws IOException {
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
		
		Processor.ProcessPage();
		
	}
}
