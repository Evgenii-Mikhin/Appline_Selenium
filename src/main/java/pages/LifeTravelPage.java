package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//5.Проверить наличие на странице заголовка – Страхование путешественников
//6. Нажать на – Оформить Онлайн
public class LifeTravelPage {
    @FindBy(xpath = "//*[text()='Страхование путешественников']")
    public static WebElement elementOfSite;
    @FindBy(xpath = "//span[contains(text(),'Оформить онлайн')]")
    public static WebElement registrationButton;

    public LifeTravelPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}