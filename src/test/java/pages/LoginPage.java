package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By USER_INPUT = By.id("user-name"); //большими буквами, потому что константа
    private final By PASSWORD_INPUT = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR_MESSAGE = By.xpath("//h3[@data-test = 'error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open login page")
    public void open() {
        driver.get(BASE_URL);
        //takeScreenshot(driver);
    }

    @Step("Log in to system with username {user} and password {password}")
    public void login(String user, String password) {
        driver.findElement(USER_INPUT).sendKeys(user);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        //takeScreenshot(driver);
    }

    @Step("Get the text of error message")
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
