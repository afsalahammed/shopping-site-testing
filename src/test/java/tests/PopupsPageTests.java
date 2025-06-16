package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PopupsPage;

public class PopupsPageTests extends BaseTest {

    @Test
    public void testSignUpModalOpenClose() {
        PopupsPage popup = new PopupsPage(driver);
        popup.clickSignUp();
        Assert.assertTrue(popup.isModalDisplayed(popup.getSignUpModal()), "Sign Up modal should be displayed.");
        popup.closeModal();
    }

    @Test
    public void testLoginModalOpenClose() {
        PopupsPage popup = new PopupsPage(driver);
        popup.clickLogin();
        Assert.assertTrue(popup.isModalDisplayed(popup.getLoginModal()), "Login modal should be displayed.");
        popup.closeModal();
    }

    @Test
    public void testContactModalOpenClose() {
        PopupsPage popup = new PopupsPage(driver);
        popup.clickContact();
        Assert.assertTrue(popup.isModalDisplayed(popup.getContactModal()), "Contact modal should be displayed.");
        popup.closeModal();
    }

    @Test
    public void testAboutUsModalOpenClose() {
        PopupsPage popup = new PopupsPage(driver);
        popup.clickAboutUs();
        Assert.assertTrue(popup.isModalDisplayed(popup.getAboutUsModal()), "About Us modal should be displayed.");
        popup.closeModal();
    }

    @Test
    public void testPlaceOrderModalOpenClose() {
        PopupsPage popup = new PopupsPage(driver);
        popup.goToCart();
        popup.clickPlaceOrder();
        Assert.assertTrue(popup.isModalDisplayed(popup.getPlaceOrderModal()), "Place Order modal should be displayed.");
        popup.closeModal();
    }
}
