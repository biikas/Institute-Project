package com.nikosera.cas.totp.time;

import com.nikosera.common.exception.TimeProviderException;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public interface TimeProvider {
    /**
     * @return The number of seconds since Jan 1st 1970, 00:00:00 UTC.
     */
    long getTime() throws TimeProviderException;
}