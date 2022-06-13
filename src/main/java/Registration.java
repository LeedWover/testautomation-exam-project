import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Registration {
    WebDriver driver;

    private final By navigateToRegistrationPageButton = By.xpath("//button[@id=\"register-form-button\"]");
    private final By registrationButton = By.xpath("//button[@onclick=\"registerUser()\"]");
    private final By usernameField = By.xpath("//input[@id=\"register-username\"]");
    private final By passwordField = By.xpath("//input[@id=\"register-password\"]");
    private final By emailField = By.xpath("//input[@id=\"register-email\"]");
    private final By descriptionField = By.xpath("//input[@id=\"register-description\"]");
    private final By successMessage = By.xpath("//p[@id=\"register-alert\"]");

    public Registration(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToRegistration() {
        driver.findElement(navigateToRegistrationPageButton).click();
    }

    public void fillTheFieldsWithUserData(String username, String password, String email, String descprtion) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(descriptionField).sendKeys(descprtion);

    }
    public String makeARegistration() {
        driver.findElement(registrationButton).click();
        return driver.findElement(successMessage).getText();
    }

}
