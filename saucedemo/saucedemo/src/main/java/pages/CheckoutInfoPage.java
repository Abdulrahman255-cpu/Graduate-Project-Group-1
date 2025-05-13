package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutInfoPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(css = ".title")
    private WebElement pageTitle;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        waitForElementToBeVisible(pageTitle);
        return pageTitle.isDisplayed() && pageTitle.getText().equals("Checkout: Your Information");
    }

    public CheckoutInfoPage enterFirstName(String firstName) {
        sendKeys(firstNameField, firstName);
        return this;
    }

    public CheckoutInfoPage enterLastName(String lastName) {
        sendKeys(lastNameField, lastName);
        return this;
    }

    public CheckoutInfoPage enterPostalCode(String postalCode) {
        sendKeys(postalCodeField, postalCode);
        return this;
    }

    public CheckoutOverviewPage clickContinue() {
        clickElement(continueButton);
        return new CheckoutOverviewPage(driver);
    }

    public CartPage clickCancel() {
        clickElement(cancelButton);
        return new CartPage(driver);
    }

    public String getErrorMessage() {
        waitForElementToBeVisible(errorMessage);
        return errorMessage.getText();
    }

    public CheckoutOverviewPage fillShippingInfo(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        return clickContinue();
    }
}