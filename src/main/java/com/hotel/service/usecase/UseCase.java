package com.hotel.service.usecase;

/**
 * Created by vinil jose on 21/05/16.
 * This the base interface needs to be implemented for all the usecases.
 *
 */
public interface UseCase {

   UseCaseResponse execute(UseCaseRequest useCaseRequest);
}
