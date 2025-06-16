package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CartPageTests extends BaseTest {

    @BeforeMethod
    public void navigateToCart() throws InterruptedException {
        //super.setup();
        driver.findElement(By.linkText("Cart")).click();
        Thread.sleep(2000);
    }

    @Test
    public void verifyCartPageLoadsSuccessfully() {
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("STORE"));
    }

    @Test
    public void verifyProductDetailsDisplayedInCart() throws InterruptedException {
        addProductToCart();
        navigateToCart();
        List<WebElement> rows = driver.findElements(By.cssSelector("#tbodyid > tr"));
        Assert.assertTrue(rows.size() > 0);
        WebElement firstRow = rows.get(0);
        Assert.assertTrue(firstRow.getText().contains("Samsung"));
    }

    @Test
    public void verifyDeleteButtonFunctionality() throws InterruptedException {
        addProductToCart();
        navigateToCart();
        WebElement deleteBtn = driver.findElement(By.xpath("//a[text()='Delete']"));
        deleteBtn.click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElements(By.cssSelector("#tbodyid > tr")).size(), 0);
    }

    @Test
    public void verifyPlaceOrderButtonVisible() {
        WebElement placeOrderBtn = driver.findElement(By.xpath("//button[text()='Place Order']"));
        Assert.assertTrue(placeOrderBtn.isDisplayed());
    }

    @Test
    public void verifyTotalPriceCalculation() throws InterruptedException {
        addProductToCart();
        navigateToCart();
        Thread.sleep(2000);
        WebElement priceElement = driver.findElement(By.xpath("//tbody/tr/td[3]"));
        int productPrice = Integer.parseInt(priceElement.getText());
        WebElement totalElement = driver.findElement(By.id("totalp"));
        int total = Integer.parseInt(totalElement.getText());
        Assert.assertEquals(total, productPrice);
    }

    @Test
    public void verifyTotalUpdatesAfterRemovingItem() throws InterruptedException {
        addProductToCart();
        navigateToCart();
        int beforeTotal = Integer.parseInt(driver.findElement(By.id("totalp")).getText());
        driver.findElement(By.xpath("//a[text()='Delete']")).click();
        Thread.sleep(2000);
        int afterTotal = Integer.parseInt(driver.findElement(By.id("totalp")).getText());
        Assert.assertNotEquals(beforeTotal, afterTotal);
    }

    @Test
    public void verifyPlaceOrderModalOpens() {
        driver.findElement(By.xpath("//button[text()='Place Order']")).click();
        WebElement modalTitle = driver.findElement(By.id("orderModalLabel"));
        Assert.assertTrue(modalTitle.isDisplayed());
    }

    @Test
    public void verifyEmptyCartDisplaysNoItems() {
        List<WebElement> rows = driver.findElements(By.cssSelector("#tbodyid > tr"));
        Assert.assertEquals(rows.size(), 0);
    }

    @Test
    public void verifyProductImageDisplayed() throws InterruptedException {
        addProductToCart();
        navigateToCart();
        WebElement img = driver.findElement(By.cssSelector("#tbodyid > tr > td:nth-child(1) img"));
        Assert.assertTrue(img.isDisplayed());
    }

    @Test
    public void verifyDuplicateProductHandling() throws InterruptedException {
        addProductToCart();
        addProductToCart();
        navigateToCart();
        List<WebElement> rows = driver.findElements(By.cssSelector("#tbodyid > tr"));
        Assert.assertTrue(rows.size() >= 2);
    }

    @Test
    public void addProductToCart() throws InterruptedException {
        driver.findElement(By.linkText("Home"))   // Go to Home in case Cart loaded
              .click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Samsung galaxy s6")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Add to cart")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
    }
}
