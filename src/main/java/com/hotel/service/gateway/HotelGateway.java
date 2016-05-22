package com.hotel.service.gateway;

import com.hotel.service.entity.Hotel;

import java.util.List;

/**
 * Created by vinil jose on 22/05/16.
 * Responsible for Loading the data from Inmemory database.
 * Currently connecting to Inmemory DB, later can be connected to SQL or NoSQL DBS
 */
public interface HotelGateway {
    List<Hotel> getHotels(String city);
}
