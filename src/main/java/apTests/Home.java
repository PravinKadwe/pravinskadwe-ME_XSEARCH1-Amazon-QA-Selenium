package apTests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
// import java.util.Set;
import org.openqa.selenium.By;
// import org.openqa.selenium.JavascriptExecutor;
// import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
// import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.*;


public class Home {

    RemoteWebDriver driver;
    String url = "https://www.amazon.in/";

    public Home(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public Boolean amazonHomepage() {
        this.driver.get(this.url);

        if (this.driver.getCurrentUrl().equals(this.url)) {
            // System.out.println("The URL is Contains expected title amazon.");
            return true;
        }
        return false;
    }

    public Boolean searchForProduct(String product) {
        try {


            WebElement SearchBox = driver.findElement(By.id("twotabsearchtextbox"));
            SearchBox.sendKeys(product);
    
            // System.out.println("Searching for "+product);
    
            WebElement SearchButton = driver.findElement(By.id("nav-search-submit-button"));
            SearchButton.click();

            return true;
        } catch (Exception e) {
            System.out.println("Error while searching for a product: " + e.getMessage());
            return false;
        }
    }

    public List<WebElement> getSearchResults(String product) {
        List<WebElement> searchResults = new ArrayList<WebElement>() {};
        try {
            
            // searchResults = driver.findElements(By.xpath("//div[contains(@class,'css-sycj1h')]"));

            searchResults = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));

            return searchResults;
        } catch (Exception e) {
            System.out.println("There were no search results: " + e.getMessage());
            return searchResults;

        }
    }
    
    public Boolean selectFromNavigationMenu(String Categorie){

    try {

            WebElement SelectfromNavigation = driver.findElement(By.xpath("//a[contains(text(),'"+Categorie+"')]"));

            // if(SelectfromNavigation.isDisplayed()){
            SelectfromNavigation.click();
                return true;
            // }

            // return false;
        } catch (Exception e) {
            System.out.println("There were no search results: " + e.getMessage());
            return false;
        }

    }

}
