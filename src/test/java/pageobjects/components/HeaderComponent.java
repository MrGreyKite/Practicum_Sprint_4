package pageobjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobjects.MainPageObject;
import pageobjects.OrderStatusPageObject;


public class HeaderComponent {

    private final WebDriver driver;

    //логотип Яндекса
    private final By yandexLogo = By.className("Header_LogoYandex__3TSOI");

    //логотип Самоката
    private final By scooterMainLogo = By.className("Header_LogoScooter__3lsAR");

    //Кнопка с текстом "Статус заказа"
    private final By checkOrderStatusButton = By.className("Header_Link__1TAG7");

    //поле ввода номера заказа
    private final By orderNumberField = By.className("Header_Input__xIoUq");

    //кнопка "Go!"
    private final By goButton = By.xpath(".//button[text()='Go!']");

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
    }

    public MainPageObject clickOnScooterLogo() {
        driver.findElement(scooterMainLogo).click();
        return new MainPageObject(driver);
    }

    public void clickOnYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    public HeaderComponent clickCheckOrderStatusButton() {
        driver.findElement(checkOrderStatusButton).click();
        return this;
    }

    public HeaderComponent setOrderNumberInField(String orderNumber) {
        driver.findElement(orderNumberField).sendKeys(orderNumber);
        return this;
    }

    public OrderStatusPageObject clickGoToOrderButton() {
        driver.findElement(goButton).click();
        return new OrderStatusPageObject(driver);
    }


}
