package org.example.helpers;

import io.qameta.allure.Step;

public class Assert {

    @Step("Проверка уникальности имен аватаров")
    public static void assertTrue(boolean expression, String message){
        org.testng.Assert.assertTrue(expression, message);
    }
}
