package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(css = ".title")
    private WebElement pageTitle;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        waitForElementToBeVisible(pageTitle);
        return pageTitle.isDisplayed() && pageTitle.getText().equals("Your Cart");
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public CheckoutInfoPage clickCheckout() {
        clickElement(checkoutButton);
        return new CheckoutInfoPage(driver);
    }

    public InventoryPage continueShopping() {
        clickElement(continueShoppingButton);
        return new InventoryPage(driver);
    }
}