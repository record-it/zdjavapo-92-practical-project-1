package repository;

import model.CurrencyRate;

import java.util.List;

public interface CurrencyRateRepository {
    List<CurrencyRate> findAll();
}
