package com.belovaoa.exerciseexample;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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

    @ValueSource (strings = {"Selenide", "Allure"})
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
}
