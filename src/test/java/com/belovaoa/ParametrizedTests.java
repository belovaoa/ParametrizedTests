package com.belovaoa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("DemoQA book store tests")
public class ParametrizedTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1900x1000";
        open("https://demoqa.com/books");
    }

    @ValueSource(strings = {"java", "git"})
    // @DisplayName("Параметризованный тест на поиск на https://demoqa.com/books")
    @Tag("Medium")
    @ParameterizedTest(name = "Поиск на https://demoqa.com/books по слову {0}")
    void searchDemoqaValueTest(String searchQuery) {
        $("#searchBox").setValue(searchQuery).click();
        $$(".rt-tbody").shouldHave(texts(searchQuery));
    }

    @CsvSource(value = {
            "java| Learning JavaScript Design Patterns",
            "git| Git Pocket Guide"
    },
            delimiter = '|')
    @Tag("Medium")
    @ParameterizedTest(name = "Поиск на https://demoqa.com/books по слову {0} и проверка отображения текста {1}")
    void searchDemoqaCsvStringValueTest(String searchQuery, String expectedResult) {
        $("#searchBox").setValue(searchQuery).click();
        $$(".rt-tbody").shouldHave(texts(expectedResult));
    }

    static Stream<Arguments> searchDemoqaMethodSourceTest() {
        return Stream.of(
                Arguments.of("java", Arrays.asList("Learning JavaScript Design Patterns")),
                Arguments.of("git", Arrays.asList("Git Pocket Guide"))
        );
    }

    @MethodSource
    @Tag("Medium")
    @ParameterizedTest(name = "Поиск на https://demoqa.com/books по слову {0} и проверка отображения текста {1}")
    void searchDemoqaMethodSourceTest(String searchQuery, List<String> expectedResult) {
        $("#searchBox").setValue(searchQuery).click();
        $$(".rt-tbody").shouldHave(texts(expectedResult.get(0)));
    }

    @EnumSource(SearchQueryDemoqa.class)
    @Tag("Medium")
    @ParameterizedTest(name = "Поиск на https://demoqa.com/books по слову {0}")
    void searchDemoqaEnumValuesTest(SearchQueryDemoqa searchQueryDemoqa) {
            $("#searchBox").setValue(String.valueOf(searchQueryDemoqa)).click();
            $$(".rt-tbody").shouldHave(texts(String.valueOf(searchQueryDemoqa)));
    }
}
