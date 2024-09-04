package reqres;

import io.qameta.allure.Feature;
import org.example.avatarNames.AvatarNamesTestActions;
import org.example.dto.Response;
import org.example.dto.User;
import org.example.helpers.Assert;
import org.example.helpers.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.avatarNames.AvatarNamesTestActions.getResponse;
import static org.example.helpers.Properties.testsProperties;
import static org.example.utils.UniqueAvatarNamesChecker.checkAvatarNamesUnique;

public class Tests {
    /**
     * Метод тестирования на уникальность имен файлов
     * @author Мороз Сергей LuvMiit*/
    @Test
    public void fileNamesMustBeUnique(){
        Response response = getResponse(testsProperties.listUsersAPI());

        Assert.assertTrue(checkAvatarNamesUnique(
                response.getData().stream()
                        .map(User::getAvatar)
                        .collect(Collectors.toList())
                ),
                "Названия аватаров не уникальны");
    }


    /**
     * Метод тестирования работы метода проверки уникальности имен при не уникальных именах.
     * @param avatarLinks Список ссылок аватаров
     * @author Мороз Сергей LuvMiit
     * */
    @Feature("Тестирование работы метода проверки уникальности имен при не уникальных именах")
    @Test(dataProviderClass = DataProvider.class, dataProvider = "avatarLinksNotUniqueProvider")
    public void uniqueCheckerMustReturnFalseWhenNamesAreNotUnique(List<String> avatarLinks){
        org.testng.Assert.assertFalse(checkAvatarNamesUnique(avatarLinks), "Метод проверки уникальности говорит, " +
                "что аватары уникальны, хотя на самом деле не уникальны");
    }


    /**
     * Метод тестирования работы метода проверки уникальности имен при уникальных именах.
     * @param avatarLinks Список ссылок аватаров
     * @author Мороз Сергей LuvMiit
     * */
    @Feature("Тестирование работы метода проверки уникальности имен при уникальных именах")
    @Test(dataProviderClass = DataProvider.class, dataProvider = "avatarLinksUniqueProvider")
    public void uniqueCheckerMustReturnTrueWhenNamesAreUnique(List<String> avatarLinks){
        org.testng.Assert.assertTrue(checkAvatarNamesUnique(avatarLinks),"Метод проверки уникальности говорит, " +
                "что аватары не уникальны, хотя на самом деле уникальны");
    }
}
