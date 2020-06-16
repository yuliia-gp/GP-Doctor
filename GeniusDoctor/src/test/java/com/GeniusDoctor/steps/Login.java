package com.GeniusDoctor.steps;

import org.openqa.selenium.By;

import com.GeniusDoctor.pages.LoginPageElements;
import com.GeniusDoctor.utils.CommonMethods;

import cucumber.api.java.en.*;

public class Login extends CommonMethods{
	
	LoginPageElements signUp = new LoginPageElements();

	@Given("User is on the GeniusDoctor signUp page")
	public void user_is_on_the_GeniusDoctor_signUp_page() {
	 String title = driver.getTitle();
	 System.out.println(title);
	}

	@When("User signs in")
	public void user_signs_in() {
		
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/nav/div[1]/div[2]/button")).click();
		signUp.firstname.sendKeys("Yuliia");
		signUp.lastName.sendKeys("Hudzenko");
		signUp.email.sendKeys("yuliia3@geniusplaza.com");
		signUp.password.sendKeys("123123");
		driver.findElement(By.xpath("//*[@id='root']/div[3]/div[1]/div/div[2]/form/div[5]/button")).click();
		
	}

	@Then("User should be successfully sign in")
	public void user_should_be_successfully_sign_in() {
	   String text = driver.getTitle();
	   System.out.println(text);
	}

	
	
	
	@Given("User is on the GeniusDoctor page")
	public void user_is_on_the_GeniusDoctor_page() {
	   String logo = driver.getTitle();
	   System.out.println(logo);
	}

	@When("User is logs in")
	public void user_is_logs_in() {
	  driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/nav/div[1]/div[2]/button")).click();
	  signUp.emailLogIn.sendKeys("yhudzenko1166@gmail.com");
	  signUp.passwordLogIn.sendKeys("123123");
	  signUp.loginBtn.click();
	}

	@Then("User should be successfully log in")
	public void user_should_be_successfully_log_in() {
	  String text = driver.getTitle();
	  System.out.println(text);
	}





	
}
