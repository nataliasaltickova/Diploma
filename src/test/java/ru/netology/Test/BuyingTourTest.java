package ru.netology.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.Page.CreditPage;
import ru.netology.Page.MainPage;
import ru.netology.Page.PaymentPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class BuyingTourTest {

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080");
    }

    @Test
    void shouldBuyingTourWithCardPayment() {
        var buttonPage = new MainPage();
        PaymentPage paymentPage = buttonPage.clickButton();
        paymentPage.paymentPage();

        $("[class=\"notification__title\"]").shouldBe(Condition.text("Успешно"), Duration.ofSeconds(15));
       $("[class=\"notification__content\"]").shouldBe(Condition.text("Операция одобрена Банком."));
    }

    @Test
    void shouldBuyingTourWithInvalidCardPayment() {
        var buttonPage =new MainPage();
        PaymentPage paymentPage =buttonPage.clickButton();
        paymentPage.notPaymentPage();
        //карта должна быть отклонена? отказано в проведении операции?

    }
    @Test
    void shouldBuyingTourWithCardCredit() {
        var buttonPage = new MainPage();
        CreditPage creditPage =buttonPage.clickButtonOther();
        creditPage.creditPage();
        $("[class=\"notification__title\"]").shouldBe(Condition.text("Успешно"), Duration.ofSeconds(15));
        $("[class=\"notification__content\"]").shouldBe(Condition.text("Операция одобрена Банком."));
    }
    @Test
    void shouldBuyingTourWithInvalidCardCredit() {
        var buttonPage = new MainPage();
        CreditPage creditPage = buttonPage.clickButtonOther();
        creditPage.notCreditPage();
        //карта должна быть отклонена? отказано в проведении операции??
    }
    @Test
    void shouldBuyingTourWithRandomCardCredit() {
        var buttonPage =new MainPage();
        CreditPage creditPage = buttonPage.clickButtonOther();
        creditPage.randomCard();
        $$(".notification__title").get(1).shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15));
        $$(".notification__content").get(1).shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }







}
