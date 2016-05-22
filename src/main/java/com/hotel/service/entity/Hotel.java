package com.hotel.service.entity;

import java.math.BigDecimal;

/**
 * Created by vinil jose on 21/05/16.
 * Representing the business entity.
 */
public class Hotel {
    private String city;
    private String room;
    private Long hotelId;
    private BigDecimal price;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "city='" + city + '\'' +
                ", room='" + room + '\'' +
                ", hotelId=" + hotelId +
                ", price=" + price +
                '}';
    }
}
