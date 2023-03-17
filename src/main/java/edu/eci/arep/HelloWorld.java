package edu.eci.arep;

import static spark.Spark.*;

public class HelloWorld {
    public static void main(String[] args) {
        secure(getKeyStore(), getKeyStorePwd(), null, null);
        port(getPort());
        get("/hello", (req, res) -> "Hello Heroku");
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000; // returns default port if heroku-port isn't set (i.e. on localhost)
    }

    static String getKeyStore() {
        if (System.getenv("KEYSTORE") != null) {
            return System.getenv("KEYSTORE");
        }
        return "keys/ecikeystore.p12"; // returns default port if heroku-port isn't set (i.e. on localhost)
    }

    static String getKeyStorePwd() {
        if (System.getenv("KEYSTOREPWD") != null) {
            return System.getenv("KEYSTOREPWD");
        }
        return "santi1234"; // returns default port if heroku-port isn't set (i.e. on localhost)
    }
}