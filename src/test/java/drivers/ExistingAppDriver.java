package drivers;

import static config.Properties.PROP;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;

import com.codeborne.selenide.WebDriverProvider;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import helpers.AdbInfoHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class ExistingAppDriver implements WebDriverProvider {

    private void initPackageAndActivity() {
        AdbInfoHelper helper = new AdbInfoHelper();
        if (PROP.getAppPackage() == null || PROP.getAppPackage().isEmpty()) {
            System.setProperty("appPackage", helper.getAppPackageFromAdb());
        }
        if (PROP.getAppActivity() == null || PROP.getAppActivity().isEmpty()) {
            System.setProperty("appActivity", helper.getAppMainActivityFromAdb());
        }
    }

    @Override
    @NotNull
    public WebDriver createDriver(@NotNull Capabilities capabilities) {
        initPackageAndActivity();
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(ANDROID_UIAUTOMATOR2);
        options.setPlatformName("Android");
        options.setPlatformVersion(PROP.getAndroidVersion());
        options.setDeviceName(PROP.getAndroidDevice());
        options.setNewCommandTimeout(Duration.ofSeconds(1100));
        options.setFullReset(false);
        options.setAppPackage(System.getProperty("appPackage"));
        options.setAppActivity(System.getProperty("appActivity"));
        options.setAppWaitActivity("*");

        try {
            AndroidDriver driver = new AndroidDriver(new URL(PROP.getLocalUrl()), options);
            DriverManager.setDriver(driver);
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
