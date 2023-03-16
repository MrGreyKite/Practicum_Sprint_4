package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderStatusPageObject {
    private final WebDriver driver;
    protected WebDriverWait webDriverWait;

    private final By trackNumberField = By.className("Track_Input__1g7lq");

    private final By seeOrderByNumberButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Посмотреть']");

    public OrderStatusPageObject(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public String getNumberOfOrderFromField() {
        return driver.findElement(trackNumberField).getText();
    }

    public OrderStatusPageObject pressSeeOrderByNumberButton() {
        driver.findElement(seeOrderByNumberButton).click();
        return this;
    }



}
