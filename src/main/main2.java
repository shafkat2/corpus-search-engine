package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;

import textprocessing.TST;
import textprocessing.TrieST;

public class main2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String [] wordSearch = {"protein","complex","PPI","prediction"};
		TST<Integer> st = new TST<Integer>();
		String file = "C:\\Users\\Shafkat\\eclipse-workspace\\new\\Assignment4\\src\\textprocessing\\Protein.txt";
		Path path = Paths.get(file);
		List<String> lines = Files.readAllLines(path);
		for (int i = 0; i <lines.size(); i++) {
			
			StringTokenizer baka = new StringTokenizer(lines.get(i));
			while(baka.hasMoreTokens()) {
				String Token = baka.nextToken();
				if (st.get(Token) == null) {
					st.put(Token, 1);
				}else {
					st.put(Token,st.get(Token)+1);
				}
				
			}
		}
		for (int i = 0; i < wordSearch.length;i++) {
			
			System.out.println("The occurences of "+ wordSearch[i] +" is "+st.get(wordSearch[i]));
			
		}
			
			
			
	}
	

}
