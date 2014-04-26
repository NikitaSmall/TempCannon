package com.github.alexYer.tempCannon.util.exception;

/**
 * @author Olexander Yermakov (mannavard1611@gmail.com)
 */
public class TempCannonException extends Exception {
    public TempCannonException() {
        super();
    }

    
    public TempCannonException(final String msg) {
        super(msg);
    }


    public TempCannonException(final Throwable throwable) {
        super(throwable);
    }


    public TempCannonException(final String msg, final Throwable throwable) {
        super(msg, throwable);
    }
}
