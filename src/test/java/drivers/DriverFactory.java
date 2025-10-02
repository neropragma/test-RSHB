package drivers;

public class DriverFactory {

    public static Class<?> getDriverClass(){
        String app = System.getProperty("appName");
        return switch (app) {
            case "Ozon" -> LocalAndroidDriver.class;
            case "Vpn" -> ExistingAppDriver.class;
            default -> throw new RuntimeException("No such app driver for app: " + app);
        };
    }
}
