package com.GeniusDoctor.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GeniusDoctor.utils.CommonMethods;

public class LoginPageElements extends CommonMethods{

	
		@FindBy(linkText = "Sign up")
		public static WebElement signUpBtn;
		
		@FindBy(name = "firstName")
		public WebElement firstname;
		
	    @FindBy(name = "lastName")
	    public WebElement lastName;
	    
		@FindBy( name = "email")
		public WebElement email;
		
		@FindBy(name = "password")
		public WebElement password;
		
		@FindBy( linkText = "d3k2t9-7 hluMWJ btn btn-orange")
		public WebElement SignUp;

		@FindBy(name = "email")
		public WebElement emailLogIn;
		
		@FindBy(name = "password")
		public WebElement passwordLogIn;
		
		@FindBy (xpath = "//*[@id='root']/div[3]/div[1]/div/div[2]/form/div[3]/button")
		public WebElement loginBtn;
		
		
		
		
		
		
		public LoginPageElements() {
			
			
			PageFactory.initElements(driver, this);
		}
		
	

}
