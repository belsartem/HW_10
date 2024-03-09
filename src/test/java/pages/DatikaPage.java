package pages;

import static com.codeborne.selenide.Selenide.*;

public class DatikaPage {

    public DatikaPage openPage() {

        String datikaLandingPageURL = "https://datika.me/";

        open(datikaLandingPageURL);
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }
}