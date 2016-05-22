package com.hotel.service.web.model;

import com.hotel.service.entity.Hotel;

import java.util.List;

/**
 * Created by vinil jose on 21/05/16.
 */
public class SearchResponse {
    private List<Hotel> hotels;

    private String error;

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
