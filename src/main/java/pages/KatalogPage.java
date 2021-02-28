package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// 4.Нажать на - Страхование путешественников
public class KatalogPage {
    @FindBy(xpath = "//div[@class='uc-full__block uc-full__block_header']//child::a[contains(@href, '/life/travel')]")
    public static WebElement lifeTravel;

    public KatalogPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
