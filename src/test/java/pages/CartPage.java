package pages;

import io.qameta.allure.Step;
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

    @Step("Open Cart")
    public void open() {
        driver.get(BASE_URL + "cart.html");
    }

    //CHECK THE LABELS
    @Step("Get the title of Cart page")
    public String getCartTitle() {
        return driver.findElement(CART_TITLE_LABEL).getText();
    }

    @Step("Get the quantity of products in Cart")
    public String getQuantityCartLabel() {
        return driver.findElement(QUANTITY_CART_LABEL).getText();
    }

    @Step("Get the description of Cart")
    public String getDescriptionCartLabel() {
        return driver.findElement(DESCRIPTION_CART_LABEL).getText();
    }

    //CHECK ADDED PRODUCTS DETAILS
    @Step("Get the '{product}' descriptions in Cart")
    public String getProductDescription(String product) {
        By productDescription = By.xpath(String.format(DESCRIPTION_PATTERN, product));
        return driver.findElement(productDescription).getText();
    }

    @Step("Get the '{product}' prices in Cart")
    public String getProductPrice(String product) {
        By productPrice = By.xpath(String.format(PRICE_PATTERN, product));
        return driver.findElement(productPrice).getText();
    }

    //ACTIONS WITH BUTTONS
    @Step("Click 'Continue Shopping' button")
    public void clickContinueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    @Step("Click 'Checkout' button")
    public void clickCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    @Step("Remove the '{product}' from Cart")
    public void clickRemove(String product) {
        By removingProduct = By.xpath(String.format(REMOVE_BUTTON_PATTERN, product));
        driver.findElement(removingProduct).click();
    }
}
