import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import utils.ConfigProperties;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    @BeforeAll
    public static void beforeClass() {
        if(ConfigProperties.getTestProperty("platform").contains("Web")){
            Configuration.baseUrl = "http://www.google.com";
            Configuration.timeout = 20000;
            Configuration.browser = "safari";

//            System.out.println(ConfigProperties.getTestProperty("platform"));
//            System.out.println(Configuration.browser);
//            System.out.println(Configuration.baseUrl);
//            System.out.println(Configuration.timeout);
//            Configuration.browser = DriverProvider.class.getName();
            open("/");
            sleep(2000);
        }
        else {
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

//    public void swipe (){
//        actions().scrollByAmount(1000,0);
//    }
}
