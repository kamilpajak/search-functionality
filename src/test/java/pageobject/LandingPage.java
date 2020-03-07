package pageobject;

import setup.Environment;
import setup.Settings;

import static com.codeborne.selenide.Selenide.open;
import static setup.Settings.setUp;

public class LandingPage {

    public LandingPage() {
        setUp();
        String environment = Settings.properties.getProperty("environment");
        open(Environment.valueOf(environment).url());
    }
}
