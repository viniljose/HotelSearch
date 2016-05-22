package com.hotel.service.usecase.search;

import com.hotel.service.entity.Hotel;
import com.hotel.service.usecase.UseCaseResponse;

import java.util.List;

/**
 * Created by vinil jose on 22/05/16.
 */
public class SearchUseCaseResponse extends UseCaseResponse{
    List<Hotel> hotels;

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}
