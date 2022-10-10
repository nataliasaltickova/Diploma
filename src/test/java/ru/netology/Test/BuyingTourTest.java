package ru.netology.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.Data.DBHelper;
import ru.netology.Data.DataHelper;
import ru.netology.Page.CreditPage;
import ru.netology.Page.MainPage;
import ru.netology.Page.PaymentPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class BuyingTourTest {

    MainPage buttonPage;


    @BeforeEach

    void setup() {
        //DBHelper.cleanUpDB();
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
         //String actual = DBHelper.getLastStatus();
         //assert ()//TODO status

    }

    @Test
    void shouldBuyingTourWithCardPaymentInValidDateYear() {
        PaymentPage paymentPage = buttonPage.clickButton();
        paymentPage.paymentPageInValidDateYearCard();

        $("[class=\"input__sub\"]").shouldBe(Condition.text("Истёк срок действия карты"), Duration.ofSeconds(15));
    }

    @Test
    void shouldBuyingTourWithCardPaymentInValidDateMonth() {
        PaymentPage paymentPage = buttonPage.clickButton();
        paymentPage.paymentPageInValidDateMonthCard();

        $("[class=\"input__sub\"]").shouldBe(Condition.text("Неверно указан срок действия карты"), Duration.ofSeconds(15));
    }

    @Test
    void shouldBuyingTourWithInvalidCardPayment() {
        PaymentPage paymentPage = buttonPage.clickButton();
        paymentPage.paymentPageInValidCard();

        $$(".notification__title").get(1).shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15));
        $$(".notification__content").get(1).shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }
    @Test
    //TODO bag 4 -Issie!
    void shouldBuyingTourPaymentValidCardWithInvalidCode() {
        PaymentPage paymentPage = buttonPage.clickButton();
        paymentPage.paymentPageValidCardWithInvalidCode();

        $$(".notification__title").get(1).shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15));
        $$(".notification__content").get(1).shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }
    @Test
        //TODO bag 6 -Issie!
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
        //TODO bag 3 -Issue!!
    void shouldBuyingTourWithInvalidNameNumberCardPayment() {
        PaymentPage paymentPage = buttonPage.clickButton();
        paymentPage.paymentPageValidNumberCardWithInValidNameNumber();

        $$(".notification__title").get(1).shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15));
        $$(".notification__content").get(1).shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }
    @Test
        //TODO bag 7 -Issue!!
    void shouldBuyingTourWithInvalidNameNumberCardCredit() {
        CreditPage creditPage = buttonPage.clickButtonOther();
        creditPage.creditPageValidCardInvalidNameNumber();

        $$(".notification__title").get(1).shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15));
        $$(".notification__content").get(1).shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }
    @Test
        //TODO bag 7 -Issue!!
    void shouldBuyingTourValidCardCreditWithInvalidNameSimbol() {
        CreditPage creditPage = buttonPage.clickButtonOther();
        creditPage.creditPageValidCardInvalidNameSimbol();

        $$(".notification__title").get(1).shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15));
        $$(".notification__content").get(1).shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }
    @Test
        //TODO bag 4 -Issue!!
    void shouldBuyingTourWithInvalidNameSimbolCardPayment() {
        PaymentPage paymentPage = buttonPage.clickButton();
        paymentPage.paymentPageValidNumberCardWithInValidNameSimbol();

        $$(".notification__title").get(1).shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15));
        $$(".notification__content").get(1).shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }

    @Test
    void shouldBuyingTourWithCreditCardInValidDateYar() {
        CreditPage creditPage = buttonPage.clickButtonOther();
        creditPage.creditPageInValidDateYearCard();

        $("[class=\"input__sub\"]").shouldBe(Condition.text("Истёк срок действия карты"), Duration.ofSeconds(15));
    }

    @Test
    void shouldBuyingTourWithCreditCardInValidDateMonth() {
        CreditPage creditPage = buttonPage.clickButtonOther();
        creditPage.creditPageInValidDateMonthCard();

        $("[class=\"input__sub\"]").shouldBe(Condition.text("Неверно указан срок действия карты"), Duration.ofSeconds(15));
    }

}
