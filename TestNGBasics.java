package gyansys.QA;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGBasics {

    public static WebDriver driver;

    // This method will run once before the entire test suite starts
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
    }

    // This method will run once after all the tests in the test suite have completed
    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite");
    }

    // This method will run before each group of tests specified in the TestNG XML configuration
    @BeforeGroups("someGroup")
    public void beforeGroups() {
        System.out.println("Before groups");
    }

    // This method will run after each group of tests specified in the TestNG XML configuration
    @AfterGroups("someGroup")
    public void afterGroups() {
        System.out.println("After groups");
    }

    // This method will run after each test method in the class
    //@AfterMethod
    public void logout() {
        System.out.println("Logout");
    }

    // This method will run before each test method in the class
    //@BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method");
    }

    // Test methods are commented out for demonstration. Uncomment to use.
     @Test(priority = 1, groups = "someGroup")
     public void launchApp() {
         System.out.println("launchApp");
     }

     @Test(priority = 2, groups = "someGroup")
     public void registerUser1() {
         System.out.println("registerUser1");
     }

     @Test(priority = 3, groups = "someGroup")
     public void registerUser2() {
         System.out.println("registerUser2");
     }

   // This method will run once before any of the test methods in the class are run
    @BeforeClass
    public void beforeClass() {
        System.out.println("Before class");
    }

    // This method will run once after all test methods in the class have completed
    @AfterClass
    public void afterClass() {
        System.out.println("After class");
    }

    // This method will run once after all test methods in the class have completed
    @AfterTest
    public void afterTest() {
        System.out.println("AfterTest");
    }

    // This method will run once before any of the test methods in the class are run
    @BeforeTest
    public void initiateTest() {
        System.out.println("initiateTest");
    }
}
