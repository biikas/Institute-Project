package com.nikosera.cas.totp.code.generator;

import com.nikosera.common.exception.CodeGenerationException;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public interface CodeGenerator {

    String generate(String secret, long counter) throws CodeGenerationException;
}
