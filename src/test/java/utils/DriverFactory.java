package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static WebDriver driver;
    private final static String DRIVER_PATH = "src/test/resources/";

    public DriverFactory() {
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
                System.setProperty("webdriver.chrome.driver", DRIVER_PATH + "chromedriver");
                driver = new ChromeDriver(new ChromeOptions().setExperimentalOption("debuggerAddress", "172.17.0.1:9222"));
            }
        }

        return driver;
    }
}
