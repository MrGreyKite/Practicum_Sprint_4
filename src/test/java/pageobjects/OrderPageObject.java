package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPageObject {

    private final WebDriver driver;
    protected WebDriverWait webDriverWait;

    //заголовок над формой
    private final By formHeader = By.className("Order_Header__BZXOb");

    //поле для вода имени
    private final By firstNameField = By.xpath(".//input[@placeholder='* Имя']");

    //поле для ввода фамилии
    private final By secondNameField = By.xpath(".//input[@placeholder='* Фамилия']");

    //поле для ввода адреса
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //поле для ввода номера телефона
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //поле для выбора станции метро
    private final By subwayStationField = By.xpath(".//input[@placeholder='* Станция метро']");

    //шаблон для подстановки индекса станции
    private final String selectedStationPattern = ".select-search__row[data-value='%s']";

    //кнопка "Далее"
    private final By proceedButton = By.xpath(".//button[text()='Далее']");

    //поле "Когда привезти самокат" - дата; пример правильной даты 20.04.2023
    private final By dateOfOrderField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //поле "Срок аренды"
    private final By timeOfArendField = By.cssSelector("[aria-haspopup='listbox']");

    //шаблон для подстановки времени аренды
    private final String arendTimePattern = ".//div[@role='option' and text()='%s']";

    //поле "Выбор цвета" - чекбокс
    private final String chooseColorPattern = "input[id='%s']";

    //поле "Комментарий для курьера"
    private final By commentaryField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //кнопка оформления заказа
    private final By doOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    //модальное окно подтверждения заказа
    private final By modalWindowForOrder = By.className("Order_Modal__YZ-d3");

    //кнопка с текстом 'Да'
    private final By okButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    //заголовок "Заказ оформлен"
    private final By headerOrderIsCreated = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    //кнопка "Посмотреть статус"
    private final By seeOrderButton = By.xpath(".//button[text()='Посмотреть статус']");

    private final By orderNumberPath = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']/text()[2]");

    public OrderPageObject(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public OrderPageObject fillFirstNameField(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
        return this;
    }

    public OrderPageObject fillSecondNameField(String secondName) {
        driver.findElement(secondNameField).sendKeys(secondName);
        return this;
    }

    public OrderPageObject fillAddressField(String address) {
        driver.findElement(addressField).sendKeys(address);
        return this;
    }

    public OrderPageObject fillPhoneNumberField(String phoneNumber) {
        driver.findElement(phoneField).sendKeys(phoneNumber);
        return this;
    }

    public OrderPageObject chooseSubwayStationFromList(String stationId) {
        driver.findElement(subwayStationField).click();
        driver.findElement(By.cssSelector(String.format(selectedStationPattern, stationId))).click();
        return this;
    }

    public OrderPageObject pressProceed() {
        driver.findElement(proceedButton).click();
        webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(formHeader, "Про аренду"));
        return this;
    }

    public OrderPageObject fillDateOfOrderField(String orderDate) {
        driver.findElement(dateOfOrderField).sendKeys(orderDate);
        return this;
    }

    public OrderPageObject chooseTimeOfArend(String timeOfArend) {
        driver.findElement(timeOfArendField).click();
        driver.findElement(By.xpath(String.format(arendTimePattern, timeOfArend))).click();
        return this;
    }

    public OrderPageObject chooseColor(String colorName) {
        driver.findElement(By.cssSelector(String.format(chooseColorPattern, colorName))).click();
        return this;
    }

    public OrderPageObject fillCommentField(String comment) {
        driver.findElement(commentaryField).sendKeys(comment);
        return this;
    }

    public OrderPageObject pressDoOrder() {
        driver.findElement(doOrderButton).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(modalWindowForOrder));
        return this;
    }

    public OrderPageObject pressOK() {
        driver.findElement(okButton).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(seeOrderButton));
        return this;
    }

    public boolean orderIsCreated() {
        return driver.findElement(headerOrderIsCreated).
                getText().contains("Заказ оформлен");
    }

    //можно потом извлекать как value из поля после перехода по кнопке просмотра статуса
    public String getOrderNumberFromPage() {
        return driver.findElement(orderNumberPath).getText();
    }

    public OrderStatusPageObject goToSeeYourOrder() {
        driver.findElement(seeOrderButton).click();
        return new OrderStatusPageObject(driver);
    }


}
