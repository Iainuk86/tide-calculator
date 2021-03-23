package org.iainuk.tidecalculator;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@Component
public class TideCoder {

    private final String TIDE_RESOURCE = "https://www.worldtides.info/api/v2?heights&date=";
    private final String API_KEY = System.getenv("TIDE_API_KEY");

    public String TideSync(String latLong) throws IOException, InterruptedException {

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(date);

        HttpClient httpClient = HttpClient.newHttpClient();

        String requestUri = TIDE_RESOURCE + formattedDate + latLong + "&stationDistance=100&key=" + API_KEY;

        HttpRequest tideCodingRequest = HttpRequest.newBuilder().GET().uri(URI.create(requestUri))
                .timeout(Duration.ofMillis(2000)).build();

        HttpResponse tideCodingResponse = httpClient.send(tideCodingRequest,
                HttpResponse.BodyHandlers.ofString());

        return (String) tideCodingResponse.body();
    }

}
