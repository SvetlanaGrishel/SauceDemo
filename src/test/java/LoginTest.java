import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void checkLogin() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String title = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(title,
                "Products",
                "Переход на страницу продуктов не выполнен");
    }

    @Test
    public void checkNoLogin() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String errorMessageNoLogin = driver.findElement(By.xpath("//h3[@data-test= 'error']")).getText();
        assertEquals(errorMessageNoLogin,
                "Epic sadface: Username is required");
    }

    @Test
    public void checkNoPassword() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();
        String errorMessageNoPassword = driver.findElement(By.xpath("//h3[@data-test= 'error']")).getText();
        assertEquals(errorMessageNoPassword,
                "Epic sadface: Password is required");
    }

    @Test
    public void checkNotValidPassword() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("1111");
        driver.findElement(By.id("login-button")).click();
        String errorMessageWrongPassword = driver.findElement(By.xpath("//h3[@data-test= 'error']")).getText();
        assertEquals(errorMessageWrongPassword,
                "Epic sadface: Username and password do not match any user in this service");
    }
}
