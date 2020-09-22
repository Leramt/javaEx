import com.codeborne.selenide.SelenideElement;
import cucumber.api.Scenario;
import org.junit.After;
import org.openqa.selenium.support.PageFactory;

public class hooks {

    @After(order = 1, timeout = defaultStepTimeout)
    public void getScenarioAfterInfo(Scenario scenario) {
        System.out.println(getStorageAsString());
        printCurrentTabName();
        addLogInfo("Тест - " + scenario.getName() + " выполнился статус -" + scenario.getStatus());
        logoutIfPossible();
    }

    void printCurrentTabName() {
        if (new PageFactory().getPage("ОбщиеЭлементыВерхнПанели").get("Текущая открытая вкладка").exists()) {
            System.out.println(сonsoleSeparator + "\nТекущая вкладка: "
                    + new PageFactory().getPage("ОбщиеЭлементыВерхнПанели").get("Текущая открытая вкладка").getText()
                    + "\n" + сonsoleSeparator);
        }
    }

    void logoutIfPossible() {
        SelenideElement toolbar = page(MainPage.class).get("ToolbarUserInfoButtonId");
        new PageFactory().getPage("ОбщиеЭлементыВерхнПанели");
        if (toolbar.exists()) {
            if (toolbar.isDisplayed()) {
                logout();
            }
        }
    }
}
