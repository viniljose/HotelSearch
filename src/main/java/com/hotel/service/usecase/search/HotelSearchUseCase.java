package com.hotel.service.usecase.search;

import com.hotel.service.entity.Hotel;
import com.hotel.service.gateway.HotelGateway;
import com.hotel.service.usecase.UseCase;
import com.hotel.service.usecase.UseCaseRequest;
import com.hotel.service.usecase.UseCaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.List;

/**
 * Created by vinil jose on 21/05/16.
 * This use case contains the busines logic to do the search for Hotels based on the city.
 * It also support ascending and descending order sort of prices in the result.
 */
public class HotelSearchUseCase implements UseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private HotelGateway hotelGateway;

    public void setHotelGateway(HotelGateway hotelGateway) {
        this.hotelGateway = hotelGateway;
    }

    @Override
    public UseCaseResponse execute(UseCaseRequest useCaseRequest) {
        SearchUseCaseRequest searchRequest = (SearchUseCaseRequest)useCaseRequest;
        LOGGER.info("UseCase---------->"+searchRequest.getCity()+"   "+searchRequest.isSortDescending());
        List<Hotel> hotels = hotelGateway.getHotels(searchRequest.getCity());
        if(searchRequest.isSortDescending())
            Collections.sort(hotels,new PriceComparator().reversed());
        else
            Collections.sort(hotels,new PriceComparator());
        SearchUseCaseResponse useCaseResponse =new SearchUseCaseResponse();
        useCaseResponse.setHotels(hotels);
        return useCaseResponse;
    }
}
