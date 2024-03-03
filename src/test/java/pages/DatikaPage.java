package pages;

import static com.codeborne.selenide.Selenide.*;

public class DatikaPage {

    public void openPage(String url) {
        String sSevenPageURL = "https://datika.me/";
        open(sSevenPageURL);
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }
}