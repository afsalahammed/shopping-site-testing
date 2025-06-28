package tests;

import base.BaseTest;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void testLoginModalOpens() {
        LoginPage login = new LoginPage(driver);
        login.openLoginModal();
        Assert.assertTrue(login.isLoginModalVisible(), "Login modal should be visible");
    }

    @Test
    public void testLoginModalCloses() {
        LoginPage login = new LoginPage(driver);
        login.openLoginModal();
        login.closeLoginModal();
        Assert.assertFalse(login.isLoginModalVisible(), "Login modal should be closed");
    }

    @Test
    public void testValidLogin() {
        LoginPage login = new LoginPage(driver);
        login.openLoginModal();
        login.enterUsername("validUser");
        login.enterPassword("Test1234!");
        login.clickLoginSubmit();
        waitForAlertAndAccept();
        Assert.assertTrue(login.getWelcomeMessage().contains("validUser"), "Welcome message should be shown");
        Assert.assertTrue(login.isLogoutVisible(), "Logout button should be visible");
    }

    @Test
    public void testInvalidUsername() {
        LoginPage login = new LoginPage(driver);
        login.openLoginModal();
        login.enterUsername("invalidUser");
        login.enterPassword("Test1234!");
        login.clickLoginSubmit();
        Assert.assertTrue(waitForAlertContains("User does not exist"), "Expected alert for wrong username");
    }

    @Test
    public void testInvalidPassword() {
        LoginPage login = new LoginPage(driver);
        login.openLoginModal();
        login.enterUsername("validUser");
        login.enterPassword("wrongPass");
        login.clickLoginSubmit();
        Assert.assertTrue(waitForAlertContains("Wrong password"), "Expected alert for wrong password");
    }

    @Test
    public void testEmptyUsername() {
        LoginPage login = new LoginPage(driver);
        login.openLoginModal();
        login.enterUsername("");
        login.enterPassword("pass123");
        login.clickLoginSubmit();
        Assert.assertTrue(waitForAlertPresent(), "Alert should appear for empty username");
    }

    @Test
    public void testEmptyPassword() {
        LoginPage login = new LoginPage(driver);
        login.openLoginModal();
        login.enterUsername("validUser");
        login.enterPassword("");
        login.clickLoginSubmit();
        Assert.assertTrue(waitForAlertPresent(), "Alert should appear for empty password");
    }

    @Test
    public void testBothFieldsEmpty() {
        LoginPage login = new LoginPage(driver);
        login.openLoginModal();
        login.enterUsername("");
        login.enterPassword("");
        login.clickLoginSubmit();
        Assert.assertTrue(waitForAlertPresent(), "Alert should appear for both fields empty");
    }

    @Test
    public void testLoginWithSpecialCharacters() {
        LoginPage login = new LoginPage(driver);
        login.openLoginModal();
        login.enterUsername("@#$%");
        login.enterPassword("@123!");
        login.clickLoginSubmit();
        Assert.assertTrue(waitForAlertPresent(), "Alert should appear for invalid input");
    }

    @Test
    public void testLoginWithLongInput() {
        LoginPage login = new LoginPage(driver);
        login.openLoginModal();
        login.enterUsername("userwithlonginputthatexceedsthenormallengthofusername1234567890");
        login.enterPassword("verylongpasswordvalueinputshouldnotcrash");
        login.clickLoginSubmit();
        Assert.assertTrue(waitForAlertPresent(), "App should handle long input gracefully");
    }

    @Test
    public void testLoginWithWhitespace() {
        LoginPage login = new LoginPage(driver);
        login.openLoginModal();
        login.enterUsername(" valid ");
        login.enterPassword(" pass ");
        login.clickLoginSubmit();
        Assert.assertTrue(waitForAlertPresent(), "App should handle or reject whitespaces");
    }

    @Test
    public void testLoginModalClosesOnSuccess() {
        LoginPage login = new LoginPage(driver);
        login.openLoginModal();
        login.enterUsername("validUser");
        login.enterPassword("Test1234!");
        login.clickLoginSubmit();
        waitForAlertAndAccept();
        Assert.assertFalse(login.isLoginModalVisible(), "Modal should close on successful login");
    }

    @Test
    public void testWelcomeMessageAfterLogin() {
        LoginPage login = new LoginPage(driver);
        login.openLoginModal();
        login.enterUsername("validUser");
        login.enterPassword("Test1234!");
        login.clickLoginSubmit();
        waitForAlertAndAccept();
        Assert.assertTrue(login.getWelcomeMessage().contains("validUser"));
    }

    @Test
    public void testLogoutVisibleAfterLogin() {
        LoginPage login = new LoginPage(driver);
        login.openLoginModal();
        login.enterUsername("validUser");
        login.enterPassword("Test1234!");
        login.clickLoginSubmit();
        waitForAlertAndAccept();
        Assert.assertTrue(login.isLogoutVisible());
    }

    @Test
    public void testFieldResetAfterModalClose() {
        LoginPage login = new LoginPage(driver);
        login.openLoginModal();
        login.enterUsername("temp");
        login.enterPassword("temp");
        login.closeLoginModal();
        login.openLoginModal();
        Assert.assertTrue(login.isUsernameFieldEmpty() && login.isPasswordFieldEmpty());
    }

    @Test
    public void testLogoutClearsSession() {
        LoginPage login = new LoginPage(driver);
        login.openLoginModal();
        login.enterUsername("validUser");
        login.enterPassword("Test1234!");
        login.clickLoginSubmit();
        waitForAlertAndAccept();
        login.logout();
        Assert.assertFalse(login.isLogoutVisible(), "Logout button should disappear");
    }

    // Utility
    private boolean waitForAlertContains(String expected) {
        try {
            Alert alert = driver.switchTo().alert();
            String text = alert.getText();
            alert.accept();
            return text.contains(expected);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean waitForAlertPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void waitForAlertAndAccept() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception ignored) {}
    }
}
