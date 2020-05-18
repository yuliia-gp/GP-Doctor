package com.GeniusDoctor.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.GeniusDoctor.testbase.PageInitializer;

public class CommonMethods extends PageInitializer { // we have multilevel inheritence
														// baseclass -> pageinitializer -> commonmethods

	// all commonly used methods within framework

	// ALert Methods

	/**
	 * this method will accept the alert
	 * 
	 * @throws will throw NoAlertExeption if alert is not present.
	 */

	public static void acceptAlert() {

		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert is not present");
		}
	}

	/**
	 * this method will dismiss the alert
	 * 
	 * @throws will throw NoAlertExeption if alert is not present.
	 */

	public static void dismissAlert() {

		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert is not present");
		}
	}

	/**
	 * this method will get the alert text
	 * 
	 * @throws will throw NoAlertExeption if alert is not present.
	 */

	public static String getAlertText() {

		try {
			Alert alert = driver.switchTo().alert();
			return alert.getText();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert is not present");
			return null;
		}
	}

	// Frame methods

	/**
	 * This method will switch to the frame
	 * 
	 * @param nameOrId
	 */

	public static void switchToFrame(String nameOrId) {

		try {
			driver.switchTo().frame(nameOrId);
		} catch (NoSuchFrameException e) {
			System.out.println("Frame is not present.");
		}
	}

	/**
	 * This method will switch to the frame
	 * 
	 * @param element
	 */
	public static void switchToFrame(WebElement element) {

		try {
			driver.switchTo().frame(element);
		} catch (NoSuchFrameException e) {
			System.out.println("Frame is not present.");
		}
	}

	/**
	 * This method will switch to the frame
	 * 
	 * @param index
	 */
	public static void switchToFrame(int index) {

		try {
			driver.switchTo().frame(index);
		} catch (NoSuchFrameException e) {
			System.out.println("Frame is not present.");
		}
	}

	// JSExecutor Methods

	/**
	 * This method will click on the specified WebElement
	 * 
	 * @param element
	 */
	public static void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * This method will scroll to the view of the specified WebElement
	 * 
	 * @param element
	 */
	public static void scrollIntoElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * This method will scroll page down by the given pixel
	 * 
	 * @param pixel
	 */
	public static void scrollDown(int pixel) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + pixel + ")");
	}

	/**
	 * This method will scroll page up by the given pixel
	 * 
	 * @param pixel
	 */
	public static void scrollUp(int pixel) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -" + pixel + ")");
	}


	/**
	 * This method will take a screenshot and store it in a file and return the
	 * filePath Provide the file name as a parameter
	 * 
	 * @param fileName
	 */
	public static byte[] takeScreenshot(String fileName) {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("_yyyy_MMdd_HHmmss");
		String timeStamp = sdf.format(date.getTime());

		TakesScreenshot ts = (TakesScreenshot) driver;
		byte[] picture = ts.getScreenshotAs(OutputType.BYTES); // it'll return array of bytes
		// the two lines above is the only ones to take ss and pass it to cucumber

		// The others - we're keeping them to take ss as file and store it.

		File file = ts.getScreenshotAs(OutputType.FILE);
		String ssFile = Constants.SCREENSHOT_FILEPATH + fileName + timeStamp + ".png";
		try {
			FileUtils.copyFile(file, new File(ssFile));
		} catch (IOException e) {
			System.out.println("Cannot take a screenshot - " + e.getMessage());
		}

		return picture;
	}

	/**
	 * This method creates an explicit wait and returns the "wait"
	 * 
	 * @return
	 */
	public static WebDriverWait getWaitObject() {
		WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT_TIME);
		return wait;
	}

	/**
	 * This method will wait until the specified element is clickable
	 * 
	 * @param element
	 */
	public static void waitForClickability(WebElement element) {
		getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method will wait until the specified element is visible
	 * 
	 * @param element
	 */
	public static void waitForVisibility(WebElement element) {
		getWaitObject().until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method will wait until the specified element is invisible
	 * 
	 * @param element
	 */
	public static void waitForInvisibility(WebElement element) {
		getWaitObject().until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * This method will click on the specified element
	 * 
	 * @param element
	 */
	public static void click(WebElement element) {
		waitForClickability(element);
		element.click();
	}

	/**
	 * This method sends text to the specified element
	 * 
	 * @param element
	 * @param value
	 */
	public static void sendText(WebElement element, String value) {
		waitForVisibility(element);
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * This method will select the given text among visible texts within the given
	 * drop down element 
	 * 
	 * @param element
	 * @param text
	 */
	public static void selectDropdownByText(WebElement element, String text) {
		try {
			Select s = new Select(element);
			s.selectByVisibleText(text);
		} catch (Exception e) {
			System.out.println("Value could not be found within the dropdown options.");
		}
	}

	/**
	 * This method will select the option with the given index from the specified
	 * drop down
	 * 
	 * @param element
	 * @param index
	 */
	public static void selectDropdownByIndex(WebElement element, int index) {
		Select s = new Select(element);
		List<WebElement> options = s.getOptions();
		boolean isFound = false;
		if (options.size() > index) {
			s.selectByIndex(index);
			isFound = true;
		}

		if (!isFound) {
			System.out.println("The option with index " + index + " could not be selected.");
		}
	}

	/**
	 * This method will select the specified day from the specified calendar table
	 * 
	 * @param table
	 * @param day
	 */
	public static void selectDateFromTable(WebElement table, String day) {
		List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));
		List<WebElement> cells = new ArrayList<>();
		boolean daySelected = false;
		for (WebElement row : rows) {
			if (row.getText().contains(day)) {
				cells = row.findElements(By.xpath("./td/a"));
				break;
			}
		}
		for (WebElement cell : cells) {
			if (cell.getText().equals(day)) {
				jsClick(cell);
				daySelected = true;
				break;
			}
		}
		if (!daySelected) {
			System.out.println("The specified day could not be selected from the calendar.");
		}

	}

	/**
	 * This method will read a .json file and return it in a String type written in
	 * json format - for passing REST payloads
	 */
	static String jsonFile;

	public static String readJson(String fileName) {
		try {
			jsonFile = new String(Files.readAllBytes(Paths.get(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jsonFile;
	}
/**
 * This method is to select values from bootstrap drop downs 
 * Pass the element and date desired in String format as arguments
 * @param element
 * @param date
 */
	public static void selectDateByJS(WebElement element, String date) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','" + date + "');", element);

	}

}
