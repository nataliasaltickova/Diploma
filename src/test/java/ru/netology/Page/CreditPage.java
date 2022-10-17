package ru.netology.Page;

import ru.netology.Data.DataHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static ru.netology.Data.DataHelper.*;

public class CreditPage {

    public void creditPageValidCard() {
        $$(".input__control").get(CARD_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getNumber());
        $$(".input__control").get(MONTH_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getMonth());
        $$(".input__control").get(JAR_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getYear());
        $$(".input__control").get(NAME_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getName());
        $$(".input__control").get(CODE_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getCode());
        $x("//span[text() =\"Продолжить\"] ").click();
    }

    public void creditPageValidCardWithInvalidNameAsNumber() {
        $$(".input__control").get(CARD_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getNumber());
        $$(".input__control").get(MONTH_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getMonth());
        $$(".input__control").get(JAR_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getYear());
        $$(".input__control").get(NAME_INPUT_INDEX).setValue("1234567");
        $$(".input__control").get(CODE_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getCode());
        $x("//span[text() =\"Продолжить\"] ").click();
    }

    public void creditPageValidCardWithInvalidNameAsSymbol() {
        $$(".input__control").get(CARD_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getNumber());
        $$(".input__control").get(MONTH_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getMonth());
        $$(".input__control").get(JAR_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getYear());
        $$(".input__control").get(NAME_INPUT_INDEX).setValue("*&^%");
        $$(".input__control").get(CODE_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getCode());
        $x("//span[text() =\"Продолжить\"] ").click();
    }

    public void creditPageValidCardInvalidCode() {
        $$(".input__control").get(CARD_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getNumber());
        $$(".input__control").get(MONTH_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getMonth());
        $$(".input__control").get(JAR_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getYear());
        $$(".input__control").get(NAME_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getName());
        $$(".input__control").get(CODE_INPUT_INDEX).setValue("000");
        $x("//span[text() =\"Продолжить\"] ").click();
    }

    public void creditPageInValidDateYearCard() {
        $$(".input__control").get(CARD_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getNumber());
        $$(".input__control").get(MONTH_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getMonth());
        $$(".input__control").get(JAR_INPUT_INDEX).setValue(generateExpiredYearDateCard(2));
        $$(".input__control").get(NAME_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getName());
        $$(".input__control").get(CODE_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getCode());
        $x("//span[text() =\"Продолжить\"] ").click();
    }

    public void creditPageInValidDateMonthCard() {
        $$(".input__control").get(CARD_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getNumber());
        $$(".input__control").get(MONTH_INPUT_INDEX).setValue(generateExpiredMonthDateCard(2));
        $$(".input__control").get(JAR_INPUT_INDEX).setValue(generateExpiredYearDateCard(0));
        $$(".input__control").get(NAME_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getName());
        $$(".input__control").get(CODE_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getCode());
        $x("//span[text() =\"Продолжить\"] ").click();
    }

    public void creditPageInValidCard() {
        $$(".input__control").get(CARD_INPUT_INDEX).setValue(DataHelper.getInvalidCardInfo().getNumber());
        $$(".input__control").get(MONTH_INPUT_INDEX).setValue(DataHelper.getInvalidCardInfo().getMonth());
        $$(".input__control").get(JAR_INPUT_INDEX).setValue(DataHelper.getInvalidCardInfo().getYear());
        $$(".input__control").get(NAME_INPUT_INDEX).setValue(DataHelper.getInvalidCardInfo().getName());
        $$(".input__control").get(CODE_INPUT_INDEX).setValue(DataHelper.getInvalidCardInfo().getCode());
        $x("//span[text() =\"Продолжить\"] ").click();
    }

    public void creditPageAnotherCard() {
        $$(".input__control").get(CARD_INPUT_INDEX).setValue(DataHelper.getAnotherCardInfo().getNumber());
        $$(".input__control").get(MONTH_INPUT_INDEX).setValue(DataHelper.getAnotherCardInfo().getMonth());
        $$(".input__control").get(JAR_INPUT_INDEX).setValue(DataHelper.getAnotherCardInfo().getYear());
        $$(".input__control").get(NAME_INPUT_INDEX).setValue(DataHelper.getAnotherCardInfo().getName());
        $$(".input__control").get(CODE_INPUT_INDEX).setValue(DataHelper.getAnotherCardInfo().getCode());
        $x("//span[text() =\"Продолжить\"] ").click();
    }

    public String generateExpiredYearDateCard(int year) {
        return LocalDate.now().minusYears(year).format(DateTimeFormatter.ofPattern("yy"));
    }

    public String generateExpiredMonthDateCard(int month) {
        return LocalDate.now().minusMonths(month).format(DateTimeFormatter.ofPattern("MM"));
    }
}
