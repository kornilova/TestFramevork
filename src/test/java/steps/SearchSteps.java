package steps;


import pages.SearchPage;

public class SearchSteps {

    private SearchPage searchPage = new SearchPage();

    public SearchResultSteps executeSearchByKeyword(String text) throws InterruptedException {
        searchPage.pasteToSearchField(text);
        //searchPage.fillSearchField(text);
        searchPage.clickSearchButtonOrPressEnter();
        Thread.sleep(5000);
        return new SearchResultSteps();
    }

    public SearchSteps openSearchTooltip()
    {
        searchPage.moveToVoiceSearchButton();
        return this;
    }

    public SearchSteps verifyThatVoiceSearchTooltipContainsText(String text)
    {
        searchPage.assertThatVoiceSearchTooltipContainsText(text);
        return this;
    }
}
