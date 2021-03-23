package org.iainuk.tidecalculator;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
public class Geocoder {

    private static final String GEOCODE_RESOURCE = "http://www.mapquestapi.com/geocoding/v1/address?key=";
    private static final String API_KEY = System.getenv("GEO_API_KEY");

    public String GeocodeSync(String address) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();

        String encodedQuery = URLEncoder.encode(address, "UTF-8");
        String requestUri = GEOCODE_RESOURCE + API_KEY + "&inFormat=kvp&outFormat=json&location=" + encodedQuery
                + "&thumbMaps=false";

        HttpRequest geocodingRequest = HttpRequest.newBuilder().GET().uri(URI.create(requestUri))
                .timeout(Duration.ofMillis(2000)).build();

        HttpResponse geocodingResponse = httpClient.send(geocodingRequest,
                HttpResponse.BodyHandlers.ofString());

        return (String) geocodingResponse.body();
    }
}
