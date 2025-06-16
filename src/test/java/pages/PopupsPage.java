package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PopupsPage {
    WebDriver driver;

    public PopupsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Modal triggers
    By signUpBtn = By.id("signin2");
    By loginBtn = By.id("login2");
    By contactLink = By.xpath("//a[text()='Contact']");
    By aboutUsLink = By.xpath("//a[text()='About us']");
    By cartLink = By.id("cartur");
    By placeOrderBtn = By.xpath("//button[text()='Place Order']");

    // Modal containers (by ID)
    By signUpModal = By.id("signInModal");
    By loginModal = By.id("logInModal");
    By contactModal = By.id("exampleModal");
    By aboutUsModal = By.id("videoModal");
    By placeOrderModal = By.id("orderModal");

    // Modal close buttons
    By closeBtn = By.xpath("//div[@class='modal-content']//button[@class='close']");

    public void clickSignUp() {
        driver.findElement(signUpBtn).click();
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }

    public void clickContact() {
        driver.findElement(contactLink).click();
    }

    public void clickAboutUs() {
        driver.findElement(aboutUsLink).click();
    }

    public void goToCart() {
        driver.findElement(cartLink).click();
    }

    public void clickPlaceOrder() {
        driver.findElement(placeOrderBtn).click();
    }

    public boolean isModalDisplayed(By modalLocator) {
        return driver.findElement(modalLocator).isDisplayed();
    }

    public void closeModal() {
        driver.findElement(closeBtn).click();
    }

    // Modal Locators for assertions
    public By getSignUpModal() { return signUpModal; }
    public By getLoginModal() { return loginModal; }
    public By getContactModal() { return contactModal; }
    public By getAboutUsModal() { return aboutUsModal; }
    public By getPlaceOrderModal() { return placeOrderModal; }
}
