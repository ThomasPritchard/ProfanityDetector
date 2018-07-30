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
			var br = new BufferedReader(new FileReader(filePath));
			var sb = new StringBuilder();
			var line = br.readLine();
			
			while(line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			
			fileString = sb.toString();
			br.close();
		} catch (IOException e) {
			Debug.error("IO error found");
			System.exit(1);
		} 
		return fileString;
	}
	
	public static LinkedHashMap<String, Boolean> loadProfanityFileIntoHashMap(String filePath){ // Should only be used by the swear word file. 
		var wordMap = new LinkedHashMap<String, Boolean>();
		
		try {
			var br = new BufferedReader(new FileReader(filePath));
			var line = br.readLine();
			
			while(line != null) {
				wordMap.put(line, true);
				line = br.readLine();
			}
			
			br.close();
		} catch(IOException e) {
			Debug.error("IO error found");
			System.exit(1);
		}
		return wordMap;
	}
}
