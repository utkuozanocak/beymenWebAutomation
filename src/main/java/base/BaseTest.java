package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import baseactions.BaseActions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    protected Properties properties;
    protected static WebDriver driver;
    protected static final Logger logger = LogManager.getLogger(BaseActions.class);
    String url = System.getProperty("page");
    @BeforeEach
    public void setUp(TestInfo testInfo) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("disable-gpu");
        options.addArguments("test-type");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        setDriver(driver);
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        if (url == null || url.isEmpty()) {
            url = getProperty("page");
        }
        getDriver().get(url);
        logger.info(testInfo.getDisplayName() + " İsimli Test Başladı...");
    }
    @AfterEach
    public void tearDown(TestInfo testInfo) {
        if (driver != null) {
            driver.quit();
            logger.info(testInfo.getDisplayName() + " İsimli Test Tamamlandı...");
        }
    }
    public static WebDriver getDriver() {
        return driver;
    }
    public static void setDriver(WebDriver driver) {
        BaseTest.driver = driver;
    }

    public BaseTest() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected String getProperty(String key) {
        return properties.getProperty(key);
    }
}
