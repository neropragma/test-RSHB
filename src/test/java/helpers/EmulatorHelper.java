package helpers;

import com.codeborne.selenide.appium.SelenideAppiumElement;
import com.google.common.collect.ImmutableMap;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import drivers.LocalAndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class EmulatorHelper extends LocalAndroidDriver {

    public static void pressSearch(){
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public static void tapInsideElement(SelenideAppiumElement elem, double offsetX, double offsetY){
        Point start = elem.getLocation();
        Dimension size = elem.getSize();

        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "x",start.x+ size.width*offsetX,
                "y", start.y+ size.height*offsetY
        ));
    }
}
