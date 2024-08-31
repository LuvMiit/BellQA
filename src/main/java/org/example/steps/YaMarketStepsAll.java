package org.example.steps;

import io.qameta.allure.Step;
import org.example.pageForYandexMarket.ChapterPage;
import org.example.pageForYandexMarket.YaMainPage;
import org.example.util.ExpectedConditionsChecker;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class YaMarketStepsAll {

    private WebDriver chromeDriver;


    public YaMarketStepsAll(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
    }
    /**
     * Метод перехода на сайт
     * @param chromeDriver веб-драйвер
     * @param url - ссылка на сайт
     * @author Мороз Сергей LuvMiit
     * */
    @Step("Переход на сайт {url}")
    public void openSite(String url, WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
        this.chromeDriver.get(url);
    }

    /**
     * Метод перехода в каталог и наведения курсора на вкладку
     * @param tabName название вкладки
     * @author Мороз Сергей LuvMiit
     * */
    @Step("Переход в каталог и наведение курсора на вкладку '{tabName}'")
    public void goToCatalogAndHoverTab(String tabName, String subChapterName){
        YaMainPage yaMainPage = new YaMainPage(chromeDriver);
        yaMainPage.goToCatalog();

        yaMainPage.hoverTab(tabName, subChapterName);
    }
    /**
     * Метод перехода в подраздел вкладки в каталоге
     * @param subChapterName название подраздела
     * @author Мороз Сергей LuvMiit
     * */
    @Step("Переход в раздел '{subChapterName}'")
    public void findSubChapterAndCLick(String subChapterName){
        YaMainPage yaMainPage = new YaMainPage(chromeDriver);
        yaMainPage.findAndClickSubChapter(subChapterName);
    }

    /**
     * Метод ввода минимального значения цены
     * @param minValue минимальное значение цены
     * @author Мороз Сергей LuvMiit
     * */
    @Step("Ввод минимальной стоимости: {minValue}")
    public void enterMinValue(int minValue){
        ChapterPage chapterPage = new ChapterPage(chromeDriver);
        chapterPage.enterMinValue(minValue);
    }

    /**
     * Метод ввода максимального значения цены
     * @param maxValue максимальное значение цены
     * @author Мороз Сергей LuvMiit
     * */
    @Step("Ввод максимальной стоимости: {maxValue}")
    public void enterMaxValue(int maxValue){
        ChapterPage chapterPage = new ChapterPage(chromeDriver);
        chapterPage.enterMaxValue(maxValue);
    }

    /**
     * Метод раскрытия списка производителей
     * @author Мороз Сергей LuvMiit
     * */
    @Step("Раскрытие списка производителей")
    public void showAllProducers(){
        ChapterPage chapterPage = new ChapterPage(chromeDriver);
        chapterPage.openProducers();
    }

    /**
     * Метод ввода наименования производителя и выбора его из списка
     * @param producerNames - список наименований производителей
     * @author Мороз Сергей LuvMiit
     * */
    @Step("Ввод и выбор производителя: {producerName}")
    public void chooseProducer(List<String> producerNames){
        ChapterPage chapterPage = new ChapterPage(chromeDriver);
        for(String producerName: producerNames){
            showAllProducers();
            chapterPage.enterProducerNameFilter(producerName);
            chapterPage.chooseProducer(producerName);
        }
    }

    @Step("Проверка, количество результатов после фильтрации на первой странице больше {expectedCountResults} шт.")
    public void checkCountResults(int expectedCountResults){
        ChapterPage chapterPage = new ChapterPage(chromeDriver);
        chapterPage.checkCountResults(expectedCountResults);

    }

    @Step("Проверка совпадений результатов с параметрами фильтрации. " +
            "Стоимость: от {costMin} до {costMax}, Производители: {producerNames}")
    public void checkCostAndName(int costMin, int costMax,List<String> producerNames){
        ChapterPage chapterPage = new ChapterPage(chromeDriver);
        chapterPage.checkCostAndName(costMin, costMax,  producerNames);
    }

    @Step("Запоминание названия первого результата на странице и поиск через поисковую строку")
    public void takeFirstName(){
        ChapterPage chapterPage = new ChapterPage(chromeDriver);
        chapterPage.takeFirstName();
    }

}
