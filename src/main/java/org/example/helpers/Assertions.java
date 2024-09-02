package org.example.helpers;

import io.qameta.allure.Step;
/**
 * Класс переопределенных "ассертов" со степами
 * @author Мороз Сергей LuvMiit*/
public class Assertions {
    /**
     * Метод проверки соответствия названия фильтру
     * @param realName фактическая модель
     * @param expectedPartOfName ожидаемая модель
     * @param message сообщение в случае ошибки
     * @author Мороз Сергей LuvMiit*/
    @Step("Проверка названия {realName} на соответствие фильтру {expectedPartOfName}")
    public static void assertContains(String realName, String expectedPartOfName, String message){
        org.junit.jupiter.api.Assertions.assertTrue(realName.toLowerCase().contains(expectedPartOfName.toLowerCase()), message);
    }
}
