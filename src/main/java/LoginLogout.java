import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginLogout {
    WebDriver driver;

    private final By loginPageButton = By.xpath("//div[@style=\"display: block;\"]/button[@id=\"login-form-button\"]");
    private final By usernameField = By.xpath("//input[@id=\"email\"]");
    private final By passwordField = By.xpath("//input[@id=\"password\"]");
    private final By registrationButton = By.xpath("//button[@onclick=\"myFunction()\"]");
    private final By logoutButton = By.xpath("//li[@id=\"logout-link\"]/a");

    public LoginLogout(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToLoginPage() {
        driver.findElement(loginPageButton).click();
    }

    public void fillTheFieldsWithUserData(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickToLogin() {
        driver.findElement(registrationButton).click();
    }

    public void logout() {
        driver.findElement(logoutButton).click();
    }

    public boolean isLoggedIn() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        return !driver.findElements(logoutButton).isEmpty();

    }
}
