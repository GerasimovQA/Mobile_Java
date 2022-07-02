import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.FindBy;

public class Page {
    @FindBy(xpath = ".//a[text()=\"About\"]")
    @iOSXCUITFindBy(id = "Page Control")
    @AndroidFindBy(id = "com.android.contacts:id/user_profile_button")
    SelenideElement buttonPageControl;
    @FindBy(xpath = ".//a[@data-g-cta_text=\"Get product support\"]")
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"UIKitCatalog\"]")
    SelenideElement buttonUIKitCatalog;
}
