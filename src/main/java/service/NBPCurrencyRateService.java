package service;

import model.CurrencyRate;
import repository.CurrencyRateRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class NBPCurrencyRateService implements CurrencyRateService{
    private final CurrencyRateRepository rateRepository;

    public NBPCurrencyRateService(CurrencyRateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public BigDecimal calculateToPLN(BigDecimal amount, CurrencyRate currencyRate) {
        BigDecimal mid = new BigDecimal(currencyRate.getMid());
        return mid.multiply(amount);
    }

    @Override
    public Optional<CurrencyRate> findRateByCode(String code) {
        return rateRepository.findAll()
                .stream()
                .filter(rate -> rate.getCode().equals(code))
                .findFirst();
    }
}
