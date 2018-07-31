package com.thomasp.main.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.thomasp.debug.Debug;

public class AnalyserModel {
	
	private String fileText;
	private LinkedHashMap<String, Boolean> wordMap;
	private ArrayList<String> wordArray;
	
	private boolean hasImportedFile;
	
	public AnalyserModel() {	
		var getProgramFilesPath = System.getenv("ProgramFiles");
		wordMap = FileManager.loadProfanityFileIntoHashMap(getProgramFilesPath+"/ProfanityDetector/swearWords.txt"); // Loads pre-defined list of swear words. 
	}
	
	public void setFileText(String fileText) {
		this.fileText = fileText;
	}
	
	public boolean checkWordMapValidity() {
		if(wordMap.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean compareFileWithMap() {
		fileToArray();
		// Compare all words in array with the swear word hash map. 
		for(int i = 0 ; i < wordArray.size() ; i++) {
			if(wordMap.containsKey(wordArray.get(i))) {
				return true;
			}
		} 
		return false;
	}
	
	private void fileToArray() { // Transfers all words from a file into an array. 
		final var LAST_WORD_PADDING_REMOVE_CONSTANT = 2;
		var regexFindPunctuation = "[,.!?\\\\-]";
		
		wordArray = new ArrayList<String>();
		
		var tempString = "";
		
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
		if(hasImportedFile) tempString = tempString.substring(0, tempString.length() - LAST_WORD_PADDING_REMOVE_CONSTANT);
		else tempString = tempString.substring(0, tempString.length());
		wordArray.add(tempString);
		Debug.msg(wordArray.toString());
	}
	
	public void setImportedFile(boolean hasImportedFile) {
		this.hasImportedFile = hasImportedFile;
	}
}
