package ru.netology.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.Data.Card;
import ru.netology.Data.DBHelper;
import ru.netology.Data.Requests;
import ru.netology.Page.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestTest {
    MainPage buttonPage;
    //сначала перед запуском тестов нужно выбрать одну из БД:
    String DB_URL = DBHelper.DB_URL_POSTGRESQL;
    //String DB_URL = DBHelper.DB_URL_MYSQL;


    @BeforeEach
    void setup() {
        DBHelper.cleanUpCredit(DB_URL);
        DBHelper.cleanUpPayment(DB_URL);

    }

    @Test
    void shouldBuyingTourWithCardPayment() {
        Card validCard = Card.builder()
                .number("4444 4444 4444 4441")
                .month("09")
                .year("23")
                .holder("Ivanov Ivan")
                .cvc("999").build();
        Requests.requestPay(validCard);
        //проверка того, как прошел платеж в БД
        assertEquals("APPROVED", DBHelper.getLastStatusPayment(DB_URL));
    }

    @Test
    void shouldBuyingTourWithCardPaymentCorrectAmount() {
        Card validCard = Card.builder()
                .number("4444 4444 4444 4441")
                .month("09")
                .year("23")
                .holder("Ivanov Ivan")
                .cvc("999").build();
        Requests.requestPay(validCard);
        //проверка того, в какой сумме прошел платеж в БД
        assertEquals(45000, DBHelper.getLastAmountPayment(DB_URL));
    }

    @Test
    void shouldBuyingTourWithCardCredit() {
        Card validCard = Card.builder()
                .number("4444 4444 4444 4441")
                .month("09")
                .year("23")
                .holder("Ivanov Ivan")
                .cvc("999").build();
        Requests.requestCredit(validCard);
        //проверка того, как прошел платеж в БД
        assertEquals("APPROVED", DBHelper.getLastStatusCredit(DB_URL));
    }

    @Test
    void shouldBuyingTourWithCardPaymentInValidDateYear() {
        Card invalidYearCard = Card.builder()
                .number("4444 4444 4444 4441")
                .month("09")
                .year("21")
                .holder("Ivanov Ivan")
                .cvc("999").build();
        Requests.requestPay(invalidYearCard);
        //проверка БД -ничего не должно записаться)
        assertEquals("", DBHelper.getLastStatusPayment(DB_URL));
    }

    @Test
    void shouldBuyingTourWithCardPaymentInValidDateMonth() {
        Card invalidMonthCard = Card.builder()
                .number("4444 4444 4444 4441")
                .month("09")
                .year("22")
                .holder("Ivanov Ivan")
                .cvc("999").build();
        Requests.requestPay(invalidMonthCard);
        //проверка БД -ничего не должно записаться)
        assertEquals("", DBHelper.getLastStatusPayment(DB_URL));
    }

    @Test
    void shouldBuyingTourWithInvalidCardPayment() {
        Card invalidBlockedCard = Card.builder()
                .number("4444 4444 4444 4442")
                .month("09")
                .year("23")
                .holder("Ivanov Ivan")
                .cvc("999").build();
        Requests.requestPay(invalidBlockedCard);
        //проверка того, как прошел платеж в БД)
        assertEquals("DECLINED", DBHelper.getLastStatusPayment(DB_URL));
    }

    @Test
    void shouldBuyingTourWithCardPaymentWithInvalidCode() {
        Card invalidCodeCard = Card.builder()
                .number("4444 4444 4444 4441")
                .month("09")
                .year("23")
                .holder("Ivanov Ivan")
                .cvc("000").build();
        Requests.requestPay(invalidCodeCard);
        //проверка того, как прошел платеж в БД -должно быть отклонено, но в БД -одобрено!)
        assertEquals("DECLINED", DBHelper.getLastStatusPayment(DB_URL));
    }

    @Test
    void shouldBuyingTourWithCardCreditInvalidCode() {
        Card invalidCodeCard = Card.builder()
                .number("4444 4444 4444 4441")
                .month("09")
                .year("23")
                .holder("Ivanov Ivan")
                .cvc("000").build();
        Requests.requestCredit(invalidCodeCard);
        //проверка того, как прошел платеж в БД --должно быть отклонено, но в БД -одобрено!)
        assertEquals("DECLINED", DBHelper.getLastStatusCredit(DB_URL));
    }

    @Test
    void shouldBuyingTourWithAnotherCardPayment() {
        Card anotherCard = Card.builder()
                .number("2222 2222 2222 2222")
                .month("09")
                .year("23")
                .holder("Ivanov Ivan")
                .cvc("999").build();
        Requests.requestPay(anotherCard);
        //проверка БД -ничего н должно записаться)
        assertEquals("", DBHelper.getLastStatusPayment(DB_URL));
    }

    @Test
    void shouldBuyingTourWithInvalidCardCredit() {
        Card invalidCard = Card.builder()
                .number("4444 4444 4444 4442")
                .month("09")
                .year("23")
                .holder("Ivanov Ivan")
                .cvc("999").build();
        Requests.requestCredit(invalidCard);
        //проверка того, как прошел платеж в БД --должно быть отклонено и факт. -отклонено)
        assertEquals("DECLINED", DBHelper.getLastStatusCredit(DB_URL));
    }

    @Test
    void shouldBuyingTourWithAnotherCardCredit() {
        Card anotherCard = Card.builder()
                .number("2222 2222 2222 2222")
                .month("09")
                .year("23")
                .holder("Ivanov Ivan")
                .cvc("999").build();
        Requests.requestCredit(anotherCard);
        //проверка БД -ничего н должно записаться)
        assertEquals("", DBHelper.getLastStatusCredit(DB_URL));
    }

    @Test
    void shouldBuyingTourCardPaymentWithInvalidNameAsNumber() {
        Card invalidNameNumberCard = Card.builder()
                .number("4444 4444 4444 4441")
                .month("09")
                .year("23")
                .holder("1234567")
                .cvc("999").build();
        Requests.requestPay(invalidNameNumberCard);
        //проверка того, как прошел платеж в БД -должно быть отклонено, но в БД -одобрено!)
        assertEquals("DECLINED", DBHelper.getLastStatusPayment(DB_URL));
    }

    @Test
    void shouldBuyingTourCardCreditWithInvalidNameAsNumber() {
        Card invalidNameNumberCard = Card.builder()
                .number("4444 4444 4444 4441")
                .month("09")
                .year("23")
                .holder("1234567")
                .cvc("999").build();
        Requests.requestCredit(invalidNameNumberCard);
        //проверка того, как прошел платеж в БД -должно быть отклонено, но в БД -одобрено!)
        assertEquals("DECLINED", DBHelper.getLastStatusCredit(DB_URL));
    }

    @Test
    void shouldBuyingTourCardPaymentWithInvalidNameAsSymbol() {
        Card invalidNameSymbolCard = Card.builder()
                .number("4444 4444 4444 4441")
                .month("09")
                .year("23")
                .holder("*&^^%")
                .cvc("999").build();
        Requests.requestPay(invalidNameSymbolCard);
        //проверка того, как прошел платеж в БД -должно быть отклонено, но в БД -одобрено!)
        assertEquals("DECLINED", DBHelper.getLastStatusPayment(DB_URL));
    }

    @Test
    void shouldBuyingTourCardCreditWithInvalidNameAsSymbol() {
        Card invalidNameSymbolCard = Card.builder()
                .number("4444 4444 4444 4441")
                .month("09")
                .year("23")
                .holder("*&^^%")
                .cvc("999").build();
        Requests.requestCredit(invalidNameSymbolCard);
        //проверка того, как прошел платеж в БД -должно быть отклонено, но в БД -одобрено!)
        assertEquals("DECLINED", DBHelper.getLastStatusCredit(DB_URL));
    }

    @Test
    void shouldBuyingTourWithCardCreditInValidDateMonth() {
        Card invalidMonthCard = Card.builder()
                .number("4444 4444 4444 4441")
                .month("09")
                .year("22")
                .holder("Ivanov Ivan")
                .cvc("999").build();
        Requests.requestCredit(invalidMonthCard);
        //проверка БД -ничего н должно записаться)
        assertEquals("", DBHelper.getLastStatusCredit(DB_URL));
    }

    @Test
    void shouldBuyingTourWithCardCreditInValidDateYear() {
        Card invalidYearCard = Card.builder()
                .number("4444 4444 4444 4441")
                .month("09")
                .year("21")
                .holder("Ivanov Ivan")
                .cvc("999").build();
        Requests.requestCredit(invalidYearCard);
        //проверка БД -ничего н должно записаться)
        assertEquals("", DBHelper.getLastStatusCredit(DB_URL));
    }
}
