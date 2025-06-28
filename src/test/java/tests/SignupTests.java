package tests;

import base.BaseTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignupPage;

public class SignupTests extends BaseTest {

    @Test
    public void testSignupModalOpens() {
        SignupPage signup = new SignupPage(driver);
        signup.openSignupModal();
        Assert.assertTrue(signup.isSignupModalVisible(), "Signup modal should be visible");
    }

    @Test
    public void testSignupModalCloses() {
        SignupPage signup = new SignupPage(driver);
        signup.openSignupModal();
        signup.closeModal();
        // Wait or assert invisibility logic can be added
    }

    @Test
    public void testSuccessfulSignup() {
        SignupPage signup = new SignupPage(driver);
        signup.openSignupModal();
        String uniqueUser = "user" + System.currentTimeMillis();
        signup.enterUsername(uniqueUser);
        signup.enterPassword("Test1234!");
        signup.clickSignupButton();
        Assert.assertTrue(alertTextContains("Sign up successful"), "Expected success alert");
    }

    @Test
    public void testSignupExistingUser() {
        SignupPage signup = new SignupPage(driver);
        signup.openSignupModal();
        signup.enterUsername("existingUser"); // Use a known existing username
        signup.enterPassword("anyPass123");
        signup.clickSignupButton();
        Assert.assertTrue(alertTextContains("This user already exist"), "Expected existing user alert");
    }

    @Test
    public void testSignupEmptyUsername() {
        SignupPage signup = new SignupPage(driver);
        signup.openSignupModal();
        signup.enterUsername("");
        signup.enterPassword("somePassword");
        signup.clickSignupButton();
        Assert.assertTrue(alertTextPresent(), "Expected alert for empty username");
    }

    @Test
    public void testSignupEmptyPassword() {
        SignupPage signup = new SignupPage(driver);
        signup.openSignupModal();
        signup.enterUsername("newuser");
        signup.enterPassword("");
        signup.clickSignupButton();
        Assert.assertTrue(alertTextPresent(), "Expected alert for empty password");
    }
    
    @Test
    public void testSignupEmptyFields() {
        SignupPage signup = new SignupPage(driver);
        signup.openSignupModal();
        signup.enterUsername("");
        signup.enterPassword("");
        signup.clickSignupButton();
        Assert.assertTrue(alertTextPresent(), "Expected alert for both fields empty");
    }
    
    @Test
    public void testSignupWithSpecialCharacters() {
        SignupPage signup = new SignupPage(driver);
        signup.openSignupModal();
        signup.enterUsername("@f$al!");
        signup.enterPassword("Pass@123");
        signup.clickSignupButton();
        Assert.assertTrue(alertTextPresent(), "Expected alert or response for special characters in username");
    }
    
    @Test
    public void testSignupWithLongInput() {
        SignupPage signup = new SignupPage(driver);
        signup.openSignupModal();
        String longUsername = "userwithaverylongnamethatisunusuallylengthy" + System.currentTimeMillis();
        String longPassword = "P@sswordWithExtraLongCharactersToTestLengthHandling";
        signup.enterUsername(longUsername);
        signup.enterPassword(longPassword);
        signup.clickSignupButton();
        Assert.assertTrue(alertTextPresent(), "Expected system to handle long inputs gracefully");
    }
    
    @Test
    public void testSignupWithWhitespace() {
        SignupPage signup = new SignupPage(driver);
        signup.openSignupModal();
        signup.enterUsername(" afsal ");
        signup.enterPassword(" pass word ");
        signup.clickSignupButton();
        Assert.assertTrue(alertTextPresent(), "Expected response for whitespace in username/password");
    }
    
    @Test
    public void testFieldsResetAfterModalClose() {
        SignupPage signup = new SignupPage(driver);
        signup.openSignupModal();
        signup.enterUsername("tempuser");
        signup.enterPassword("tempPass");
        signup.closeModal();

        // Reopen modal and check fields are blank
        signup.openSignupModal();
        String username = driver.findElement(By.id("sign-username")).getAttribute("value");
        String password = driver.findElement(By.id("sign-password")).getAttribute("value");

        Assert.assertEquals(username, "", "Username field should be blank");
        Assert.assertEquals(password, "", "Password field should be blank");
    }

    // Helper method to verify alerts
    private boolean alertTextContains(String expected) {
        try {
            String alertText = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();
            return alertText.contains(expected);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean alertTextPresent() {
        try {
            String alertText = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();
            return alertText != null && !alertText.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
