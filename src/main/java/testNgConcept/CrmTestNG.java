package testNgConcept;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CrmTestNG {

	WebDriver Driver;
	String browser = null;
	String url = null;
	
	@BeforeClass
	public void readConfig() {
		
		Properties prop = new Properties();
		// InputStream  // FileReader // BufferedReader  // Scanner
		
		try {
			
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Used Browser: " + browser);
			url = prop.getProperty("url");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	@BeforeMethod
	public void launchBrowser() {

		

		if(browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			Driver = new ChromeDriver();
			
		}else if(browser.equalsIgnoreCase("Firefox")){
			System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
			Driver = new FirefoxDriver();
		}
		
		
		Driver.get(url);
		Driver.manage().window().maximize();
		Driver.manage().deleteAllCookies();
		Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test(priority = 1 )
	public void loginTest() {

		// varType varName = varValue

		// storing WebElement
		// element library

		WebElement USERNAME_FIELD_ELEMENT = Driver.findElement(By.xpath("//input[@id=\'username\']"));
		WebElement PASSWORD_FEILD_ELEMENT = Driver.findElement(By.xpath("//input[@id=\'password\']"));
		WebElement SINGIN_BUTTON_ELEMENT = Driver.findElement(By.xpath("//button[@name='login']"));

		USERNAME_FIELD_ELEMENT.clear();
		USERNAME_FIELD_ELEMENT.sendKeys("demo@techfios.com");
		PASSWORD_FEILD_ELEMENT.sendKeys("abc123");

		// SINGIN_BUTTON.click();
		SINGIN_BUTTON_ELEMENT.click();

		WebElement DASHBOARD_PAGE_TITLE_ELEMENT = Driver.findElement(By.xpath("//h2[contains(text(),'Dashboard')]"));
		
		WebDriverWait wait = new WebDriverWait(Driver, 3);
		wait.until(ExpectedConditions.visibilityOf(DASHBOARD_PAGE_TITLE_ELEMENT));
		
		waitForElement(Driver, 3, DASHBOARD_PAGE_TITLE_ELEMENT );
		
		
		Assert.assertEquals(DASHBOARD_PAGE_TITLE_ELEMENT.getText(), "Dashboard", "Dashboard title doesn't match!!");

	}
	
	
	public void waitForElement(WebDriver driver, int timeInSeconds, WebElement locator) {
		WebDriverWait wait = new WebDriverWait(Driver, 3);
		
		
	}

	@Test(priority=2)
	public void addCustomerTest() throws InterruptedException {

		// Element Library
		By USER_NAME_FIELD = By.id("username");
		By PASSWORD_FIELD = By.id("password");
		By SIGNIN_BUTTON = By.name("login");
		By DASHBOARD_BUTTON = By.xpath("//span[contains(text(), 'Dashboard')]");
		By CUSTOMERS_BUTTON = By.xpath("//span[contains(text(), 'Customers')]");
		By ADD_CUSTOMER_BUTTON = By.xpath("//a[contains(text(), 'Add Customer')]");
		By ADD_CONTACT_LOCATOR = By.xpath("//h5[contains(text(),'Add Contact')]");
		By FULL_NAME_FIELD = By.xpath("//input[@id='account']");
		By COMPANY_NAME_FIELD = By.xpath("//input[@id='company']");
		By EMAIL_FIELD = By.xpath("//input[@id='email']");
		By PHONE_FIELD = By.xpath("//input[@id='phone']");
		By ADDRESS_FIELD = By.xpath("//input[@id='address']");
		By CITY_FIELD = By.xpath("//input[@id='city']");
		By STATE_REGION_FIELD = By.xpath("//input[@id='state']");
		By ZIP_FIELD = By.xpath("//input[@id='zip']");
		By SUBMIT_BUTTON = By.xpath("//button[@class='btn btn-primary']");
		By LIST_CONTACTS_BUTTON = By.xpath("//a[contains(text(),'List Contacts')]");

		// Login Data
		String loginId = "demo@techfios.com";
		String password = "abc123";
		
		//Test Data
		String fullName= "Test June";

		String companyName = "Techfios";
		String emailAddress = "test@gmail.com";
		String phoneNumber = "2316564564";
		
		//Perform Login In
		Driver.findElement(USER_NAME_FIELD).sendKeys(loginId);
		Driver.findElement(PASSWORD_FIELD).sendKeys(password);
		Driver.findElement(SIGNIN_BUTTON).click();
		
		//Validate Dashboard Page
		waitForElement(Driver, 3, DASHBOARD_BUTTON);
		String dashboardValidationText = Driver.findElement(DASHBOARD_BUTTON).getText();
		Assert.assertEquals("Dashboard", dashboardValidationText, "Wrong Page!!!");
		
		Driver.findElement(CUSTOMERS_BUTTON).click();
		Driver.findElement(ADD_CUSTOMER_BUTTON).click();
		waitForElement(Driver, 3, ADD_CONTACT_LOCATOR);
		
		//Generate Random Number
		Random rnd = new Random();
		int randomNum = rnd.nextInt(999);
		
		//Fill out add customers form
		Driver.findElement(FULL_NAME_FIELD).sendKeys(fullName + randomNum);
		Driver.findElement(EMAIL_FIELD).sendKeys(randomNum + emailAddress);
		
	}

		
		
		
	

	
//
//	@AfterMethod
//	public void tearDown() {
//		Driver.close();
//		Driver.quit();
//	}
	
	
	public void waitForElement(WebDriver driver, int timeInSeconds, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));



}
	
}


