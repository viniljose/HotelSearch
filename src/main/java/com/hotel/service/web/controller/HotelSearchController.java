package com.hotel.service.web.controller;

import com.hotel.service.exception.HotelServiceException;
import com.hotel.service.usecase.UseCase;
import com.hotel.service.usecase.ratelimit.RateLimitUseCaseRequest;
import com.hotel.service.usecase.ratelimit.RateLimitUseCaseResponse;
import com.hotel.service.usecase.search.SearchUseCaseRequest;
import com.hotel.service.usecase.search.SearchUseCaseResponse;
import com.hotel.service.web.model.SearchRequest;
import com.hotel.service.web.model.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;

/**
 * Created by vinil jose on 21/05/16.
 * This is rest controller responsible exposing the http service.
 *  http://localhost:8080/search
 *  This expects the request in JSON format.
 *  {"city":"Amsterdam","sortDescending":true,"apiKey":"B"}
 *  This will provide JSON Response containing an array.
 *  Incase of error there will be an error element populated.
 */
@RestController
public class HotelSearchController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Autowired
    private UseCase hotelSearchUseCase;
    @Autowired
    private UseCase rateLimitUseCase;

    @RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SearchResponse> search(@RequestBody final SearchRequest request, BindingResult result){
        LOGGER.info("----------Controller----Start---------->"+request.getCity()+"   "+request.isSortDescending());
        ResponseEntity<SearchResponse> responseEntity;
        try {
            RateLimitUseCaseRequest rateLimitUseCaseRequest = new RateLimitUseCaseRequest();
            rateLimitUseCaseRequest.setApiKey(request.getApiKey());
            RateLimitUseCaseResponse response = (RateLimitUseCaseResponse)rateLimitUseCase.execute(rateLimitUseCaseRequest);

            LOGGER.info("enableSearch-------->"+response.isEnableSearch());
            SearchResponse searchResponse = new SearchResponse();
            SearchUseCaseRequest searchUseCaseRequest = new SearchUseCaseRequest();
            searchUseCaseRequest.setCity(request.getCity());
            searchUseCaseRequest.setSortDescending(request.isSortDescending());
            if (response.isEnableSearch()) {
                SearchUseCaseResponse searchUseCaseResponse= (SearchUseCaseResponse)hotelSearchUseCase.execute(searchUseCaseRequest);
                searchResponse.setHotels(searchUseCaseResponse.getHotels());
                responseEntity = new ResponseEntity<SearchResponse>(searchResponse, HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<SearchResponse>(searchResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch(HotelServiceException e){
            LOGGER.error("Controller----Exception------>"+e.getErrorReason());
            SearchResponse searchResponse = new SearchResponse();
            searchResponse.setError(e.getErrorCode());
            responseEntity = new ResponseEntity<SearchResponse>(searchResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.info("----------------Controller----End------------->");
        return responseEntity;
    }

}
