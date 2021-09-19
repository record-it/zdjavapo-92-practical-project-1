package model;

import lombok.Data;

@Data
public class CurrencyRate {
    private String currency;
    private String code;
    private double mid;
}
