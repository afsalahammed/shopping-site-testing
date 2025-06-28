package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PlaceOrderPage;

public class PlaceOrderTests extends BaseTest {
    PlaceOrderPage placeOrder;

    @BeforeMethod
    public void prepareCart() {
        driver.findElement(By.xpath("(//a[text()='Add to cart'])[1]"))
              .click();
        acceptAlert();
        driver.findElement(By.id("cartur")).click();
        placeOrder = new PlaceOrderPage(driver);
    }

    @Test
    public void TC_PO_001_OpenModal() {
        placeOrder.clickPlaceOrder();
        Assert.assertTrue(placeOrder.isModalOpen(), "Modal should open");
    }

    @Test
    public void TC_PO_002_SubmitCompleteForm() {
        placeOrder.clickPlaceOrder();
        placeOrder.fillForm("Afsal", "India", "Calicut", "123456789012", "06", "2025");
        placeOrder.submitForm();
        Assert.assertTrue(placeOrder.isConfirmationVisible(), "Confirmation alert should be shown");
        placeOrder.confirmAlert();
    }

    @Test
    public void TC_PO_003_EmptyFormSubmit() {
        placeOrder.clickPlaceOrder();
        placeOrder.submitForm();
        Assert.assertTrue(placeOrder.isModalOpen(), "Form should not close if empty");
    }

    @Test
    public void TC_PO_004_MissingName() {
        placeOrder.clickPlaceOrder();
        placeOrder.fillForm("", "India", "Calicut", "1234", "06", "2025");
        placeOrder.submitForm();
        Assert.assertTrue(placeOrder.isModalOpen(), "Form should not submit without name");
    }

    @Test
    public void TC_PO_005_MissingCity() {
        placeOrder.clickPlaceOrder();
        placeOrder.fillForm("Test", "India", "", "1234", "06", "2025");
        placeOrder.submitForm();
        Assert.assertTrue(placeOrder.isModalOpen(), "Form should not submit without city");
    }

    @Test
    public void TC_PO_006_MissingCard() {
        placeOrder.clickPlaceOrder();
        placeOrder.fillForm("Test", "India", "City", "", "06", "2025");
        placeOrder.submitForm();
        Assert.assertTrue(placeOrder.isModalOpen(), "Form should not submit without card");
    }

    @Test
    public void TC_PO_007_MissingMonth() {
        placeOrder.clickPlaceOrder();
        placeOrder.fillForm("Test", "India", "City", "1234", "", "2025");
        placeOrder.submitForm();
        Assert.assertTrue(placeOrder.isConfirmationVisible(), "Should still submit if Month is optional");
        placeOrder.confirmAlert();
    }

    @Test
    public void TC_PO_008_MissingYear() {
        placeOrder.clickPlaceOrder();
        placeOrder.fillForm("Test", "India", "City", "1234", "06", "");
        placeOrder.submitForm();
        Assert.assertTrue(placeOrder.isConfirmationVisible(), "Should still submit if Year is optional");
        placeOrder.confirmAlert();
    }

    @Test
    public void TC_PO_009_InvalidCardFormat() {
        placeOrder.clickPlaceOrder();
        placeOrder.fillForm("Test", "India", "Calicut", "abcd1234", "06", "2025");
        placeOrder.submitForm();
        Assert.assertTrue(placeOrder.isModalOpen(), "Should not accept invalid card format");
    }

    @Test
    public void TC_PO_010_CancelOrder() {
        placeOrder.clickPlaceOrder();
        placeOrder.cancelForm();
        Assert.assertFalse(placeOrder.isModalOpen(), "Modal should close on cancel");
    }

    @Test
    public void TC_PO_011_ConfirmationFormat() {
        placeOrder.clickPlaceOrder();
        placeOrder.fillForm("Afsal", "India", "Calicut", "123456789012", "06", "2025");
        placeOrder.submitForm();
        Assert.assertTrue(placeOrder.isConfirmationVisible(), "Confirmation should appear");
        String text = placeOrder.getConfirmationText();
        Assert.assertTrue(text.contains("Id") && text.contains("Amount"), "Confirmation text should include Id and Amount");
        placeOrder.confirmAlert();
    }

    @Test
    public void TC_PO_012_FormResetAfterSuccess() {
        placeOrder.clickPlaceOrder();
        placeOrder.fillForm("Afsal", "India", "Calicut", "123456789012", "06", "2025");
        placeOrder.submitForm();
        placeOrder.confirmAlert();
        placeOrder.clickPlaceOrder();
        Assert.assertEquals(driver.findElement(By.id("name")).getAttribute("value"), "");
    }

    private void acceptAlert() {
        try {
            Thread.sleep(1000);
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            System.out.println("No alert present");
        }
    }
}
