package ru.stupakov.currencyrate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Stupakov D. L.
 **/
@Data
@AllArgsConstructor
public class CurrencyRate {
    private String id;
    private int numCode;
    private String charCode;
    private int nominal;
    private String name;
    private double value;

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "id='" + id + '\'' +
                ", numCode=" + numCode +
                ", charCode='" + charCode + '\'' +
                ", nominal=" + nominal +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}' + "\n";
    }
}
