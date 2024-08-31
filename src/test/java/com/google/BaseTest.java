package com.google;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.example.helpers.CustomWait.implicitlyWait;
import static org.example.helpers.Properties.testProperties;

/**
 * Базовый класс тестов с начальными настройками
 * @author Мороз Сергей LuvMiit
 */
public class BaseTest {
    /**
     * Переменная вебдрайвер
     * @author Мороз Сергей LuvMiit
     */
    protected WebDriver chromeDriver;
    /**
     * Метод перед каждым тестом устанавливает настройки расположения "chromeDriver".
     * Убиарет ожидание загрузки всей страницы (из-за блокировки ютуба страница бесконечно грузится и падает в timeOutException).
     * Устанавливает максимальный размер окна и ожидание загрузки объекта.
     * @author Мороз Сергей LuvMiit
     */
    @BeforeEach
    public void beforeEach() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        chromeDriver = new ChromeDriver(options);

        chromeDriver
                .manage()
                .window()
                .maximize();
        implicitlyWait(chromeDriver, testProperties.defaultTimeout());

    }
    /**
     * Метод после каждого теста закрывает браузер и все вкладки.
     * @author Мороз Сергей LuvMiit
     */
//    @AfterEach
//    public void afterEach() {
//        chromeDriver.quit();
//    }
}
