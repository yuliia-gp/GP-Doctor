package com.GeniusDoctor.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;			// for cucumber 4.3 version

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions; // this is for cucumber 4.8 version


@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/features"
		,glue="com/GeniusDoctor/steps"	
		,dryRun = false
		,plugin= {"pretty" 
				, "html:target/html/cucumber-default-report"
				, "json:target/cucumber.json"
				, "junit:target/cucumber.xml"
				, "rerun:target/failed.txt"}
		,monochrome=true 
			
		)

public class RunnerClass {

	
	
}


