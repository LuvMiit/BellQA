package org.example.pagesForBankAndWiki;

import org.example.util.ExpectedConditionsChecker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;

/**
 * Класс-страница википедии
 * @author Мороз Сергей LuvMiit
 */
public class WikiPage {
    /**
     * Переменная вебдрайвера
     * @author Мороз Сергей LuvMiit
     */
    private WebDriver chromeDriver;
    /**
     * Переменная проверки видимости объекта
     * @author Мороз Сергей LuvMiit
     */
    private ExpectedConditionsChecker visibilityChecker;
    /**
     * Переменная драйвера ожидания
     * @author Мороз Сергей LuvMiit
     */
    private WebDriverWait webDriverWait;
    /**
     * Переменная-массив строк в таблице
     * @author Мороз Сергей LuvMiit
     */
    private String[] rows;

    public WikiPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        visibilityChecker = new ExpectedConditionsChecker(chromeDriver);

    }
    /**
     * Метод поиска позиции в таблице преподавателя
     * @param teacher Имя Отчество искомого преподавателя
     * @param tableName Название таблицы в которой искать
     * @return Позиция преподавателя.
     * "-1" - если преподаватель отсутствует в таблице.
     * @author Мороз Сергей LuvMiit
     */
    public int checkPositionTeacher(String teacher, String tableName){
        visibilityChecker.visibilityWait("//table[@class='wikitable']//caption[contains(text(),'"+tableName+"')]");
        WebElement table = chromeDriver.findElement(By.xpath("//table[@class='wikitable']"));
        rows = table.getText().split("\n");
        String[] rowsWithoutHeaders = Arrays.copyOfRange(rows, 2, rows.length-1);
        for(int i=0;i<rowsWithoutHeaders.length;i++){
            if(rowsWithoutHeaders[i].contains(teacher)){
                return i;
            }
        }
        return -1;
    }
}
