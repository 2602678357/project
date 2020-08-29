package com.bhsoftware.projectserver.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;

public class SaltUtil {

    private static final String salt="YzcmCZNvbXocrsz9dm8e";

    public static String getSalt() {
        return salt;
    }
}
