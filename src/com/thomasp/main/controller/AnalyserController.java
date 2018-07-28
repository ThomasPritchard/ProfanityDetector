package com.thomasp.main.controller;

import com.thomasp.main.model.AnalyserModel;
import com.thomasp.main.view.WindowView;

public class AnalyserController {
	
	private AnalyserModel model;
	private WindowView view;
	
	public AnalyserController(AnalyserModel model, WindowView view) {
		this.model = model;
		this.view = view;
	}
}
