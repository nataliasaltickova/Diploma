package ru.netology.Test;

import com.codeborne.selenide.Condition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.Data.DBHelper;
import ru.netology.Page.CreditPage;
import ru.netology.Page.MainPage;
import ru.netology.Page.PaymentPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class BuyingTourTest {
    //сначала перед запуском тестов нужно выбрать одну из БД:
    String DB_URL = DBHelper.DB_URL_POSTGRESQL;
    //String DB_URL = DBHelper.DB_URL_MYSQL;

    MainPage buttonPage;


    @BeforeEach
    void setup() {
        DBHelper.cleanUpCredit(DB_URL);
        DBHelper.cleanUpPayment(DB_URL);
        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080");
        buttonPage = new MainPage();
    }

    @Test
    void shouldBuyingTourWithCardPayment() {
        PaymentPage paymentPage = buttonPage.clickButton();
        paymentPage.paymentPageValidCard();
        $("[class=\"notification__title\"]").shouldBe(Condition.text("Успешно"), Duration.ofSeconds(15));
        $("[class=\"notification__content\"]").shouldBe(Condition.text("Операция одобрена Банком."));
    }

    @Test
    void shouldBuyingTourWithCardPaymentCorrectAmount() {
        PaymentPage paymentPage = buttonPage.clickButton();
        paymentPage.paymentPageValidCard();
        $("[class=\"notification__title\"]").shouldBe(Condition.text("Успешно"), Duration.ofSeconds(15));
        $("[class=\"notification__content\"]").shouldBe(Condition.text("Операция одобрена Банком."));
        //проверка корректности списанной суммы -
        assertEquals(45000, DBHelper.getLastAmountPayment(DB_URL));
    }

    @Test
    void shouldBuyingTourWithCardPaymentInValidDateYear() {
        PaymentPage paymentPage = buttonPage.clickButton();
        paymentPage.paymentPageInValidDateYearCard();
        $("[class=\"input__sub\"]").shouldBe(Condition.text("Истёк срок действия карты"), Duration.ofSeconds(15));
        //проверка БД -ничего н должно записаться)
        assertEquals("", DBHelper.getLastStatusPayment(DB_URL));
    }

    @Test
    void shouldBuyingTourWithCardPaymentInValidDateMonth() {
        PaymentPage paymentPage = buttonPage.clickButton();
        paymentPage.paymentPageInValidDateMonthCard();
        $("[class=\"input__sub\"]").shouldBe(Condition.text("Неверно указан срок действия карты"), Duration.ofSeconds(15));
        //проверка БД -ничего н должно записаться)
        assertEquals("", DBHelper.getLastStatusPayment(DB_URL));
    }

    @Test
    void shouldBuyingTourWithInvalidCardPayment() {
        PaymentPage paymentPage = buttonPage.clickButton();
        paymentPage.paymentPageInValidCard();
        $$(".notification__title").get(1).shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15));
        $$(".notification__content").get(1).shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }

    @Test
    void shouldBuyingTourPaymentValidCardWithInvalidCode() {
        PaymentPage paymentPage = buttonPage.clickButton();
        paymentPage.paymentPageValidCardWithInvalidCode();
        $$(".notification__title").get(1).shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15));
        $$(".notification__content").get(1).shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }

    @Test
    void shouldBuyingTourCreditValidCardWithInvalidCode() {
        CreditPage creditPage = buttonPage.clickButtonOther();
        creditPage.creditPageValidCardInvalidCode();
        $$(".notification__title").get(1).shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15));
        $$(".notification__content").get(1).shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }

    @Test
    void shouldBuyingTourWithAnotherCardPayment() {
        PaymentPage paymentPage = buttonPage.clickButton();
        paymentPage.paymentPageAnotherCard();

        $$(".notification__title").get(1).shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15));
        $$(".notification__content").get(1).shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }

    @Test
    void shouldBuyingTourWithCardCredit() {
        CreditPage creditPage = buttonPage.clickButtonOther();
        creditPage.creditPageValidCard();
        $("[class=\"notification__title\"]").shouldBe(Condition.text("Успешно"), Duration.ofSeconds(15));
        $("[class=\"notification__content\"]").shouldBe(Condition.text("Операция одобрена Банком."));
    }

    @Test
    void shouldBuyingTourWithInvalidCardCredit() {
        CreditPage creditPage = buttonPage.clickButtonOther();
        creditPage.creditPageInValidCard();
        $$(".notification__title").get(1).shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15));
        $$(".notification__content").get(1).shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }

    @Test
    void shouldBuyingTourWithAnotherCardCredit() {
        CreditPage creditPage = buttonPage.clickButtonOther();
        creditPage.creditPageAnotherCard();
        $$(".notification__title").get(1).shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15));
        $$(".notification__content").get(1).shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }

    @Test
    void shouldBuyingTourCardPaymentWithInvalidNameAsNumber() {
        PaymentPage paymentPage = buttonPage.clickButton();
        paymentPage.paymentPageValidNumberCardWithInValidNameAsNumber();
        $$(".notification__title").get(1).shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15));
        $$(".notification__content").get(1).shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }

    @Test
    void shouldBuyingTourCardCreditWithInvalidNameAsNumber() {
        CreditPage creditPage = buttonPage.clickButtonOther();
        creditPage.creditPageValidCardWithInvalidNameAsNumber();
        $$(".notification__title").get(1).shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15));
        $$(".notification__content").get(1).shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }

    @Test
    void shouldBuyingTourCardCreditWithInvalidNameAsSymbol() {
        CreditPage creditPage = buttonPage.clickButtonOther();
        creditPage.creditPageValidCardWithInvalidNameAsSymbol();
        $$(".notification__title").get(1).shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15));
        $$(".notification__content").get(1).shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }

    @Test
    void shouldBuyingTourCardPaymentWithInvalidNameAsSymbol() {
        PaymentPage paymentPage = buttonPage.clickButton();
        paymentPage.paymentPageValidNumberCardWithInValidNameAsSymbol();
        $$(".notification__title").get(1).shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15));
        $$(".notification__content").get(1).shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }

    @Test
    void shouldBuyingTourCreditCardWithInValidDateYar() {
        CreditPage creditPage = buttonPage.clickButtonOther();
        creditPage.creditPageInValidDateYearCard();

        $("[class=\"input__sub\"]").shouldBe(Condition.text("Истёк срок действия карты"), Duration.ofSeconds(15));
        //проверка БД -ничего н должно записаться)
        assertEquals("", DBHelper.getLastStatusPayment(DB_URL));
    }

    @Test
    void shouldBuyingTourCreditCardWithInValidDateMonth() {
        CreditPage creditPage = buttonPage.clickButtonOther();
        creditPage.creditPageInValidDateMonthCard();

        $("[class=\"input__sub\"]").shouldBe(Condition.text("Неверно указан срок действия карты"), Duration.ofSeconds(15));
        //проверка БД -ничего н должно записаться)
        assertEquals("", DBHelper.getLastStatusPayment(DB_URL));
    }
}
