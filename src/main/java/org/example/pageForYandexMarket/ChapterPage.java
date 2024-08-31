package org.example.pageForYandexMarket;

import io.qameta.allure.Step;
import org.example.helpers.Assertions;
import org.example.util.ExpectedConditionsChecker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.helpers.CustomWait.waitWhileDataUpdate;
import static org.example.helpers.CustomWait.waitWhileLoad;

public class ChapterPage {
    private WebDriver chromeDriver;
    private ExpectedConditionsChecker expectedConditionsChecker;

    /**
     * Переменная - элемент-загрузка
     * @author Мороз Сергей LuvMiit
     * */
    private WebElement loadElement;
    /**
     * Переменная - элемент-строка для раскрытия списка
     * @author Мороз Сергей LuvMiit
     * */
    private WebElement showAllElement;
    /**
     * Переменная - окно ввода минимального значения
     * @author Мороз Сергей LuvMiit
     * */
    private WebElement minValueInput;
    /**
     * Переменная - окно ввода максимального значения
     * @author Мороз Сергей LuvMiit
     * */
    private WebElement maxValueInput;
    /**
     * Переменная - строка ввода названия производителя
     * @author Мороз Сергей LuvMiit
     * */
    private WebElement producerNameInput;
    /**
     * Переменная - коллекция результатов поиска по имени производителя
     * @author Мороз Сергей LuvMiit
     * */
    private List<WebElement> producerItems;
    private List<WebElement> firstPageFilteredResults;
    /**
     * Переменная - коллекция результатов поиска после фильтрации
     * @author Мороз Сергей LuvMiit
     * */
    private List<WebElement> pagesWithResults;
    /**
     * Переменная - коллекция результатов поиска со всех прогруженных страниц
     * @author Мороз Сергей LuvMiit
     * */
    private List<WebElement> allResultsFromPages;
    /**
     * Переменная - элемент поисковая строка
     * @author Мороз Сергей LuvMiit
     * */
    private WebElement searchBar;
    /**
     * Переменная - элемент кнопка поиска
     * @author Мороз Сергей LuvMiit
     * */
    private WebElement searchButton;

    /**
     * Переменная - путь к элементу загрузки (кружок загрузки)
     * @author Мороз Сергей LuvMiit
     * */
    private final static String XPATH_TO_LOAD_ELEMENT ="//div[@data-auto='preloader']//span[@data-auto='spinner']";
    /**
     * Переменная - путь к месту ввода минимального значения цены
     * @author Мороз Сергей LuvMiit
     * */
    private final static String XPATH_TO_MIN_INPUT ="//div[@data-auto='filter-range-glprice']//span[@data-auto='filter-range-min']//input";
    /**
     * Переменная - путь к элементу ввода максимального значения цены
     * @author Мороз Сергей LuvMiit
     * */
    private final static String XPATH_TO_MAX_INPUT ="//div[@data-auto='filter-range-glprice']//span[@data-auto='filter-range-max']//input";
    /**
     * Переменная - путь к блоку с производителями
     * @author Мороз Сергей LuvMiit
     * */
    private final static String XPATH_TO_PRODUCER = "//div[@data-filter-id='7893318']";
    /**
     * Переменная - путь к элементу ввода имени производителя
     * @author Мороз Сергей LuvMiit
     * */
    private final static String XPATH_TO_PRODUCER_NAME_INPUT = XPATH_TO_PRODUCER+"//div[@data-zone-name='filterSearchValueField']//input[@placeholder='Найти']";
    /**
     * Переменная - путь к элементам после поиска
     * @author Мороз Сергей LuvMiit
     * */
    private final static String XPATH_TO_PRODUCER_ITEMS = XPATH_TO_PRODUCER+"//div[@data-baobab-name='filterValue']";
    /**
     * Переменная - путь к текствовому полю "Показать всё" во вкладке "Производитель"
     * @author Мороз Сергей LuvMiit
     * */
    private final static String XPATH_TO_PRODUCER_SHOW_ALL = XPATH_TO_PRODUCER + "//span[contains(text(), 'Показать всё')]";
    /**
     * Переменная - путь к первой странице с результатами фильтрации
     * @author Мороз Сергей LuvMiit
     * */
    private final static String XPATH_TO_RESULTS_LIST = "//div[@data-auto='SerpList']";

