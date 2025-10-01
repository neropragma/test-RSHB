package pages.ozon;

import static com.codeborne.selenide.appium.SelenideAppium.$;

import static helpers.EmulatorHelper.pressSearch;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.appium.SelenideAppiumElement;

import io.appium.java_client.AppiumBy;

import io.qameta.allure.Step;

public class MainPage {
    private final SelenideAppiumElement searchField = $(AppiumBy.id("ru.ozon.app.android:id/searchTv"));
    private final SelenideAppiumElement searchFieldExpanded = $(AppiumBy.id("ru.ozon.app.android:id/etSearch"));
    private final SelenideAppiumElement mainMenuButton = $(AppiumBy.id("ru.ozon.app.android:id/menu_main"));

    public MainPage(){
        mainMenuButton.should(Condition.selected);
    }

    @Step("Поиск товара по строке {item}")
    public SearchResultsPage searchItem(String item, int count){
        searchField.should(Condition.visible).click();
        searchFieldExpanded.setValue(item);
        pressSearch();
        return new SearchResultsPage(item, count);
    }

}
