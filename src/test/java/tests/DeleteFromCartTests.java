package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;

public class DeleteFromCartTests extends BaseTest {
    CartPage cart;

    public void addProductForDeletion() {
        driver.findElement(By.xpath("(//a[text()='Add to cart'])[1]")).click();
        acceptAlert();
        cart = new CartPage(driver);
        cart.goToCart();
    }

    @Test
    public void TC_DC_001_DeleteSingleProduct() {
        addProductForDeletion();
        int before = cart.getProductCount();
        cart.deleteFirstProduct();
        waitForDOMUpdate();
        int after = cart.getProductCount();
        Assert.assertTrue(after < before, "Product should be removed");
    }

    @Test
    public void TC_DC_002_DeleteUpdatesTotal() {
        // Add two products
        driver.findElement(By.xpath("(//a[text()='Add to cart'])[1]")).click();
        acceptAlert();
        driver.navigate().back();
        driver.findElement(By.xpath("(//a[text()='Add to cart'])[2]")).click();
        acceptAlert();
        cart = new CartPage(driver);
        cart.goToCart();
        String totalBefore = cart.getTotalPrice();
        cart.deleteFirstProduct();
        waitForDOMUpdate();
        String totalAfter = cart.getTotalPrice();
        Assert.assertNotEquals(totalBefore, totalAfter, "Total price should update after deletion");
    }

    @Test
    public void TC_DC_003_DeleteLastItem() {
        addProductForDeletion();
        cart.deleteFirstProduct();
        waitForDOMUpdate();
        Assert.assertEquals(cart.getProductCount(), 0, "Cart should be empty");
    }

    @Test
    public void TC_DC_004_VisibleDeleteButton() {
        addProductForDeletion();
        Assert.assertTrue(cart.isDeleteButtonVisible(), "Delete button should be visible");
    }

    @Test
    public void TC_DC_005_DeleteAllProducts() {
        // Add two products
        driver.findElement(By.xpath("(//a[text()='Add to cart'])[1]")).click();
        acceptAlert();
        driver.navigate().back();
        driver.findElement(By.xpath("(//a[text()='Add to cart'])[2]")).click();
        acceptAlert();
        cart = new CartPage(driver);
        cart.goToCart();

        while (cart.getProductCount() > 0) {
            cart.deleteFirstProduct();
            waitForDOMUpdate();
        }

        Assert.assertEquals(cart.getProductCount(), 0, "All products should be removed");
    }

    @Test
    public void TC_DC_006_TotalZeroAfterAllDeleted() {
        addProductForDeletion();
        cart.deleteFirstProduct();
        waitForDOMUpdate();
        Assert.assertEquals(cart.getTotalPrice(), "0", "Total should be 0 after deleting all items");
    }

    @Test
    public void TC_DC_010_DOMUpdatedAfterDelete() {
        addProductForDeletion();
        cart.deleteFirstProduct();
        waitForDOMUpdate();
        int elements = driver.findElements(By.xpath("//tr[@class='success']")).size();
        Assert.assertEquals(elements, 0, "Deleted product should not be present in DOM");
    }

    private void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
        } catch (Exception ignored) {}
    }

    private void waitForDOMUpdate() {
        try {
            Thread.sleep(2000); // simple wait to allow DOM update
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}