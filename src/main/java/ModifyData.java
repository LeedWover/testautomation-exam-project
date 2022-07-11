import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ModifyData {
    WebDriver driver;
    public ModifyData(WebDriver driver) {
        this.driver = driver;
    }

    private final String profileURL = "https://lennertamas.github.io/roxo/profile";
    private final By nameInput = By.xpath("//input[@id=\"name\"]");
    private final By saveButton = By.xpath("//button[@onclick=\"editUser()\"]");
    private final By successMessage = By.xpath("//p[@id=\"edit-alert\" and @style=\"display: block;\"]");

    public void navigate() {
        driver.navigate().to(profileURL);
    }

    public void changeName(String name) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(saveButton).click();
    }

    public Boolean isNameChanged() {
        return !driver.findElements(successMessage).isEmpty();
    }
}
