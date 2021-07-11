package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import textprocessing.BoyerMoore;
import textprocessing.BruteForceMatch;
import textprocessing.KMP;

public class main {

	public static void main(String[] args) throws IOException {
		
		int numberTime  = 100;
		long [] algoTime = new long[3];
		long start = 0;
		long end = 0;
		long avgTime = 0;
		String [] algoName = {"BruteForceMatch","BoyerMoore","KMP"};
		
		String [] patterns = {"hard","disk","hard disk","hard dist","xltpru"};
		String file = "C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment4\\src\\textprocessing\\hardisk.txt";
		Path path = Paths.get(file);
		List<String> lines = Files.readAllLines(path);
		for (int a = 0; a<algoTime.length; a++) {
			avgTime = 0;
			for (int b = 0; b < numberTime; b++  ) {
				for ( int c = 0; c< patterns.length; c++) {
					int count = 0;
					int offset = 0;
					
					for( int d = 0; d < lines.size(); d++ ) {
						start = System.nanoTime();
						if (algoName[a] == "BruteForceMatch") {
							
							int temp = BruteForceMatch.search1(patterns[c], lines.get(d));
							if (temp != lines.get(d).length()){
								offset = offset + temp; 
								count++;
							}else {
								offset = offset + temp;
							}
							
						}else if (algoName[a] == "BoyerMoore") {
							
							BoyerMoore boyermoore1 = new BoyerMoore(patterns[c]);
							int temp = boyermoore1.search(lines.get(d));
							if (temp != lines.get(d).length()){
								offset = offset + temp;		
								count++;
							}else {
								offset = offset + temp;
							}
							
						}else if (algoName[a] == "KMP") {
							
							KMP kmp1 = new KMP(patterns[c]);
							int temp = kmp1.search(lines.get(d));
							if (temp != lines.get(d).length()){
								offset = offset + temp;
								count++;
							}else {
								offset = offset + temp;
							}
							
						}
						end = System.nanoTime();
						avgTime = avgTime + (end - start);
						System.out.print("offset: "+ offset +" ");
					
					}
					System.out.println();
					System.out.println("Pattern: "+ patterns[c]+" "+ " count: "+count );
					System.out.println("----------------------------------------------");
						
				}
			}
			algoTime[a] = avgTime/100;
			System.out.println("Algoname: "+ algoName[a]+" "+ " Time: "+algoTime[a] );
		}
		
		
		
	}

}
