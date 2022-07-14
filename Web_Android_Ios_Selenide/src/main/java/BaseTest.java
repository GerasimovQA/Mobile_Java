import com.codeborne.selenide.*;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Dimension;
import utils.ConfigProperties;

import java.time.Duration;
import java.time.LocalTime;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    public enum Direction {UP, DOWN, LEFT, RIGHT;}

    @BeforeAll
    public static void beforeClass() {
        if (ConfigProperties.getTestProperty("platform").contains("Web")) {
            Configuration.baseUrl = "http://www.google.com";
            Configuration.timeout = 20000;
            Configuration.browser = "safari";
            open("/");
            sleep(2000);
        } else {
            closeWebDriver();
            Configuration.browserSize = null;
            Configuration.browser = DriverProvider.class.getName();
            open();
        }

    }

    @AfterAll
    public static void tearDown() {
        closeWebDriver();
    }

    public void clickElement(SelenideElement e) {
        String Locator = e.toString();
        e.shouldBe(Condition.visible, Duration.ofSeconds(60));
        e.click();
        sout("click " + Locator);
    }

    public void sout(String text) {
        System.out.println(currentTime() + " - " + text);
    }

    public String currentTime() {
        LocalTime currentDayAndTime = LocalTime.now();
//        sout("Неизмененное время:" + currentDayAndTime.toString());
        return currentDayAndTime.toString();
    }

    public void swipeScreen(Direction dir) {
        AndroidDriver driver = (AndroidDriver) WebDriverRunner.getWebDriver();
        System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions


        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 300; // ms

        final int PRESS_TIME = 200; // ms

        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Dimension dims = Selenide.webdriver().driver().getWebDriver().manage().window().getSize();
        System.out.println(dims);

        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder - 1000, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder + 1000, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }
        //         execute swipe using TouchAction
        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }
//
        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }
}

