package com.hotel.service.usecase.ratelimit;

import com.hotel.service.usecase.UseCaseRequest;

/**
 * Created by vinil jose on 22/05/16.
 */
public class RateLimitUseCaseRequest extends UseCaseRequest {
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
