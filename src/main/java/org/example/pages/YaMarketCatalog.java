package org.example.pages;

import com.codeborne.selenide.HoverOptions;
import com.codeborne.selenide.WebElementCondition;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class YaMarketCatalog extends BasePage{

    public YaMarketCatalog(WebDriver webDriver){
        super(webDriver);
    }
    public YaMarketCatalog(){}
    /**
     * Метод наведения курсора на вкладку
     * @param tabName Название вкладки
     * @return объект следующей страницы
     * @author Мороз Сергей LuvMiit*/
    @Step("Наведение курсора на вкладку '{tabName}'")
    public YaMarketCatalog hoverTab(String tabName){
        $x("//div[@data-zone-name='catalog-content']//ul[@role='tablist']")
                .shouldBe(visible)
                .shouldBe(enabled);
        $x("//div[@data-zone-name='catalog-content']//ul[@role='tablist']//span[contains(text(), '"+tabName+"')]")
                .shouldBe(clickable)
                .hover();

        return page(YaMarketCatalog.class);
    }

    /**
     * Метод
     * @param subChapterName Название подраздела
     * @return объект следующей страницы
     * @author Мороз Сергей LuvMiit*/
    @Step("Нажатие по подразделу '{subChapterName}'")
    public ChapterPage clickOnSubChapter(String subChapterName){

        $x("//li//a[contains(text(), '"+subChapterName+"')]")
                .shouldBe(visible)
                .shouldBe(enabled)
                .click();
        $x("//div[@data-zone-name=\"searchTitle\"]/*[contains(text(), 'Смартфоны')]")
                .shouldBe(visible)
                .shouldHave(text(subChapterName));
        return page(ChapterPage.class);
    }
}

