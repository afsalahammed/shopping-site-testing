package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PlaceOrderPage {
    WebDriver driver;

    public PlaceOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    private By placeOrderBtn = By.xpath("//button[text()='Place Order']");
    private By nameField = By.id("name");
    private By countryField = By.id("country");
    private By cityField = By.id("city");
    private By cardField = By.id("card");
    private By monthField = By.id("month");
    private By yearField = By.id("year");
    private By purchaseBtn = By.xpath("//button[text()='Purchase']");
    private By closeBtn = By.xpath("//button[text()='Close']");
    private By confirmationModal = By.className("sweet-alert");
    private By confirmationOK = By.xpath("//button[text()='OK']");
    private By confirmationText = By.xpath("//p[@class='lead text-muted ']\n");

    public void clickPlaceOrder() {
        driver.findElement(placeOrderBtn).click();
    }

    public void fillForm(String name, String country, String city, String card, String month, String year) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(countryField).clear();
        driver.findElement(countryField).sendKeys(country);
        driver.findElement(cityField).clear();
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(cardField).clear();
        driver.findElement(cardField).sendKeys(card);
        driver.findElement(monthField).clear();
        driver.findElement(monthField).sendKeys(month);
        driver.findElement(yearField).clear();
        driver.findElement(yearField).sendKeys(year);
    }

    public void submitForm() {
        driver.findElement(purchaseBtn).click();
    }

    public void cancelForm() {
        driver.findElement(closeBtn).click();
    }

    public boolean isConfirmationVisible() {
        return driver.findElements(confirmationModal).size() > 0;
    }

    public void confirmAlert() {
        driver.findElement(confirmationOK).click();
    }

    public boolean isModalOpen() {
        return driver.findElements(nameField).size() > 0;
    }

    public String getFieldValue(By locator) {
        return driver.findElement(locator).getAttribute("value");
    }

    public String getConfirmationText() {
        return driver.findElement(confirmationText).getText();
    }

    public void reopenModal() {
        cancelForm();
        clickPlaceOrder();
    }
}