package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.CurrencyRate;
import model.TableRates;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

public class NBPApiCurrencyRateRepository implements CurrencyRateRepository{
    public static final String URI_1 = "https://api.nbp.pl/api/exchangerates/tables/A?format=json";
    private static final ObjectMapper mapper = new ObjectMapper();
    @Override
    public List<CurrencyRate> findAll() {
        try {
            HttpRequest request = HttpRequest
                    .newBuilder(new URI(URI_1))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            TableRates[] table = mapper.readValue(response.body(), TableRates[].class);
            return table[0].getRates();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
