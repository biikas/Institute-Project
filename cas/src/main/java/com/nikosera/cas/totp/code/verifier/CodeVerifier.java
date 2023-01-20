package com.nikosera.cas.totp.code.verifier;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public interface CodeVerifier {

    boolean isValidCode(String secret, String code);
}
