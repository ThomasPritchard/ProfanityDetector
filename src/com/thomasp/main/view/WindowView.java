package com.thomasp.main.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.thomasp.debug.Debug;
import com.thomasp.main.model.AnalyserModel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WindowView{
	
	@SuppressWarnings("unused")
	private AnalyserModel model;
	private Button quitButton;
	private Button analyseLyricsButton;
	private Stage primaryStage;
	private TextArea middleText;
	private Text notificationsText;
	
	public WindowView(AnalyserModel model, Stage primaryStage) {
		this.model = model;		
		try {
			initialiseWindow(primaryStage);
		} catch (FileNotFoundException e) {
			Debug.error("Logo file not found");
		}
	}
	
	// Configure all GUI graphics here. 

	private void initialiseWindow(Stage primaryStage) throws FileNotFoundException {
		this.primaryStage = primaryStage;
		var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Creating a borderPane object.
		var bPane = new BorderPane();
		
		// Creating a horizontal box. 
		var hBox = addHorizontalBox("-fx-background-color: #FFFFFF;");
		
		// Creating the logo.
		var logo = new Image(new FileInputStream("resources/site_logo.png"));
		
		var imageView = new ImageView(logo);
		imageView.setFitHeight(61);
		imageView.setFitWidth(389);
		imageView.setPreserveRatio(true);
			
		// Adding the logo to the horizontal box.
		hBox.getChildren().add(imageView);
		
		// Creating the title. 
		var title = new Text();
		title.setFont(new Font("Anke", 45));
		title.setText("JetStream Radio Profanity Detector");
		title.setFill(Color.BLACK);
		
		// Adding title to the horizontal box. 
		hBox.getChildren().add(title);
		
		hBox.setAlignment(Pos.CENTER_LEFT);
			
		// Adding box to the top of the window. 
		bPane.setTop(hBox);
		
		// Creating textfield.
		middleText = new TextArea();
		middleText.setFont(new Font(15));
		BorderPane.setMargin(middleText, new Insets(12,12,50,12));
		bPane.setCenter(middleText);
		
		// Adding buttons at the bottom of the window. 
		// Button to initialse lyrics analyser.
		var hBox2 = addHorizontalBox("-fx-background-color: #F4F4F4;");
		analyseLyricsButton = new Button("Analyse Lyrics");
		analyseLyricsButton.setPrefSize(200, 100);
		analyseLyricsButton.setFont(new Font(25));
		
		// Quit button. 
		quitButton = new Button("Exit");
		quitButton.setPrefSize(200, 100);
		quitButton.setFont(new Font(25));
		
		// Notifications text. 
		notificationsText = new Text();
		notificationsText.setText("Press 'Analyse Lyrics' to search for a file");
		notificationsText.setFont(new Font(45));
		
		// Adding buttons to the hBox and adding the box to the pane. 
		hBox2.getChildren().add(analyseLyricsButton);
		hBox2.getChildren().add(quitButton);
		hBox2.getChildren().add(notificationsText);
		hBox2.setAlignment(Pos.CENTER);
		BorderPane.setMargin(hBox2, new Insets(0,0,50,0));
		bPane.setBottom(hBox2);
		
		// Creating the scene. 
		var scene = new Scene(bPane, screenSize.getWidth() - 200, screenSize.getHeight() - 200);
		
		// Configuring the stage. 
		configureStage(primaryStage, scene);
	}
	
	private HBox addHorizontalBox(String backgroundColor) {
		var hBox = new HBox();
		hBox.setPadding(new Insets(10, 10, 10, 10));
	    hBox.setSpacing(10);
	    hBox.setStyle(backgroundColor);
		return hBox;
	}
	
	public void setNotificationText(String text) {
		notificationsText.setText(text);
	}
	
	private void configureStage(Stage primaryStage, Scene scene) {
		primaryStage.setTitle("Profanity Analyser");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();
	}
	
	public Stage getStage() {
		return primaryStage;
	}
	
	public void setText(String text) {
		middleText.setText(text);
	}
	
	// Configure all listeners here. 
	
	// Exit Listener.
	public void setExitListener(EventHandler<ActionEvent> exitHandler) {
		quitButton.setOnAction(exitHandler);
	}
	
	// Analyser Listener.
	public void setAnalyseListener(EventHandler<ActionEvent> analyseHandler) {
		analyseLyricsButton.setOnAction(analyseHandler);
	}
	
	// Error handling
	
	public void createErrorDialog(String errorMessage) {
		
	}
	
	// Console debugging tests.  
	
	public void printDetected() {
		Debug.msg("Swear word detected"); 
	}
	
	public void printNotDetected() {
		Debug.msg("No swear word detected"); 
	}
}
