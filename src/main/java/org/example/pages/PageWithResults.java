package org.example.pages;

import org.example.util.VisibilityChecker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
/**
 * Класс-страница с результатами поиска
 * @author Мороз Сергей LuvMiit
 */
public class PageWithResults extends MainPage{
    /**
     * Коллекция, хранящая список результатов поиска
     * @author Мороз Сергей LuvMiit
     */
    private List<WebElement> results;
    /**
     * Переменная, представляющая элемент "Заголовок результата"
     * @author Мороз Сергей LuvMiit
     */
    private WebElement searchedResult;
    /**
     * Переменная проверки видимости объекта
     * @author Мороз Сергей LuvMiit
     */
    private VisibilityChecker visibilityChecker;

    public PageWithResults(WebDriver chromeDriver) {
        super(chromeDriver);
        visibilityChecker = new VisibilityChecker(chromeDriver);

    }
    /**
     * Метод ищет и собирает все рузультаты поиска
     * @return Список результатов
     * @author Мороз Сергей LuvMiit
     */
    public List<WebElement> getResults() {
        visibilityChecker.visibilityWait("//div[@id='rso']//h3");
        results = chromeDriver.findElements(By.xpath("//div[@id='rso']//h3"));
        return results;
    }
    /**
     * Метод ищет необходимый результат
     * @return Название найденного результата
     * @author Мороз Сергей LuvMiit
     */
    public String findHeader (String title) {
        visibilityChecker.visibilityWait("//div[@id='rso']//h3[contains(text(), '"+title+"')]");

        searchedResult = chromeDriver
                .findElement(By.xpath("//div[@id='rso']//h3[contains(text(), '"+title+"')]"));

        return searchedResult.getText();
    }
    /**
     * Метод для клика по найденному результату
     * @author Мороз Сергей LuvMiit
     */
    public void clickOnHeader(){
        searchedResult.click();
    };
}