    private final static String XPATH_TO_RESULTS_NAMES = ".//div[@data-baobab-name='title']//span";
    private static final String XPATH_TO_RESULTS_COSTS = ".//span[@data-auto='snippet-price-current']//span[not (contains(text(), '₽'))]";
    private static final String XPATH_TO_RESULTS_COSTS_SECOND = ".//div[@data-auto='snippet-price-current']//div";
    private static final String XPATH_TO_SEARCH_BAR = "//input[@placeholder='Найти товары']";
    private static final String XPATH_TO_SEARCH_BUTTON ="//button[@data-auto='search-button']";

    public ChapterPage(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
        expectedConditionsChecker = new ExpectedConditionsChecker(chromeDriver);
    }
    /**
     * Метод ввода минимального значения цены
     * @param minValue минимальное значение цены
     * @author Мороз Сергей LuvMiit
     * */
    public void enterMinValue(int minValue){
        expectedConditionsChecker.visibilityWait(XPATH_TO_MIN_INPUT);
        minValueInput = chromeDriver.findElement(By.xpath(XPATH_TO_MIN_INPUT));
        minValueInput.click();
        minValueInput.sendKeys(String.valueOf(minValue));
    }
    /**
     * Метод ввода максимального значения цены
     * @param maxValue максимальное значение цены
     * @author Мороз Сергей LuvMiit
     * */
    public void enterMaxValue(int maxValue){
        expectedConditionsChecker.visibilityWait(XPATH_TO_MAX_INPUT);
        maxValueInput = chromeDriver.findElement(By.xpath(XPATH_TO_MAX_INPUT));
        maxValueInput.click();
        maxValueInput.sendKeys(String.valueOf(maxValue));
    }

    /**
     * Метод раскрытия списка всех производителей
     * @author Мороз Сергей LuvMiit
     * */
    public void openProducers(){
        waitWhileLoad(chromeDriver, XPATH_TO_LOAD_ELEMENT);
        showAllElement = chromeDriver.findElement(By.xpath(XPATH_TO_PRODUCER_SHOW_ALL));
        Actions actions = new Actions(chromeDriver);
        actions.moveToElement(showAllElement);
        expectedConditionsChecker.visibilityWait(XPATH_TO_PRODUCER_SHOW_ALL);
        showAllElement.click();
    }
    /**
     * Метод ввода наименования производителя
     * @param producerName наименование производителя
     * @author Мороз Сергей LuvMiit
     * */
    public void enterProducerNameFilter(String producerName){
        expectedConditionsChecker.visibilityWait(XPATH_TO_PRODUCER_NAME_INPUT);
        producerNameInput = chromeDriver.findElement(By.xpath(XPATH_TO_PRODUCER_NAME_INPUT));
        producerNameInput.click();
        producerItems = chromeDriver.findElements(By.xpath(XPATH_TO_PRODUCER_ITEMS));
        producerNameInput.sendKeys(producerName);

    }
    /**
     * Метод выбора производителя
     * @param producerName наименование производителя
     * @author Мороз Сергей LuvMiit
     * */
    public void chooseProducer(String producerName){
        expectedConditionsChecker.visibilityWait(XPATH_TO_PRODUCER_ITEMS);
        waitWhileDataUpdate(chromeDriver, XPATH_TO_PRODUCER_ITEMS, producerItems.size());
        producerItems = chromeDriver.findElements(By.xpath(XPATH_TO_PRODUCER_ITEMS));
        List<WebElement> currentProducer = producerItems.stream()
                .filter(item ->
                        item.findElement(By.xpath("//span[contains(text(), '"+producerName+"')]")).getText().equals(producerName)
                )
                .collect(Collectors.toList());
        if(!currentProducer.isEmpty()){
            currentProducer.get(0).click();
        }
    }
    /**
     * Метод проверки количества результатов на первой странице
     * @param expectedCountResults ожидаемое количество результатов
     * @author Мороз Сергей LuvMiit
     * */
    public void checkCountResults(int expectedCountResults){
        waitWhileLoad(chromeDriver, XPATH_TO_LOAD_ELEMENT);
        expectedConditionsChecker.visibilityWait(XPATH_TO_RESULTS_LIST);
        Assertions.assertCount(getFirstPageFilteredResults().size()>expectedCountResults, "Результатов на странице меньше, чем " + expectedCountResults);
    }

