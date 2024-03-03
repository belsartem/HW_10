package tests;

import data.Language;
import helpers.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.DatikaPage;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DatikaLandingTest extends TestBase {

    DatikaPage datikaPage = new DatikaPage();

    @ValueSource(strings = {
            "s24 ultra", "iphone 14"
    })
    @ParameterizedTest(name = "For Search Request {0} Should Be Returned Not Empty List")
    @Tag("REGRESSION")
    void searchResultsShouldNotBeEmpty(String searchQuery) throws InterruptedException {
        datikaPage.openPage("");
        $("#search").setValue(searchQuery).pressEnter();
        $$x("//*[@class='product-list products_view_grid']//li")
                .shouldBe(sizeGreaterThan(0));
    }

    @EnumSource(Language.class)
    @ParameterizedTest(name = "Datika Online Store Landing Page {0} Should Have Corresponding Offer Title")
    @Tag("SMOKE")
    void datikaSiteShouldDisplayCorrectText(Language languages) throws InterruptedException {
        datikaPage.openPage("");
        $(".country-select").hover();
        $(".country-select").$(byText(languages.name())).click();
        $("h4").shouldHave(text(languages.description));
    }

    static Stream<Arguments> datikaSiteShouldDisplayCorrectMenuButtonsInCategories() {
        return Stream.of(
                Arguments.of(
                        Language.CG,
                        List.of(
                                "Bijela tehnika",
                                "Mali kućni aparati",
                                "Klima uređaji",
                                "Televizori, audio, video, foto tehnika",
                                "Mobilni telefoni, tableti, pametni uređaji",
                                "Laptop, desktop računari, komponente",
                                "Alati, pribor, oprema",
                                "Bašta, dvorište",
                                "Sve za dom",
                                "Lifestyle, sport, putovanje",
                                "Poklon kartice",
                                "AKCIJA")
                ),
                Arguments.of(
                        Language.EN,
                        List.of("Home appliances",
                                "Small appliances",
                                "Air conditioning",
                                "Televizions, audio, video, photo equipment",
                                "Mobil phones, tablets, smart gadgets",
                                "Laptop, desktop computers, components",
                                "Tools, equipment",
                                "Garden, yard",
                                "Housing",
                                "Lifestyle, sport, traveling",
                                "Poklon kartice",
                                "SALE")
                ),
                Arguments.of(
                        Language.RU,
                        List.of(
                                "Бытовая техника",
                                "Малая бытовая техника",
                                "Климатическая техника",
                                "Телевизоры, аудио, видео, фото техника",
                                "Мобильные телефоны, планшеты, умные устройства",
                                "Ноутбуки, компьютеры, компоненты",
                                "Инструмент, оборудование",
                                "Сад, двор",
                                "Все для дома",
                                "Lifestyle, спорт, путешествия",
                                "Poklon kartice",
                                "АКЦИЯ")
                )
        );
    }

    @MethodSource
    @ParameterizedTest
    @Tags({
            @Tag("REGRESSION"),
            @Tag("SMOKE")
    })
    @DisplayName("Datika Online Store Landing Page {0} Should Have Corresponding Categories Menu To Every Language")
    void datikaSiteShouldDisplayCorrectMenuButtonsInCategories(Language languages, List<String> expectedButtons) throws InterruptedException {
        datikaPage.openPage("");
        $(".country-select").hover();
        $(".country-select").$(byText(languages.name())).click();
        $$x("//*[@class='side_block_collapsible side_menu_wrap hidden-xs']//*[@class='menu']//a")
                .filter(visible)
                .shouldHave(texts(expectedButtons));
    }
}