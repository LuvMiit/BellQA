package com.google;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {

    protected WebDriver chromeDriver;

    @BeforeEach
    public void beforeEach() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        chromeDriver = new ChromeDriver();
        chromeDriver
                .manage()
                .window()
                .maximize();

        chromeDriver
                .manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(30));
        chromeDriver
                .manage()
                .timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @AfterEach
    public void afterEach() {
        chromeDriver.quit();
    }
}
