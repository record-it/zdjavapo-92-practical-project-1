package app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.CurrencyRate;
import model.TableRates;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class NBPApiCurrencyRates {
    //URL do wielu tabel
    public static final String URI_2 = "http://api.nbp.pl/api/exchangerates/tables/a/2012-01-01/2012-01-31/?format=json";
    //URL do jednej tabeli
    public static final String URI_1 = "https://api.nbp.pl/api/exchangerates/tables/A?format=json";
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder(new URI(URI_1))
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("*************************************");
        TableRates[] ratesTable = mapper.readValue(response.body(), TableRates[].class);

        System.out.println("Liczba pobranych tablic: " + ratesTable.length);
        System.out.println(ratesTable[0].getRates());

        String code = "USD";
        double amout = 100_000_000.0;

        List<CurrencyRate> kursy = ratesTable[0].getRates();
        for(CurrencyRate rate: kursy){
            if (rate.getCode().equals(code)){
                double pln = calculateToPLN(amout, rate);
                System.out.println("Kwota w pln " + pln);
                System.out.println("Kwota w pln (jako BigDecimal) " + calculateToPLN( new BigDecimal("100000000"), rate));
            }
        }
    }

    public static double calculateToPLN(double amount, CurrencyRate currencyRate){
        return currencyRate.getMid() * amount;
    }

    public  static BigDecimal calculateToPLN(BigDecimal amount, CurrencyRate currencyRate){
        BigDecimal mid = new BigDecimal(currencyRate.getMid());
        return amount.multiply(mid);
    }
}
