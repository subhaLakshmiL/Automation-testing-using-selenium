package gyansys.QA;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GiftCardPageTest {

    public static WebDriver driver;
    public static Properties PROP;

    public static void main(String[] args) throws IOException, InterruptedException {
        setUp();
        verifyPageTitle();
        verifyHeader();
        verifyGiftCardOptions();
        selectAndAddGiftCard();
        verifyGiftCardInCart();
        //searchGiftCard();
        //logout();
        quitBrowser();
    }

    public static void setUp() throws IOException {
        // Uncomment the following line if you are using WebDriverManager
        // WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Load properties for Gift Card Page
        PROP = new Properties();
        FileReader readGiftCardPage = new FileReader("../gyansys.QA/PageLocators/GiftCardPage.properties");
        PROP.load(readGiftCardPage);

        // Open the URL
        driver.get(PROP.getProperty("GiftCardPageURL")); // Use the property for the URL
    }

    public static void verifyPageTitle() {
        String expectedTitle = "Demo Web Shop. Gift Cards"; // Updated to match the actual page title
        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Successfully navigated to the 'Gift Cards' page. Page title is: " + actualTitle);
        } else {
            System.out.println("Failed to navigate to the 'Gift Cards' page. Expected title: " + expectedTitle + ", but got: " + actualTitle);
        }
    }

    public static void verifyHeader() {
        WebElement header = driver.findElement(By.tagName("h1"));
        String headerText = header.getText();
        if (headerText.equals("Gift Cards")) {
            System.out.println("Header text is as expected: " + headerText);
        } else {
            System.out.println("Header text does not match. Expected: 'Gift Cards', but got: " + headerText);
        }
    }

    public static void verifyGiftCardOptions() {
        WebElement giftCard1 = driver.findElement(By.xpath(PROP.getProperty("gift_card_xpath"))); // Use the property for the Gift Card option
        WebElement giftCard2 = driver.findElement(By.xpath("//a[contains(text(),'Physical Gift Card')]"));
        if (giftCard1.isDisplayed() && giftCard2.isDisplayed()) {
            System.out.println("Both Gift Card options are present on the page.");
        } else {
            System.out.println("One or both Gift Card options are not displayed.");
        }
    }

    public static void selectAndAddGiftCard() {
        // Click on the "Virtual Gift Card" link
        WebElement giftCardLink = driver.findElement(By.xpath(PROP.getProperty("gift_card_xpath")));
        giftCardLink.click();

        // Filling the details of recipient and sender in add cart
        driver.findElement(By.id(PROP.getProperty("addcart_receipentname_id"))).sendKeys(PROP.getProperty("receipent_name"));
        driver.findElement(By.id(PROP.getProperty("addcart_receipentmail_id"))).sendKeys(PROP.getProperty("receipent_mail"));
        driver.findElement(By.id(PROP.getProperty("addcart_sendername_id"))).sendKeys(PROP.getProperty("addcart_sendername"));
        driver.findElement(By.id(PROP.getProperty("addcart_sendermail_id"))).sendKeys(PROP.getProperty("addcart_sendermail"));
        driver.findElement(By.id(PROP.getProperty("addcart_giftcardmessage_id"))).sendKeys(PROP.getProperty("addcart_giftcard_message"));
        driver.findElement(By.id(PROP.getProperty("addcart_quantity_id"))).sendKeys(PROP.getProperty("addcart_quantity"));

        // Click the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.xpath(PROP.getProperty("add_to_cart_button_xpath")));
        addToCartButton.click();

        // Check for the success notification
        WebElement cartNotification = driver.findElement(By.id(PROP.getProperty("cart_notification_id")));
        String notificationText = cartNotification.getText();
        if (notificationText.contains("The product has been added to your ")) {
            System.out.println("Successfully added the Gift Card to the cart.");
        } else {
            System.out.println("Gift card was not added to the cart successfully.");
        }
    }

    public static void verifyGiftCardInCart() {
        // Click on the "Shopping cart" link
        WebElement cartLink = driver.findElement(By.className(PROP.getProperty("shoppingcart_Class")));
        cartLink.click();

        // Verify the Gift Card is present in the cart
        WebElement cartItem = driver.findElement(By.cssSelector(".product-name"));  // Example updated CSS selector
        String cartItemText = cartItem.getText();
        if (cartItemText.contains("Gift Card")) {
            System.out.println("Gift Card is present in the cart.");
        } else {
            System.out.println("Gift Card is not present in the cart.");
        }
    }

//    public static void searchGiftCard() {
//        // Search for "Gift Card"
//        WebElement searchBox = driver.findElement(By.id(PROP.getProperty("search_box_id")));  // Ensure this ID is correct
//
//        searchBox.sendKeys("Gift Card");
//        searchBox.submit();
//
//        // Verify search results contain "Gift Card"
//        WebElement searchResult = driver.findElement(By.xpath(PROP.getProperty("search_result_xpath")));
//        String resultText = searchResult.getText();
//        if (resultText.contains("Gift Card")) {
//            System.out.println("Search for 'Gift Card' was successful.");
//        } else {
//            System.out.println("Search did not return the expected Gift Card results.");
//        }
//    }

//    public static void logout() {
//        // Click the "Log out" link
//        WebElement logoutLink = driver.findElement(By.linkText("Log out"));
//        logoutLink.click();
//
//        // Verify "Log in" link is displayed
//        WebElement loginLink = driver.findElement(By.linkText("Log in"));
//        if (loginLink.isDisplayed()) {
//            System.out.println("Successfully logged out from the site.");
//        } else {
//            System.out.println("Logout failed. Log in link is not visible.");
//        }
//    }

    public static void quitBrowser() {
            driver.quit();
        
        System.out.println("Testing completed. Browser closed.");
    }
}
