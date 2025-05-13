package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import pages.CartPage;
import pages.CheckoutInfoPage;
import pages.CheckoutOverviewPage;
import pages.CheckoutCompletePage;

public class SauceDemoTest extends BaseTest {

    private static final String userName = "error_user";
    private static final String password = "secret_sauce";
    private static final String product = "Sauce Labs Backpack";
    private static final String firstName = "John";
    private static final String lastName = "Doe";
    private static final String postalCode = "12345";

    @Test
    public void fullPurchaseFlow() {
        // Login
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.login(userName, password);

        // Verify successful login
        Assert.assertTrue(inventoryPage.isDisplayed(), "Failed to login or inventory page is not displayed");

        // Add product to cart
        inventoryPage.addProductToCart(product);

        // Go to cart
        CartPage cartPage = inventoryPage.goToCart();
        Assert.assertTrue(cartPage.isDisplayed(), "Cart page is not displayed");
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "Product was not added to cart");

        // Proceed to checkout
        CheckoutInfoPage checkoutInfoPage = cartPage.clickCheckout();
        Assert.assertTrue(checkoutInfoPage.isDisplayed(), "Checkout information page is not displayed");

        // Fill shipping information
        CheckoutOverviewPage checkoutOverviewPage = checkoutInfoPage.fillShippingInfo(firstName, lastName, postalCode);
        Assert.assertTrue(checkoutOverviewPage.isDisplayed(), "Checkout overview page is not displayed");
        Assert.assertEquals(checkoutOverviewPage.getCartItemCount(), 1, "Product count doesn't match");

        // Complete purchase
        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.clickFinish();
        Assert.assertTrue(checkoutCompletePage.isDisplayed(), "Checkout complete page is not displayed");

        // Verify order success message
        String completeHeader = checkoutCompletePage.getCompleteHeader();
        Assert.assertEquals(completeHeader, "Thank you for your order!", "Order completion message doesn't match");

        // Return to home page
        inventoryPage = checkoutCompletePage.clickBackHome();
        Assert.assertTrue(inventoryPage.isDisplayed(), "Failed to return to inventory page");
    }
}