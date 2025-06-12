package tests;

import pages.NavigationBar;
import base.BaseTest;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationBarTests extends BaseTest {

    @Test(priority = 1)
    public void testLogoRedirectsToHomePage() {
        NavigationBar nav = new NavigationBar(driver);
        nav.clickLogo();
        Assert.assertTrue(driver.getCurrentUrl().contains("index.html") || driver.getCurrentUrl().equals(baseUrl));
    }

    @Test(priority = 2)
    public void testContactModalOpens() {
        NavigationBar nav = new NavigationBar(driver);
        nav.clickContact();
        Assert.assertTrue(driver.getPageSource().contains("New message")); // Rough validation
    }

    @Test(priority = 3)
    public void testAboutUsModalOpens() {
        NavigationBar nav = new NavigationBar(driver);
        nav.clickAboutUs();
        Assert.assertTrue(driver.getPageSource().contains("About us")); // Modal check
    }

    @Test(priority = 4)
    public void testCartNavigation() {
        NavigationBar nav = new NavigationBar(driver);
        nav.clickCart();
        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"));
    }

    @Test(priority = 5)
    public void testLoginModalOpens() {
        NavigationBar nav = new NavigationBar(driver);
        nav.clickLogin();
        Assert.assertTrue(driver.getPageSource().contains("Log in")); // Modal content check
    }

    @Test(priority = 6)
    public void testSignUpModalOpens() {
        NavigationBar nav = new NavigationBar(driver);
        nav.clickSignUp();
        Assert.assertTrue(driver.getPageSource().contains("Sign up")); // Modal content check
    }
}
