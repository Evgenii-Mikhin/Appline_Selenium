package pages;
import util.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//7. На вкладке – Выбор полиса  выбрать сумму страховой защиты – Минимальная
//8. Нажать Оформить

public class RegistrationPage extends BaseTest {

    //public WebDriver driver;
    @FindBy(xpath = "//button[contains(text(),'Оформить')]")
    public WebElement button;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void waitSendAppClickable() {

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[text()='Минимальная']")));

        // driver.findElement(By.xpath("//div[contains(text(),'РФ и страны СНГ')]")).click();
        // driver.findElement(By.xpath("//a[contains(text(),'Шенген и страны Совета Европы, кроме РФ')]")).click();  //Проверка кейса else

        if (driver.findElements(By.xpath("//div[@class='online-card-program selected']/*[text()='Минимальная']")).size() > 0) {
            System.out.println("Выбран полис за минимальную стоимость");
            button.click();
        }
        else{
            if (driver.findElements(By.xpath("//div[@class='online-card-program']/*[text()='Минимальная']")).size() > 0) {
                driver.findElement(By.xpath("//div[@class='online-card-program']/*[text()='Минимальная']")).click();
            }
        }
    }
}

