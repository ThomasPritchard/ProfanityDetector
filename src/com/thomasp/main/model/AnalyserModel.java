package com.thomasp.main.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.thomasp.debug.Debug;

public class AnalyserModel {
	
	private String fileText;
	private LinkedHashMap<String, Boolean> wordMap;
	private ArrayList<String> wordArray;
	
	public AnalyserModel(String filePath) {		
		wordMap = FileManager.loadProfanityFileIntoHashMap("swearWords.txt");
		Debug.msg(wordMap.toString());
		
		// TODO turn the file text block into a listener action. 
		try {
			fileText = FileManager.loadSongFileIntoString(filePath);
		}catch(FileNotFoundException e){
			Debug.error("File not found");
		}
		
		Debug.msg(fileText);
	}
	
	public void compareFileWithMap() {
		// TODO Check if word file and swear word file have been correctly initialised. 
		fileToArray();
		
		// Compare all words in array with the swear word hash map. 
		for(int i = 0 ; i < wordArray.size() ; i++) {
			// TODO Finish for loop. 
		}
	}
	
	private void fileToArray(){ // Transfers all words from a file into an array. 
		wordArray = new ArrayList<String>();
		
		String tempString = "";
		
		// TODO Disregard punctuation inside of lyrics. 
		for(int i = 0 ; i < fileText.length() ; i++) {
			if(fileText.charAt(i) == ' ') {
				wordArray.add(tempString);
				tempString = "";
			}
			else {
				tempString = tempString + Character.toString(fileText.charAt(i)).toLowerCase();
			}
		}
		Debug.msg(wordArray.toString());
	}
}
