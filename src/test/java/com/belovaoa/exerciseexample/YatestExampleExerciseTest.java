package com.belovaoa.exerciseexample;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class YatestExampleExerciseTest {

    @DisplayName("Поиск в ya.ru слова Selenide")
    @Tag("BLOCKER")
    @Test
    void selenideSearchTest() {
        open("https://yandex.ru/");
        $("#text").setValue("Selenide");
        $("button[type='submit']").click();
        $$("li.serp-item").find(Condition.text("Selenide"))
                .shouldBe(Condition.visible);
    }
}
