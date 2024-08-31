package org.example.helpers;

import io.qameta.allure.Step;

import java.util.List;

public class Assertions {
    @Step("Проверка перехода в раздел '{expectedChapter}'")
    public static void assertChapter(String chapter, String expectedChapter, String message){
        org.junit.jupiter.api.Assertions.assertTrue(chapter.contains(expectedChapter), message);
    }
    @Step("Проверка количества результатов на странице")
    public static void assertCount(boolean expression, String message){
        org.junit.jupiter.api.Assertions.assertTrue(expression, message);
    }

    @Step("Проверка на соответствие фильтру с производителем по названиям: '{expectedProducerName}'")
    public static void assertContains(boolean expression, String message){
        org.junit.jupiter.api.Assertions.assertTrue(expression, message);
    }

    @Step("Проверка на соответствие заданному диапазону цен: от {costMin} до {costMax}")
    public static void assertInRange(int costMin, int costMax, int currentValue, String message){
        org.junit.jupiter.api.Assertions.assertTrue(currentValue>=costMin && currentValue<=costMax, message);
    }

    @Step("Проверка на наличие результата на первой странице")
    public static void assertContainsOnFirstPage(boolean expression, String message){
        org.junit.jupiter.api.Assertions.assertTrue(expression, message);
    }
}
