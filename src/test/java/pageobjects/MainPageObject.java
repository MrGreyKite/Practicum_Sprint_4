package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.components.HeaderComponent;

import java.time.Duration;

public class MainPageObject {
    private final WebDriver driver;
    protected WebDriverWait webDriverWait;

    //главный заголовок страницы
    private final By mainHeader = By.className("Home_Header__iJKdX");

    //заголовок раздела с вопросами
    private final By importantQuestionsHeader = By.cssSelector("div.Home_FourPart__1uthg > div.Home_SubHeader__zwi_E");

    //текст заголовка с вопросами - для проверки скролла
    private final String textInImportantQuestionsHeader = "Вопросы о важном";

    //FAQ-блок: вопрос
    private By questionInQuestionsBlockById;
    private final String questionInQuestionBlockPattern = ".//*[@id='accordion__heading-%s']";

    //FAQ-блок: ответ
    private By answerInQuestionsBlockById;
    private final String answerInQuestionsBlockPattern = ".//*[@id='accordion__panel-%s']/p";

    //Кнопка(кнопки) с текстом "Заказать"
    private final By orderButton = By.xpath(".//button[text()='Заказать']");

    private final HeaderComponent header;


    public MainPageObject(WebDriver driver) {
        this.driver = driver;
        this.header = new HeaderComponent(driver);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public By getQuestionInQuestionsBlockById(String id) {
        return questionInQuestionsBlockById = By.xpath(String.format(questionInQuestionBlockPattern, id));
    }
    public By getAnswerInQuestionsBlockById(String id) {
        return answerInQuestionsBlockById = By.xpath(String.format(answerInQuestionsBlockPattern, id));
    }

    public MainPageObject scrollToImportantQuestions(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()",
                driver.findElement(importantQuestionsHeader));
        webDriverWait.until(ExpectedConditions.
                textToBePresentInElementLocated(importantQuestionsHeader, textInImportantQuestionsHeader));
        return this;
    }

    public MainPageObject clickOnSomeQuestionInQuestionsBlock(String questionId) {
        driver.findElement(getQuestionInQuestionsBlockById(questionId)).click();
        return this;
    }

    //метод проверки, что ответы не отображаются раскрытыми, пока не кликнули на блок с вопросом (не исп-зуется в текущих тестах)
    public boolean answerInQuestionBlockIsHidden(String answerID) {
        return (driver.findElement(getAnswerInQuestionsBlockById(answerID)).getAttribute("hidden") != null);
    }

    public boolean answerInQuestionBlockIsVisible(String answerID) {
        return driver.findElement(getAnswerInQuestionsBlockById(answerID)).isDisplayed();
    }

    public String getTextAnswerInQuestionBlock(String answerID) {
        return driver.findElement(getAnswerInQuestionsBlockById(answerID)).getText();
    }

    //метод клика на верхнюю (индекс 0) или нижнюю (индекс 1) кнопку с текстом "Заказать"
    public OrderPageObject clickOnOrderButtonByIndex(int buttonIndex){
        driver.findElements(orderButton).get(buttonIndex).click();
        return new OrderPageObject(driver);
    }

    //метод проверяет, что отображается главный хедер
    public boolean mainHeaderIsPresent() {
        return driver.findElement(mainHeader).isDisplayed();
    }

    public HeaderComponent getHeader() {
        return this.header;
    }
}
