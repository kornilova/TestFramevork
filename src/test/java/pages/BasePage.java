package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import static tests.BaseTest.getDriver;

public abstract class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    Actions builder;
    JavascriptExecutor executor;

    public BasePage() {
        this.driver = getDriver();
        builder = new Actions(driver);
        executor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    boolean isElementFound(By by, int timeout) throws InterruptedException {
        List<WebElement> elements = driver.findElements(by);
        for (int i = 0; (i < timeout && elements.size() == 0); i++) {
            Thread.sleep(2000);
            elements = driver.findElements(by);

        }
        return elements.size() > 0;
    }

    protected void pasteTextToElementFromClipboard(WebElement element,
                                                   String text) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection strSelection = new StringSelection(text);
        clipboard.setContents(strSelection, null);

        element.sendKeys(getCommandCtrlV());
    }

    protected CharSequence getCommandCtrlV() {
        String os = System.getProperty("os.name");
        if (os.equals("WINDOWS")) {
            return Keys.chord(Keys.CONTROL, "v");
        } else {
            return Keys.chord(Keys.COMMAND, "v");
        }
    }

    protected void clickWithJavaScript(WebElement element) {
        executor.executeScript("arguments[0].click()", element);
    }
}
