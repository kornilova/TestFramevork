package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchPage extends BasePage {
    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchFieldElement;

    private final String searchButtonBy = "//input[@name='btnK']";
    @FindBy(xpath = searchButtonBy)
    private WebElement searchButton;

    @FindBy(xpath = "//body")
    private WebElement pageBody;

    @FindBy(xpath = "//div[@aria-label='Search by voice']")
    private WebElement searchByVoiceButton;

    public SearchPage() {
        super();
    }

    public void fillSearchField(String text) {
        searchFieldElement.click();
        searchFieldElement.sendKeys(text);
    }

    public void pressEnter() {
        builder.sendKeys(searchFieldElement, Keys.ENTER).build().perform();
        // searchFieldElement.sendKeys();
    }

    public void clickSearchButtonOrPressEnter() throws InterruptedException {
        if (isElementFound(By.xpath(searchButtonBy), 3)) {
            wait.until(ExpectedConditions.elementToBeClickable(searchButton));
            clickWithJavaScript(searchButton);
            //searchButton.click();
        } else {
            pressEnter();
        }
    }

    public void pasteToSearchField(String text) {
        pasteTextToElementFromClipboard(searchFieldElement, text);
    }

    public void moveToVoiceSearchButton() {
        builder.moveToElement(searchByVoiceButton).build().perform();
    }

    public void assertThatVoiceSearchTooltipContainsText(String text) {
        assertThat(pageBody.findElements(By.xpath("//*[contains(text(), '" + text + "')]")).size()).as("Search tooltip is correct").isNotZero();
    }
}
