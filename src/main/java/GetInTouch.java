import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GetInTouch {

    WebDriver driver;
    public GetInTouch(WebDriver driver) {
        this.driver = driver;
    }

    private final By getInTouchPageButton = By.xpath("//span[@data-text=\"Get in touch\"]");
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By emailInput = By.id("email");
    private final By projectTypeSelect = By.xpath("//select[@id=\"projectType\"]");
    private final By descriptionTextArea = By.id("aboutProject");
    private final By sendMessageButton = By.id("contact-form-button");


    public void navigate() {
        driver.findElement(getInTouchPageButton).click();
    }



    public void fillTheFields(String firstName, String lastName, String email, String projectType, String description) {

        By webDesignOption = By.xpath("//option[@value=\"" + projectType + "\"]");

        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(projectTypeSelect).click();
        driver.findElement(webDesignOption).click();
        driver.findElement(descriptionTextArea).sendKeys(description);
        driver.findElement(sendMessageButton).click();
    }

    public String getSuccessMessageFromAlert() {
        return driver.switchTo().alert().getText();
    }
}
