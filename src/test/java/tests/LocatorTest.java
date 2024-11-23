package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorTest extends BaseTest {

    @Test
    public void locatorTest() {
        //general locators
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name"));
        driver.findElement(By.name("password"));
        driver.findElement(By.className("login_container"));
        driver.findElement(By.tagName("h4"));
        //login and open Products page
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.linkText("Sauce Labs Bolt T-Shirt"));
        driver.findElement(By.partialLinkText("T-Shirt"));
        //xpath locators
        driver.findElement(By.xpath("//a[@tabindex='-1']"));
        driver.findElement(By.xpath("//a[text()='Logout']"));
        driver.findElement(By.xpath("//a[contains(@id,'item_')]"));
        driver.findElement(By.xpath("//title[contains(text(),'Swag')]"));
        driver.findElement(By.xpath("//a[@tabindex='-1']//ancestor::div[4]"));
        driver.findElement(By.xpath("//footer//descendant::div"));
        driver.findElement(By.xpath("//meta//following::div[5]"));
        driver.findElement(By.xpath("//footer//parent::li/a[text()='Facebook']"));
        driver.findElement(By.xpath("//body//preceding::div/div[@id='']"));
        driver.findElement(By.xpath("//a[@target='_blank' and text()='Twitter']"));
        //css locators
        driver.findElement(By.cssSelector(".page_wrapper"));
        driver.findElement(By.cssSelector(".bm-item.menu-item"));
        driver.findElement(By.cssSelector(".bm-cross-button .bm-cross"));
        driver.findElement(By.cssSelector("#menu_button_container"));
        driver.findElement(By.cssSelector("button"));
        driver.findElement(By.cssSelector("li.social_twitter"));
        driver.findElement(By.cssSelector("[aria-hidden=true]"));
        driver.findElement(By.cssSelector("[tabindex~='-1']"));
        driver.findElement(By.cssSelector("[data-test|='primary']"));
        driver.findElement(By.cssSelector("[href^='/favicon']"));
        driver.findElement(By.cssSelector("[href$='.png']"));
        driver.findElement(By.cssSelector("[style*='width']"));
    }
}
