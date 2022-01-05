package steps;

import pages.SearchResultsPage;

public class SearchResultSteps {
    private SearchResultsPage searchResultsPage = new SearchResultsPage();

    public SearchResultSteps verifyThatLinkImageElementIsDisplayedTrue(String text) {
        searchResultsPage.assertThatLinkImageElementIsDisplayedTrue();
        return this;
    }

    public SearchResultSteps verifyThatTitlePageIsCorrect(String text) {
        searchResultsPage.assertThatTitlePageIsCorrect(text);
        return this;
    }

    public SearchResultSteps verifyThatAllSearchResultHeaderContainsText(String text) {
        searchResultsPage.assertThatAllSearchResultHeaderContainsText(text);
        return this;
    }


}
