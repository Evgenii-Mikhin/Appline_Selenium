import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class SberTest {

    private WebDriver driver;
    private String Url;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Deri\\IdeaProjects\\UdemyMaven\\Drivers\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Deri\\IdeaProjects\\UdemyMaven\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Url = "http://www.sberbank.ru/ru/person";
    }

    @Test

    public void testInsurance() throws Exception {
        driver.get(Url);
        driver.findElement(By.xpath("//li[@class='kitt-top-menu__item kitt-top-menu__item_first']/a[contains(text(),'Страхование')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Перейти в каталог')]")).click();
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
// Скрол страницы при помощи джаваскрипт
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,1000)", "");//скрол страницы
        WebElement findElement = driver.findElement(By.xpath("//div[@class='uc-full__block uc-full__block_header']//child::a[contains(@href, '/life/travel')]"));
        findElement.click();
        if (driver.findElements(By.xpath("//*[text()='Страхование путешественников']")).size() > 0)
            System.out.println("Элемент 'Страхование путешественников' найден");
        else System.out.println("Элемент 'Страхование путешественников' не представлен");
        assertEquals("Страхование путешественников",
                driver.findElement(By.xpath("//*[text()='Страхование путешественников']")).getText());

        driver.findElement(By.xpath("//span[contains(text(),'Оформить онлайн')]")).click();
        Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//*[text()='Минимальная']"))));
           /*
            driver.findElement(By.xpath("//div[contains(text(),'РФ и страны СНГ')]")).click();
            driver.findElement(By.xpath("//a[contains(text(),'Шенген и страны Совета Европы, кроме РФ')]")).click();//Проверка кейса else
            */
        if (driver.findElements(By.xpath("//div[@class='online-card-program selected']/*[text()='Минимальная']")).size() > 0)
            System.out.println("Выбран полис за минимальную стоимость");
        else {
            if (driver.findElements(By.xpath("//div[@class='online-card-program']/*[text()='Минимальная']")).size() > 0) {
                driver.findElement(By.xpath("//div[@class='online-card-program']/*[text()='Минимальная']")).click();
            }
        }
        driver.findElement(By.xpath("//button[contains(text(),'Оформить')]")).click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='container']"))));

// Заполнение полей "Застрахованные c проверкой внесенных данных."
        WebElement surnameVZR = driver.findElement(By.id("surname_vzr_ins_0"));
        WebElement nameVZR = driver.findElement(By.id("name_vzr_ins_0"));
        WebElement dateVZR = driver.findElement(By.id("birthDate_vzr_ins_0"));

        fillField(surnameVZR, "Баширов");
        fillField(nameVZR, "Кирилл");
        fillField(dateVZR, "02021990");

        Assert.assertEquals("Фамилия застрахованного введена некорректно","Баширов",surnameVZR .getAttribute("value"));
        Assert.assertEquals("Имя застрахованного введена некорректно","Кирилл",nameVZR .getAttribute("value"));
        Assert.assertEquals("Дата рождения застрахованного введена некорректно","02.02.1990",dateVZR .getAttribute("value"));

// Заполнение полей "Страхователь c проверкой внесенных данных."
        WebElement person_lastName = driver.findElement(By.id("person_lastName"));
        WebElement person_firstName = driver.findElement(By.id("person_firstName"));
        WebElement person_middleName = driver.findElement(By.id("person_middleName"));
        WebElement person_birthDate = driver.findElement(By.id("person_birthDate"));

        driver.findElement(By.id("person_firstName")).click();
        fillField(person_lastName, "Иванов");
        fillField(person_firstName, "Иван");
        fillField(person_birthDate, "02021990");
        person_middleName.click();
        fillField(person_middleName, "Иванович");



        Assert.assertEquals("Фамилия страхователя введена некорректно","Иванов",person_lastName .getAttribute("value"));
        Assert.assertEquals("Имя страхователя введена некорректно","Иван",person_firstName .getAttribute("value"));
        Assert.assertEquals("Имя отчества страхователя введено некорректно","Иванович",person_middleName .getAttribute("value"));
        Assert.assertEquals("Дата рождения страхователя введена некорректно","02.02.1990",person_birthDate .getAttribute("value"));

//Выбрать пол
        WebElement btn = driver.findElement((By.xpath("//label[contains(text(),'Женский')]")));
        btn.click();

// Заполнение полей "Паспортные данные" c более короткой версией проверки полей.

        fillFields(By.id("passportSeries"), "5555");
        fillFields(By.id("passportNumber"), "123456");
        fillFields(By.id("documentDate"), "02021990");
        driver.findElement(By.id("documentIssue")).click();
        fillFields(By.id("documentIssue"), "Тест");

        assertEquals("5555", driver.findElement(By.id("passportSeries")).getAttribute("value"));
        assertEquals("123456", driver.findElement(By.id("passportNumber")).getAttribute("value"));
        assertEquals("02.02.2010", driver.findElement(By.id("documentDate")).getAttribute("value"));
        assertEquals("Тест", driver.findElement(By.id("documentIssue")).getAttribute("value"));


// Прокрутить к вебэлементу "продолжить" и нажать на него.
        WebElement btnContinue = driver.findElement(By.xpath("//*[contains(text(),'Продолжить')]"));
        goElement(btnContinue);
        btnContinue.click();


        assertEquals("Поле не заполнено.",
                driver.findElement(By.xpath("//span[contains(text(),'Поле не заполнено.')]")).getText());


// Найти ошибку "При заполнении данных произошла ошибка"
        WebElement textErrorMessage = driver.findElement(By.xpath("//div[@class='alert-form alert-form-error']"));
        wait.until(ExpectedConditions.visibilityOf(textErrorMessage));
        Assert.assertEquals("При заполнении данных произошла ошибка", textErrorMessage.getText());
    }

    @After
   public void tearDown() throws Exception {
        driver.quit();
    }

// Методы отчистки и заполнения текстовых полей
    private void fillField(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
    }
    private void fillFields(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }
// Метод скрола к элементу (Джаваскрипт)
    private void goElement(WebElement element) {
            ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollIntoView(true);", element);
    }

}




