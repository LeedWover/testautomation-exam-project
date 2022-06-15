import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PortfolioListSize {
    WebDriver driver;
    public PortfolioListSize(WebDriver driver) {
        this.driver = driver;
    }

    private final By portfolioPageLink = By.xpath("//a[contains(text(),\"Portfolio\")]");
    private final By projectItems = By.xpath("//div[@class=\"site-project-item-content\"]");
    private final By nextButton = By.xpath("//li/a[@aria-label=\"Next\"]");
    private final By disabledNextButton = By.xpath("//li[@class=\"page-item disabled\"]/a[@aria-label=\"Next\"]");

    public void navigate() {
        driver.findElement(portfolioPageLink).click();
    }

    public Boolean paginate() {
        if(driver.findElements(disabledNextButton).isEmpty()) {
            driver.findElement(nextButton).click();
            return false;
        }
        return true;
    }

    public int countProjectItems() {
        int numberOfItems = 0;

        do {
            numberOfItems += driver.findElements(projectItems).size();
        }
        while (!paginate());

        return numberOfItems;
    }

}
