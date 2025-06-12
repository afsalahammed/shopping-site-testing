package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageTests extends BaseTest {

    @Test
    public void testLogoIsDisplayed() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isLogoDisplayed(), "Logo should be displayed on Home Page");
    }

    @Test
    public void testClickLogoRedirectsToHome() {
        HomePage homePage = new HomePage(driver);
        homePage.clickNext(); // Navigate to another set of products
        homePage.clickLogo(); // Click logo to go back
        Assert.assertTrue(driver.getCurrentUrl().contains("demoblaze.com"), "Should be on home page");
    }

    @Test
    public void testProductGridCount() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getProductCount() > 0, "Product grid should contain products");
    }

    @Test
    public void testPaginationNextButton() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isNextButtonDisplayed(), "Next button should be displayed");
        homePage.clickNext(); // Click Next
    }

    @Test
    public void testCategoryFilterPhones() {
        HomePage homePage = new HomePage(driver);
        homePage.selectCategory("Phones");
        Assert.assertTrue(homePage.getProductCount() > 0, "Phone category should display products");
    }

    @Test
    public void testFooterIsVisible() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isFooterVisible(), "Footer should be visible");
    }
}