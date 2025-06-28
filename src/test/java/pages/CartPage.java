package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private By productRows = By.cssSelector("tr.success");
    private By productTitle = By.cssSelector("tr.success td:nth-child(2)");
    private By productPrice = By.cssSelector("tr.success td:nth-child(3)");
    private By deleteButtons = By.linkText("Delete");
    private By totalPrice = By.id("totalp");
    private By placeOrderBtn = By.xpath("//button[text()='Place Order']");
    private By cartLink = By.id("cartur");
    private By productRow = By.xpath("//tr[@class='success']");
    private By deleteButton = By.xpath("//tr[@class='success']//a[text()='Delete']");


    public List<WebElement> getProductRows() {
        return driver.findElements(productRows);
    }

    public List<WebElement> getProductTitles() {
        return driver.findElements(productTitle);
    }

    public List<WebElement> getProductPrices() {
        return driver.findElements(productPrice);
    }

    public List<WebElement> getDeleteButtons() {
        return driver.findElements(deleteButtons);
    }

    public String getTotalPrice() {
        return driver.findElement(totalPrice).getText();
    }

    public boolean isPlaceOrderButtonVisible() {
        return driver.findElement(placeOrderBtn).isDisplayed();
    }

    public void clickDeleteFirstItem() {
        driver.findElements(deleteButtons).get(0).click();
    }

    public void clickPlaceOrder() {
        driver.findElement(placeOrderBtn).click();
    }

    public boolean isProductPresentInCart() {
        return driver.findElements(productRows).size() > 0;
    }
    
    public void goToCart() {
        driver.findElement(cartLink).click();
    }

    public int getProductCount() {
        return driver.findElements(productRow).size();
    }

    public String getFirstProductTitle() {
        return driver.findElement(By.xpath("//tr[@class='success']//td[2]")).getText();
    }

    public String getFirstProductPrice() {
        return driver.findElement(By.xpath("//tr[@class='success']//td[3]")).getText();
    }

    public void deleteFirstProduct() {
        driver.findElement(deleteButton).click();
    }

    public boolean isDeleteButtonVisible() {
        return driver.findElement(deleteButton).isDisplayed();
    }
}