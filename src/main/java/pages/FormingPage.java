package pages;

import util.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
9.На вкладке Оформление заполнить поля:
        •Фамилию и Имя, Дату рождения застрахованных
        •Данные страхователя: Фамилия, Имя, Отчество, Дата рождения, Пол
        •Паспортные данные
        •Контактные данные не заполняем
10.Проверить, что все поля заполнены правильно
11.Нажать продолжить
12.Проверить, что появилось сообщение - "При заполнении данных произошла ошибка", "Поле не заполнено"
 */
public class FormingPage extends BaseTest {
    // Заполнение полей "Застрахованные c проверкой внесенных данных."
    public static WebElement surnameVZR = driver.findElement(By.id("surname_vzr_ins_0"));
    public static WebElement nameVZR =  driver.findElement(By.id("name_vzr_ins_0"));
    public static WebElement dateVZR =  driver.findElement(By.id("birthDate_vzr_ins_0"));

    // Заполнение полей "Страхователь c проверкой внесенных данных."
    public static WebElement person_lastName = driver.findElement(By.id("person_lastName"));
    public static WebElement person_firstName = driver.findElement(By.id("person_firstName"));
    public static WebElement person_middleName = driver.findElement(By.id("person_middleName"));
    public static WebElement person_birthDate = driver.findElement(By.id("person_birthDate"));

    //Выбрать пол.
    public static WebElement btn = driver.findElement((By.xpath("//label[contains(text(),'Женский')]")));

    // Заполнение полей.
    public static WebElement passportSeries = driver.findElement(By.id("passportSeries"));
    public static WebElement passportNumber = driver.findElement(By.id("passportNumber"));
    public static WebElement documentDate = driver.findElement(By.id("documentDate"));
    public static WebElement documentIssue = driver.findElement(By.id("documentIssue"));
    //перейти к элементу 'Продолжить'
    public static WebElement btnContinue = driver.findElement(By.xpath("//*[contains(text(),'Продолжить')]"));


    // Найти ошибку "Поле не заполнено"
    @FindBy(xpath = "//*[text()='Поле не заполнено.']")
    public static WebElement fieldError;
    // Найти ошибку "При заполнении данных произошла ошибка"
    @FindBy(xpath = "//div[@class='alert-form alert-form-error']")
    public static WebElement textErrorMessage;

    public FormingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static void fillField(WebElement element, String surname) {
        element.clear();
        element.sendKeys(surname);

    }

}