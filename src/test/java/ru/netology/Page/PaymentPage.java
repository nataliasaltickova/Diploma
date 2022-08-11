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

    public void notPaymentPage(DataHelper.CardInfo invalidCardInfo) {
        //TODO -изменить
        $$(".input__control").get(CARD_INPUT_INDEX).setValue(invalidCardInfo.getNumber());
        $$(".input__control").get(MONTH_INPUT_INDEX).setValue(invalidCardInfo.getMonth());
        $$(".input__control").get(JAR_INPUT_INDEX).setValue(invalidCardInfo.getJar());
        $$(".input__control").get(NAME_INPUT_INDEX).setValue(invalidCardInfo.getName());
        $$(".input__control").get(CODE_INPUT_INDEX).setValue(invalidCardInfo.getCode());
        $x("//span[text() =\"Продолжить\"] ").click();


    }
}

