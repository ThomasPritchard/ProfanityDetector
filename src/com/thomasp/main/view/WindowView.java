package com.thomasp.main.view;

import com.thomasp.debug.Debug;
import com.thomasp.main.model.AnalyserModel;

public class WindowView {
	
	private AnalyserModel model;
	
	public WindowView(AnalyserModel model) {
		this.model = model;
	}
	
	public void printDetected() {
		Debug.msg("Swear word detected"); // TODO Change to a view message. 
	}
	
	public void printNotDetected() {
		Debug.msg("No swear word detected"); 
	}
}
