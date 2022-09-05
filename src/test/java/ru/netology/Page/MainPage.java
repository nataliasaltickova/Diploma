package ru.netology.Page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {


    public PaymentPage clickButton() {
        $x("//span[text() =\"Купить\"] ").click();
        $("[class=\"heading heading_size_m heading_theme_alfa-on-white\"]").shouldBe(Condition.text("Оплата по карте"));
        return new PaymentPage();

    }

    public CreditPage clickButtonOther() {
        $x("//span[text() =\"Купить в кредит\"] ").click();
        $("[class=\"heading heading_size_m heading_theme_alfa-on-white\"]").shouldBe(Condition.text("Кредит по данным карты"));
        return new CreditPage();


    }


}
