package org.example.pages;

import org.example.util.VisibilityChecker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
/**
 * Класс-страница сайта банка
 * @author Мороз Сергей LuvMiit
 */
public class BankPage {
    /**
     * Переменная вебдрайвера
     * @author Мороз Сергей LuvMiit
     */
    private WebDriver chromeDriver;
    /**
     * Переменная-элемент "Текст"
     * @author Мороз Сергей LuvMiit
     */
    private WebElement rates;
    /**
     * Переменная-элемент "блок"
     * @author Мороз Сергей LuvMiit
     */
    private WebElement exchangeBlock;
    /**
     * Переменная-элемент "Таблица курсов валют"
     * @author Мороз Сергей LuvMiit
     */
    private WebElement exchangeTable;
    /**
     * Переменная-коллекция с строками таблицы
     * @author Мороз Сергей LuvMiit
     */
    List<WebElement> rowsInTable = new ArrayList<>();
    /**
     * Переменная проверки видимости объекта
     * @author Мороз Сергей LuvMiit
     */
    private VisibilityChecker visibilityChecker;
    /**
     * Переменная драйвера ожидания
     * @author Мороз Сергей LuvMiit
     */
    private WebDriverWait webDriverWait;

    public BankPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        visibilityChecker = new VisibilityChecker(chromeDriver);
        webDriverWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));

    }
    /**
     * Метод поиска блока
     * @param blockName Название искомого блока
     * @return Название найденного блока
     * @author Мороз Сергей LuvMiit
     */
    public String findExchangeBlock(String blockName) {
        visibilityChecker.visibilityWait("//div[@class='CurrencyExchange_currency-exchange-wrapper__K_gc4']//span[contains(text(), '"+blockName+"')]");
        exchangeBlock = chromeDriver.findElement(By.xpath("//div[@class='CurrencyExchange_currency-exchange-wrapper__K_gc4']//span[contains(text(), '"+blockName+"')]"));
        return exchangeBlock.getText();
    }
    /**
     * Метод поиска текста и клика на него
     * @param link Искомый текст
     * @author Мороз Сергей LuvMiit
     */
    public void findAndClickRates(String link){
        visibilityChecker.visibilityWait("//a[contains(text(), '"+link+"')]");
        rates = chromeDriver.findElement(
                By.xpath("//a[contains(text(),'"+link+"')]"));
        ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].scrollIntoView(true);", rates);
        rates.click();

    }
    /**
     * Метод поиска количества курсов валют, отображенных на странице
     * @return Количество курсов валют
     * @author Мороз Сергей LuvMiit
     */
    public int checkRatesPage(){
        visibilityChecker.visibilityWait("//table[@class='card-rates-table cards']");
        exchangeTable = chromeDriver.findElement(By.xpath("//table[@class='card-rates-table cards']"));
        ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].scrollIntoView(true);", exchangeTable);

        List<WebElement> rows = chromeDriver.findElements(By.xpath("//table[@class='card-rates-table cards']//tr"));

        for (int i = 1; i < rows.size()-15; i++) {
            rowsInTable.add(rows.get(i));//Убираю значения, сичтанные из скрытой таблицы, которая непонятно откуда взялась в верстке и заголовок
        }
        return rowsInTable.size();
    }

    /**
     * Метод поиска курсов продажи и покупки искомой валюты.
     * Подсчет разницы между курсами с целью определения какой из курсов больше или меьнше.
     * @param currency Название искомой валюты
     * @return Разница между курсами
     * @author Мороз Сергей LuvMiit
     */
    public double currencyExchange(String currency){
        visibilityChecker.visibilityWait("//tr[@class='card-rates-table__row']//td[@class='card-rates-table__cell card-rates-table__sale large-text']");
        visibilityChecker.visibilityWait("//tr[@class='card-rates-table__row']//td[@class='card-rates-table__cell card-rates-table__purchase large-text']");
        int rowIndex = 0;
        for (int i = 1; i < rowsInTable.size()-15; i++) {
            if(rowsInTable.get(i).getText().contains(currency)){
                rowIndex = i;
            }
        }
        List<WebElement> currencySaleRows = chromeDriver.findElements(By.xpath("//tr[@class='card-rates-table__row']//td[@class='card-rates-table__cell card-rates-table__sale large-text']"));
        double salePrice = Double.parseDouble(currencySaleRows.get(rowIndex).getText().replace(",", "."));

        List<WebElement> currencyPurchaseRows = chromeDriver.findElements(By.xpath("//tr[@class='card-rates-table__row']//td[@class='card-rates-table__cell card-rates-table__purchase large-text']"));
        double purchasePrice = Double.parseDouble(currencyPurchaseRows.get(rowIndex).getText().replace(",", "."));
        return purchasePrice-salePrice;

    }
}
