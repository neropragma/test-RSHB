package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import base.BaseTest;
import io.qameta.allure.Description;
import pages.ozon.MainPage;

@Tag("Ozon")
public class OzonTests extends BaseTest {

    @BeforeAll
    static void setApp(){
        System.setProperty("appName", "Ozon");
    }

    @RepeatedTest(5)
    @DisplayName("Добавления найденного товара в корзину")
    @Description("Поиск товара в поле поиска и добавление нужного кол-ва товаров в корзину")
    public void checkBasket(){
        Assertions.assertTrue(new MainPage()
                .searchItem("cat", 4)
                .add4ItemToCart()
                .openCart()
                .checkNumberOfItems());
    }
}
