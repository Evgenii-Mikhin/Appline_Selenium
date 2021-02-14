import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseTest {
    protected static WebDriver driver;
    protected static String Url;
    public static Properties properties= TestProperties.getInstance().getProperties();

    @BeforeClass
    public static void setUp() throws Exception {
        switch (properties.getProperty("browser")){
            case "firefox":
                System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
        }

        Url = properties.getProperty("app.url");
        System.out.println(Url);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
    // Методы отчистки и заполнения текстовых полей
    public void fillField(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
    }
    public void fillFields(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }
    // Метод скрола к элементу (Джаваскрипт)
    public void goElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollIntoView(true);", element);
    }
}
