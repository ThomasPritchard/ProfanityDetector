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
		
		// TODO turn the file text block into a listener action. 
		try {
			fileText = FileManager.loadSongFileIntoString(filePath);
		}catch(FileNotFoundException e){
			Debug.error("File not found");
		}
		
		Debug.msg(fileText);
	}
	
	public void compareFileWithMap() {
		// TODO Check if word file and swear word file have been correctly initialised. Wait until GUI has been made. 
		fileToArray();
		
		// Compare all words in array with the swear word hash map. 
		for(int i = 0 ; i < wordArray.size() ; i++) {
			if(wordMap.containsKey(wordArray.get(i))) {
				Debug.msg("Swear word detected");
				return;
			}
		}
		Debug.msg("No swear word detected");
	}
	
	private void fileToArray(){ // Transfers all words from a file into an array. 
		final int LAST_WORD_PADDING_REMOVE_CONSTANT = 2;
		String regexFindPunctuation = "[,.!?\\\\-]";
		
		wordArray = new ArrayList<String>();
		
		String tempString = "";
		
		for(int i = 0 ; i < fileText.length() ; i++) {
			if(fileText.charAt(i) == ' ' || Character.toString(fileText.charAt(i)).matches(regexFindPunctuation)) {
				wordArray.add(tempString);
				tempString = "";
			}
			else {
				tempString = tempString + Character.toString(fileText.charAt(i)).toLowerCase();
			}
		}
		
		// For the last word in the for loop that does not get added to the array. 
		tempString = tempString.substring(0, tempString.length() - LAST_WORD_PADDING_REMOVE_CONSTANT);
		wordArray.add(tempString);
		Debug.msg(wordArray.toString());
	}
}
