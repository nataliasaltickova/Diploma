package ru.netology.Page;

import ru.netology.Data.DataHelper;

import static com.codeborne.selenide.Selenide.$x;

public class CreditPage {
    //TODO  -переделать-
    public void creditGate  (DataHelper.CardInfo validCardInfo) {
        $x("//span [text()placeholder =\"0000_0000_0000_0000\"]").setValue(validCardInfo.getNumber());
        $x("//span [text()placeholder =\"08\"]").setValue(validCardInfo.getMonth());
        $x("//span [text()placeholder =\"22\"]").setValue(validCardInfo.getJar());
        $x("//span [text()autocomplete=\"on\"]").setValue(validCardInfo.getName());
        $x("//span [text()placeholder =\"999\"]").setValue(validCardInfo.getCode());
        $x("//span[text() =\"Продолжить\"] ").click();

    }
    public void notCreditGate (DataHelper.CardInfo invalidCardInfo) {
        $x("//span [text()placeholder =\"0000_0000_0000_0000\"]").setValue(invalidCardInfo.getNumber());
        $x("//span [text()placeholder =\"08\"]").setValue(invalidCardInfo.getMonth());
        $x("//span [text()placeholder =\"22\"]").setValue(invalidCardInfo.getJar());
        $x("//span [text()autocomplete=\"on\"]").setValue(invalidCardInfo.getName());
        $x("//span [text()placeholder =\"999\"]").setValue(invalidCardInfo.getCode());
        $x("//span[text() =\"Продолжить\"] ").click();

    }
}
