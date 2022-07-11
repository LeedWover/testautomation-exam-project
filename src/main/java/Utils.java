import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Utils {
    WebDriver driver;

    private final String baseUrl = "https://lennertamas.github.io/roxo/";
    private final By acceptButton = By.xpath("//button[@id=\"terms-and-conditions-button\"]");

    public Utils(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToBasePage() {
        driver.navigate().to(baseUrl);
    }

    public Boolean isThereConditions() {
        if(driver.findElements(acceptButton).isEmpty()) {
            return false;
        }
        return true;
    }

    public void acceptConditions() {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            driver.findElement(acceptButton).click();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void login() {
        Registration registration = new Registration(driver);
        LoginLogout login = new LoginLogout(driver);

        navigateToBasePage();
        acceptConditions();

        registration.navigateToRegistrationPage();
        registration.fillTheFieldsWithUserData("user", "abcd", "valami@valami.com", "Hello my name is");
        registration.makeARegistration();

        login.navigateToLoginPage();
        login.fillTheFieldsWithUserData("user", "abcd");
        login.clickToLogin();
    }

}
