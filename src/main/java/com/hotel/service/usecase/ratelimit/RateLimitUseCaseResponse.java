package com.hotel.service.usecase.ratelimit;

import com.hotel.service.usecase.UseCaseResponse;

/**
 * Created by vjose on 22/05/16.
 */
public class RateLimitUseCaseResponse extends UseCaseResponse {
    private boolean enableSearch;

    public boolean isEnableSearch() {
        return enableSearch;
    }

    public void setEnableSearch(boolean enableSearch) {
        this.enableSearch = enableSearch;
    }
}
