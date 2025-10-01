package pages.ozon;

import static com.codeborne.selenide.appium.SelenideAppium.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.appium.SelenideAppiumElement;

import org.apache.commons.lang3.Strings;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

public class CartPage {
    private final String item;
    private final int count;
    private final SelenideAppiumElement quantityItem = $(AppiumBy.id("ru.ozon.app.android:id/quantityEt"));
    private final SelenideAppiumElement cartItemTitle = $(AppiumBy.id("ru.ozon.app.android:id/textAtom"));
    private final SelenideAppiumElement cartMenuButton = $(AppiumBy.id("ru.ozon.app.android:id/menu_cart"));

    public CartPage(String item, int count){
        cartMenuButton.should(Condition.selected);
        this.item = item;
        this.count = count;
    }

    @Step("Проверка наличия товаров в корзине")
    public boolean checkNumberOfItems(){
        return Strings.CI.contains(cartItemTitle.getText(), item) && quantityItem.getText().equals(count + "");
    }
}
