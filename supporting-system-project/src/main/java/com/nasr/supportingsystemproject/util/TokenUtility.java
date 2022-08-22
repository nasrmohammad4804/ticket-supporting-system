package com.nasr.supportingsystemproject.util;

import static com.nasr.supportingsystemproject.constant.ConstantField.TOKEN_PREFIX;

public class TokenUtility {


    public static String getToken(String auth) {
        return auth.replace(TOKEN_PREFIX, "");

    }
}
