package pages.vpn;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static com.codeborne.selenide.appium.SelenideAppium.$x;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.appium.SelenideAppiumElement;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

public class VpnMainPage {
    private final SelenideAppiumElement textConncet = $x("//android.widget.TextView[@text=\"Tap to connect automatically\"]");
    private final SelenideAppiumElement connectButton = $x("//android.widget.TextView[@text=\"ON\"]/parent::android.view.View");
    private final SelenideAppiumElement okAlertButton = $(AppiumBy.id("android:id/button1"));

    public VpnMainPage(){
        textConncet.is(Condition.visible);
    }

    @Step("Подключение к vpn")
    public VpnMainPage clickOnConnectButton(){
        connectButton.click();
        return this;
    }

    @Step("Подключение к vpn")
    public VpnMainPage clickOnOkAlertButton(){
        if(okAlertButton.is(Condition.visible)) {
            okAlertButton.click();
        }
        return this;
    }
}
