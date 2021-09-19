package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Photo;
import model.TableRates;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PhotoApiRepository implements PhotoRepository{
    public static final String URI_1 = "https://jsonplaceholder.typicode.com/photos";
    private static final ObjectMapper mapper = new ObjectMapper();
    @Override
    public List<Photo> findAll() {
        try {
            HttpRequest request = HttpRequest
                    .newBuilder(new URI(URI_1))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Photo[] photos = mapper.readValue(response.body(), Photo[].class);
            return Arrays.asList(photos);
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
