package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignupPage {
    private WebDriver driver;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    private By signupLink = By.id("signin2");
    private By signupModal = By.id("signInModal");
    private By usernameField = By.id("sign-username");
    private By passwordField = By.id("sign-password");
    private By signupButton = By.xpath("//button[text()='Sign up']");
    private By closeButton = By.xpath("//div[@id='signInModal']//button[@class='btn btn-secondary']");

    public void openSignupModal() {
        driver.findElement(signupLink).click();
    }

    public boolean isSignupModalVisible() {
        WebElement modal = driver.findElement(signupModal);
        return modal.isDisplayed();
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignupButton() {
        driver.findElement(signupButton).click();
    }

    public void closeModal() {
        driver.findElement(closeButton).click();
    }
}
