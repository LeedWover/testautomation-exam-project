
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registration {
    WebDriver driver;

    private final By registrationPageButton = By.xpath("//button[@id=\"register-form-button\"]");
    private final By registrationButton = By.xpath("//button[@onclick=\"registerUser()\"]");
    private final By usernameField = By.xpath("//input[@id=\"register-username\"]");
    private final By passwordField = By.xpath("//input[@id=\"register-password\"]");
    private final By emailField = By.xpath("//input[@id=\"register-email\"]");
    private final By descriptionField = By.xpath("//input[@id=\"register-description\"]");
    private final By successMessage = By.xpath("//p[@id=\"register-alert\"]");

    public Registration(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToRegistrationPage() {
        driver.findElement(registrationPageButton).click();
    }

    public void fillTheFieldsWithUserData(String username, String password, String email, String description) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(descriptionField).sendKeys(description);

    }
    public String makeARegistration() {
        driver.findElement(registrationButton).click();
        return driver.findElement(successMessage).getText();
    }

    public List<Map> createUsersFromJson() {

        List<Map> result = new ArrayList();

        Response response = RestAssured
                .when()
                .get("https://jsonplaceholder.typicode.com/users")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        List<Map<String, ?>> users = response.jsonPath().getList("");

        for(Map<String, ?> user : users) {
            HashMap mMap = new HashMap();
            mMap.put("name",user.get("username").toString());
            mMap.put("password",user.get("website").toString());
            mMap.put("email",user.get("email").toString());
            mMap.put("description",user.get("name").toString());
            result.add(mMap);

            if(user.get("id").equals(3)) {
                break;
            }

        }
        return result;
    }
}
