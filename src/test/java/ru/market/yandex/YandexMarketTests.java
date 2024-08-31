package ru.market.yandex;

import com.google.BaseTest;
import io.qameta.allure.Feature;
import org.example.steps.YaMarketStepsAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.example.helpers.Properties.testProperties;

/**
 *
 * Класс тестов по заданию 1.4 для Яндекс Маркета
 * @author Мороз Сергей LuvMiit
 * */
public class YandexMarketTests extends BaseTest {
    @Feature("Тестирование Яндекс.Маркета")
    @DisplayName("Тестирование страницы ноутбуков на Яндекс.Маркет")
    @ParameterizedTest(name="{displayName}: {argumentsWithNames}")
    @MethodSource("org.example.helpers.DataProvider#providerYandex")
    public void mustOpenPageAndDoSomething(
            String title,
            String tabName,
            String subChapterName,
            int minValue,
            int maxValue,
            List<String> producerNames,
            int countResults){

        YaMarketStepsAll stepsAll = new YaMarketStepsAll(chromeDriver);
        stepsAll.openSite(testProperties.yandexMarketURL(), chromeDriver);
        stepsAll.goToCatalogAndHoverTab(tabName, subChapterName);
        stepsAll.findSubChapterAndCLick(subChapterName);
        stepsAll.enterMinValue(minValue);
        stepsAll.enterMaxValue(maxValue);
        stepsAll.chooseProducer(producerNames);
        stepsAll.checkCountResults(countResults);
        stepsAll.checkCostAndName(minValue, maxValue, producerNames);
        stepsAll.takeFirstName();

    }
}
