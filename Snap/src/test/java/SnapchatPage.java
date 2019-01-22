import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import static io.restassured.RestAssured.given;

public class SnapchatPage extends GlobalPage {

    String Login = "";
    String Password = "";
    String GetRandomUser = "";
    String NameElement = "";
    String Uptime = "";
    String Version = "";

    public SnapchatPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Button Login
    @AndroidFindBy(id = "login_and_signup_page_fragment_login_button")
    public AndroidElement buttonLogin;

    //Input Login
    @AndroidFindBy(id = "username_or_email_field")
    public AndroidElement inputUsername;

    //Input Password
    @AndroidFindBy(id = "password_field")
    public AndroidElement inputPassword;

    //Button "Enter"
    @AndroidFindBy(id = "registration_nav_container")
    public AndroidElement buttonEnter;

    //Button "Profile"
    @AndroidFindBy(id = "bitmoji")
    public AndroidElement buttonProfile;

    //Button "Setting"
    @AndroidFindBy(id = "profile_v3_settings_button")
    public AndroidElement buttonSetting;

    //List "Setting Elements"
    @AndroidFindBy(className = "android.widget.TextView")
    public List<AndroidElement> listSettingElements;

    //Chek-Box "General"
    @AndroidFindBy(id = "settings_general_checkbox")
    public AndroidElement chekBoxGeneral;

    //Button "Friends"
    @AndroidFindBy(id = "hova_nav_feed_primary")
    public AndroidElement buttonFriends;

    //Button "New Chat"
    @AndroidFindBy(id = "neon_header_new_chat_button")
    public AndroidElement buttonNewChat;

    //List "Name New Chat"
    @AndroidFindBy(className = "android.widget.TextView")
    public List<AndroidElement> listNameNewChat;

    //Field "Text"
    @AndroidFindBy(id = "chat_input_text_field")
    public AndroidElement fieldText;

    //List "Sent messages"
    @AndroidFindBy(className = "android.widget.TextView")
    public List<AndroidElement> listSentMessages;

//Сlick the element
    public void click(WebElement element) {
        WebDriverWait iWait = new WebDriverWait(driver, 30);
        iWait.until(ExpectedConditions.visibilityOf(element)).click();
    }

//Сlick the element that exists and contains the necessary text
    public void clickTextElement(List<AndroidElement> elements, String text) {
        int U = 0;
        for (WebElement element : elements) {
            System.out.println(element.getText());
            if (element.getText().equals(text)) {
                NameElement = element.getText();
                System.out.println("Element = " + NameElement);
                element.click();
                U = U + 1;
                break;
            }
        }
        if (U != 1) {
            throw new AssertionError("Element not found");
        }
    }

//Сlick the element that exists and contains the necessary text + scrolling the screen
    public void clickTextElementAndScroll(List<AndroidElement> elements, String text) {
        int U = 0;
        for (WebElement element : elements) {
            System.out.println(element.getText());
            if (element.getText().equals(text)) {
                NameElement = element.getText();
                System.out.println("Element = " + NameElement);
                element.click();
                U = U + 1;
                break;
            }
        }
    }

//Enter the text in the element
    public void SendKeys(WebElement element, String text) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        iWait.until(ExpectedConditions.elementToBeClickable(element)).click();
        element.sendKeys(text);
        System.out.println("Print: " + text);
    }

//Get login and password from JSON file
    public void getAuth() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("E:\\Selenium\\Snap\\src\\test\\Files\\auth.json"));
            JSONObject jsonObject = (JSONObject) obj;
            org.json.JSONObject o = new org.json.JSONObject(jsonObject);
            Login = o.getJSONObject("account").getString("login");
            System.out.println(Login);
            Password = o.getJSONObject("account").getString("password");
            System.out.println(Password);
        } catch (FileNotFoundException e) {
        } catch (ParseException e) {
        } catch (IOException e) {
        }
    }

//Get email and timezones.description from API
    public void getRandomUser() {
        Response response =
                given().log().all().
                        contentType(ContentType.JSON).
                        baseUri("https://your site/passport/_runstats").
                        when().
                        get().
                        then().extract().response();

        Uptime = "Analog email = " + response.path("uptime");
        Version = "Analog timezones.description = " + response.path("version");
        GetRandomUser = response.getBody().asString();
        System.out.println(Uptime);
        System.out.println(Version);
        System.out.println("Response: " + GetRandomUser);
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }
}
