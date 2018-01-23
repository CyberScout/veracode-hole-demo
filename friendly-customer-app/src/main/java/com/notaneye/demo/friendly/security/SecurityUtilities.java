package com.notaneye.demo.friendly.security;


import org.owasp.encoder.Encode;
import org.springframework.util.ObjectUtils;


public class SecurityUtilities {

    public static String scrub(Object o) {

        String untrusted = ObjectUtils.nullSafeToString(o);
        return Encode.forHtml(untrusted
                                      .replace("\r", "")
                                      .replace("\n", ""));
    }
}
