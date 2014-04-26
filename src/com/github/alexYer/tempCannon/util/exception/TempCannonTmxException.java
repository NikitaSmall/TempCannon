package com.github.alexYer.tempCannon.util.exception;


/**
 * @author Olexander Yermakov (mannavard1611@gmail.com)
 */
public class TempCannonTmxException extends TempCannonException {
    public TempCannonTmxException() {
        super();
    }

    
    public TempCannonTmxException(final String msg) {
        super(msg);
    }


    public TempCannonTmxException(final Throwable throwable) {
        super(throwable);
    }


    public TempCannonTmxException(final String msg, final Throwable throwable) {
        super(msg, throwable);
    }
}
