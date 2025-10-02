package helpers;

import static config.Properties.PROP;
import static helpers.DeviceHelper.executeSh;
import static helpers.Utils.findValueFromString;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class AdbInfoHelper {
    private final String appPackage;

    public AdbInfoHelper(){
        String appPackage = PROP.getVpnAppPackage();
        if(appPackage == null || appPackage.isEmpty()){
            throw new RuntimeException("No value for key 'vpnAppPackage' providing app package in properties");
        }
        this.appPackage = appPackage;
    }

    public String getAppPackageFromAdb() {
        try {
            String result = executeSh("adb shell pm list packages | grep " + appPackage);
            if (result.startsWith("package:")) {
                return appPackage;
            }
            throw new RuntimeException("Package " + appPackage + " not found");

        } catch (IOException | InterruptedException | ExecutionException e){
            throw new RuntimeException(e);
        }
    }

    public String getAppMainActivityFromAdb() {
        try {
            String result = findValueFromString(
                    executeSh("adb shell dumpsys package " + appPackage + " | grep -A 1 MAIN | grep " + appPackage),
                    appPackage + "[\\S]+"
            );

            if (result != null && !result.isEmpty()) {
                return result;
            }
            throw new RuntimeException("Activity not found for: " + appPackage);

        } catch (IOException | InterruptedException | ExecutionException e){
            throw new RuntimeException(e);
        }
    }
}