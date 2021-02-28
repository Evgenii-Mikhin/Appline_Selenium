package util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;

public class BaseTest {
    public static WebDriver driver;
    public static String url;
    public static Properties properties = TestProperties.getInstance().getProperties();

    public void fillFields (By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    // Метод скрола к элементу (Джаваскрипт)
    public void goElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollIntoView(true);", element);
    }
}

