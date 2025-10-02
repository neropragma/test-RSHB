package helpers;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static config.Properties.PROP;
import static helpers.DeviceHelper.executeSh;
import static helpers.Utils.findGroup1ValueFromString;

public class ApkInfoHelper {
    private final String apkInfo;

    public ApkInfoHelper() {
        String app = PROP.getAppPath();
        if(app == null || app.isEmpty()){
            throw new RuntimeException("No value for key 'app' providing apk path in properties");
        }
        try {
            String pathToAapt = "D:\\Users\\Yousoro\\AppData\\Local\\Android\\Sdk\\build-tools\\36.1.0\\";
            apkInfo = executeSh(pathToAapt + "aapt dumb badging " + PROP.getAppPath());
        } catch (IOException | InterruptedException | ExecutionException e){
            throw new RuntimeException(e);
        }
    }

    public String getAppPackageFromApk() {
        return findGroup1ValueFromString(apkInfo,"package: name='\\s*([^']+?)\\s*'");
    }

    public String getAppMainActivity()  {
        return findGroup1ValueFromString(apkInfo, "launchable-activity: name='\\s*([^']+?)\\s*'");
    }
}
