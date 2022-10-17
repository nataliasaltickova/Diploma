package ru.netology.Data;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class Requests {

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    public static void requestPay(Card card) {
        Gson gson = new Gson();
        String userBody = gson.toJson(card);
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(userBody) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/v1/pay") // на какой путь, относительно BaseUri отправляем запрос
                .then(); // "тогда ожидаем"

    }
    public static void requestCredit(Card card) {
        Gson gson = new Gson();
        String userBody = gson.toJson(card);
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(userBody) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/v1/credit") // на какой путь, относительно BaseUri отправляем запрос
                .then(); // "тогда ожидаем"

    }

}

