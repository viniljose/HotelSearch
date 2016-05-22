package com.hotel.service.gateway.inmemory;

import com.hotel.service.entity.Hotel;
import com.hotel.service.gateway.HotelGateway;
import com.hotel.service.util.CsvLoader;

import java.util.List;

/**
 * Created by vjose on 22/05/16.
 */
public class InMemoryHotelGateway implements HotelGateway {
    @Override
    public List<Hotel> getHotels(String city) {
        return CsvLoader.getCityMap().get(city);
    }
}
