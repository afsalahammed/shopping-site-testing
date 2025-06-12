package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationBar {
    WebDriver driver;

    // Constructor
    public NavigationBar(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By logo = By.id("nava");
    By homeLink = By.xpath("//a[text()='Home ']");
    By contactLink = By.xpath("//a[text()='Contact']");
    By aboutUsLink = By.xpath("//a[text()='About us']");
    By cartLink = By.id("cartur");
    By loginLink = By.id("login2");
    By signUpLink = By.id("signin2");
    By logoutLink = By.id("logout2");
    By welcomeUser = By.id("nameofuser");

    // Actions
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    public void clickHome() {
        driver.findElement(homeLink).click();
    }

    public void clickContact() {
        driver.findElement(contactLink).click();
    }

    public void clickAboutUs() {
        driver.findElement(aboutUsLink).click();
    }

    public void clickCart() {
        driver.findElement(cartLink).click();
    }

    public void clickLogin() {
        driver.findElement(loginLink).click();
    }

    public void clickSignUp() {
        driver.findElement(signUpLink).click();
    }

    public void clickLogout() {
        driver.findElement(logoutLink).click();
    }

    public boolean isLogoutVisible() {
        return driver.findElement(logoutLink).isDisplayed();
    }

    public boolean isWelcomeMessageDisplayed() {
        return driver.findElement(welcomeUser).isDisplayed();
    }

    public String getWelcomeMessage() {
        return driver.findElement(welcomeUser).getText();
    }
}
