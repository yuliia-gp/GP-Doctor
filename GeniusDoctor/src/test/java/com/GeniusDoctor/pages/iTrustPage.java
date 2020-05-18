package com.GeniusDoctor.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GeniusDoctor.utils.CommonMethods;


public class iTrustPage extends CommonMethods {

	@FindBy(id = "USER")
	public WebElement iTrustUserName;
	
	@FindBy(id = "PASSWORD")
	public WebElement iTrustPassword;
	
	@FindBy(id = "Image2")
	public WebElement iTrustLoginButton;
	
	

	
public iTrustPage() {
		
		
		PageFactory.initElements(driver, this);
	}
	
	
}
