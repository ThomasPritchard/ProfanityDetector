package com.thomasp.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import com.thomasp.main.model.AnalyserModel;

public class JUnitTest {

	// Enter all tests here.
	
	// AnalyserModel tests. 
	
	@Test
	public void swearWordShouldBeDetected() {
		AnalyserModel modelTest = new AnalyserModel();
		
		modelTest.setFileText("You fucking idiot");
		
		assertTrue("Result should return true", modelTest.compareFileWithMap());
	}
	
	@Test
	public void fileArrayShouldContainNoPunctuationOrWhitespace() {
		AnalyserModel modelTest = new AnalyserModel();
		
		modelTest.setFileText("You fucking, idiot! I wished you died. Jeez help me.");
		
		modelTest.compareFileWithMap();
		
		ArrayList<String> wordArray = modelTest.getWordArray();
				
		for(int i = 0 ; i < wordArray.size() ; i++) {
			assertFalse("each word should contain no punctuation. ", wordArray.get(i).matches("[,.!?\\\\-]"));
			assertFalse("Each entry should contain no spaces.", StringUtils.isBlank(wordArray.get(i)));
		}
	}
}
