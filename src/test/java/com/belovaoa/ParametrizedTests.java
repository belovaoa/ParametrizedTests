package com.belovaoa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
    @ParameterizedTest(name = "Поиск на https://demoqa.com/books {0}")
    void parametrizedSearchDemoqaTest(String searchQuery) {
        $("#searchBox").setValue(searchQuery).click();
        $$(".rt-tbody").shouldHave(texts(searchQuery));
    }
}
