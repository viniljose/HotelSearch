package com.hotel.service.usecase.search;

import com.hotel.service.entity.Hotel;

import java.util.Comparator;

/**
 * Created by vinil jose on 21/05/16.
 */
public class PriceComparator implements Comparator<Hotel> {
    @Override
    public int compare(Hotel o1, Hotel o2) {
        return o1.getPrice().compareTo(o2.getPrice()) ;
    }
}
