package stepDefinition;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataprovider.ConfigFileReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import project.TestPopulation;

public class OldNavyDefinitions {
	
	
	static final Logger LOGGER = LogManager.getLogger(OldNavyDefinitions.class.getName());


	ConfigFileReader configFileReader=new ConfigFileReader();
	WebDriver driver;
	
	@Given("Browser is open")
	public void browser_is_open() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver",configFileReader.getDriverPath());
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
	}

	@Given("User is on old navy home Page {string}")
	public void user_is_on_old_navy_home_page(String url) throws InterruptedException {
	   driver.get(configFileReader.getApplicationUrl());
	   driver.manage().window().maximize();
	   driver.navigate().refresh();
	   //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@When("User enters search item {string} in serach button")
	public void user_enters_search_item_in_serach_button(String searchItem) throws InterruptedException {
		    WebElement searchBox=driver.findElement(By.name("searchText"));
			searchBox.sendKeys(configFileReader.getSearchItem());
			searchBox.sendKeys(Keys.RETURN);
	 
	}

	@Then("User have to select second item from the items displayed")
	public void user_have_to_select_item_from_the_items_displayed() throws InterruptedException{
		WebElement wantedItem=driver.findElement(By.xpath("//*[@id=\"498771022\"]/a/img"));
		wantedItem.click();
	}

	@Then("User have to select the size")
	public void user_have_to_select_the_size() throws InterruptedException {
		//selecting size XL
		driver.findElement(By.id("variant-1-sizeDimension1-XL")).click();
	}

	@Then("User clicks Add to Bag button")
	public void user_clicks_add_to_bag_button() throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,500)");
		driver.findElement(By.id("AddToBag_add-to-bag__button")).click();
	}

	@Then("User clicks checkout")
	public void user_clicks_checkout() throws InterruptedException{
	
		driver.findElement(By.id("checkoutButton")).click();
	}

	@Then("again User clicks Checkout")
	public void again_user_clicks_checkout() throws InterruptedException {
		
		PropertyConfigurator.configure("log4j.properties");
		
		int size = driver.findElements(By.tagName("iframe")).size();
	    LOGGER.info("size:"+size);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		WebElement checkOut=wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout-button")));
		checkOut.click();
		Thread.sleep(1000);
	}

	@Then("verify whether the User is in the sign in page.")
	public void verify_whether_the_user_is_in_the_sign_in_page() throws InterruptedException {
		PropertyConfigurator.configure("log4j.properties");
	   String title=driver.getTitle();
	   LOGGER.info("Title:"+title);
	   Assert.assertEquals(title, "Sign in | Old Navy ");
		/*
		 * boolean status=
		 * driver.findElement(By.id("acc-verifyEmailAddress")).isDisplayed(); if(status)
		 * { System.out.println("you are in sign in page"); } else {
		 * System.out.println("Not successful"); }
		 */
	   
	  
	 
	}

}
