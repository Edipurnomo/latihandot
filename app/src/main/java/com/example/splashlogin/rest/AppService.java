package com.example.splashlogin.rest;

public class AppService {
    private static String token;
    private static int idbuku;
//common getter and setter

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        AppService.token = token;
    }

    public static int getIdbuku() {
        return idbuku;
    }

    public static void setIdbuku(int idbuku) {
        AppService.idbuku = idbuku;
    }
}
