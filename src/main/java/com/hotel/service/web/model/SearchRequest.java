package com.hotel.service.web.model;

/**
 * Created by vinil jose on 21/05/16.
 */
public class SearchRequest {
    private String city;
    private boolean sortDescending;
    private String apiKey;

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

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
