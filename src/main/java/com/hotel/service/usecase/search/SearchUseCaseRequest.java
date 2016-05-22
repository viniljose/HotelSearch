package com.hotel.service.usecase.search;

import com.hotel.service.usecase.UseCaseRequest;

/**
 * Created by vinil jose on 22/05/16.
 */
public class SearchUseCaseRequest extends UseCaseRequest{
    private String city;
    private boolean sortDescending;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isSortDescending() {
        return sortDescending;
    }

    public void setSortDescending(boolean sortDescending) {
        this.sortDescending = sortDescending;
    }
}
