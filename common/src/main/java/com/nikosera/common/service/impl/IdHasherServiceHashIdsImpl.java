package com.nikosera.common.service.impl;

import com.nikosera.common.service.IdHasherService;
import lombok.AllArgsConstructor;
import org.hashids.Hashids;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@AllArgsConstructor
public class IdHasherServiceHashIdsImpl implements IdHasherService {

    private final Hashids hashids;

    @Override
    public String encode(Long id) {
        return hashids.encode(id);
    }

    @Override
    public Long decode(String hashId) {
        return hashids.decode(hashId)[0];
    }
}
