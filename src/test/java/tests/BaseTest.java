package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {

    protected static WebDriverManager wdm;
    protected WebDriver driver;
    protected static final int WAIT_SECONDS = 10;
    protected static final String URL = "https://qa-scooter.praktikum-services.ru/";

    @BeforeClass
    public static void setUp() {
//        WebDriverManager.chromedriver().setup();
//      mvn test -Dwdm.defaultBrowser="firefox" - так запускать
        System.getProperty("wdm.defaultBrowser", "chrome");
        wdm = WebDriverManager.getInstance();
    }

    @Before
    public void start() {
//        driver = new ChromeDriver();
        driver = wdm.create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_SECONDS));
        driver.get(URL);
        //если отображается плашка с информацией о сборе кук - подтвердить куки и закрыть плашку
        if (driver.findElement(By.className("App_CookieConsent__1yUIN")).isDisplayed()) {
            driver.findElement(By.id("rcc-confirm-button")).click();
        }
    }

    @After
    public void closeUp(){
        System.clearProperty("wdm.defaultBrowser");
        driver.quit();
    }
}
