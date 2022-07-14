import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class SaveDataToFile {
    WebDriver driver;
    public SaveDataToFile(WebDriver driver) {
        this.driver = driver;
    }

    private final String aboutURL = "https://lennertamas.github.io/roxo/about/";
    private final By listItems = By.xpath("//ul[@class=\"site-expertise-list\"]/li");

    public void navigate() {
        driver.navigate().to(aboutURL);
    }

    public void createFile(String fileName) {
        try {
            File createdFile = new File(fileName + ".txt");
            if (createdFile.createNewFile()) {
                System.out.println("File created: " + createdFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String writeToFile(String fileName) {
        List<WebElement> textList = driver.findElements(listItems);
        String message = "Initial message";
        for(WebElement text : textList) {
            try {
                FileWriter writer = new FileWriter(fileName + ".txt", true);
                writer.write(text.getText() + "\n");
                writer.close();
                message = "Successfully wrote to the file.";
            } catch (IOException e) {
                message = "An error occurred.";
            }
        }
        return message;
    }
}
