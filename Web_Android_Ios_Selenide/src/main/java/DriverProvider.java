import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import utils.ConfigProperties;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.net.MalformedURLException;
import java.net.URL;

@ParametersAreNonnullByDefault
public class DriverProvider implements WebDriverProvider {
    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver createDriver(Capabilities capabilities) {
        WebDriver driver = null;
        String platform = ConfigProperties.getTestProperty("platform");
        switch (platform) {
            case "IOS":
                XCUITestOptions options = new XCUITestOptions();
                options.merge(capabilities);
                options.setAutomationName("XCUITest");
                options.setPlatformName("iOS");
                options.setDeviceName("iPhone 11");
                options.setPlatformVersion("14.1");
                options.setApp("/Users/alekseigerasimov/Mobile/Web_Android_Ios_Selenide/src/main/resources/UIKitCatalog.app");
                try {
                    driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "ANDROID":
                UiAutomator2Options optionsAndroid = new UiAutomator2Options();
                optionsAndroid.merge(capabilities);
                optionsAndroid.setPlatformName("Android");
                optionsAndroid.setDeviceName("AndroidTestDevice");
                optionsAndroid.setPlatformVersion("7.0");
                optionsAndroid.setAutomationName("Appium");
                optionsAndroid.setAppPackage("com.android.contacts");
                optionsAndroid.setAppActivity("com.android.contacts.activities.PeopleActivity");
                try {
                    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), optionsAndroid);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                throw new AssertionError("This platform doesn't exist");
        }
        return driver;
    }
}