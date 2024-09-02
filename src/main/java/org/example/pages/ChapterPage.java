package org.example.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.example.helpers.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ChapterPage extends BasePage{
    /**
     * переменная-путь к названиям товаров со всех страниц
     * @author Мороз Сергей LuvMiit*/
    private static final String XPATH_TO_RESULTS_NAMES = "//div[@data-auto='SerpList']/div[not(descendant::div[@data-apiary-widget-name=\"@light/SearchIncut\"])]//div[@data-baobab-name='title']//span";
    /**
     * переменная-путь к кнопке "Показать всё"
     * @author Мороз Сергей LuvMiit*/
    private static final String XPATH_TO_SHOW_ALL = "//span[contains(text(), 'Показать всё')]";
    /**
     * переменная-путь к кружочку загрузки страницы после изменения фильтров
     * @author Мороз Сергей LuvMiit*/
    private static final String XPATH_TO_LOAD_ELEMENT = "//div[@data-auto=\"preloader\"]//span[@data-auto='spinner']";
    /**
     * переменная-путь к реузльтатам поиска фильтра производителя
     * @author Мороз Сергей LuvMiit*/
    private static final String XPATH_TO_RESULTS_FOR_PRODUCER = "//div[@data-zone-name='Filter' and descendant::fieldset//legend//div/*[contains(text(), 'Производитель')]]//div[@data-baobab-name='filterValue']";
    /**
     * переменная-путь к месту ввода названия производителя
     * @author Мороз Сергей LuvMiit*/
    private static final String XPATH_TO_INPUT_SEARCH_FOR_FILTER_PRODUCER = "//input[@placeholder='Найти']";
    public ChapterPage(WebDriver webDriver) {
        super(webDriver);
    }
    public ChapterPage() {}
    /**
     * Метод задания названия производителя
     * @param producerName Список названий производителей
     * @return объект следующей страницы
     * @author Мороз Сергей LuvMiit*/
    @Step("Задание производителя {producerName}")
    public ChapterPage setProducerName(List<String> producerName){
        for(String name: producerName){
            showAllProducers();
            $x(XPATH_TO_INPUT_SEARCH_FOR_FILTER_PRODUCER)
                    .shouldBe(visible)
                    .click();
            $x(XPATH_TO_INPUT_SEARCH_FOR_FILTER_PRODUCER)
                    .sendKeys(name);
            ElementsCollection elements = $$x(XPATH_TO_RESULTS_FOR_PRODUCER)
                    .shouldHave(sizeGreaterThan(0));
            elements.get(0)
                    .shouldHave(text(name))
                    .click();
            $$x(XPATH_TO_LOAD_ELEMENT)//ожидание пока обновится страница после выбора производителя
                    .shouldHave(size(1), Duration.ofSeconds(10));
            $$x(XPATH_TO_LOAD_ELEMENT)
                    .shouldHave(size(0), Duration.ofSeconds(10));

        }
        return page(ChapterPage.class);
    }
    /**
     * Метод клика по текстовому полю "Показать всё"
     * @return объект следующей страницы
     * @author Мороз Сергей LuvMiit*/
    @Step("Клик по текстовому полю 'Показать всё'")
    private ChapterPage showAllProducers(){
        $x(XPATH_TO_SHOW_ALL)
                .shouldBe(visible)
                .shouldHave(clickable)
                .click();
        return page(ChapterPage.class);
    }
    /**
     * Метод проверки соответствия фильтру производителя по модели
     * @param model название модели устройства
     * @return объект следующей страницы
     * @author Мороз Сергей LuvMiit*/
    @Step("Проверка, что в выборку попали только устройства модели {model}")
    public ChapterPage checkCompliance(String model){
        List<String> allNames =
                $$x(XPATH_TO_RESULTS_NAMES)
                        .shouldHave(sizeGreaterThan(0))
                        .texts();
        for(String name:allNames){
            Assertions.assertContains(name, model, "Результат"+name+" не соответствует фильтру "+model);
        }
        return page(ChapterPage.class);
    }
}
