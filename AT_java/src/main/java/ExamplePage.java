import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
public class ExamplePage {

    @FindBy(xpath = "//input[@name='var/hpc.mass.close.cm3r.category']")
    public SelenideElement Комбобокс_Категория;


    @FindBy(xpath = "//input[@name='var/hpc.mass.close.cm3r.resolution.code']")
    public SelenideElement Комбобокс_Код_закрытия;


    @FindBy(xpath = "//input[@name='var/hpc.mass.close.cm3r.logical.name']")
    public SelenideElement Поле_Основное_КЕ;


    @FindBy(xpath = "//input[@name='var/hpc.mass.close.cm3r.service']")
    public SelenideElement Поле_Услуга;

}
