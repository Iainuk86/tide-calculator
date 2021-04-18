package dev.iainmcintosh.tidecalculator.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.iainmcintosh.tidecalculator.Geocoder;
import dev.iainmcintosh.tidecalculator.TideCoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TideServiceImpl implements TideService {

    @Autowired
    private Geocoder geocoder;

    @Autowired
    private TideCoder tideCoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String getLatLong(String address) throws IOException, InterruptedException {

        String response = geocoder.GeocodeSync(address);
        JsonNode responseJsonNode = objectMapper.readTree(response);

        String lat = responseJsonNode.at("/results/0/locations/0/latLng/lat").asText();
        String lng = responseJsonNode.at("/results/0/locations/0/latLng/lng").asText();

        return "&lat=" + lat + "&lon=" + lng;
    }

    @Override
    public String getTideLevel(String latLong) throws IOException, InterruptedException
    {
        String tideResponse = tideCoder.TideSync(latLong);
        JsonNode tideResponseJsonNode = objectMapper.readTree(tideResponse);

        String station = tideResponseJsonNode.at("/station").asText();
        String height = tideResponseJsonNode.at("/heights/12/height").asText();

        return "Nearest Tide Station: " + station + ".   Height relative to datum: " + height + " meters.";
    }
}
