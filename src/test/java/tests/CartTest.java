package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {

    @Test(testName = "Add 1 product", description = "Add 1 product to Cart and navigate to Cart", priority = 1)
    @Description("Add 1 product to Cart and navigate to Cart")
    @Epic("Shopping Cart module SauceDemo")
    @Severity(SeverityLevel.CRITICAL)
    public void checkAddingOfOneProductToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Onesie");
        cartPage.open();
        String productInCart = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
        assertEquals(productInCart, "Sauce Labs Onesie", "(!) Product names are different");
    }

    @Test(testName = "Return to 'Products' page", description = "Return from Cart to 'Products' page via 'Continue Shopping' button", priority = 2)
    @Description("Return from Cart to 'Products' page via 'Continue Shopping' button")
    @Epic("Shopping Cart module SauceDemo")
    @Severity(SeverityLevel.NORMAL)
    public void checkReturnToProductsPageFromCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Fleece Jacket");
        productsPage.clickShoppingCart();
        cartPage.clickContinueShopping();
        String openedPageAfterClick = productsPage.getTitle();
        assertEquals(openedPageAfterClick, "Products", "(!) Page 'Products' isn't opened");
    }

    @Test(testName = "Proceed to Checkout", description = "Proceed to Checkout via 'Checkout' button", priority = 3)
    @Description("Proceed to Checkout via 'Checkout' button")
    @Epic("Shopping Cart module SauceDemo")
    @Severity(SeverityLevel.CRITICAL)
    public void checkProceedToCheckoutFromCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.clickShoppingCart();
        cartPage.clickCheckout();
        String openedCheckoutPage = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(openedCheckoutPage, "Checkout: Your Information", "(!) Page 'Checkout' isn't opened");
    }

    @Test(testName = "Return to Cart", description = "Return from 'Checkout' to Cart", priority = 4)
    @Description("Return from 'Checkout' to Cart")
    @Epic("Shopping Cart module SauceDemo")
    @Severity(SeverityLevel.NORMAL)
    public void checkReturnToCartFromCheckoutPage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.clickShoppingCart();
        cartPage.clickCheckout();
        driver.findElement(By.id("cancel")).click();
        String openedCartPage = cartPage.getCartTitle();
        assertEquals(openedCartPage, "Your Cart", "(!) Page 'Cart' isn't opened");
    }

    @Test(testName = "Refresh Cart", description = "Refresh the Cart and check that added product is still present in Cart", priority = 5)
    @Description("Refresh the Cart and check that added product is still present in Cart")
    @Epic("Shopping Cart module SauceDemo")
    @Severity(SeverityLevel.TRIVIAL)
    public void checkRefreshingCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Onesie");
        productsPage.clickShoppingCart();
        driver.navigate().refresh();
        String productInCart = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
        assertEquals(productInCart, "Sauce Labs Onesie", "(!) Product names are different");
    }

    @Test(testName = "Remove 1 product", description = "Add 1 product to Cart and remove it", priority = 6)
    @Description("Add 1 product to Cart and remove it")
    @Epic("Shopping Cart module SauceDemo")
    @Severity(SeverityLevel.CRITICAL)
    public void checkAddingOneProductToCartAndRemoving() {
        HashMap<String, String> productNamesAndPrices = new HashMap<>();
        productNamesAndPrices.put("Sauce Labs Backpack", "$29.99");
        productNamesAndPrices.put("Sauce Labs Bike Light", "$9.99");
        productNamesAndPrices.put("Sauce Labs Bolt T-Shirt", "$15.99");
        productNamesAndPrices.put("Sauce Labs Fleece Jacket", "$49.99");
        productNamesAndPrices.put("Sauce Labs Onesie", "$7.99");
        productNamesAndPrices.put("Test.allTheThings() T-Shirt (Red)", "$15.99");
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Fleece Jacket");
        productsPage.clickShoppingCart();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getProductPrice("Sauce Labs Fleece Jacket"),
                productNamesAndPrices.get("Sauce Labs Fleece Jacket"),
                "(!) Can't find product in Cart");
        cartPage.clickRemove("Sauce Labs Fleece Jacket");
        boolean resultRemoving = driver.findElement(By.className("removed_cart_item")).isDisplayed();
        softAssert.assertFalse(resultRemoving, "Cart isn't empty");
        softAssert.assertAll();
    }

    @Test(testName = "Remove 2 products", description = "Add 2 products to Cart and remove two products", priority = 7)
    @Description("Add 2 products to Cart and remove two products")
    @Epic("Shopping Cart module SauceDemo")
    @Severity(SeverityLevel.NORMAL)
    public void checkAddingTwoProductsToCartAndRemovingBoth() {
        HashMap<String, String> productNamesAndPrices = new HashMap<>();
        productNamesAndPrices.put("Sauce Labs Backpack", "$29.99");
        productNamesAndPrices.put("Sauce Labs Bike Light", "$9.99");
        productNamesAndPrices.put("Sauce Labs Bolt T-Shirt", "$15.99");
        productNamesAndPrices.put("Sauce Labs Fleece Jacket", "$49.99");
        productNamesAndPrices.put("Sauce Labs Onesie", "$7.99");
        productNamesAndPrices.put("Test.allTheThings() T-Shirt (Red)", "$15.99");
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.clickShoppingCart();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getProductPrice("Sauce Labs Backpack"),
                productNamesAndPrices.get("Sauce Labs Backpack"),
                "(!) Can't find product in Cart");
        softAssert.assertEquals(cartPage.getProductPrice("Sauce Labs Bike Light"),
                productNamesAndPrices.get("Sauce Labs Bike Light"),
                "(!) Can't find product in Cart");
        cartPage.clickRemove("Sauce Labs Backpack");
        cartPage.clickRemove("Sauce Labs Bike Light");
        boolean resultRemoving = driver.findElement(By.className("removed_cart_item")).isDisplayed();
        softAssert.assertFalse(resultRemoving, "Cart isn't empty");
        softAssert.assertAll();
    }

    @Test(testName = "Check empty Cart", description = "Check elements of empty Cart", priority = 8)
    @Description("Check elements of empty Cart")
    @Epic("Shopping Cart module SauceDemo")
    @Severity(SeverityLevel.TRIVIAL)
    public void checkElementsOfEmptyCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickShoppingCart();
        SoftAssert softAssert = new SoftAssert();
        String cartTitle = cartPage.getCartTitle();
        String cartQuantityLabel = cartPage.getQuantityCartLabel();
        String cartDescriptionLabel = cartPage.getDescriptionCartLabel();
        softAssert.assertEquals(cartTitle, "Your Cart", "(!) Title of 'Cart' page is wrong");
        softAssert.assertEquals(cartQuantityLabel, "QTY", "(!) Quantity label of 'Cart' page is wrong");
        softAssert.assertEquals(cartDescriptionLabel, "Description", "(!) Quantity label of 'Cart' page is wrong");
        softAssert.assertAll();
    }

    @Test(testName = "Check prices", description = "Check prices of all products in Cart", priority = 9)
    @Description("Check prices of all products in Cart")
    @Epic("Shopping Cart module SauceDemo")
    @Severity(SeverityLevel.CRITICAL)
    public void checkPricesOfAllProductsInCart() {
        HashMap<String, String> productNamesAndPrices = new HashMap<>();
        productNamesAndPrices.put("Sauce Labs Backpack", "$29.99");
        productNamesAndPrices.put("Sauce Labs Bike Light", "$9.99");
        productNamesAndPrices.put("Sauce Labs Bolt T-Shirt", "$15.99");
        productNamesAndPrices.put("Sauce Labs Fleece Jacket", "$49.99");
        productNamesAndPrices.put("Sauce Labs Onesie", "$7.99");
        productNamesAndPrices.put("Test.allTheThings() T-Shirt (Red)", "$15.99");
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.clickAddButton("Sauce Labs Bolt T-Shirt");
        productsPage.clickAddButton("Sauce Labs Fleece Jacket");
        productsPage.clickAddButton("Sauce Labs Onesie");
        productsPage.clickAddButton("Test.allTheThings() T-Shirt (Red)");
        productsPage.clickShoppingCart();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getProductPrice("Sauce Labs Backpack"),
                productNamesAndPrices.get("Sauce Labs Backpack"),
                "(!) Can't find product 'Sauce Labs Backpack' in Cart");
        softAssert.assertEquals(cartPage.getProductPrice("Sauce Labs Bike Light"),
                productNamesAndPrices.get("Sauce Labs Bike Light"),
                "(!) Can't find product 'Sauce Labs Bike Light' in Cart");
        softAssert.assertEquals(cartPage.getProductPrice("Sauce Labs Bolt T-Shirt"),
                productNamesAndPrices.get("Sauce Labs Bolt T-Shirt"),
                "(!) Can't find product 'Sauce Labs Bolt T-Shirt' in Cart");
        softAssert.assertEquals(cartPage.getProductPrice("Sauce Labs Fleece Jacket"),
                productNamesAndPrices.get("Sauce Labs Fleece Jacket"),
                "(!) Can't find product 'Sauce Labs Fleece Jacket' in Cart");
        softAssert.assertEquals(cartPage.getProductPrice("Sauce Labs Onesie"),
                productNamesAndPrices.get("Sauce Labs Onesie"),
                "(!) Can't find product 'Sauce Labs Onesie' in Cart");
        softAssert.assertEquals(cartPage.getProductPrice("Test.allTheThings() T-Shirt (Red)"),
                productNamesAndPrices.get("Test.allTheThings() T-Shirt (Red)"),
                "(!) Can't find product 'Test.allTheThings() T-Shirt (Red)' in Cart");
        softAssert.assertAll();
    }

    @Test(testName = "Check descriptions" , description = "Check descriptions of all products in Cart", priority = 10)
    @Description("Check descriptions of all products in Cart")
    @Epic("Shopping Cart module SauceDemo")
    @Severity(SeverityLevel.NORMAL)
    public void checkDescriptionsOfAllProductsInCart() {
        HashMap<String, String> productNamesAndDescriptions = getStringStringHashMap();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddButton("Sauce Labs Backpack");
        productsPage.clickAddButton("Sauce Labs Bike Light");
        productsPage.clickAddButton("Sauce Labs Bolt T-Shirt");
        productsPage.clickAddButton("Sauce Labs Fleece Jacket");
        productsPage.clickAddButton("Sauce Labs Onesie");
        productsPage.clickAddButton("Test.allTheThings() T-Shirt (Red)");
        productsPage.clickShoppingCart();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getProductDescription("Sauce Labs Backpack"),
                productNamesAndDescriptions.get("Sauce Labs Backpack"),
                "(!) Can't find product 'Sauce Labs Backpack' in Cart");
        softAssert.assertEquals(cartPage.getProductDescription("Sauce Labs Bike Light"),
                productNamesAndDescriptions.get("Sauce Labs Bike Light"),
                "(!) Can't find product 'Sauce Labs Bike Light' in Cart");
        softAssert.assertEquals(cartPage.getProductDescription("Sauce Labs Bolt T-Shirt"),
                productNamesAndDescriptions.get("Sauce Labs Bolt T-Shirt"),
                "(!) Can't find product 'Sauce Labs Bolt T-Shirt' in Cart");
        softAssert.assertEquals(cartPage.getProductDescription("Sauce Labs Fleece Jacket"),
                productNamesAndDescriptions.get("Sauce Labs Fleece Jacket"),
                "(!) Can't find product 'Sauce Labs Fleece Jacket' in Cart");
        softAssert.assertEquals(cartPage.getProductDescription("Sauce Labs Onesie"),
                productNamesAndDescriptions.get("Sauce Labs Onesie"),
                "(!) Can't find product 'Sauce Labs Onesie' in Cart");
        softAssert.assertEquals(cartPage.getProductDescription("Test.allTheThings() T-Shirt (Red)"),
                productNamesAndDescriptions.get("Test.allTheThings() T-Shirt (Red)"),
                "(!) Can't find product 'Test.allTheThings() T-Shirt (Red)' in Cart");
        softAssert.assertAll();
    }

    private static HashMap<String, String> getStringStringHashMap() {
        HashMap<String, String> productNamesAndDescriptions = new HashMap<>();
        productNamesAndDescriptions.put("Sauce Labs Backpack", "carry.allTheThings() with the sleek, streamlined Sly Pack that" +
                " melds uncompromising style with unequaled laptop and tablet protection.");
        productNamesAndDescriptions.put("Sauce Labs Bike Light", "A red light isn't the desired state in testing but it sure " +
                "helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.");
        productNamesAndDescriptions.put("Sauce Labs Bolt T-Shirt", "Get your testing superhero on with the Sauce Labs bolt " +
                "T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.");
        productNamesAndDescriptions.put("Sauce Labs Fleece Jacket", "It's not every day that you come across a midweight " +
                "quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.");
        productNamesAndDescriptions.put("Sauce Labs Onesie", "Rib snap infant onesie for the junior automation engineer in " +
                "development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.");
        productNamesAndDescriptions.put("Test.allTheThings() T-Shirt (Red)", "This classic Sauce Labs t-shirt is perfect to " +
                "wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.");
        return productNamesAndDescriptions;
    }
}
