package ru.netology.Data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Card {
    public String number;
    public String month;
    public String year;
    public String holder;
    public String cvc;
}
