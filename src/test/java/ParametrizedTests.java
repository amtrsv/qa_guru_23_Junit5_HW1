import com.codeborne.selenide.CollectionCondition;
import data.Languages;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.List;
import java.util.stream.Stream;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class ParametrizedTests extends TestBase {



    @ValueSource(strings = {
            "Java",
            "Python",
            "JavaScript"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен выдаваться не пустой список карточек")
    @Tag("BLOCKER")
    @Tag("WEB")
    void searchResultsShouldNotBeEmpty(String searchItems) {
        $("[placeholder= 'Поиск…']").setValue(searchItems).pressEnter();
        $$("a.catalog-rich-card__link-wrapper")
                .shouldBe(CollectionCondition.sizeGreaterThan(0));

    }


    @CsvFileSource(resources = "/test_data/searchResultsShouldContainExpectedItems.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} первый результат содержит название курса {1}")
    @Tag("BLOCKER")
    void searchResultsShouldContainExpectedURL(String searchItems, String expectedText) {
        $("[placeholder= 'Поиск…']").setValue(searchItems).pressEnter();
        $(".course-card__title").shouldHave(text(expectedText));

    }

    @EnumSource(Languages.class)
    @ParameterizedTest(name =  "Для поискового запроса результат содержит названия для языка {0}")
    @Tag("WEB")
    void selenideSiteShouldDisplayCorrectText(Languages languages){
        $("#ember23").click();
        $(".drop-down__body").find(byText(languages.name())).click();
        $(".catalog-block__title",0).shouldHave(text(languages.descriptionFirst));
        $(".catalog-block__title",1).shouldHave(text(languages.descriptionSecond));


    }

    static Stream<Arguments> StepikSourceShouldContainsLanguageLists() {
        return Stream.of(
                Arguments.of(Languages.Русский, List.of(
                        "Новые курсы",
                        "Хиты Stepik",
                        "Пакеты курсов",
                        "Актуальные скидки",
                        "Выбирают компании",
                        "Разработка на Python",
                        "Разработка приложений",
                        "Тестирование ПО",
                        "Frontend",
                        "Data Science и ML"
                ) ),
                Arguments.of(Languages.English, List.of(
                        "Editors' choice",
                        "Popular courses",
                        "Computer Science",
                        "Learn English",
                        "Bioinformatics"
                ) )
        );
    }
    @MethodSource
    @ParameterizedTest(name = "Для поискового запроса с языком {0} первый результат содержит тайтлы - {1}")
    void StepikSourceShouldContainsLanguageLists(Languages languages, List<String> expectedButtons){
        $("#ember23").click();
        $(".drop-down__body").find(byText(languages.name())).click();
        $$(".tab__item").shouldHave(texts(expectedButtons));

    }

}

