package com.google;

import org.example.pagesForBankAndWiki.BankPage;
import org.example.pagesForBankAndWiki.MainPage;
import org.example.pagesForBankAndWiki.PageWithResults;
import org.example.pagesForBankAndWiki.WikiPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Класс тестов по заданию 1
 * @author Мороз Сергей LuvMiit
 */
public class MyTests extends BaseTest{
    /**
     * Метод открывает браузер и ищет необходимое слово через поисковую строку.
     * @param word Искомое слово
     * @author Мороз Сергей LuvMiit
     */
    @ParameterizedTest
    @CsvSource({"Гладиолус"})
    public void mustOpenMainPageAndShowResults(String word){// Задание 1.1
        chromeDriver.get("https://www.google.com");
        Assertions.assertTrue(chromeDriver.getTitle().contains("Google"), "Окно с title "+chromeDriver.getTitle()+" не содержит 'Google'");
        MainPage mainPage = new MainPage(chromeDriver);
        mainPage.find(word);

    }

    /**
     * Метод открывает браузер и ищет необходимое слово через поисковую строку.
     * Кликает по необходимому результату.
     * Проверяет наличие необходимого блока.
     * Переходит по текстовому полю внутри.
     * Проверяет количество строк в таблице.
     * Проверяет разгницу значений в строке таблицы.
     *
     * @param searchedWord Искомое слово
     * @param header Название результата
     * @param link Текстовое поле для клика
     * @param title Название открытой вкладки
     * @param titleOpenedPage Название открытой вкладки
     * @param blockName Название искомого блока
     * @param currency1 Название валюты
     * @param currency2 Название валюты
     * @author Мороз Сергей LuvMiit
     */
    @ParameterizedTest
    @CsvSource({"Открытие, " +
            "'Банк Открытие: кредит наличными, ипотека, кредитные и ...', " +
            "Все курсы, " +
            "Банк Открытие, Курсы обмена, " +
            "Курс обмена, " +
            "USD, EUR"})
    public void mustOpenMainPageAndDoSomeActions(
            String searchedWord,
            String header,
            String link,
            String title,String titleOpenedPage,
            String blockName,
            String currency1, String currency2
    ){
        chromeDriver.get("https://www.google.com");
        Assertions.assertTrue(chromeDriver.getTitle().contains("Google"), "Вкладка с title "+chromeDriver.getTitle()+" не открылось");

        PageWithResults pageWithResults = new PageWithResults(chromeDriver);

        pageWithResults.find(searchedWord);
        Assertions.assertFalse(pageWithResults.getResults().isEmpty(), "Результатов не найдено");

        Assertions.assertTrue(pageWithResults.findHeader(header).contains(header), "Результата с header"+header+"не найдено");
        pageWithResults.clickOnHeader();
        Assertions.assertTrue(chromeDriver.getTitle().contains(title), "Вкладка с title "+title+ " не открылось");

        BankPage bankPage = new BankPage(chromeDriver);

        Assertions.assertTrue(bankPage.findExchangeBlock(blockName).contains(blockName),"Блок "+blockName+" отсутствует'");

        bankPage.findAndClickRates(link);
        Assertions.assertTrue(chromeDriver.getTitle().contains(titleOpenedPage), "Элемента "+titleOpenedPage+" не найдено");

        Assertions.assertTrue(bankPage.checkRatesPage()>=3, "На странице менее 3-х курсов валют");

        Assertions.assertTrue(bankPage.currencyExchange(currency1)>0, "У курса "+currency1+" продажа больше или равна покупке");
        Assertions.assertTrue(bankPage.currencyExchange(currency2)>0, "У курса "+currency2+" продажа больше или равна покупке");

    }

    /**
     * Метод открывает браузер и ищет необходимое слово через поисковую строку.
     * Кликает по необходимому результату.
     * Проверяет наличие необходимого блока.
     * Переходит по текстовому полю внутри.
     * Проверяет количество строк в таблице.
     * Проверяет разгницу значений в строке таблицы.
     *
     * @param searchedWord Искомое слово
     * @param header Название результата
     * @param title Название открытой вкладки
     * @param tableName Название таблицы
     * @param teacherNP1 Имя Отчество преподавателя
     * @param teacherNP2 имя Отчество преподавателя
     */
    @ParameterizedTest
    @CsvSource({"Таблица википедия," +
            "Таблица, " +
            "Википедия, " +
            "Преподаватели кафедры программирования, " +
            "Сергей Владимирович, Сергей Адамович"})
    public void mustOpenWikipediaAndClickOnTableHeader(
            String searchedWord,
            String header,
            String title,
            String tableName,
            String teacherNP1, String teacherNP2){
        chromeDriver.get("https://www.google.com");
        Assertions.assertTrue(chromeDriver.getTitle().contains("Google"), "Вкладка с title "+chromeDriver.getTitle()+" не открылась");

        PageWithResults pageWithResults = new PageWithResults(chromeDriver);

        pageWithResults.find(searchedWord);
        Assertions.assertFalse(pageWithResults.getResults().isEmpty(), "Результатов не найдено");
        Assertions.assertTrue(pageWithResults.findHeader(header).contains(header), "Результата с header"+header+"не найдено");
        pageWithResults.clickOnHeader();

        WikiPage wikiPage = new WikiPage(chromeDriver);
        Assertions.assertTrue(chromeDriver.getTitle().contains(title), "Вкладка с title "+title+" не открылась");

        wikiPage.checkPositionTeacher(teacherNP1,tableName);
        int teacher1Result = wikiPage.checkPositionTeacher(teacherNP1, tableName);
        int teacher2Result = wikiPage.checkPositionTeacher(teacherNP2, tableName);

        Assertions.assertFalse(teacher1Result == -1, teacherNP1 + " не находится в таблице");
        Assertions.assertFalse(teacher2Result == -1, teacherNP2 + " не находится в таблице");

        Assertions.assertEquals(0, teacher1Result, teacherNP1 + " находится не в конце таблицы");
        Assertions.assertEquals(6, teacher2Result, teacherNP2 + " находится не в конце таблицы");


    }

}
