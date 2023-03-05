import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected static final int WAIT_SECONDS = 10;
    protected final static String baseURL = "https://qa-scooter.praktikum-services.ru/";

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_SECONDS));
    }

    @After
    public void closeUp(){
        driver.quit();
    }
}
