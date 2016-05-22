package com.hotel.service.usecase.ratelimit;

/**
 * Created by vinil jose on 21/05/16.
 * This class contains the log for RateLimit InMemory Implementation.
 * It Initialize with delay(api service suspending time) and limit(duration where maximum of one request supported).
 * isUnderLimit() Checks whether the limit is reached.
 * suspend() will suspend the apikey till delay(5 mins)
 */
public class LimitTracker {
    private long delay;
    private long limit;
    private long lastRequestTime ;
    private long suspendedTill =0;


    public LimitTracker(long limit, long delay) {
        this.limit = 1000*limit;
        this.delay = 1000*delay;
        lastRequestTime = getCurrentTime();
    }

    public long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public boolean isUnderLimit(){
        return (getCurrentTime()-lastRequestTime)< limit;
    }

    public void update(){
        lastRequestTime = getCurrentTime();
    }

    public void suspend(){
        suspendedTill = getCurrentTime()+delay;
    }

    public boolean isSuspended(){
        return (suspendedTill>0)&&(suspendedTill>getCurrentTime() );
    }
}
