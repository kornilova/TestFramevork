package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import steps.SearchSteps;
import utils.DriverFactory;
import utils.PropertyReader;

import java.util.concurrent.TimeUnit;


public abstract class BaseTest {
    protected static WebDriver driver;

    SearchSteps searchSteps;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void setUp() {
        driver = DriverFactory.getDriver(PropertyReader.getBrowser());
        searchSteps = new SearchSteps();
    }

    @BeforeMethod
    public void toBaseUrl() {
        driver.navigate().to(PropertyReader.getBaseUrl());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (PropertyReader.isMaximizeWindow()) driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

    @DataProvider(name = "dataProvider")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{"testing"}, {"selenium"}};
    }
}
