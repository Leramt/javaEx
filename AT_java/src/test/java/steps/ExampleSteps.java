import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.assertTrue;


public abstract class ExampleSteps {

    /**
     * Метод для получения вэб-элемента через его аннотацию в указанном PageObject и ожидание его видимости на странице
     *
     * @param page        = имя страницы
     * @param elementName = имя элемента
     * @return = возвращает вэблемент
     * @throws Exception
     */
    public static SelenideElement elementFinder(String page, String elementName) {
        waitUntilPageIsAvailable();
        checkAlertDoesNotExist();
        SelenideElement element = new PageElement().getElementFromPage(page, elementName);
        assertTrue("Ошибка! Элемент \"" + elementName + "\" не найден на странице. \n(" + element + ")\n" + page,
                waitElementIsExist(element.parent()));
        System.out.println("Найден элемент " + elementName);
        return element;
    }

    /**
     * Метод для получения коллекции вэб-элементов через его аннотацию в указанном PageObject
     *
     * @param page                  = имя страницы
     * @param elementCollectionName = аннотированное имя коллекции
     * @return = возвращает коллекцию вэб-элементов
     * @throws Exception
     */
    public static ElementsCollection elementCollectionFinder(String page, String elementCollectionName) {
        waitUntilPageIsAvailable();
        checkAlertDoesNotExist();
        ElementsCollection collection = new PageElement().getElementsCollectionFromPage(page, elementCollectionName);
        assertTrue("Ошибка! Коллекция элементов  \"" + elementCollectionName + "\" (" + collection.get(0) + ") не найдена на странице " + page,
                waitElementIsExist(collection.get(0).parent()));
        System.out.println("Найдена коллекция элементов " + elementCollectionName);
        return collection;
    }
}