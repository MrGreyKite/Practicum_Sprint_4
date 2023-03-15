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
    private final By phoneField = By.xpath("* Телефон: на него позвонит курьер");

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

    //поле "Выбор цвета" - чекбокс "черный" label[for="black"] либо сразу input[id="black"]

    //поле "Выбор цвета" - чекбокс "серый" - label[for="grey"]

    //поле "Комментарий для курьера"
    private final By commentaryField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //кнопка оформления заказа
    private final By doOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    //модальное окно подтверждения - class Order_Modal__YZ-d3
    //кнопка class 'Button_Button__ra12g Button_Middle__1CSJM' с текстом 'Да'
    //поле с классом Order_ModalHeader__3FDaJ содержит текст "Заказ оформлен"
    //кнопка "Посмотреть статус" class Order_NextButton__1_rCA
    // записать в переменную номер заказа - ".//*[@id='root']/div/div[2]/div[5]/div[1]/div/text()[2]" getText()
    //либо потом извлекать как value из поля после перехода по кнопке просмотра статуса

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

}
