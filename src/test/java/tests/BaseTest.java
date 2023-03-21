package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class BaseTest {

    protected static WebDriverManager wdm;
    protected WebDriver driver;
    protected static final int WAIT_SECONDS = 10;
    protected static final String URL = "https://qa-scooter.praktikum-services.ru/";

    @BeforeClass
    public static void setUp() {
        /*
        String defaultBrowser = "chrome"; //edge, chrome, firefox
        //вписать нужный браузер либо передавать через mvn test -D"wdm.defaultBrowser"={browser}, но при этом ломается кодировка

        System.setProperty("wdm.defaultBrowser", defaultBrowser);
        wdm = WebDriverManager.getInstance();
        */

        WebDriverManager.chromedriver().setup();
        // WebDriverManager.firefoxdriver().setup(); - если не запустится хром
    }

    @Before
    public void start() {
//        driver = wdm.create();
        new ChromeDriver();
//        new FirefoxDriver(); - если не запустится хром

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
