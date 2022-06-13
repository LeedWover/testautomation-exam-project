import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public void acceptConditions() {
        driver.findElement(acceptButton).click();
    }
}
