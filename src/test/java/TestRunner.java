import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageobject.LandingPage;
import pageobject.SearchPage;

public class TestRunner {

    @Test
    @DisplayName("To be renamed")
        // TODO: Change description
    void toBeRenamed() { // TODO: Change test name
        LandingPage landingPage = new LandingPage();
        SearchPage searchPage = landingPage.goToSearch();
    }
}
