package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddToCartPage {
    WebDriver driver;

    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFirstAddToCartOnHome() {
        driver.findElement(By.xpath("(//a[text()='Add to cart'])[1]")).click();
    }

    public void openProductDetail(String productName) {
        driver.findElement(By.linkText(productName)).click();
    }

    public void clickAddToCartInDetailPage() {
        driver.findElement(By.xpath("//a[text()='Add to cart']")).click();
    }

    public void goToCart() {
        driver.findElement(By.id("cartur")).click();
    }

    public String getFirstCartProductTitle() {
        return driver.findElement(By.xpath("//tr[@class='success']//td[2]")).getText();
    }

    public String getFirstCartProductPrice() {
        return driver.findElement(By.xpath("//tr[@class='success']//td[3]")).getText();
    }

    public String getCartTotal() {
        return driver.findElement(By.id("totalp")).getText();
    }

    public boolean isAddToCartButtonVisible() {
        return driver.findElement(By.xpath("(//a[text()='Add to cart'])[1]")).isDisplayed();
    }
}