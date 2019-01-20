import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import static io.appium.java_client.touch.offset.PointOption.point;

public class SnapchatLogic {

    private AppiumDriver driver;
    private SnapchatPage p;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "AndroidTestDevice");
        desiredCapabilities.setCapability("platformVersion", "7.0");
        desiredCapabilities.setCapability("automationName", "Appium");
        desiredCapabilities.setCapability("appPackage", "com.snapchat.android");
        desiredCapabilities.setCapability("appActivity", ".LandingPageActivity");
        desiredCapabilities.setCapability("autoAcceptAlerts", true);
        desiredCapabilities.setCapability("eventTimings", true);
        desiredCapabilities.setCapability("autoGrantPermissions", true);
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.rotate(ScreenOrientation.PORTRAIT);
        p = new SnapchatPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void snapWork() {
//Get Login and Password
        p.getAuth();
//Get random user
        p.getRandomUser();
//Login
        p.click(p.buttonLogin);
        p.SendKeys(p.inputUsername, p.Login);
        p.SendKeys(p.inputPassword, p.Password);
        p.click(p.buttonEnter);
//Start setting
        p.click(p.buttonProfile);
        p.click(p.buttonSetting);
//Looking for Notification Settings
        do {
            p.clickTextElementAndScroll(p.listSettingElements, "Notification Settings");
            if (p.NameElement.equals("Notification Settings")) {
                break;
            }
            new TouchAction(driver).press(point(300, 140))
                    .moveTo(point(300, 100)).release().perform();

        } while (!p.NameElement.equals("Notification Settings"));

        p.click(p.chekBoxGeneral);
        driver.navigate().back();
        driver.navigate().back();

//Start send message
        p.click(p.buttonFriends);
        p.click(p.buttonNewChat);
//Looking for Sol Marl
        p.clickTextElement(p.listNameNewChat, "sol marl");
        p.SendKeys(p.fieldText, p.Uptime + ", " + p.Version);
        new TouchAction(driver).press(point(440, 811))
                .moveTo(point(440, 811)).release().perform();
//Сheck that the message has been sent
        p.clickTextElement(p.listSentMessages, p.Uptime + ", " + p.Version);
    }
}