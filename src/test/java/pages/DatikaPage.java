package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class DatikaPage {

    public DatikaPage openPage() {

        String datikaLandingPageURL = "https://datika.me/";

        open(datikaLandingPageURL);
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    private final SelenideElement
            inputSearchQuery = $("#search");

    public DatikaPage setSearchQuere(String searchQuery) {
        inputSearchQuery.setValue(searchQuery).pressEnter();
        return this;
    }
}