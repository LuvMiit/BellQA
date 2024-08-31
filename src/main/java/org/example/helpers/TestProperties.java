package org.example.helpers;

import org.aeonbits.owner.Config;
import org.openqa.selenium.WebDriver;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:src/main/resources/tests.properties"
}
)
public interface TestProperties extends Config {
    @Config.Key("yandex.market.url")
    String yandexMarketURL();

    @Config.Key("default.timeout")
    int defaultTimeout();

    @Config.Key("default.timeout.to.load")
    int defaultTimeoutToLoad();
}
