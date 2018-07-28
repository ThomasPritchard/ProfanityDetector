// The file loader class helps to load text files to be analysed by the model. 
package com.thomasp.main.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

import com.thomasp.debug.Debug;

public class FileManager {
	
	private static String fileString;

	public static String loadSongFileIntoString(String filePath) throws FileNotFoundException { // Read file and returns text in the file as a string. 
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			
			while(line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			
			fileString = sb.toString();
			br.close();
		} catch (IOException e) {
			Debug.error("IO error found");
		} 
		return fileString;
	}
	
	public static LinkedHashMap<String, Boolean> loadProfanityFileIntoHashMap(String filePath){ // Should only be used by the swear word file. 
		LinkedHashMap<String, Boolean> wordMap = new LinkedHashMap<String, Boolean>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = br.readLine();
			
			while(line != null) {
				wordMap.put(line, true);
				line = br.readLine();
			}
			
			br.close();
		} catch(IOException e) {
			Debug.error("IO error found");
		}
		return wordMap;
	}
}
