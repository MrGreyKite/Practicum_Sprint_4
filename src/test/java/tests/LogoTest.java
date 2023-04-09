package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.MainPageObject;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LogoTest extends BaseTest {

    @Test
    public void clickOnScooterLogoLeadsToMainPage() {
        MainPageObject page = new MainPageObject(driver);
        boolean isMainPAgeOpened = page.clickOnOrderButtonByIndex(0).
                getHeader().
                clickOnScooterLogo().
                mainHeaderIsPresent();
        assertTrue(isMainPAgeOpened);
    }

    @Test
    public void clickOnYandexLogoLeadsToYandexSearch(){
        MainPageObject page = new MainPageObject(driver);
        page.getHeader().clickOnYandexLogo();

        // переключение на новую вкладку
        String currentTab = driver.getWindowHandle();
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(currentTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        //ожидание, что страница загрузилась
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form[action='https://yandex.ru/search/']")));

        // проверка, что открылась нужная страница
        String expectedUrl = "https://dzen.ru/?yredirect=true";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);

    }
}
