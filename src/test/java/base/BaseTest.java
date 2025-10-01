package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.appium.SelenideAppium;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.LocalAndroidDriver;

import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeAll
    static void setUp() {
        Configuration.timeout = 1500000;
        Configuration.pageLoadTimeout = 1500000;
        Configuration.browserSize = null;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    void openBrowser() {
        Configuration.browser = LocalAndroidDriver.class.getName();
        SelenideAppium.launchApp();
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }
}
