package tests;


import helpers.Attach;
import org.junit.jupiter.api.AfterEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @AfterEach
    public void afterEach() {
        Attach.takeScreenshot("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}