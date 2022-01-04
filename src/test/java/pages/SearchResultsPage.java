package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchResultsPage extends BasePage {
    @FindBy(xpath = "//a[text()='Images']")
    private WebElement linkImageElement;

    @FindBy(xpath = "//div/a/h3")
    private List<WebElement> headersSearchResultList;

    public SearchResultsPage() {
        super();
    }

    public void assertThatLinkImageElementIsDisplayedTrue() {
        assertThat(linkImageElement.isDisplayed()).as("Link is not visible").isTrue();
    }

    public void assertThatTitlePageIsCorrect(String expected) {
        assertThat(driver.getTitle()).as("Page title is wrong").isEqualTo(expected + " - Google Search");
    }

    public void assertThatAllSearchResultHeaderContainsText(String expected) {
        wait.until(ExpectedConditions.visibilityOf(headersSearchResultList.get(0)));

        Iterator<WebElement> namesIterator = headersSearchResultList.iterator();
        boolean allContains = true;
        while (namesIterator.hasNext()) {
            String t = namesIterator.next().getText().toLowerCase(Locale.ROOT);
            if (!t.isEmpty() && !t.contains(expected)) allContains = false;
        }

        assertThat(allContains).as("Not all search links contain string " + expected).isTrue();
    }
}
