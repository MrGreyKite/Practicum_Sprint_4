package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageObject {
    private final WebDriver driver;
    protected WebDriverWait webDriverWait;

    //заголовок раздела с вопросами
    private final By importantQuestionsHeader = By.cssSelector("div.Home_FourPart__1uthg > div.Home_SubHeader__zwi_E");
    //текст заголовка с вопросами - для проверки скролла
    private final String textInImportantQuestionsHeader = "Вопросы о важном";

    private By questionInQuestionsBlockById;
    private By answerInQuestionsBlockById;

    public MainPageObject(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public By getQuestionInQuestionsBlockById(String id) {
        return questionInQuestionsBlockById = By.xpath(String.format(".//*[@id='accordion__heading-%s']", id));
    }
    public By getAnswerInQuestionsBlockById(String id) {
        return answerInQuestionsBlockById = By.xpath(String.format(".//*[@id='accordion__panel-%s']/p", id));
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

    public boolean answerInQuestionBlockIsHidden(String answerID) {
        return (driver.findElement(getAnswerInQuestionsBlockById(answerID)).getAttribute("hidden") != null);
    }

    public boolean answerInQuestionBlockIsVisible(String answerID) {
        return driver.findElement(getAnswerInQuestionsBlockById(answerID)).isDisplayed();
    }

    public String getTextAnswerInQuestionBlock(String answerID) {
        return driver.findElement(getAnswerInQuestionsBlockById(answerID)).getText();
    }


    public By getQuestionInQuestionsBlockById() {
        return questionInQuestionsBlockById;
    }

    public void setQuestionInQuestionsBlockById(String id) {
        this.questionInQuestionsBlockById = By.xpath(String.format(".//*[@id='accordion__heading-%s']", id));
    }
}
