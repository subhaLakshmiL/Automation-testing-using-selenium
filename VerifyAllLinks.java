package gyansys.QA;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class VerifyAllLinks {

    public static void main(String[] args) {
        // Setup ChromeDriver with options
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Set path to your ChromeDriver executable
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        
        WebDriver driver = new ChromeDriver(options);
        
            driver.get("https://demowebshop.tricentis.com/");

            // Find all links on the page
            List<WebElement> listOfLinks = driver.findElements(By.tagName("a"));
            int countLinks = listOfLinks.size();
            System.out.println("Total number of links on the page: " + countLinks);

            for (int i = 0; i < countLinks; i++) {
                WebElement link = listOfLinks.get(i);
                String linkText = link.getText();
                String linkUrl = link.getAttribute("href");
                System.out.println(i + "  Link text: " + linkText + " | URL: " + linkUrl);

                if (linkUrl != null && !linkUrl.isEmpty()) {
                    // Open link in a new tab
                    driver.switchTo().newWindow(WindowType.TAB);
                    driver.get(linkUrl);
                    // Print the title of the new page to verify the navigation
                    System.out.println("Navigated to: " + driver.getTitle());
                    // Close the new tab and switch back to the original tab
                    driver.close();
                    driver.switchTo().window(driver.getWindowHandles().iterator().next());
                }
            }

        
    }
}
