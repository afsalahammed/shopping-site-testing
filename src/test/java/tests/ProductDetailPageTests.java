package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductDetailPage;

public class ProductDetailPageTests extends BaseTest {

    ProductDetailPage productDetailPage;

    @BeforeMethod
    public void goToProductDetail() {
        driver.findElement(By.cssSelector(".card-title a")).click(); // Click first product
        productDetailPage = new ProductDetailPage(driver);
    }

    @Test
    public void test01_productImageIsDisplayed() {
        Assert.assertTrue(productDetailPage.isImageDisplayed(), "Product image is not visible.");
    }

    @Test
    public void test02_productTitleIsDisplayed() {
        Assert.assertTrue(productDetailPage.isTitleDisplayed(), "Product title is not visible.");
    }

    @Test
    public void test03_productPriceIsDisplayed() {
        Assert.assertTrue(productDetailPage.isPriceDisplayed(), "Product price is not visible.");
    }

    @Test
    public void test04_productDescriptionIsDisplayed() {
        Assert.assertTrue(productDetailPage.isDescriptionDisplayed(), "Product description is not visible.");
    }

    @Test
    public void test05_addToCartButtonIsDisplayed() {
        Assert.assertTrue(productDetailPage.isAddToCartButtonDisplayed(), "Add to Cart button is not visible.");
    }

    @Test
    public void test06_clickAddToCartTriggersAlert() throws InterruptedException {
        productDetailPage.clickAddToCart();
        Thread.sleep(2000);
        Assert.assertTrue(productDetailPage.getAlertText().contains("Product added"));
        productDetailPage.acceptAlert();
    }

    @Test
    public void test07_alertTextIsCorrect() throws InterruptedException {
        productDetailPage.clickAddToCart();
        Thread.sleep(2000);
        String alert = productDetailPage.getAlertText();
        Assert.assertEquals(alert, "Product added", "Alert message not matching");
        productDetailPage.acceptAlert();
    }

    @Test
    public void test08_productAddedAppearsInCart() throws InterruptedException {
        productDetailPage.clickAddToCart();
        Thread.sleep(2000);
        productDetailPage.acceptAlert();
        driver.findElement(By.id("cartur")).click(); // Navigate to Cart
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isProductPresentInCart(), "Product not found in cart");
    }

    @Test
    public void test09_duplicateProductAdded() throws InterruptedException {
        productDetailPage.clickAddToCart();
        Thread.sleep(1500);
        productDetailPage.acceptAlert();
        productDetailPage.clickAddToCart();
        Thread.sleep(1500);
        productDetailPage.acceptAlert();
        driver.findElement(By.id("cartur")).click();
        int itemCount = driver.findElements(By.cssSelector("tr.success")).size();
        Assert.assertTrue(itemCount >= 2, "Duplicate product not added properly.");
    }

    @Test
    public void test10_navigateBackToHomePage() {
        driver.findElement(By.cssSelector("a.navbar-brand")).click(); // Click logo
        Assert.assertTrue(driver.getCurrentUrl().contains("demoblaze.com"), "Did not navigate to Home page.");
    }

    @Test
    public void test11_backToCategoryShowsFilteredProducts() throws InterruptedException {
        driver.navigate().back(); // Assuming we clicked product from category
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElements(By.cssSelector(".card")).size() > 0, "No products shown after navigating back.");
    }
}