package com.thomasp.test;

import com.thomasp.main.controller.AnalyserController;
import com.thomasp.main.model.AnalyserModel;
import com.thomasp.main.view.WindowView;

import javafx.application.Application;

public class Test {

	public static void main(String[] args) {
		
		AnalyserModel model = new AnalyserModel(); 
		WindowView view = new WindowView(model);
		AnalyserController controller = new AnalyserController(model, view, "songLyrics.txt"); // TODO Take away file name from constructor. 
		
		controller.startAnalysis(); 
		//Application.launch(WindowView.class, args);
	}
}
