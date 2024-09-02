package ru.yandexMarket;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLog;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.example.allure.selenide.CustomAllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    public static void setup(){
        Configuration.pageLoadStrategy = "none";
        SelenideLogger
                .addListener(
                    "CustomAllureSelenide",
                    new CustomAllureSelenide()
                            .screenshots(true)
                            .savePageSource(true));
    }
}
