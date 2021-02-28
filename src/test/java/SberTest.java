
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import util.BaseTest;
import pages.KatalogPage;
import pages.LifeTravelPage;
import pages.MainPage;
import pages.RegistrationPage;
import pages.FormingPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unused")
public class SberTest extends BaseTest {
    @BeforeClass
    public static void setUp() throws Exception {
        String browser = properties.getProperty("browser");
        if ("firefox".equals(browser)) {
            System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));
            driver = new FirefoxDriver();
        } else if ("chrome".equals(browser)) {
            System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
            driver = new ChromeDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
            driver = new ChromeDriver();
        }

        url = properties.getProperty("app.url");
        System.out.println(url);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void testInsurance() {
        driver.get(url + "/");
        new MainPage(driver).menuItems.click();
        new MainPage(driver).selectInsuranceItem("Перейти в каталог");

        new KatalogPage(driver);
        goElement(KatalogPage.lifeTravel);
        KatalogPage.lifeTravel.click();

        new LifeTravelPage(driver);
        assertEquals("Страхование путешественников", LifeTravelPage.elementOfSite.getText());
        LifeTravelPage.registrationButton.click();

        new RegistrationPage(driver).waitSendAppClickable();

        FormingPage.fillField(FormingPage.surnameVZR, "Аладар");
        FormingPage.fillField(FormingPage.nameVZR, "Кирилл");
        FormingPage.fillField(FormingPage.dateVZR, "02.02.1990");
        assertEquals("Фамилия застрахованного введена некорректно", "Аладар", FormingPage.surnameVZR.getAttribute("value"));
        assertEquals("Имя застрахованного введена некорректно", "Кирилл", FormingPage.nameVZR.getAttribute("value"));
        assertEquals("Дата рождения застрахованного введена некорректно", "02.02.1990", FormingPage.dateVZR.getAttribute("value"));

        FormingPage.person_lastName.click();
        FormingPage.fillField(FormingPage.person_lastName, "Иванов");
        FormingPage.fillField(FormingPage.person_firstName, "Иван");
        FormingPage.fillField(FormingPage.person_birthDate, "02021990");
        FormingPage.person_middleName.click();
        FormingPage.fillField(FormingPage.person_middleName, "Иванович");

        assertEquals("Фамилия страхователя введена некорректно", "Иванов", FormingPage.person_lastName.getAttribute("value"));
        assertEquals("Имя страхователя введена некорректно", "Иван", FormingPage.person_firstName.getAttribute("value"));
        assertEquals("Имя отчества страхователя введено некорректно", "Иванович", FormingPage.person_middleName.getAttribute("value"));
        assertEquals("Дата рождения страхователя введена некорректно", "02.02.1990", FormingPage.person_birthDate.getAttribute("value"));

        FormingPage.btn.click();

        FormingPage.fillField(FormingPage.passportSeries, "5555");
        FormingPage.fillField(FormingPage.passportNumber, "123456");
        FormingPage.fillField(FormingPage.documentDate, "02021990");
        FormingPage.documentIssue.click();
        FormingPage.fillField(FormingPage.documentIssue, "Тест");

        assertEquals("Серия паспорта введена некорректно", "5555", FormingPage.passportSeries.getAttribute("value"));
        assertEquals("Номер паспорта введен некорректно", "123456", FormingPage.passportNumber.getAttribute("value"));
        assertEquals("Дата регистрации введена некорректно", "02.02.2010", FormingPage.documentDate.getAttribute("value"));
        assertEquals("Кем выдан введено некорректно", "Тест", FormingPage.documentIssue.getAttribute("value"));

        goElement(FormingPage.btnContinue);
        FormingPage.btnContinue.click();

        new FormingPage(driver);
        assertEquals("Поле не заполнено.", FormingPage.fieldError.getText());

        new FormingPage(driver);
        assertEquals("При заполнении данных произошла ошибка", FormingPage.textErrorMessage.getText());

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}