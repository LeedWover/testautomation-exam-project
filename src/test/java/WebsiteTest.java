import groovy.util.logging.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class WebsiteTest {

    WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void isThereConditionsTest() {
        Utils utils = new Utils(driver);

        utils.navigateToBasePage();

        Assertions.assertTrue(utils.isThereConditions());
    }

    @Test
    public void registrationTest() {
        Registration registration = new Registration(driver);
        Utils utils = new Utils(driver);

        utils.navigateToBasePage();
        utils.acceptConditions();

        registration.navigateToRegistrationPage();
        registration.fillTheFieldsWithUserData("user", "abcd", "valami@valami.com", "Hello my name is");

        String actual = registration.makeARegistration();
        String expected = "User registered!";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void loginTest() {
        Login login = new Login(driver);
        Utils utils = new Utils(driver);

        utils.navigateToBasePage();
        utils.acceptConditions();

        utils.login();

        boolean isLoggedIn = login.isLoggedIn();

        Assertions.assertTrue(isLoggedIn);
    }

    @Test
    public void portfolioPaginationTest() {
        Utils utils = new Utils(driver);
        PortfolioList portfolioList = new PortfolioList(driver);

        utils.navigateToBasePage();
        utils.acceptConditions();
        utils.login();

        portfolioList.navigate();

        int actual = portfolioList.countProjectItems();
        int expected = 5;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void portfolioListComparisonTest() {
        Utils utils = new Utils(driver);
        PortfolioList portfolioList = new PortfolioList(driver);

        utils.navigateToBasePage();
        utils.acceptConditions();
        utils.login();

        portfolioList.navigate();
        String[] actual = portfolioList.getProjectlistData();
        String[] expected = {
                "KIO-TAPE BRAND",
                "USE-LESS BRAND",
                "OSEN CLOCK",
                "SEAMLESS WATCH"
        };

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void makeRegistrationFromJsonTest() {
        Utils utils = new Utils(driver);
        Login login = new Login(driver);
        Registration registration = new Registration(driver);

        List<Map> users = registration.createUsersFromJson();
        for(Map user : users) {
            utils.navigateToBasePage();
            utils.acceptConditions();
            registration.navigateToRegistrationPage();
            registration.fillTheFieldsWithUserData(user.get("name").toString(), user.get("password").toString(), user.get("email").toString(), user.get("description").toString());
            registration.makeARegistration();
            //driver.manage().deleteAllCookies();
            driver.navigate().refresh();
        }

        login.fillTheFieldsWithUserData("Samantha", "ramiro.info");
        login.clickToLogin();
        boolean isLoggedIn = login.isLoggedIn();
        Assertions.assertTrue(isLoggedIn);
    }

    @AfterEach
    public void Dispose(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
