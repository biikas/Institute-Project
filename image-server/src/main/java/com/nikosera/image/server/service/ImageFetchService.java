package com.nikosera.image.server.service;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public interface ImageFetchService {

    String fetchImageUrl(String bucket,String service, int expiresIn);

}
