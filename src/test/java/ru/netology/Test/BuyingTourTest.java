package ru.netology.Test;

import Page.PaymentGate;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.Data.DataHelper;
import ru.netology.Page.ButtonPage;
import ru.netology.Page.PaymentPage;

import javax.swing.*;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static ru.netology.Data.DataHelper.*;
import static ru.netology.Data.DataHelper.CODE_INPUT_INDEX;

public class BuyingTourTest {

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080");
    }

    @Test
    void shouldBuyingTourWithCardPayment() {
        var buttonPage = new ButtonPage();
        PaymentPage paymentPage = buttonPage.paymentPage();
        paymentPage.paymentPage();



        $("[class=\"notification__title\"]").shouldBe(Condition.text("Успешно"), Duration.ofSeconds(15));
       $("[class=\"notification__content\"]").shouldBe(Condition.text("Операция одобрена Банком."));

    }


    //[class="input__control"
    //var paymentPage = new PaymentPage();
    //var paymentGate = DataHelper.getValidCardInfo();
}
