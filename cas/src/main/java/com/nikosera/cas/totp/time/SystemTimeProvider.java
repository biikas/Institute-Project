package com.nikosera.cas.totp.time;

import com.nikosera.common.exception.TimeProviderException;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class SystemTimeProvider implements TimeProvider {
    @Override
    public long getTime() throws TimeProviderException {
        return System.currentTimeMillis();
    }
}
