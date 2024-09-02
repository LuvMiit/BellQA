package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class YaMarketMainPage extends BasePage{
    public YaMarketMainPage(WebDriver webDriver){
        super(webDriver);
    }
    public YaMarketMainPage(){}

    /**
     * Метод перехода в каталог
     * @author Мороз Сергей LuvMiit*/
    @Step("Переход в каталог")
    public YaMarketCatalog goToCatalog(){
        $x("//div[@data-zone-name='catalog']").shouldBe(clickable).click();

        return page(YaMarketCatalog.class);

    }

}
