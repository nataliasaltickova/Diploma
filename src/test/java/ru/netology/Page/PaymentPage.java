package ru.netology.Page;


import ru.netology.Data.DataHelper;


import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static ru.netology.Data.DataHelper.*;

public class PaymentPage {

    //TODO con visible

    public void paymentPage() {
        $$(".input__control").get(CARD_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getNumber());
        $$(".input__control").get(MONTH_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getMonth());
        $$(".input__control").get(JAR_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getJar());
        $$(".input__control").get(NAME_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getName());
        $$(".input__control").get(CODE_INPUT_INDEX).setValue(DataHelper.getValidCardInfo().getCode());
        $x("//span[text() =\"Продолжить\"] ").click();

    }

    public void notPaymentPage() {
        $$(".input__control").get(CARD_INPUT_INDEX).setValue(DataHelper.getInvalidCardInfo().getNumber());
        $$(".input__control").get(MONTH_INPUT_INDEX).setValue(DataHelper.getInvalidCardInfo().getMonth());
        $$(".input__control").get(JAR_INPUT_INDEX).setValue(DataHelper.getInvalidCardInfo().getJar());
        $$(".input__control").get(NAME_INPUT_INDEX).setValue(DataHelper.getInvalidCardInfo().getName());
        $$(".input__control").get(CODE_INPUT_INDEX).setValue(DataHelper.getInvalidCardInfo().getCode());
        $x("//span[text() =\"Продолжить\"] ").click();
    }
}

