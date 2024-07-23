package gyansys.QA;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DemoWebShop {

    public static WebDriver driver;
    public static Properties PROP;
    public static FileReader readHomePage, readRegisterPage;
	
    @Test(description ="To initiate the test",enabled = true)
    public static void initiateTest() throws IOException
	{
	    PROP = new Properties();
	
	    FileReader readHomePage = new FileReader("../gyansys.QA/PageLocators/Home_page.properties");
	    FileReader readRegisterPage = new FileReader("../gyansys.QA/PageLocators/Register.properties");
	
	    PROP.load(readHomePage);
	    PROP.load(readRegisterPage);
	
	    // Assuming that the ChromeDriver executable is in your system path
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    System.out.println("InitiatedTest");
	}
    
    @Test(description ="To create new user",enabled = false)
    public static void createNewUser() throws InterruptedException {
        
            WebElement registerLink = driver.findElement(By.linkText(PROP.getProperty("registerlink")));
            registerLink.click();

            driver.findElement(By.id(PROP.getProperty("femaleradiobutton_ID"))).click();
            driver.findElement(By.id(PROP.getProperty("firstname_ID"))).sendKeys(PROP.getProperty("firstname_value_ID"));
            driver.findElement(By.id(PROP.getProperty("lastname_ID"))).sendKeys(PROP.getProperty("lastname_value_ID"));
            driver.findElement(By.id(PROP.getProperty("email_ID"))).sendKeys(PROP.getProperty("email_value_ID"));
            driver.findElement(By.id(PROP.getProperty("password_ID"))).sendKeys(PROP.getProperty("password_value_ID"));
            driver.findElement(By.id(PROP.getProperty("confirmpassword_ID"))).sendKeys(PROP.getProperty("confirmpassword_value_ID"));

            driver.findElement(By.id(PROP.getProperty("registerbtn_ID"))).click();
        
            Thread.sleep(5000);
    }

    

    public static void launchHomePage() throws InterruptedException {
            driver.get(PROP.getProperty("URL"));

            String actualHomePageTitle = driver.getTitle();
            String expectedHomePageTitle = "Demo Web Shop";
            WebElement loginLink = driver.findElement(By.linkText("Log in"));
            loginLink.click();
            String loginPageTitle = driver.getTitle();
            System.out.println("Launched Home Page");
            System.out.println("The home page title is: " + actualHomePageTitle);
            System.out.println("The login page title is: " + loginPageTitle);

            Thread.sleep(5000);;
            
            // Verify whether the landed page is the home page
            if (expectedHomePageTitle.equals(actualHomePageTitle)) {
                System.out.println("This is the correct Home Page");
            } else {
                System.out.println("This is not the correct Home Page...");
            }
      
    }
    
    public static void verifyRegistration() {
        WebElement registeredEmail = driver.findElement(By.className("account"));
        String expectedEmail = PROP.getProperty("email_value_ID");
        String confirmationMsg = driver.findElement(By.xpath("//div[contains(text(),'Your registration completed')]")).getText();
        System.out.println("Verifying Registration");
        
        if (registeredEmail.getText().equals(expectedEmail) && confirmationMsg.contains("Your registration completed")) {
            System.out.println("Registration successful. Registered email: " + registeredEmail.getText()+ " Confirmation message: " + confirmationMsg);
        } else {
            System.out.println("Registration failed. Expected email: " + expectedEmail + ", but found: " + registeredEmail.getText());
        }
        
        driver.findElement(By.className(PROP.getProperty("logout_link_class"))).click();
    }
    public static void PerformingLogin() {
    	
    	System.out.println("Performing Login"); 
    	
         driver.findElement(By.className(PROP.getProperty("login_link_class"))).click();
         
         driver.findElement(By.id(PROP.getProperty("login_email_Id"))).sendKeys(PROP.getProperty("email_value_ID"));
         driver.findElement(By.id(PROP.getProperty("login_Pwd_Id"))).sendKeys(PROP.getProperty("password_value_ID"));
         
         driver.findElement(By.id(PROP.getProperty("Rememberme_checkbox_Id"))).click();
         driver.findElement(By.xpath(PROP.getProperty("login_btn_xpath"))).click();
         System.out.println("Performing login completed");
     
    }

    public static void verifyLogin()
    {
    	System.out.println("Verify Login");
    	WebElement registeredEmail = driver.findElement(By.className("account"));
        String expectedEmail = PROP.getProperty("email_value_ID");
        if (registeredEmail.getText().equals(expectedEmail)) {
            System.out.println("Logged in successfully with this Email: " + registeredEmail.getText());
        } else {
            System.out.println("can't able to login check password and the EmailID");
        }
    	
    }

    public static void quitBrowser() 
    {
        driver.quit();
        System.out.println("Testing completed. Browser closed.");
    }

    public static void main(String[] args) throws IOException,InterruptedException {
        initiateTest();
        launchHomePage();
        createNewUser();
        verifyRegistration();
        PerformingLogin();
        verifyLogin();
        quitBrowser();
    }
}
