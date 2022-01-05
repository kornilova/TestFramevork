package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DriverFactory {
    private static WebDriver driver;
    private final static String DRIVER_PATH = "src/test/resources/" + getOsPath();

    public DriverFactory() {
    }

    private static String getOsPath() {
        String os = System.getProperty("os.name").toUpperCase(Locale.ROOT);
        if (os.contains("LINUX")) return "linux/";
        else if (os.contains("WINDOWS")) return "windows/";
        else if (os.contains("MAC OS")) return "mac/";
        else return null;
    }

    public static WebDriver getDriver(Browser browser) {
        switch (browser) {
            case IE -> {
                return null;
            }
            case CHROME -> {
                System.setProperty("webdriver.chrome.driver", DRIVER_PATH + "chromedriver");
                driver = new ChromeDriver();
            }
            case FIREFOX -> {
                System.setProperty("webdriver.gecko.driver", DRIVER_PATH + "geckodriver");
                driver = new FirefoxDriver();
            }
            case REMOTE_CHROME -> {
                System.setProperty("webdriver.gecko.driver", DRIVER_PATH + "geckodriver");
//                driver = new ChromeDriver(new ChromeOptions().setExperimentalOption("debuggerAddress", "172.17.0.1:9222"));
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", "firefox");
                capabilities.setCapability("browserVersion", "95.0");
                capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                        "enableVNC", true,
                        "enableVideo", false
                ));
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("intl.accept_languages","en-ua");
                capabilities.setCapability(FirefoxDriver.PROFILE,profile.toString());
                try {
                    driver = new RemoteWebDriver(
                            URI.create("http://192.168.31.33:4444/wd/hub").toURL(),
                            capabilities
                    );
                } catch (MalformedURLException e) {
                    throw new IllegalStateException("Can not parse address", e);
                }
            }
        }

        return driver;
    }
}
