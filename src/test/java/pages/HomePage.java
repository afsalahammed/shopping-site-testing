package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    private WebDriver driver;

    // Locators
    private By logo = By.id("nava");
    private By productCards = By.cssSelector(".card.h-100");
    private By nextButton = By.id("next2");
    private By previousButton = By.id("prev2");
    private By phonesCategory = By.linkText("Phones");
    private By laptopsCategory = By.linkText("Laptops");
    private By monitorsCategory = By.linkText("Monitors");
    private By carousel = By.id("carouselExampleIndicators");
    private By footer = By.cssSelector("footer");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public boolean isLogoDisplayed() {
        return driver.findElement(logo).isDisplayed();
    }

    public void clickLogo() {
        driver.findElement(logo).click();
    }

    public int getProductCount() {
        return driver.findElements(productCards).size();
    }

    public boolean isNextButtonDisplayed() {
        return driver.findElement(nextButton).isDisplayed();
    }

    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    public void clickPrevious() {
        driver.findElement(previousButton).click();
    }

    public void selectCategory(String category) {
        switch (category.toLowerCase()) {
            case "phones":
                driver.findElement(phonesCategory).click();
                break;
            case "laptops":
                driver.findElement(laptopsCategory).click();
                break;
            case "monitors":
                driver.findElement(monitorsCategory).click();
                break;
        }
    }
    
    public void clickFirstProduct() {
        driver.findElement(By.cssSelector(".card-title a")).click();
    }
    
    public void addFirstProductToCart() {
        driver.findElement(By.cssSelector(".card-title a")).click();
        driver.findElement(By.linkText("Add to cart")).click();
        driver.switchTo().alert().accept();
        driver.navigate().back(); // Go back to Home
    }


    public boolean isFooterVisible() {
        return driver.findElement(footer).isDisplayed();
    }

    public List<WebElement> getAllProductCards() {
        return driver.findElements(productCards);
    }
}