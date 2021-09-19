package service;

import model.CurrencyRate;

import java.math.BigDecimal;
import java.util.Optional;

public interface CurrencyRateService {
    BigDecimal calculateToPLN(BigDecimal amount, CurrencyRate currencyRate);
    Optional<CurrencyRate> findRateByCode(String code);
}
