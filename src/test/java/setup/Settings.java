package setup;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Properties;

public final class Settings {

    private Settings() {
    }

    public static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setUp() {
        setDriver();
        setTimeout();
    }

    private static void setTimeout() {
        String timeout = properties.getProperty("selenide.timeout");
        Configuration.timeout = StringUtils.isNumeric(timeout) ? Integer.parseInt(timeout) * 1000 : 8000;
    }

    private static void setDriver() {
        String url = properties.getProperty("selenium.grid");
        UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
        if (urlValidator.isValid(url)) {
            setupRemoteWebDriver(url);
        } else {
            setupLocalWebDriver();
        }
    }

    private static void setupRemoteWebDriver(String url) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        try {
            RemoteWebDriver driver = new RemoteWebDriver(URI.create(url).toURL(), capabilities);
            WebDriverRunner.setWebDriver(driver);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static void setupLocalWebDriver() {
        ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
        Configuration.browser = WebDriverRunner.CHROME;
    }
}
