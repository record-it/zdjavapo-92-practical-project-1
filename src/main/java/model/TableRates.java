package model;

import lombok.Data;
import java.util.List;

@Data
public class TableRates {
    private String table;
    private String no;
    private String effectiveDate;
    private List<CurrencyRate> rates;
}
