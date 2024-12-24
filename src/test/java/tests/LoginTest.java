package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(testName = "Check positive login", description = "Check positive login for standard user", priority = 1)
    @Epic("Login module SauceDemo")
    @Feature("TMS-L25")
    @Story("TMS-L25.10")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @Owner("Sviatlana Hryshel")
    @Issue("BUG-1344")
    @TmsLink("QASE-33")
    @Description("Check positive login with standard credentials")
    public void checkLogin() {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "Переход на страницу не выполнен");
    }

    @Test(testName = "Check login with empty username", description = "Login with empty username and check error message", priority = 2)
    @Description("Login with empty username and check error message")
    @Epic("Login module SauceDemo")
    @Severity(SeverityLevel.NORMAL)
    public void checkLoginWithEmptyUserName() {
        loginPage.open();
        loginPage.login("", password);
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "Сообщение об ошибке не верное");
    }

    @Test(testName = "Check login with empty password", description = "Login with empty password and check error message", priority = 3)
    @Description("Login with empty password and check error message")
    @Epic("Login module SauceDemo")
    @Severity(SeverityLevel.NORMAL)
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login(user, "");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "FAIL checkLoginWithEmptyPassword");
    }

    @Test(testName = "Check login with not valid password", description = "Login with not valid password and check error message", priority = 4)
    @Description("Login with not valid password and check error message")
    @Epic("Login module SauceDemo")
    @Severity(SeverityLevel.NORMAL)
    public void checkNotValidPassword() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("1111");
        driver.findElement(By.id("login-button")).click();
        String errorMessageWrongPassword = driver.findElement(By.xpath("//h3[@data-test= 'error']")).getText();
        assertEquals(errorMessageWrongPassword,
                "Epic sadface: Username and password do not match any user in this service");
    }

    @DataProvider() //предоставляет данные для нашего теста, это двухмерный массив
    @Step("Provide input data via DataProvider")
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"standard_user", "11223344", "Epic sadface: Username and password do not match any user in this service"},
        };
    }

    @Test (dataProvider = "loginData", testName = "Test with dataProvider",
            description = "Check error messages using dataProvider", priority = 5)
    @Description("Check error messages using dataProvider")
    @Epic("Login module SauceDemo")
    @Severity(SeverityLevel.NORMAL)
    public void testWithDataProvider(String user, String password, String expectedError) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(),
                expectedError,
                "Сообщение об ошибке неверное");
    }
}
