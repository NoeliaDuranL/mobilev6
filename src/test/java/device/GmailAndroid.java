
package device;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.net.MalformedURLException;

public class GmailAndroid implements IDevice {

    @Override
    public AndroidDriver create() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:deviceName", "Medium Phone");
        capabilities.setCapability("appium:udid", "emulator-5554");
        capabilities.setCapability("appium:platformVersion", "17.0");
        capabilities.setCapability("appium:appPackage", "com.google.android.gm");
        capabilities.setCapability(
                "appium:appActivity",
                "com.google.android.gm.ConversationListActivityGmail"
        );

        AndroidDriver driver = null;

        try {
            driver = new AndroidDriver(
                    new URL("http://127.0.0.1:4723/"),
                    capabilities
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        driver.manage().timeouts()
                .implicitlyWait(java.time.Duration.ofSeconds(10));

        return driver;
    }
}
