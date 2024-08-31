package org.example.pageForYandexMarket;

import org.example.helpers.Assertions;
import org.example.util.ExpectedConditionsChecker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.example.helpers.CustomWait.waitWhileNotOpen;
import static org.example.helpers.CustomWait.waitWhileTabNotHover;
import static org.example.helpers.Properties.testProperties;

/**
 * Класс-главная страница Яндекс-маркета
 * @author Мороз Сергей LuvMiit*/

public class YaMainPage {

    private static final String XPATH_TO_CATALOG = "//button//span[contains(text(), 'Каталог')]";
    private static final String XPATH_TO_ELEMENTS_IN_CATALOG = "//div[@role='dialog']//ul[@role='tablist']//li";
    private static final String XPATH_TO_LOAD_FLAG = "//div[@role='dialog']//ul[@role='tablist']//li[@aria-selected='true']";
    private final static String XPATH_TO_CHAPTER_NAME ="//div[@data-zone-name='searchTitle']/*";

    /**
     * Переменная-кнопка
     * @author Мороз Сергей LuvMiit*/
    private WebElement button;
    /**
     * Переменная-вкладка в каталоге
     * @author Мороз Сергей LuvMiit*/
    private WebElement tab;
    /**
     * Переменная - подраздел во вкладке в каталоге
     * @author Мороз Сергей LuvMiit
     * */
    private WebElement subChapter;

    private ExpectedConditionsChecker expectedConditionsChecker;
    private WebDriver chromeDriver;

    public YaMainPage(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
        expectedConditionsChecker = new ExpectedConditionsChecker(chromeDriver);
    }
    /**
     * Метод перехода на вкладку "Каталог"
     * @author Мороз Сергей LuvMiit*/
    public void goToCatalog(){
        expectedConditionsChecker.visibilityWait("//div[@class='page']");
        button = chromeDriver.findElement(By.xpath(XPATH_TO_CATALOG));
        waitWhileNotOpen(chromeDriver, testProperties.defaultTimeout(), "//body[@data-mainmenu='show']", button);
    }
    /**
     * Метод наведения курсора на вкладку
     * @param tabName Название вкладки
     * @author Мороз Сергей LuvMiit
     * */
    public void hoverTab(String tabName, String subChapterName){
//        expectedConditionsChecker.visibilityOfElementLocated(XPATH_TO_ELEMENTS_IN_CATALOG+"//span[contains(text(), '"+tabName+"')]");
        expectedConditionsChecker.visibilityOfAllElementsLocated(XPATH_TO_ELEMENTS_IN_CATALOG);
        System.out.println("прогрузилась");
        tab = chromeDriver.findElement(By.xpath(XPATH_TO_ELEMENTS_IN_CATALOG+"//span[contains(text(), '"+tabName+"')]"));
        System.out.println(tab.getText());
        Actions action = new Actions(chromeDriver);
        action.moveToElement(tab).perform();
        System.out.println("Нажалась");
    }
    /**
     * Метод поиска подраздела и клика по нему
     * @param subChapterName Название подраздела
     * @author Мороз Сергей LuvMiit*/
    public void findAndClickSubChapter(String subChapterName){
        String subChapterXPath = "//ul[@data-autotest-id='subItems']//a[contains(text(), '"+subChapterName+"')]";
        expectedConditionsChecker.visibilityWait(subChapterXPath);
        subChapter = chromeDriver.findElement(By.xpath(subChapterXPath));
        subChapter.click();
        String chapterNameXPATH = "//div[@data-zone-name='searchTitle']/*";
        expectedConditionsChecker.visibilityWait(chapterNameXPATH);
        String chapterName = chromeDriver.findElement(By.xpath(chapterNameXPATH)).getText();
        Assertions.assertChapter(chapterName, subChapterName, "Переход в раздел "+subChapterName+" не осуществлен");
    }
}
