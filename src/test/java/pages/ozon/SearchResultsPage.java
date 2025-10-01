package pages.ozon;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static com.codeborne.selenide.appium.SelenideAppium.$x;

import static helpers.EmulatorHelper.tapInsideElement;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.appium.SelenideAppiumElement;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

public class SearchResultsPage {
    private final String item;
    private final int count;
    private final SelenideAppiumElement addToCartButton = $x("(//android.view.View[contains(@content-desc,'ozonAddToCart')])[1]");
    private final SelenideAppiumElement tagList = $(AppiumBy.id("ru.ozon.app.android:id/tagListRv"));
    private final SelenideAppiumElement cartButton = $(AppiumBy.id("ru.ozon.app.android:id/menu_cart"));

    public SearchResultsPage(String item, int count){
        tagList.should(Condition.visible);
        this.item = item;
        this.count = count;
    }

    @Step("Добавить нужное кол-во штук товара в корзину")
    public SearchResultsPage add4ItemToCart(){
        addToCartButton.should(Condition.enabled);
        for(int i=0; i < count; i++){
            tapInsideElement(addToCartButton, 0.9, 0.5);
        }
        return this;
    }

    @Step("Открыть корзину")
    public CartPage openCart(){
        cartButton.should(Condition.enabled).click();
        return new CartPage(item, count);
    }

}
