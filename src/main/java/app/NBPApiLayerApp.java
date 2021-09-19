package app;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import model.CurrencyRate;
import repository.CurrencyRateRepository;
import repository.NBPApiCurrencyRateRepository;
import service.CurrencyRateService;
import service.NBPCurrencyRateService;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Formatter;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

public class NBPApiLayerApp {
    public static CurrencyRateRepository rateRepository = new NBPApiCurrencyRateRepository();
    public static CurrencyRateService rateService = new NBPCurrencyRateService(rateRepository);
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Wpisz kod waluty");
        String code = scanner.next();
        System.out.println("Wpisz kwotę");
        BigDecimal amout = scanner.nextBigDecimal();
        Optional<CurrencyRate> rateByCode = rateService.findRateByCode(code);
        if (rateByCode.isPresent()) {
            BigDecimal resultAmout = rateService.calculateToPLN(amout, rateByCode.get());
            System.out.println("Obliczona kwota w pln " + formatCurrency(resultAmout));
        } else {
            System.out.println("Nieznany kod waluty!");
        }
    }

    public static String formatCurrency(BigDecimal amout){
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(amout);
    }
}
