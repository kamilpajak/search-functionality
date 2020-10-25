package pageobject;

import lombok.extern.log4j.Log4j2;
import setup.Environment;
import setup.Settings;

import static com.codeborne.selenide.Selenide.*;
import static org.apache.commons.lang3.EnumUtils.getEnumIgnoreCase;

@Log4j2
public class LandingPage {

    public LandingPage() {
        Settings.setUp();
        Environment environment = getEnumIgnoreCase(Environment.class, Settings.properties.getProperty("autohero.environment"));
        open(String.format("%s%s/", environment.url(), Settings.properties.getProperty("autohero.language")));
    }

    public SearchPage goToSearch() {
        $("[data-qa-selector=\"search\"]").click();
        return page(SearchPage.class);
    }
}
