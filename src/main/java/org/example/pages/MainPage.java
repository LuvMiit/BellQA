package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * Класс-главная страница гугла
 * @author Мороз Сергей LuvMiit
 */
public class MainPage {
    /**
     * Переменная вебдрайвера
     * @author Мороз Сергей LuvMiit
     */
    protected WebDriver chromeDriver;
    /**
     * Переменная, представляющая элемент "Поисковая строка"
     * @author Мороз Сергей LuvMiit
     */
    protected WebElement searchArea;

    public MainPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.searchArea = chromeDriver.findElement(By.xpath("//textarea[@name='q']"));
    }
    /**
     * Метод осуществляет поиск заданного слова. Слово вводится в поисковую строку и нажимается клавиша "Enter"
     * @param word слово для введения в поисковую строку
     * @author Мороз Сергей LuvMiit
     */
    public void find(String word){
        searchArea.click();
        searchArea.sendKeys(word);
        searchArea.sendKeys(Keys.ENTER);
    }

}
