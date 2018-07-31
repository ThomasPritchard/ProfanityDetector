package com.thomasp.main.controller;

import java.io.File;
import java.io.FileNotFoundException;

import com.thomasp.debug.Debug;
import com.thomasp.main.model.AnalyserModel;
import com.thomasp.main.model.FileManager;
import com.thomasp.main.view.WindowView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AnalyserController {
	
	private AnalyserModel model;
	private WindowView view;
	private String fileText;
	
	public AnalyserController(AnalyserModel model, WindowView view) {
		this.model = model;
		this.view = view;
		
		createAnalyseHandler();
		createExitHandler();
		
		if(!model.checkWordMapValidity()) { // Checks if swear word file is there.
			view.createErrorDialog("Swear Word File Not Found", "You must provide a valid swear word file in order to continue");
		}
	}

	private void createAnalyseHandler() {
		var analyseHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { 
				try {
					var fileChooser = new FileChooser();
					fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
					var selectedFile = fileChooser.showOpenDialog(null);
					
					// Checking for the right file extension. 
					
					var extension = "";
					
					int i = selectedFile.getAbsolutePath().lastIndexOf('.');
					if(i > 0) {
						extension = selectedFile.getAbsolutePath().substring(i+1);
					}
					
					if(!(extension.equals("txt"))) {
						view.createInformationDialog("Chosen File Incompatible", "Please try another file");
						return;
					} else if(selectedFile != null) {	
						fileText = FileManager.loadSongFileIntoString(selectedFile.getAbsolutePath());
					}
					
				}catch(FileNotFoundException e){
					Debug.error("File not found"); 
					view.createInformationDialog("Chosen Text File Not Found", "Please try again");
					return;
				}
				
				// Checking if loaded file is valid.			
				if(fileText == null) {
					view.createInformationDialog("Chosen File Incompatible", "The file you are choosing is either incompatible or empty. Please try another file");
					return;
				}
				
				model.setFileText(fileText);
				Debug.msg(fileText);
				view.setText(fileText);
				
				if(model.compareFileWithMap()) {
					view.setNotificationText("Swear Word Found");
					view.printDetected();
				}else {
					view.setNotificationText("Swear Word Not Found");
					view.printNotDetected();
				}
			}
		};
		view.setAnalyseListener(analyseHandler);
	}

	private void createExitHandler() {
		var exitHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage stage = (Stage) view.getStage().getScene().getWindow();
				stage.close();
			}
		};
		view.setExitListener(exitHandler);
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
