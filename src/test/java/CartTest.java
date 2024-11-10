import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {

    @Test
    public void checkCart() {
        String productName = "Sauce Labs Bike Light";
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.xpath("//div[text() = '" + productName + "']/" +
                "ancestor::div[@class = 'inventory_item']//button")).click(); //добавление товара в корзину
        driver.findElement(By.cssSelector(".shopping_cart_link")).click(); //переход в корзину
        String product = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
        assertEquals(product, productName);
    }
}
