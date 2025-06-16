package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage {
    private WebDriver driver;

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    private By productImage = By.cssSelector(".item img");
    private By productTitle = By.cssSelector(".name");
    private By productPrice = By.cssSelector(".price-container");
    private By productDescription = By.cssSelector("#more-information .description");
    private By addToCartButton = By.cssSelector(".btn.btn-success.btn-lg");

    public boolean isImageDisplayed() {
        return driver.findElement(productImage).isDisplayed();
    }

    public boolean isTitleDisplayed() {
        return driver.findElement(productTitle).isDisplayed();
    }

    public boolean isPriceDisplayed() {
        return driver.findElement(productPrice).isDisplayed();
    }

    public boolean isDescriptionDisplayed() {
        return driver.findElement(productDescription).isDisplayed();
    }

    public boolean isAddToCartButtonDisplayed() {
        return driver.findElement(addToCartButton).isDisplayed();
    }

    public void clickAddToCart() {
        driver.findElement(addToCartButton).click();
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }
}