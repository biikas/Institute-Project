package com.nikosera.common.service;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public interface IdHasherService {

    public String encode(Long id);

    public Long decode(String hashId);
}
