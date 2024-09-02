package ru.yandexMarket;

import io.qameta.allure.Feature;
import org.example.pages.YaMarketMainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static org.example.helpers.Properties.testsProperties;

public class Tests extends BaseTest{

    @Feature("Тестирования Яндекс.Маркета")
    @DisplayName("Тестирование страницы товаров определенной категории на Яндекс.Маркет")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("org.example.helpers.DataProvider#selenideData")
    public void selenideTest(
            String tabName,
            String subChapterName,
            List<String> producerNames,
            String model
    ){
        open(testsProperties.yandexMarketURL(), YaMarketMainPage.class)
                .goToCatalog()
                .hoverTab(tabName)
                .clickOnSubChapter(subChapterName)
                .setProducerName(producerNames)
                .checkCompliance(model);

    }
}
