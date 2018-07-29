package com.thomasp.main.view;

import com.thomasp.debug.Debug;
import com.thomasp.main.model.AnalyserModel;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WindowView extends Application {
	
	private AnalyserModel model;
	
	public WindowView(AnalyserModel model) {
		this.model = model;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
	}
	
	public void printDetected() {
		Debug.msg("Swear word detected"); // TODO Change to a view message. 
	}
	
	public void printNotDetected() {
		Debug.msg("No swear word detected"); 
	}
}
