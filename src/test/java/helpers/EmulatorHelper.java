package helpers;

import static config.Properties.PROP;

import com.codeborne.selenide.appium.SelenideAppiumElement;
import com.google.common.collect.ImmutableMap;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import drivers.DriverManager;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class EmulatorHelper {

    public static void pressSearch(){
        DriverManager.getDriver().pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public static void tapInsideElement(SelenideAppiumElement elem, double offsetX, double offsetY){
        Point start = elem.getLocation();
        Dimension size = elem.getSize();

        DriverManager.getDriver().executeScript("mobile: clickGesture", ImmutableMap.of(
                "x",start.x+ size.width*offsetX,
                "y", start.y+ size.height*offsetY
        ));
    }

    public static void runShScript(String scriptPath) {
        try {
            ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Git\\git-bash.exe", scriptPath);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("[SCRIPT] " + line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Script execution failed with exit code " + exitCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute script: " + scriptPath, e);
        }
    }
}
