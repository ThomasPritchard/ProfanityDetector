package com.thomasp.main.controller;

import java.io.FileNotFoundException;

import com.thomasp.debug.Debug;
import com.thomasp.main.model.AnalyserModel;
import com.thomasp.main.model.FileManager;
import com.thomasp.main.view.WindowView;

public class AnalyserController {
	
	private AnalyserModel model;
	private WindowView view;
	private String fileText;
	
	public AnalyserController(AnalyserModel model, WindowView view, String filePath) {
		this.model = model;
		this.view = view;
		
		// TODO turn the file text block into a listener action. 
		try {
			fileText = FileManager.loadSongFileIntoString(filePath);
		}catch(FileNotFoundException e){
			Debug.error("File not found"); 
		}
				
		model.setFileText(fileText);
		Debug.msg(fileText); 
	}
	
	public void startAnalysis() {
		if(model.compareFileWithMap()) {
			view.printDetected();
		}
		else {
			view.printNotDetected();
		}
	}
}