    /**
     * Метод проверки соответствия результатов заданным фильтрам
     * @param costMin минимальная граница цены
     * @param costMax максимальная граница цены
     * @param producerNames список имен производителей
     * @author Мороз Сергей LuvMiit
     * */
    public void checkCostAndName(int costMin, int costMax, List<String> producerNames){
        List<WebElement> pageItems = new ArrayList<>();
        pagesWithResults = chromeDriver.findElements(By.xpath(XPATH_TO_RESULTS_LIST));
        allResultsFromPages = new ArrayList<>();

        for(WebElement page: pagesWithResults){
            pageItems = page.findElements(By.xpath(XPATH_TO_RESULTS_LIST +"/div[not(descendant::div[@data-apiary-widget-name=\"@light/SearchIncut\"])]"));
            allResultsFromPages.addAll(pageItems);
        }
        for(WebElement item: pageItems){
            String itemName = item.findElement(By.xpath(XPATH_TO_RESULTS_NAMES)).getText();
            String itemCost = null;

            //не нашел другого решения, лично у меня в маркете при несколких запусках, цена лежит то в div, то в span
            List<WebElement> elements = item.findElements(By.xpath(XPATH_TO_RESULTS_COSTS));
            if(!elements.isEmpty()){
                itemCost = item.findElement(By.xpath(XPATH_TO_RESULTS_COSTS))
                        .getText()
                        .replace("\u2009", "");
            }else{
                itemCost = item.findElement(By.xpath(XPATH_TO_RESULTS_COSTS_SECOND))
                        .getText()
                        .replace("\u2009", "");
            }

            int intCost = Integer.parseInt(itemCost);
            boolean anyMatchName = producerNames.stream()
                            .anyMatch(itemName::contains);
            Assertions.assertContains(anyMatchName, "Результат не соответствует ни одному из параметров: "+producerNames);
            Assertions.assertInRange(costMin, costMax, intCost,"Цена "+itemCost+" в результате "+itemName+" не находится в диапазоне от "+costMin+" до "+costMax);
        }

    }
    /**
     * Метод "запоминания" первого элемента и его поиска в поисковой строке
     * @author Мороз Сергей LuvMiit
     * */
    public void takeFirstName(){
        String firstName = getFirstPageFilteredResults().get(0).findElement(By.xpath(XPATH_TO_RESULTS_NAMES)).getText();
        findAndAssert(firstName);
    }
    /**
     * Метод поиска в поисковой строке
     * @param firstName название первого результата
     * @author Мороз Сергей LuvMiit
     * */
    @Step("Поиск по запросу: '{firstName}'")
    public void findAndAssert(String firstName){
        searchBar = chromeDriver.findElement(By.xpath(XPATH_TO_SEARCH_BAR));
        searchButton = chromeDriver.findElement(By.xpath(XPATH_TO_SEARCH_BUTTON));
        searchBar.sendKeys(firstName);
        searchButton.click();
        firstPageFilteredResults = getFirstPageFilteredResults();
        boolean isContainsName = firstPageFilteredResults.stream()
                        .map(item ->{
                            expectedConditionsChecker.visibilityOfElementLocated(XPATH_TO_RESULTS_LIST +"/div[not(descendant::div[@data-apiary-widget-name=\"@light/SearchIncut\"])]");
                            return item.findElement(By.xpath(XPATH_TO_RESULTS_NAMES)).getText();
                        })
                                .anyMatch(item -> item.equals(firstName));
        Assertions.assertContainsOnFirstPage(isContainsName, firstName+" не находится на первой странице результатов");

    }
    /**
     * Метод для поиска первой страницы результатов
     * @return переменная-коллекция с результатами первой страницы
     * @author Мороз Сергей LuvMiit
     * */
    private List<WebElement> getFirstPageFilteredResults(){
        expectedConditionsChecker.visibilityOfAllElementsLocated(XPATH_TO_RESULTS_LIST);
        return chromeDriver
                .findElements(By.xpath(XPATH_TO_RESULTS_LIST)).get(0)
                .findElements(By.xpath(XPATH_TO_RESULTS_LIST +"/div[not(descendant::div[@data-apiary-widget-name=\"@light/SearchIncut\"])]"));
    }

}
