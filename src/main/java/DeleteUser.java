import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeleteUser {
    WebDriver driver;
    public DeleteUser(WebDriver driver) {
        this.driver = driver;
    }

    private final String profileURL = "https://lennertamas.github.io/roxo/profile";
    private final By deleteAccountBtn = By.xpath("//button[@onclick=\"showRealDeleteAccBtn()\"]");
    private final By confirmDeleteAccBtn = By.xpath("//button[@onclick=\"deleteAccount()\"]");

    public void navigate() {
        driver.navigate().to(profileURL);
    }

    public void deleteAccount() {
        driver.findElement(deleteAccountBtn).click();
        driver.findElement(confirmDeleteAccBtn).click();
    }
}
