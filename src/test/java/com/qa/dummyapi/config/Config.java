package com.qa.dummyapi.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = Config.class.getClassLoader().getResourceAsStream("config/config.properties")) {
            if (is != null) {
                props.load(is);
            } else {
                System.err.println("config/config.properties not found; using system properties or env variables only.");
            }
        } catch (IOException e) {
            System.err.println("Could not load config/config.properties: " + e.getMessage());
        }
    }

    // Get property with default fallback
    public static String get(String key, String defaultValue) {
        String sys = System.getProperty(key);
        if (sys != null && !sys.isEmpty()) return sys;

        String env = System.getenv(key.toUpperCase());
        if (env != null && !env.isEmpty()) return env;

        return props.getProperty(key, defaultValue);
    }

    // Get property, returns null if not found
    public static String get(String key) {
        return get(key, null);
    }
}
