package com.google;

import org.example.pages.MainPage;
import org.example.pages.PageWithResults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MyTests extends BaseTest{

    @ParameterizedTest
    @CsvSource({"Гладиолус"})
    public void mustOpenMainPageAndShowResults(String word){// Задание 1.1
        chromeDriver.get("https://www.google.com");
        Assertions.assertTrue(chromeDriver.getTitle().contains("Google"), "Окно с title "+chromeDriver.getTitle()+" не содержит 'Google'");
        MainPage mainPage = new MainPage(chromeDriver);
        mainPage.find(word);

    }

    @ParameterizedTest
    @CsvSource({"Открытие, 'Банк Открытие: кредит наличными, ипотека, кредитные и ...', Все курсы"})
    public void mustOpenMainPageAndDoSomeActions(String searchedWord, String title, String link){
        chromeDriver.get("https://www.google.com");
        Assertions.assertTrue(chromeDriver.getTitle().contains("Google"), "Вкладка с title "+chromeDriver.getTitle()+" не открылось");

        PageWithResults pageWithResults = new PageWithResults(chromeDriver);
        pageWithResults.find(searchedWord);
//        Assertions.assertFalse(pageWithResults.getResults().isEmpty(), "Результатов не найдено");
        Assertions.assertTrue(pageWithResults.findHeader(title), "Хэдера "+title+" не найдено");
        Assertions.assertTrue(chromeDriver.getTitle().contains("Банк Открытие"), "Вкладка с title "+chromeDriver.getTitle()+ " не открылось");
    }

}
