package helpers;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static config.Properties.PROP;
import static helpers.DeviceHelper.executeSh;

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

    private static String findGroup1ValueFromString(String text, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()){
            return matcher.group(1);
        }
        return null;
    }
}
