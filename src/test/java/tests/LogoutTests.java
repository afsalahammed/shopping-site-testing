package tests;

import base.BaseTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LogoutTests extends BaseTest {

    LoginPage login;

    @BeforeMethod
    public void loginToApp() {
        login = new LoginPage(driver);
        login.openLoginModal();
        login.enterUsername("validUser");
        login.enterPassword("Test1234!");
        login.clickLoginSubmit();
        waitForAlertAndAccept();
    }

    @Test
    public void testLogoutVisibleOnHome() {
        Assert.assertTrue(login.isLogoutVisible(), "Logout should be visible on Home page after login.");
    }

    @Test
    public void testLogoutFromHome() {
        login.logout();
        Assert.assertFalse(login.isLogoutVisible(), "Logout should not be visible after logout.");
        Assert.assertTrue(login.isLoginButtonVisible(), "Login should reappear after logout.");
    }

    @Test
    public void testLogoutFromCartPage() {
        driver.findElement(By.id("cartur")).click();  // Cart link
        Assert.assertTrue(login.isLogoutVisible(), "Logout should be visible on Cart page.");
        login.logout();
        Assert.assertTrue(login.isLoginButtonVisible(), "Login button should appear after logout.");
    }

    @Test
    public void testLogoutFromProductDetail() {
        driver.findElement(By.xpath("//a[contains(text(),'Samsung galaxy s6')]")).click();
        Assert.assertTrue(login.isLogoutVisible(), "Logout should be visible on Product Detail page.");
        login.logout();
        Assert.assertTrue(login.isLoginButtonVisible(), "Login button should appear after logout.");
    }

    @Test
    public void testWelcomeMessageClearsOnLogout() {
        Assert.assertTrue(login.isWelcomeMessageVisible(), "Welcome message should appear after login.");
        login.logout();
        Assert.assertFalse(login.isWelcomeMessageVisible(), "Welcome message should be gone after logout.");
    }

    @Test
    public void testLoginAndSignupReappear() {
        login.logout();
        Assert.assertTrue(login.isLoginButtonVisible(), "Login button should be visible after logout.");
        Assert.assertTrue(login.isSignUpButtonVisible(), "Sign Up button should be visible after logout.");
    }

    @Test
    public void testSessionEndsOnLogout() {
        login.logout();
        driver.navigate().refresh();
        Assert.assertTrue(login.isLoginButtonVisible(), "Should remain logged out after refresh.");
    }

    @Test
    public void testBackNavigationAfterLogout() {
        login.logout();
        driver.navigate().back();
        Assert.assertTrue(login.isLoginButtonVisible(), "Should not restore session on back navigation.");
    }

    @Test
    public void testLogoutThenRefresh() {
        login.logout();
        driver.navigate().refresh();
        Assert.assertFalse(login.isLogoutVisible(), "Logout should not be visible after refresh.");
    }

    @Test
    public void testLogoutNotVisibleBeforeLogin() {
        // Already logged out by default in fresh test
        Assert.assertFalse(login.isLogoutVisible(), "Logout should not be visible without login.");
    }

    // Utility
    private void waitForAlertAndAccept() {
        try {
            driver.switchTo().alert().accept();
        } catch (Exception ignored) {}
    }
}