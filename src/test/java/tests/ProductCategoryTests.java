package tests;

import base.BaseTest;
import pages.ProductCategoryPage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductCategoryTests extends BaseTest {
    private ProductCategoryPage categoryPage;

    @BeforeMethod
    public void initPage() {
        categoryPage = new ProductCategoryPage(driver);
    }

    @Test
    public void testPhonesCategoryFilter() {
        categoryPage.clickPhones();
        Assert.assertTrue(categoryPage.areProductsLoaded(), "Phones not loaded.");
    }

    @Test
    public void testLaptopsCategoryFilter() {
        categoryPage.clickLaptops();
        Assert.assertTrue(categoryPage.areProductsLoaded(), "Laptops not loaded.");
    }

    @Test
    public void testMonitorsCategoryFilter() {
        categoryPage.clickMonitors();
        Assert.assertTrue(categoryPage.areProductsLoaded(), "Monitors not loaded.");
    }

    @Test
    public void testRapidCategorySwitching() throws InterruptedException {
        categoryPage.clickPhones();
        Thread.sleep(1000);
        categoryPage.clickLaptops();
        Thread.sleep(1000);
        categoryPage.clickMonitors();
        Assert.assertTrue(categoryPage.areProductsLoaded(), "Products not loaded after rapid switching.");
    }

    @Test
    public void testCategoryFilterAfterNavigatingAway() {
        driver.findElement(By.id("cartur")).click();  // Navigate to Cart
        driver.findElement(By.id("nava")).click();    // Back to Home
        categoryPage.clickLaptops();
        Assert.assertTrue(categoryPage.areProductsLoaded(), "Laptops not loaded after navigating away.");
    }
}
