package org.example.helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;
/**
 * Класс - поставщик данных для тестов
 * @author Мороз Сергей LuvMiit*/
public class DataProvider {
    /**
     * Метод поставки данных для тестов
     * @return Поток аргументов
     * @author Мороз Сергей LuvMiit*/
    public static Stream<Arguments> selenideData(){
        return Stream.of(
                Arguments.of(
                        "Электроника",
                        "Смартфоны",
                        List.of("Apple"),
                        "iPhone"
                )
        );
    }
}
