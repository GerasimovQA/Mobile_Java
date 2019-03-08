import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class MTCLogic {

    private AppiumDriver driver;
    private MTCPage p;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "AndroidTestDevice");
        desiredCapabilities.setCapability("platformVersion", "7.0");
        desiredCapabilities.setCapability("automationName", "Appium");
        desiredCapabilities.setCapability("appPackage", "ru.mdh.mtsamarket2memory.android.debug_prod");
        desiredCapabilities.setCapability("appActivity", "ru.mdh.mtsamarket2memory.android.init.activity.InitActivity");
        desiredCapabilities.setCapability("autoAcceptAlerts", true);
        desiredCapabilities.setCapability("eventTimings", true);
        desiredCapabilities.setCapability("autoGrantPermissions", true);
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.rotate(ScreenOrientation.PORTRAIT);
        p = new MTCPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void MTC() {
//Login
        p.click(p.buttonEnterNumber);
        p.SendKeys(p.inputPhone, p.Login);
        p.click(p.buttonFurther);
        p.SendKeys(p.inputPin, p.Pin);
        p.click(p.buttonReady);

//Load photo
        p.click(p.buttonStart);
        try {
            p.click(p.buttonAdd);
        } catch (TimeoutException e) {
            System.out.println("Element no found");
            new TouchAction(driver).press(PointOption.point(960, 1680)).release().perform();
        }

        p.click(p.buttonGallery);
        p.click(p.listGallery.get(0));
        for (int i = 0; i < 3; i++) {
            p.click(p.listGallery.get(i));
        }
        p.click(p.buttonAdd);
        p.waitLoadList(p.listPhoto, 3);

//Set tag
        p.click(p.listPhoto.get(1));
        p.click(p.buttonTag.get(2));
        try {
            p.click(p.buttonAddTag);
        }catch (TimeoutException e) {
            System.out.println("Element no found");
            new TouchAction(driver).press(PointOption.point(1020, 140)).release().perform();
        }
        p.SendKeys(p.inputTag, p.Tag);
        p.click(p.buttonReadyTag);
        p.click(p.bulletTag);
        p.click(p.buttonAdd);

//Close app
        driver.closeApp();
    }
}