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
        clickElement(page.buttonPageControl);

        swipeScreen(Direction.LEFT);
        swipeScreen(Direction.RIGHT);
        clickElement(page.buttonUIKitCatalog);
    }
}