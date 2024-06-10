
package apTests;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
//Selenium Imports
import java.util.logging.Level;


import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
///
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestCases {
    RemoteWebDriver driver;

    public TestCases() throws MalformedURLException {
        System.out.println("Constructor: TestCases");

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);


        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public static void logStatus(String type, String message, String status) {

        System.out.println(String.format("%s |  %s  |  %s | %s", String.valueOf(java.time.LocalDateTime.now()), type,
                message, status));
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01()  throws InterruptedException {
        // System.out.println("Start Test case: testCase01");
        logStatus("TestCase 01", "Start test case : Verify the URL of the homepage ", "DONE");

        boolean status;

        Home homePage = new Home(driver);
        status = homePage.amazonHomepage();
        if (status) {
            logStatus("TestCase 01", "Successfully Verify the URL of the homepage ", "PASS");
        } else {
            logStatus("TestCase 01 ", "Test Case Failure. Issues in URL of the homepage ", "FAIL");
        }
    }

    public void testCase02()  throws InterruptedException {
        logStatus("TestCase 02", "Start test case : Verify the search functionality ", "DONE");
        boolean status;
   
        String product = "laptop";

        Home homePage = new Home(driver);
        homePage.amazonHomepage();

        status = homePage.searchForProduct(product);
        if (!status) {
            logStatus("TestCase 02", "Test Case Failure. Unable to search for given product", "FAIL");
            // QkartSanity.takeScreenshot(driver, "Failure", "TestCase 3 Unable to search product");
        }

        Thread.sleep(5000);

        List<WebElement> searchResults = homePage.getSearchResults(product);

        if (searchResults.size() == 0) {
            logStatus("TestCase 02", "Test Case Failure. There were no results for the given search string", "FAIL");
            // QkartSanity.takeScreenshot(driver, "Failure", "TestCase 3 no results on given search string");
        } else {
            boolean isProductFound = false;
        
            for (WebElement webElement : searchResults) {
                // Create a SearchResult object from the parent element
                SearchResult resultElement = new SearchResult(webElement);
        
                // Verify that the result contains the searched text
                String elementText = resultElement.getTitleofResult();
                // System.out.println(elementText);
                if (elementText.toLowerCase().contains(product.toLowerCase())) {
                    logStatus("TestCase 02", "Successfully validated the search results ", "PASS");
                    isProductFound = true;
                    break;
                }
            }
        
            if (!isProductFound) {
                logStatus("TestCase 02", "Test Case Failure. No results contained the search string", "FAIL");
                // Optionally take a screenshot
                // QkartSanity.takeScreenshot(driver, "Failure", "TestCase 3 no matching product found in results");
            }
        }
    }

    public void testCase03()  throws InterruptedException {
        logStatus("TestCase 03", "Start test case : Verify the navigation menu ", "DONE");
        boolean status;
        String Categorie = "Electronics";

        Home homePage = new Home(driver);
        homePage.amazonHomepage();

        status = homePage.selectFromNavigationMenu(Categorie);
        if (!status) {
            logStatus("TestCase 03", "Test Case Failure. Unable to navigate for given Categorie", "FAIL");
            // QkartSanity.takeScreenshot(driver, "Failure", "TestCase 3 Unable to navigate Categorie");
        }

        Thread.sleep(5000);

        status = driver.getCurrentUrl().endsWith("nav_cs_electronics");  //.toLowerCase().contains("/"+Categorie);
        if (!status) {
            logStatus("TestCase 03", "Test Case Failure. Unable to navigate Categorie in URL", "FAIL");
            // QkartSanity.takeScreenshot(driver, "Failure", "TestCase 3 Unable to navigate Categorie");
        } else {
            logStatus("TestCase 03", "Successfully validated the Categorie in URL ", "PASS");
        }
    }    

}

