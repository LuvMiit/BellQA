package org.example.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
/**
 * Утилитарный класс отслеживания видимости объекта
 * @author Мороз Сергей LuvMiit
 */
public class VisibilityChecker {
    /**
     * Переменная драйвер ожидания
     * @author Мороз Сергей LuvMiit
     */
    private WebDriverWait webDriverWait;
    public VisibilityChecker(WebDriver chromeDriver) {
        webDriverWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));

    }
    /**
     * Метод ожидает видимости объекта
     * @param xpath Селектор Xpath к объекту
     * @author Мороз Сергей LuvMiit
     */
    public void visibilityWait(String xpath){
        webDriverWait
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath(xpath)));
    }
}
