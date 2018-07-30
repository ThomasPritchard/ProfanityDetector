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
	}

	private void createAnalyseHandler() {
		var analyseHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { 
				try {
					var fileChooser = new FileChooser();
					fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
					var selectedFile = fileChooser.showOpenDialog(null);
					
					if(selectedFile != null) {	
						fileText = FileManager.loadSongFileIntoString(selectedFile.getAbsolutePath());
					}
					
				}catch(FileNotFoundException e){
					Debug.error("File not found"); 
					System.exit(1);
				}
						
				model.setFileText(fileText);
				Debug.msg(fileText);
				view.setText(fileText);
				
				if(model.compareFileWithMap()) {
					view.printDetected();
				}else {
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
