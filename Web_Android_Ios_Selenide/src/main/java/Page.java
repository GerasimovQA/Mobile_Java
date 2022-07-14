import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.FindBy;

public class Page {
    @AndroidFindBy(id = "org.torproject.torbrowser:id/homeLayout")
    SelenideElement screen;
    @FindBy(xpath = ".//a[text()=\"About\"]")
    @iOSXCUITFindBy(id = "Page Control")
    @AndroidFindBy(id = "org.torproject.torbrowser:id/tor_bootstrap_connect_button")
    SelenideElement buttonPageControl;
    @FindBy(xpath = ".//a[@data-g-cta_text=\"Get product support\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"UIKitCatalog\"]")
    @AndroidFindBy(id = "org.torproject.torbrowser:id/tor_bootstrap_network_settings_button")
    SelenideElement buttonUIKitCatalog;
}
