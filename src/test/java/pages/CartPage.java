package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By CART_TITLE_LABEL = By.cssSelector(".title");
    private final By QUANTITY_CART_LABEL = By.cssSelector(".cart_quantity_label");
    private final By DESCRIPTION_CART_LABEL = By.cssSelector(".cart_desc_label");
    private final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    private final By CHECKOUT_BUTTON = By.id("checkout");
    private final String DESCRIPTION_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//div[@class='inventory_item_desc']";
    private final String PRICE_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//div[@class='inventory_item_price']";
    private final String REMOVE_BUTTON_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//button";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "cart.html");
    }

    //CHECK THE LABELS
    public String getCartTitle() {
        return driver.findElement(CART_TITLE_LABEL).getText();
    }

    public String getQuantityCartLabel() {
        return driver.findElement(QUANTITY_CART_LABEL).getText();
    }

    public String getDescriptionCartLabel() {
        return driver.findElement(DESCRIPTION_CART_LABEL).getText();
    }

    //CHECK ADDED PRODUCTS DETAILS
    public String getProductDescription(String product) {
        By productDescription = By.xpath(String.format(DESCRIPTION_PATTERN, product));
        return driver.findElement(productDescription).getText();
    }

    public String getProductPrice(String product) {
        By productPrice = By.xpath(String.format(PRICE_PATTERN, product));
        return driver.findElement(productPrice).getText();
    }

    //ACTIONS WITH BUTTONS
    public void clickContinueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public void clickCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public void clickRemove(String product) {
        By removingProduct = By.xpath(String.format(REMOVE_BUTTON_PATTERN, product));
        driver.findElement(removingProduct).click();
    }
}
