package tests;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.PropertyReader;

import java.time.Duration;

import static utils.AllureUtils.takeScreenshot;

@Listeners(TestListener.class)
public class BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    String user = System.getProperty("user", PropertyReader.getProperty("user"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @Parameters({"browser"})
    @BeforeMethod
    @Step("Open browser {browser}")
    public void setup(@Optional("chrome") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("headless");
            edgeOptions.addArguments("start-maximized");
            driver = new EdgeDriver(edgeOptions);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("headless");
            firefoxOptions.addArguments("start-maximized");
            driver = new FirefoxDriver(firefoxOptions);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    @Step("Close browser")
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(driver);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
