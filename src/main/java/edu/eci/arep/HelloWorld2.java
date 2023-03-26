package edu.eci.arep;

import static spark.Spark.*;
import static edu.eci.arep.SecureURLReader.*;

public class HelloWorld2 {
    // java -jar app-1.0-SNAPSHOT-jar-with-dependencies.jar
    // java -cp target/classes;target/dependency/* edu.eci.arep.HelloWorld2
    public static void main(String[] args) {
        port(getPort());
        secure(getKeyStore(), getKeyStorePwd(), null, null);
        loadTrustStore(getTrustStore(), getKeyStorePwd());
        get("hello", (req, res) -> "Hello from app 2");
        get("remote", (req, res) -> readURL(getLink()));
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5001; // returns default port if heroku-port isn't set (i.e. on localhost)
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

    private static String getTrustStore() {
        if (System.getenv("TSTORE") != null) {
            return System.getenv("TSTORE");
        }
        return "keys/myTrustStore.p12";
    }

    private static String getLink() {
        if (System.getenv("LINK") != null) {
            return System.getenv("LINK");
        }
        return "ec2-44-202-27-53.compute-1.amazonaws.com:5000/hello";
    }
}
