package drivers;

import static com.codeborne.selenide.Selenide.webdriver;

import com.codeborne.selenide.WebDriverProvider;
import helpers.ApkInfoHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static config.Properties.PROP;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;

public class LocalAndroidDriver implements WebDriverProvider{
    protected static AndroidDriver driver;

    private void initPackageAndActivity() {
        ApkInfoHelper helper = new ApkInfoHelper();
        if (PROP.getAppPackage() == null || PROP.getAppPackage().isEmpty()) {
            System.setProperty("appPackage", helper.getAppPackageFromApk());
        }
        if (PROP.getAppActivity() == null || PROP.getAppActivity().isEmpty()) {
            System.setProperty("appActivity", helper.getAppMainActivity());
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
        options.setApp(new File(PROP.getAppPath()).getAbsolutePath());
        options.setAppPackage(PROP.getAppPackage());
        options.setAppActivity(PROP.getAppActivity());

        try {
            return driver = new AndroidDriver(new URL(PROP.getLocalUrl()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
