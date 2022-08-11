package ru.netology.Page;

import static com.codeborne.selenide.Selenide.$x;

public class ButtonPage {


    public   PaymentPage paymentPage() {
        $x("//span[text() =\"Купить\"] ").click();
        return new PaymentPage();

    }

    public   static CreditPage creditPage () {
        $x("//span[text() =\"Купить в кредит\"] ").click();
        return new CreditPage();


    }


}
