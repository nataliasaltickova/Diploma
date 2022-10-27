package ru_netology.Data;

import lombok.Value;

public class DataHelper {
    public static final int CARD_INPUT_INDEX = 0;
    public static final int MONTH_INPUT_INDEX = 1;
    public static final int JAR_INPUT_INDEX = 2;
    public static final int NAME_INPUT_INDEX = 3;
    public static final int CODE_INPUT_INDEX = 4;

    private DataHelper() {

    }

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String name;
        String code;
    }

    public static CardInfo getValidCardInfo() {
        return new CardInfo("4444 4444 4444 4441", "09", "23", "Ivanov Ivan", "999");
    }

    public static CardInfo getInvalidCardInfo() {
        return new CardInfo("4444 4444 4444 4442", "09", "23", "Ivanov Ivan", "999");
    }

    public static CardInfo getAnotherCardInfo() {
        return new CardInfo("2222 2222 2222 2222", "09", "23", "Ivanov Ivan", "999");
    }


}
