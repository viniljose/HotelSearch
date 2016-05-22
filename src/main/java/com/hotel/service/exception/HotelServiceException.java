package com.hotel.service.exception;

/**
 * Created by vinil jose on 21/05/16.
 */
public class HotelServiceException extends RuntimeException{
    protected static final String DEFAULT_ERROR_CODE = "API_KEY_ERROR";
    protected static final String DEFAULT_ERROR_REASON = "API KEY NOT FOUND";
    protected final String errorCode;
    protected final String errorReason;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public HotelServiceException(String errorCode, String errorReason) {
        super();
        this.errorCode = errorCode;
        this.errorReason = errorReason;
    }

    public HotelServiceException( ) {
        super();
        this.errorCode = DEFAULT_ERROR_CODE;
        this.errorReason = DEFAULT_ERROR_REASON;
    }
}
