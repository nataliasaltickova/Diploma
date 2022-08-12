package ru.netology.Page;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {


    public   PaymentPage clickButton() {
        $x("//span[text() =\"Купить\"] ").click();
        return new PaymentPage();

    }

    public    CreditPage clickButtonOther() {
        $x("//span[text() =\"Купить в кредит\"] ").click();
        return new CreditPage();


    }


}
