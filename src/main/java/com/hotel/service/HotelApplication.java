package com.hotel.service;

import com.hotel.service.gateway.HotelGateway;
import com.hotel.service.gateway.inmemory.InMemoryHotelGateway;
import com.hotel.service.usecase.UseCase;
import com.hotel.service.usecase.ratelimit.RateLimitUseCase;
import com.hotel.service.usecase.search.HotelSearchUseCase;
import com.hotel.service.util.CsvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by vinil jose on 21/05/16.
 * This is the spring boot appliaction configuration file.
 * This loads the Hotels Application Service.
 */
@SpringBootApplication
public class HotelApplication {

    @Bean
    protected UseCase hotelSearchUseCase(){
        HotelSearchUseCase hotelSearchUseCase = new HotelSearchUseCase();
        hotelSearchUseCase.setHotelGateway(hotelGateway());
        return hotelSearchUseCase;
    }

    @Bean
    protected HotelGateway hotelGateway(){
        return new InMemoryHotelGateway();
    }

    @Bean
    protected UseCase rateLimitUseCase(){return new RateLimitUseCase();}

    public static void main(String[] args) throws Exception {

        SpringApplication.run(HotelApplication.class, args);
        CsvLoader.loadData();

    }
}
