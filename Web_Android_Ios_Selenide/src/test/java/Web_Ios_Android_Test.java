import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.appium.ScreenObject.screen;

public class Web_Ios_Android_Test extends BaseTest{

    Page page;
    @BeforeEach
    public void before(){
        page = screen(Page.class);
    }

    @Test
    public void test () {
//        swipe();
        System.out.println(page.buttonPageControl.getText());
        page.buttonPageControl.click();

        System.out.println(page.buttonUIKitCatalog.getText());
        page.buttonUIKitCatalog.click();
    }
}