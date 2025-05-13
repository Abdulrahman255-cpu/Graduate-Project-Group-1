package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(className = "summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(className = "summary_tax_label")
    private WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(css = ".title")
    private WebElement pageTitle;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        waitForElementToBeVisible(pageTitle);
        return pageTitle.isDisplayed() && pageTitle.getText().equals("Checkout: Overview");
    }

    public String getSubtotal() {
        return getElementText(subtotalLabel);
    }

    public String getTax() {
        return getElementText(taxLabel);
    }

    public String getTotal() {
        return getElementText(totalLabel);
    }

    public CheckoutCompletePage clickFinish() {
        clickElement(finishButton);
        return new CheckoutCompletePage(driver);
    }

    public InventoryPage clickCancel() {
        clickElement(cancelButton);
        return new InventoryPage(driver);
    }

    public int getCartItemCount() {
        return cartItems.size();
    }
}