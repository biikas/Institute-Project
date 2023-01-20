package com.nikosera.cas.util;

import com.nikosera.common.util.JacksonUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;

import java.text.ParseException;

public class RSAKeyGen {

    public static void main(String[] args) throws JOSEException, ParseException {
        RSAKey rsaKey = new RSAKeyGenerator(2048).generate().toRSAKey();
        rsaKey.toRSAKey();
        String keyPair = JacksonUtil.getString(rsaKey);
//        RSAKey rsaKey1 = RSAKey.parse(keyPair);
//        String keyPair1 = JacksonUtil.getString(rsaKey1);

//        System.out.println(keyPair1);
    }
}
