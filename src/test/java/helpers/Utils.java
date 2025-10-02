package helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utils {

    private Utils(){
        throw new UnsupportedOperationException("Utility class can not have instance");
    }

    public static String findGroup1ValueFromString(String text, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()){
            return matcher.group(1);
        }
        return null;
    }

    public static String findValueFromString(String text, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()){
            return matcher.group();
        }
        return null;
    }
}
