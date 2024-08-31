package org.example.helpers;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.example.helpers.Properties.testProperties;

public class CustomWait {

    public static int implicitlyWait = testProperties.defaultTimeout();
    public static int pageLoadWait = testProperties.defaultTimeout();
    public static int setScriptWait = testProperties.defaultTimeout();

    public static void implicitlyWait(WebDriver chromeDriver, int defaultTimeout){
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(defaultTimeout));
    }
    public static void pageLoadWait(WebDriver chromeDriver, int defaultTimeout){
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(defaultTimeout));
    }
    public static void setScriptWait(WebDriver chromeDriver, int defaultTimeout){
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(defaultTimeout));
    }


    private static void sleep(double sec){
        try{
            Thread.sleep((long) (sec*1000));
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }/**
     * Метод ожидания когда при нажатии на кнопку "Каталог" откроется вкладка с категориями.
     * (На данный момент пока страница маркета не прогрузится кнопка ничего не открывает)
     *
     * @param chromeDriver - драйвер браузера
     * @param timeWaitToOpen - время на ожидание загрузки в секундах
     * @param xpath - путь к элементу, который должен прогрузиться
     * @param btn - кнопка, при нажатии по которой должен прогрузиться каталог
     * @author Мороз Сергей LuvMiit*/
    public static void waitWhileNotOpen(WebDriver chromeDriver,int timeWaitToOpen, String xpath, WebElement btn){
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        btn.click();
        for(int i=0;i<= timeWaitToOpen;i++){

            if(!chromeDriver.findElements(By.xpath(xpath)).isEmpty()){
                break;
            }
            btn.click();
            if(i==timeWaitToOpen*1000){
                Assertions.fail("Кнопка не стала открывать окно после " + timeWaitToOpen + " секунд");
            }
            sleep(1);

        }
    }
    /**
     * Метод лжидания прогрузки элемента (кружок загрузки)
     * @param chromeDriver веб-драфйвер
     * @param timeWaitToLoad время ожидания в секундах
     * @param xpath путь к отслеживаемому элементу
     * @author Мороз Сергей LuvMiit
     * */
    public static void waitWhileLoad(WebDriver chromeDriver, String xpath){
        chromeDriver
                .manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(0));
        for(int i=0; i< testProperties.defaultTimeoutToLoad(); i++){
            if(!chromeDriver.findElements(By.xpath(xpath)).isEmpty()){
                for(int j=0;;j++){
                    if(chromeDriver.findElements(By.xpath(xpath)).isEmpty()){
                        break;
                    }
                    if(j+1==testProperties.defaultTimeoutToLoad()){
                        Assertions.fail("Элемент " + xpath + "Не пропал за "+testProperties.defaultTimeoutToLoad()+" секунд");
                    }
                    sleep(1);
                }
            }
            sleep(1);
        }
    }
    /**
     * Метод ожидания пока обновится список полсле введения фильтра
     * @param chromeDriver веб-драйвер
     * @param timeWaitToUpdate время ожидания
     * @param xpath путь к элементам
     * @param initialSize инзначальный размер результатов
     * @author Мороз Сергей LuvMiit*/
    public static void waitWhileDataUpdate(WebDriver chromeDriver, String xpath, int initialSize){
        chromeDriver
                .manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(0));
        List<WebElement> localItems;
        for (int i=0;i<testProperties.defaultTimeoutToLoad();i++){
            localItems = chromeDriver.findElements(By.xpath(xpath));
            if(localItems.size()<initialSize){
                break;
            }
            if(i+1==testProperties.defaultTimeoutToLoad()){
                Assertions.fail("Результаты в " + xpath + "Не обновились за "+testProperties.defaultTimeoutToLoad()+" секунд");
            }
            sleep(0.5);
        }
    }
    public static void waitWhileTabNotHover(WebDriver chromeDriver, String xpath, WebElement tab){
        chromeDriver
                .manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(0));
        for(int i=0; i< testProperties.defaultTimeout();i++){
            if(chromeDriver.findElements(By.xpath(xpath)).isEmpty()){
                Actions actions = new Actions(chromeDriver);
                actions.moveToElement(tab).perform();
            }else{
                System.out.println("нажалось");
                break;
            }
            System.out.println("не нажалось");
            if(i+1== testProperties.defaultTimeout()){
                Assertions.fail("Вкладка " + tab.getText() + " не активировалась за "+testProperties.defaultTimeout()+" секунд");
            }
            sleep(0.5);
        }
    }
}
