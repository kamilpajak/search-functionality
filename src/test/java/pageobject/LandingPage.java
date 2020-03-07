package pageobject;

import setup.Environment;
import setup.Settings;

import static com.codeborne.selenide.Selenide.*;
import static org.apache.commons.lang3.EnumUtils.getEnumIgnoreCase;
import static setup.Settings.setUp;

public class LandingPage {

    public LandingPage() {
        setUp();
        Environment environment = getEnumIgnoreCase(Environment.class, Settings.properties.getProperty("environment"));
        open(environment.url());
    }

    public SearchPage goToSearch() {
        $("[data-qa-selector=\"search\"]").click();
        return page(SearchPage.class);
    }
}
