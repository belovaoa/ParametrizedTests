package com.belovaoa.exerciseexample;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.*;

public class YatestExampleExerciseTest {

    @DisplayName("Поиск на https://yandex.ru/ слова Selenide")
    @Tag("BLOCKER")
    @Test
    void selenideSearchTest() {
        open("https://yandex.ru/");
        $("#text").setValue("Selenide");
        $("button[type='submit']").click();
        $$("li.serp-item").find(Condition.text("Selenide"))
                .shouldBe(Condition.visible);
    }

    @ValueSource(strings = {"Selenide", "Allure"})
    // @DisplayName("Параметризованный тест на поиск на https://yandex.ru/")
    @Tag("BLOCKER")
    @ParameterizedTest(name = "Поиск на https://yandex.ru/ слова {0}")
    void parametrizedSearchYaRuTest(String searchQuery) {
        open("https://yandex.ru/");
        $("#text").setValue(searchQuery);
        $("button[type='submit']").click();
        $$("li.serp-item").find(Condition.text(searchQuery))
                .shouldBe(Condition.visible);
    }

    // На запуск одного теста предоставляется 2 аргумента, 2 стринги и они разделены запятой
    @CsvSource(value = {
            "Selenide| лаконичные и стабильные UI тесты на Java",
            "Allure| Beauty Tips, Trends & Product Reviews"
    },
            delimiter = '|')
    @Tag("BLOCKER")
    @ParameterizedTest(name = "Поиск на https://yandex.ru/ слова {0} и проверка отображения текста {1}")
    void commonSearchYaRuTest(String searchQuery, String expectedResult) {
        open("https://yandex.ru/");
        $("#text").setValue(searchQuery);
        $("button[type='submit']").click();
        $$("li.serp-item").find(Condition.text(expectedResult))
                .shouldBe(Condition.visible);
    }

    @Disabled
    @CsvSource(value = {
            "Selenide| 1",
            "Allure| 1"
    },
            delimiter = '|')
    @Tag("BLOCKER")
    @ParameterizedTest(name = "Поиск на https://yandex.ru/ слова {0} и проверка отображения текста {1}")
    void commonSearchYaRuTest(String searchQuery, int expectedResult) {
        open("https://yandex.ru/");
        $("#text").setValue(searchQuery);
        $("button[type='submit']").click();
        $$("li.serp-item").find(Condition.text(String.valueOf(expectedResult)))
                .shouldBe(Condition.visible);
    }

    @CsvSource(value = {
            "Selenide| SELENIDE",
            "Allure| ALLURE"
    },
            delimiter = '|')
    @Tag("BLOCKER")
    @ParameterizedTest(name = "Поиск на https://yandex.ru/ слова {0} и проверка отображения текста {1}")
    void commonSearchYaRuWithEnumTest(String searchQuery, SearchQuery expectedResult) {
        open("https://yandex.ru/");
        $("#text").setValue(searchQuery);
        $("button[type='submit']").click();
        $$("li.serp-item").find(Condition.text(expectedResult.name()))
                .shouldBe(Condition.visible);
    }
}
