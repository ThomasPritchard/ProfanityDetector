package com.thomasp.main.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.thomasp.debug.Debug;
import com.thomasp.main.model.AnalyserModel;

import javafx.geometry.Insets;
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
	
	private AnalyserModel model;
	
	public WindowView(AnalyserModel model, Stage primaryStage) {
		this.model = model;		
		try {
			initialiseWindow(primaryStage);
		} catch (FileNotFoundException e) {
			Debug.error("File not found");
		}
	}

	private void initialiseWindow(Stage primaryStage) throws FileNotFoundException {
		var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Creating a borderPane object.
		var bPane = new BorderPane();
		
		// Creating a horizontal box. 
		var hBox = addHorizontalBox();
		
		// Creating the logo.
		var logo = new Image(new FileInputStream("resources/site_logo.png"));
		
		var imageView = new ImageView(logo);
		configureImageView(imageView, screenSize);
		
		// Adding the logo to the horizontal box.
		hBox.getChildren().add(imageView);
		
		// Creating the title. 
		var title = new Text();
		setTitle(screenSize, title);
		
		// Adding title to the horizontal box. 
		hBox.getChildren().add(title);
		
		// Adding box to the top of the window. 
		bPane.setTop(hBox);
		
		// Creating textfield.
		var middleText = new TextArea();
		middleText.setFont(new Font(15));
		middleText.setText("bobba");
		BorderPane.setMargin(middleText, new Insets(12,12,175,12));
		bPane.setCenter(middleText);
		
		// Adding buttons at the bottom of the window. 
		// TODO Create a hBox for this. 
		var analyseLyricsButton = new Button("Analyse Lyrics");
		analyseLyricsButton.setPrefSize(100, 50);
		BorderPane.setMargin(analyseLyricsButton, new Insets(12,12,30,200));
		bPane.setBottom(analyseLyricsButton);
		
		// Creating the scene. 
		var scene = new Scene(bPane, screenSize.getWidth() - 200, screenSize.getHeight() - 200);
		
		// Configuring the stage. 
		configureStage(primaryStage, scene);
	}
	
	private HBox addHorizontalBox() {
		var hBox = new HBox();
		hBox.setPadding(new Insets(10, 10, 10, 10));
	    hBox.setSpacing(10);
	    hBox.setStyle("-fx-background-color: #FFFFFF;");
		return hBox;
	}
	
	private void configureImageView(ImageView imageView, Dimension screenSize) {		
		imageView.setFitHeight(81);
		imageView.setFitWidth(409);
		imageView.setPreserveRatio(true);
	}

	private void setTitle(Dimension screenSize, Text title) {
		title.setFont(new Font(45));
		title.setText("JetStream Radio Profanity Detector");
		title.setFill(Color.BLACK);
	}
	
	private void configureStage(Stage primaryStage, Scene scene) {
		primaryStage.setTitle("Profanity Analyser");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();
	}
	
	public void printDetected() {
		Debug.msg("Swear word detected"); // TODO Change to a view message. 
	}
	
	public void printNotDetected() {
		Debug.msg("No swear word detected"); 
	}
}
