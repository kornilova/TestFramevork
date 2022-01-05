package tests;

import org.testng.annotations.Test;

public class Test2 extends BaseTest {

    @Test(dataProvider = "dataProvider")
    public void openWebSite(String text) throws InterruptedException {
        searchSteps.executeSearchByKeyword(text)
                .verifyThatAllSearchResultHeaderContainsText(text)
                .verifyThatLinkImageElementIsDisplayedTrue(text)
                .verifyThatTitlePageIsCorrect(text);
    }

    @Test
    public void verifySearchByVoice() {
        searchSteps.openSearchTooltip()
                .verifyThatVoiceSearchTooltipContainsText("Search by voice");
    }

}
