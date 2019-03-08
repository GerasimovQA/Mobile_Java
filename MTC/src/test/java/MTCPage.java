import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MTCPage extends GlobalPage {

    String Login = "1110000082";
    String Pin = "0000";
    String Tag = "Photo";

    public MTCPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Button EnterNumber
    @AndroidFindBy(id = "btnHand")
    public AndroidElement buttonEnterNumber;

    //Input Phone
    @AndroidFindBy(id = "etPhone")
    public AndroidElement inputPhone;

    //Button Further
    @AndroidFindBy(id = "activate_action")
    public AndroidElement buttonFurther;

    //Input Pin
    @AndroidFindBy(id = "pin_field")
    public AndroidElement inputPin;

    //Button Ready
    @AndroidFindBy(id = "confirm_pin_action")
    public AndroidElement buttonReady;

    //Button Start
    @AndroidFindBy(id = "start_app_btn")
    public AndroidElement buttonStart;

    //pulse
    @AndroidFindBy(id = "ivPulse")
    public AndroidElement pulse;

    //Button Add
    @AndroidFindBy(id = "fab")
    public AndroidElement buttonAdd;

    //Button Gallery
    @AndroidFindBy(id = "ivPickGallery")
    public AndroidElement buttonGallery;

    //ListGallery
    @AndroidFindBy(id = "iv")
    public List<AndroidElement> listGallery;

    //ListPhoto
    @AndroidFindBy(id = "imgCardFolder")
    public List<AndroidElement> listPhoto;

    //List Button
    @AndroidFindBy(id = "textView")
    public List<AndroidElement> buttonTag;

    //Button AddTag
    @AndroidFindBy(id = "menu_tag_add")
    public AndroidElement buttonAddTag;

    //Input Tag
    @AndroidFindBy(className = "android.widget.EditText")
    public AndroidElement inputTag;

    //Button ReadyTag
    @AndroidFindBy(id = "button1")
    public AndroidElement buttonReadyTag;

    //Bullet Tag
    @AndroidFindBy(id = "imgCardTagStatus")
    public AndroidElement bulletTag;
}
