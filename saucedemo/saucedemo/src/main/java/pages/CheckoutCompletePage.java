package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage {

    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    @FindBy(className = "complete-text")
    private WebElement completeText;

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    @FindBy(css = ".title")
    private WebElement pageTitle;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        waitForElementToBeVisible(pageTitle);
        return pageTitle.isDisplayed() && pageTitle.getText().equals("Checkout: Complete!");
    }

    public String getCompleteHeader() {
        return getElementText(completeHeader);
    }

    public String getCompleteText() {
        return getElementText(completeText);
    }

    public InventoryPage clickBackHome() {
        clickElement(backHomeButton);
        return new InventoryPage(driver);
    }
}