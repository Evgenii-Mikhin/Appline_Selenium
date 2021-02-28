package pages;

import util.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//1. Перейти на страницу http://www.sberbank.ru/ru/person
//2. Нажать на – Страхование
//3.Нажать на – Перейти в каталог
public class MainPage extends BaseTest {
    @FindBy(xpath = "//li[@class='kitt-top-menu__item kitt-top-menu__item_first']/a[contains(text(),'Страхование')]")
    public WebElement menuItems;

    @FindBy(xpath = "//a[contains(text(),'Перейти в каталог')]")
    WebElement menuInsurance;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void selectInsuranceItem(String itemName) {
        menuInsurance.findElement(By.xpath("//a[contains(text(),'" + itemName + "')]")).click();
    }
}
