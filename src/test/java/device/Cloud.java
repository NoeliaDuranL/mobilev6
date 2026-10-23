package device;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Cloud implements IDevice{

    String userName = "";
    String accessKey = "";

    @Override
    public AppiumDriver create() {

        DesiredCapabilities options = new DesiredCapabilities();
        options.setCapability("appium:app", "bs://");
        options.setCapability("appium:deviceName", "Samsung Galaxy S22 Ultra");
        options.setCapability("appium:platformVersion", "12.0");

        // project /build
        options.setCapability("appium:projectName", "UCBv6");
        options.setCapability("appium:buildName", "1.0");

        AppiumDriver driver = null;
        try {
            driver = new AndroidDriver(new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", userName , accessKey)), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        return driver;
    }
}
