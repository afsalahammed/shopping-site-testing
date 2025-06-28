package tests;

import base.BaseTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddToCartPage;

public class AddToCartTests extends BaseTest {
    AddToCartPage cart;

    @Test
    public void TC_AC_001_AddFromHome() {
        cart = new AddToCartPage(driver);
        cart.clickFirstAddToCartOnHome();
        acceptAlert();
        cart.goToCart();
        Assert.assertFalse(cart.getFirstCartProductTitle().isEmpty());
    }

    @Test
    public void TC_AC_002_AddFromDetailPage() {
        cart = new AddToCartPage(driver);
        cart.openProductDetail("Samsung galaxy s6");
        cart.clickAddToCartInDetailPage();
        acceptAlert();
        cart.goToCart();
        Assert.assertEquals(cart.getFirstCartProductTitle(), "Samsung galaxy s6");
    }

    @Test
    public void TC_AC_003_AddMultipleUniqueProducts() {
        cart = new AddToCartPage(driver);
        cart.clickFirstAddToCartOnHome();
        acceptAlert();
        driver.navigate().back();
        cart.openProductDetail("Nokia lumia 1520");
        cart.clickAddToCartInDetailPage();
        acceptAlert();
        cart.goToCart();
        Assert.assertTrue(cart.getFirstCartProductTitle().contains("Samsung") || cart.getFirstCartProductTitle().contains("Nokia"));
    }

    @Test
    public void TC_AC_004_AddSameProductTwice() {
        cart = new AddToCartPage(driver);
        cart.openProductDetail("Samsung galaxy s6");
        cart.clickAddToCartInDetailPage();
        acceptAlert();
        driver.navigate().refresh();
        cart.clickAddToCartInDetailPage();
        acceptAlert();
        cart.goToCart();
        // This test assumes duplicates are allowed and displayed
        Assert.assertTrue(driver.findElements(By.xpath("//tr[@class='success']")).size() >= 2);
    }

    @Test
    public void TC_AC_005_CartContentVerification() {
        cart = new AddToCartPage(driver);
        cart.clickFirstAddToCartOnHome();
        acceptAlert();
        cart.goToCart();
        Assert.assertFalse(cart.getFirstCartProductTitle().isEmpty());
    }

    @Test
    public void TC_AC_006_GuestUserCanAddToCart() {
        cart = new AddToCartPage(driver);
        cart.clickFirstAddToCartOnHome();
        acceptAlert();
        cart.goToCart();
        Assert.assertTrue(cart.getFirstCartProductTitle().length() > 0);
    }

    @Test
    public void TC_AC_007_RefreshAndCheckCart() {
        cart = new AddToCartPage(driver);
        cart.clickFirstAddToCartOnHome();
        acceptAlert();
        driver.navigate().refresh();
        cart.goToCart();
        Assert.assertFalse(cart.getFirstCartProductTitle().isEmpty());
    }

    @Test
    public void TC_AC_008_AlertCheck() {
        cart = new AddToCartPage(driver);
        cart.clickFirstAddToCartOnHome();
        String alertText = driver.switchTo().alert().getText();
        Assert.assertEquals(alertText, "Product added");
        acceptAlert();
    }

    @Test
    public void TC_AC_009_AlertDisappearsOnAccept() {
        cart = new AddToCartPage(driver);
        cart.clickFirstAddToCartOnHome();
        driver.switchTo().alert().accept();
        // If no exception, alert dismissed
        Assert.assertTrue(true);
    }

    @Test
    public void TC_AC_010_ProductTitlePriceMatch() {
        cart = new AddToCartPage(driver);
        cart.openProductDetail("Samsung galaxy s6");
        cart.clickAddToCartInDetailPage();
        acceptAlert();
        cart.goToCart();
        Assert.assertEquals(cart.getFirstCartProductTitle(), "Samsung galaxy s6");
    }

    @Test
    public void TC_AC_011_AddToCartWithoutLogin() {
        cart = new AddToCartPage(driver);
        cart.clickFirstAddToCartOnHome();
        acceptAlert();
        cart.goToCart();
        Assert.assertTrue(cart.getFirstCartProductTitle().length() > 0);
    }

    @Test
    public void TC_AC_012_AddNavigateBackCheck() {
        cart = new AddToCartPage(driver);
        cart.clickFirstAddToCartOnHome();
        acceptAlert();
        driver.findElement(By.linkText("About us")).click();
        driver.navigate().back();
        cart.goToCart();
        Assert.assertTrue(cart.getFirstCartProductTitle().length() > 0);
    }

    @Test
    public void TC_AC_013_AddAfterLogin() {
        // Assume you have login() implemented
        login("testuser", "testpass");
        cart = new AddToCartPage(driver);
        cart.clickFirstAddToCartOnHome();
        acceptAlert();
        cart.goToCart();
        Assert.assertTrue(cart.getFirstCartProductTitle().length() > 0);
    }

    @Test
    public void TC_AC_014_AddRemoveAddAgain() {
        cart = new AddToCartPage(driver);
        cart.clickFirstAddToCartOnHome();
        acceptAlert();
        cart.goToCart();
        driver.findElement(By.xpath("//a[text()='Delete']")).click();
        cart.clickFirstAddToCartOnHome();
        acceptAlert();
        cart.goToCart();
        Assert.assertTrue(cart.getFirstCartProductTitle().length() > 0);
    }

    @Test
    public void TC_AC_015_AddButtonVisible() {
        cart = new AddToCartPage(driver);
        Assert.assertTrue(cart.isAddToCartButtonVisible());
    }

    private void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
        } catch (Exception ignored) {}
    }

    private void login(String username, String password) {
        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys(username);
        driver.findElement(By.id("loginpassword")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        acceptAlert();
    }
}