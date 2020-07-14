package com.example.splashlogin.rest;

public class AppService {
    private static String token;
//common getter and setter

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        AppService.token = token;
    }
}
