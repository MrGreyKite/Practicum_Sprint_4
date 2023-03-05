package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageObject {
    private WebDriver driver;
    protected WebDriverWait webDriverWait;

    public MainPageObject(WebDriver driver) {
        this.driver = driver;

    }
}
