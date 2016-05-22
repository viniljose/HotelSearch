package com.hotel.service.usecase.ratelimit;

import com.hotel.service.exception.HotelServiceException;
import com.hotel.service.usecase.UseCase;
import com.hotel.service.usecase.UseCaseRequest;
import com.hotel.service.usecase.UseCaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vinil jose on 21/05/16.
 * This Usecase responsible for Limiting API search using apiKey shared with each client.
 * Default one request for one apikey per 10 secs.
 * if try to do more than one hit for one apikey then further 5 mins that apikey will be suspended.
 * If apikey not present then no hotel search possible.
 */
public class RateLimitUseCase implements UseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static Map<String,RateLimit> rateLimitMap;
    public static Integer delay = 300;
    private static Map<String,LimitTracker> trackerMap;

    static {
        rateLimitMap = new HashMap<String,RateLimit>();
        rateLimitMap.put("A",new RateLimit("A",1,10));
        rateLimitMap.put("B",new RateLimit("B",1,10));
        rateLimitMap.put("C",new RateLimit("C",1,10));
    }

    public UseCaseResponse execute(UseCaseRequest useCaseRequest) {
        RateLimitUseCaseRequest request = (RateLimitUseCaseRequest)useCaseRequest;

        String apiKey = request.getApiKey();
        LOGGER.info("checkRateLimit-------->"+apiKey);
        boolean enableSearch = false;
        RateLimit rateLimit;
        if(rateLimitMap.containsKey(apiKey))
         rateLimit = rateLimitMap.get(apiKey);
        else
           throw new HotelServiceException();

        if(trackerMap ==null || trackerMap.isEmpty())
            trackerMap =  new HashMap<String,LimitTracker>();

        if(trackerMap.containsKey(apiKey)){
            LOGGER.info("Again to Tracker-------->"+apiKey);
            LimitTracker tracker = trackerMap.get(apiKey);
            if(tracker.isSuspended()){
                throw new HotelServiceException("API_SUSPENDED","API SUSPENDED FOR 5 Mins");
            } else if(tracker.isUnderLimit()){
                LOGGER.info("Suspending-------->"+apiKey);
                tracker.suspend();
                throw new HotelServiceException("LIMIT_EXCEEDED","API LIMIT EXCEEDED");
            } else {
                tracker.update();
                enableSearch = true;
            }

        } else {
            LOGGER.info("First Time to Tracker-------->"+apiKey);
            trackerMap.put(apiKey,new LimitTracker(rateLimit.getDuration(),delay));
            enableSearch = true;
        }

        RateLimitUseCaseResponse response = new RateLimitUseCaseResponse();
        response.setEnableSearch(enableSearch);
        return response;
    }


}
