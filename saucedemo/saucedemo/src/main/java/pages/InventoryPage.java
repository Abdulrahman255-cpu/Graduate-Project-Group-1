package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(css = ".title")
    private WebElement pageTitle;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        waitForElementToBeVisible(pageTitle);
        return pageTitle.isDisplayed() && pageTitle.getText().equals("Products");
    }

    public InventoryPage addProductToCart(String productName) {
        for (WebElement item : inventoryItems) {
            WebElement itemName = item.findElement(By.className("inventory_item_name"));
            if (itemName.getText().equals(productName)) {
                WebElement addToCartButton = item.findElement(By.cssSelector("button.btn_inventory"));
                clickElement(addToCartButton);
                break;
            }
        }
        return this;
    }

    public CartPage goToCart() {
        clickElement(cartLink);
        return new CartPage(driver);
    }
}