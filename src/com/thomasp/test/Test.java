package com.thomasp.test;

import com.thomasp.main.controller.AnalyserController;
import com.thomasp.main.model.AnalyserModel;
import com.thomasp.main.view.WindowView;

import javafx.application.Application;
import javafx.stage.Stage;

public class Test extends Application {
	
	private static AnalyserModel model;

	public static void main(String[] args) {
		model = new AnalyserModel(); 	
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		WindowView view = new WindowView(model, primaryStage);
		@SuppressWarnings("unused")
		AnalyserController controller = new AnalyserController(model, view);
	}
}
