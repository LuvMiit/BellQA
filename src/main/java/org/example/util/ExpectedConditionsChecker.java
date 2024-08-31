package org.example.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.helpers.Properties.testProperties;

/**
 * Утилитарный класс отслеживания состояния объекта
 * @author Мороз Сергей LuvMiit
 */
public class ExpectedConditionsChecker {
    /**
     * Переменная драйвер ожидания
     * @author Мороз Сергей LuvMiit
     */
    private WebDriverWait webDriverWait;
    public ExpectedConditionsChecker(WebDriver chromeDriver) {
        webDriverWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(testProperties.defaultTimeout()));

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
    public void visibilityOfElementLocated(String xpath){
        webDriverWait
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath(xpath)));
    }
    public void visibilityOfAllElementsLocated(String xpath){
        webDriverWait
                .until(ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
    }

    public void waitTitleIs(String title){
        webDriverWait
                .until(ExpectedConditions
                        .titleIs(title));

    }

    public void waitClickable(String xpath){
        webDriverWait
                .until(ExpectedConditions
                        .elementToBeClickable(By.xpath(xpath)));
    }
    public void waitClickable(WebElement element){
        webDriverWait
                .until(ExpectedConditions
                        .elementToBeClickable(element));
    }
    public void waitPresence(String xpath){
        webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

}
