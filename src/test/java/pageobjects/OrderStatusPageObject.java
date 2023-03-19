package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class OrderStatusPageObject {
    private final WebDriver driver;
    protected WebDriverWait webDriverWait;

    //поле для ввода номера заказа
    private final By trackNumberField = By.className("Track_Input__1g7lq");

    //кнопка, чтобы посмотреть заказ по введенному номеру
    private final By seeOrderByNumberButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Посмотреть']");

    //раздел с информацией о заказе
    private final By orderInfo = By.className("Track_OrderInfo__2fpDL");

    //блок "поле - значение" внутри раздела с информацией о заказе
    private final By orderInfoBlockPattern = By.cssSelector("div.Track_Row__1sN1F");
    private final By trackTitlePattern = By.cssSelector("div.Track_Title__1XfhB");
    private final By trackValuePattern = By.cssSelector("div.Track_Value__15eEX");

    //блок с картинкой при ненахождении заказа
    private final By blockOrderNotFound = By.className("Track_NotFound__6oaoY");

    public OrderStatusPageObject(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public String getNumberOfOrderFromField() {
        return driver.findElement(trackNumberField).getAttribute("value");
    }

    public OrderStatusPageObject pressSeeOrderByNumberButton() {
        driver.findElement(seeOrderByNumberButton).click();
        return this;
    }

    public Map<String, String> getOrderInfoMap() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(orderInfo));
        // все блоки с инфо о заказе на странице
        List<WebElement> informationBlocks = driver.findElements(orderInfoBlockPattern);

        // для хранения пар "НАЗВАНИЕ_ПОЛЯ" и "ЗНАЧЕНИЕ"
        Map<String, String> informationMap = new HashMap<>();

        // перебор блоков и выделение значений
        for (WebElement informationBlock : informationBlocks) {
            // элементы "НАЗВАНИЕ_ПОЛЯ" и "ЗНАЧЕНИЕ" для текущего блока
            WebElement titleElement = informationBlock.findElement(trackTitlePattern);
            WebElement valueElement = informationBlock.findElement(trackValuePattern);

            // тексты из элементов "НАЗВАНИЕ_ПОЛЯ" и "ЗНАЧЕНИЕ"
            String titleText = titleElement.getText();
            String valueText = valueElement.getText();

            informationMap.put(titleText, valueText);
        }

        return informationMap;
    }

    public void assertThatOrderDataIsCorrectInSomeField(String fieldName, String data) {
        assertEquals(data, this.getOrderInfoMap().get(fieldName));
    }

    public boolean isOrderNotFoundErrorVisible() {
        return driver.findElement(blockOrderNotFound).isDisplayed();
    }

}
