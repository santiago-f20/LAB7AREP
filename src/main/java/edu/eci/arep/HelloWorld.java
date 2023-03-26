package edu.eci.arep;

import static spark.Spark.*;
import static edu.eci.arep.SecureURLReader.*;

public class HelloWorld {
    // java -jar app-1.0-SNAPSHOT-jar-with-dependencies.jar
    // java -cp target/classes;target/dependency/* edu.eci.arep.HelloWorld
    public static void main(String[] args) {
        port(getPort());
        secure(getKeyStore(), getKeyStorePwd(), null, null);
        loadTrustStore(HelloWorld2.getKeyStore(), getKeyStorePwd());
        get("hello", (req, res) -> "Hello from app 1");
        get("remote", (req, res) -> readURL(getLink()));
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
        return "keys/awskeystore.p12"; // returns default port if heroku-port isn't set (i.e. on localhost)
    }

    static String getKeyStorePwd() {
        if (System.getenv("KEYSTOREPWD") != null) {
            return System.getenv("KEYSTOREPWD");
        }
        return "aws1234"; // returns default port if heroku-port isn't set (i.e. on localhost)
    }

    private static String getLink() {
        if (System.getenv("LINK") != null) {
            return System.getenv("LINK");
        }
        return "https://ec2-35-175-128-228.compute-1.amazonaws.com:5001/hello";
    }
}
