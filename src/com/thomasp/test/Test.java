package com.thomasp.test;

import com.thomasp.main.controller.AnalyserController;
import com.thomasp.main.model.AnalyserModel;
import com.thomasp.main.view.WindowView;

public class Test {

	public static void main(String[] args) {
		
		AnalyserModel model = new AnalyserModel("songLyrics.txt"); // TODO Take away file name from constructor. 
		WindowView view = new WindowView(model);
		AnalyserController controller = new AnalyserController(model, view);
		
		model.compareFileWithMap();

	}
}
