package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {

    protected WebDriver chromeDriver;
    protected WebElement searchArea;

    public MainPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.searchArea = chromeDriver.findElement(By.xpath("//textarea[@name='q']"));
    }

    public void find(String word){
        searchArea.click();
        searchArea.sendKeys(word);
        searchArea.sendKeys(Keys.ENTER);
    }
}
