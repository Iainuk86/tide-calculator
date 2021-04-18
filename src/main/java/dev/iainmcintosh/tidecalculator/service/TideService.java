package dev.iainmcintosh.tidecalculator.service;

import java.io.IOException;

public interface TideService {

    String getLatLong(String address) throws IOException, InterruptedException;
    String getTideLevel(String latLong) throws IOException, InterruptedException;
}
