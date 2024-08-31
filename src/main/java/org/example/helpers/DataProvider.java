package org.example.helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class DataProvider {
    public static Stream<Arguments> providerYandex(){
        return Stream.of(
                Arguments.of(
                        "Интернет-магазин Яндекс Маркет — покупки с быстрой доставкой",
                        "Электроника",
                        "Ноутбуки",
                        10000,
                        30000,
                        List.of("HP","Lenovo"),
                        12
                )
        );
    }
}
