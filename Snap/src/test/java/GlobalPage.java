import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GlobalPage {

    public AppiumDriver driver;



    public void waitE_visibilityOf(MobileElement element) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        iWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitE_stalenessOf(MobileElement element) {
        WebDriverWait iWait = new WebDriverWait(driver, 20);
        iWait.until(ExpectedConditions.stalenessOf(element));
    }

    public String waitE_TextPresent(WebElement element, String phrase) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        sleep(100);
        iWait.until(ExpectedConditions.textToBePresentInElement(element, phrase));
        String text = element.getText();
        System.out.println(element.getText() + " ====== " + phrase);
        return text;
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
