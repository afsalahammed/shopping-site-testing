package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductCategoryPage {
    private WebDriver driver;

    private By phonesCategory = By.linkText("Phones");
    private By laptopsCategory = By.linkText("Laptops");
    private By monitorsCategory = By.linkText("Monitors");
    private By productTitle = By.cssSelector(".card-title");

    public ProductCategoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickPhones() {
        driver.findElement(phonesCategory).click();
    }

    public void clickLaptops() {
        driver.findElement(laptopsCategory).click();
    }

    public void clickMonitors() {
        driver.findElement(monitorsCategory).click();
    }

    public boolean areProductsLoaded() {
        return driver.findElements(productTitle).size() > 0;
    }

    public int getVisibleProductCount() {
        return driver.findElements(productTitle).size();
    }

    public String getFirstProductTitle() {
        return driver.findElements(productTitle).get(0).getText();
    }
}
