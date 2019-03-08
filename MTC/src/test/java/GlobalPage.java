import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GlobalPage {

    public AppiumDriver driver;

    public void click(WebElement element) {
        WebDriverWait iWait = new WebDriverWait(driver, 5);
        iWait.until(ExpectedConditions.visibilityOf(element)).click();
    }

    public void SendKeys(WebElement element, String text) {
        WebDriverWait iWait = new WebDriverWait(driver, 5);
        iWait.until(ExpectedConditions.elementToBeClickable(element)).click();
        element.sendKeys(text);
        System.out.println("Print: " + text);
    }

    public void waitLoadList(List<AndroidElement> listelement, int size) {
        int Time = 0;
        do {
            sleep(500);
            Time = Time + 500;
            System.out.println("Sum elements: " + listelement.size() + ", Time: " + Time);
            if (Time >= 6000) {
                throw new AssertionError("List not loaded");
            }
        } while (size != listelement.size());

    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
