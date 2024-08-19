package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PageWithResults extends MainPage{
    private List<WebElement> results;
    private WebElement searchedResult;
    private WebDriverWait webDriverWait;

    public PageWithResults(WebDriver chromeDriver) {
        super(chromeDriver);
        webDriverWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(30));

    }
    public boolean findHeader (String title) {
//        System.out.println("Вошел в метод");
//        searchedResult =webDriverWait
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(), 'Банк Открытие: кредит наличными, ипотека, кредитные и ...')]")));
//        searchedResult.click();
//        System.out.println("кликнул");
        searchedResult = chromeDriver.findElement(By.xpath("//textarea[@name='q']"));
        searchedResult.sendKeys("fcgvhbjnk");
        return true;
    }
}
