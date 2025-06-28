package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By loginButtonNav = By.id("login2");
    private By loginModal = By.id("logInModal");
    private By usernameField = By.id("loginusername");
    private By passwordField = By.id("loginpassword");
    private By loginSubmitButton = By.xpath("//button[text()='Log in']");
    private By closeModalButton = By.xpath("//div[@id='logInModal']//button[text()='Close']");
    private By logoutButton = By.id("logout2");
    private By welcomeMessage = By.id("nameofuser");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginModal() {
        driver.findElement(loginButtonNav).click();
    }

    public boolean isLoginModalVisible() {
        return driver.findElement(loginModal).isDisplayed();
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginSubmit() {
        driver.findElement(loginSubmitButton).click();
    }

    public void closeLoginModal() {
        driver.findElement(closeModalButton).click();
    }

    public String getWelcomeMessage() {
        return driver.findElement(welcomeMessage).getText();
    }

    public boolean isLogoutVisible() {
        return driver.findElement(logoutButton).isDisplayed();
    }

    public void logout() {
        driver.findElement(logoutButton).click();
    }

    public boolean isUsernameFieldEmpty() {
        return driver.findElement(usernameField).getAttribute("value").isEmpty();
    }

    public boolean isPasswordFieldEmpty() {
        return driver.findElement(passwordField).getAttribute("value").isEmpty();
    }
    
 // Assuming in LoginPage.java or NavbarPage.java
    public boolean isLoginButtonVisible() {
        return driver.findElement(By.id("login2")).isDisplayed();
    }

    public boolean isSignUpButtonVisible() {
        return driver.findElement(By.id("signin2")).isDisplayed();
    }

    public boolean isWelcomeMessageVisible() {
        return driver.findElements(By.id("nameofuser")).size() > 0;
    }
}
