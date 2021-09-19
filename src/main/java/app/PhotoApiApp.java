package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Photo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class PhotoApiApp {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder(new URI("https://jsonplaceholder.typicode.com/photos"))
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("*************************************");
        Photo[] photos = mapper.readValue(response.body(), Photo[].class);

        System.out.println("Liczba pobranych fotografii " + photos.length);
        System.out.println(Arrays.toString(photos));
    }
}
