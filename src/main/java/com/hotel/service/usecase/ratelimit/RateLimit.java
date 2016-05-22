package com.hotel.service.usecase.ratelimit;

/**
 * Created by vinil jose on 21/05/16.
 * Class to hold apikey,maximum request count supported per given duration.
 */
public class RateLimit {
    private String apiKey;
    private Integer maxRequestCount;
    private Integer duration;

    public RateLimit(String apiKey, Integer maxRequestCount, Integer duration) {
        this.apiKey = apiKey;
        this.maxRequestCount = maxRequestCount;
        this.duration = duration;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Integer getMaxRequestCount() {
        return maxRequestCount;
    }

    public void setMaxRequestCount(Integer maxRequestCount) {
        this.maxRequestCount = maxRequestCount;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
