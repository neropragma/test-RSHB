package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;
public class Properties {
    public static final PropInterface PROP = ConfigFactory.create(getPropertySource());

    private static Class<? extends PropInterface> getPropertySource() {
        String env = System.getProperty("env");
        if (env == null || env.equals("null")) {
            return PropInterfaceTest.class;
        } else if (env.equals("test")) {
            return PropInterfaceTest.class;
        } else {
            throw new RuntimeException("Invalid value for system property 'env'! Expected one of:[null, 'test']");
        }
    }

    @LoadPolicy(LoadType.MERGE)
    @Sources({"system:properties", "classpath:test.properties"})
    interface PropInterfaceTest extends PropInterface {
    }

    public interface PropInterface extends Config {

        @Key("androidVersion")
        String getAndroidVersion();

        @Key("androidDevice")
        String getAndroidDevice();

        @Key("appPath")
        String getAppPath();

        @Key("localUrl")
        String getLocalUrl();

        @Key("appPackage")
        String getAppPackage();

        @Key("appActivity")
        String getAppActivity();

        @Key("vpnAppPackage")
        String getVpnAppPackage();
    }
}
