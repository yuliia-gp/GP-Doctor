package com.GeniusDoctor.steps;


import com.GeniusDoctor.utils.CommonMethods;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends CommonMethods {

	@Before
	public void start(Scenario scenario) {	// passing scenario so we can use some methods of the 
		System.out.println("Starting scenario "+scenario.getName());	// Scenario interface 
		setUp();
		//initializeAllPages();
	}
	
	@After
	public void end(Scenario scenario) {
		System.out.println("Ending scenario "+scenario.getName());
		if(scenario.isFailed()) {
			byte[] picture = takeScreenshot("/failed/" +scenario.getName());
			scenario.embed(picture, "image/png");
		}
		else {
			byte[] picture = takeScreenshot("/passed/" +scenario.getName());
			scenario.embed(picture, "image/png");
		}
		
		closeBrowser();
	}
	
}
