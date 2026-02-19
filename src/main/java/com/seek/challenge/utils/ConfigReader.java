package com.seek.challenge.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    private static final Properties properties;

    static {
        try {
            String path = "src/test/resources/config.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
            logger.info("Configuration file loaded successfully from: {}", path);
        } catch (IOException e) {
            logger.error("Failed to load configuration file.", e);
            throw new RuntimeException("Configuration file not found at: " + e.getMessage());
        }
    }

    /**
     * Retrieve a configuration property as a String. Throws RuntimeException if the property is missing.
     *
     * @param key property name in config.properties
     * @return property value as String
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property '{}' not found in config file.", key);
            throw new RuntimeException("Property '" + key + "' not specified in config.properties");
        }
        return value;
    }

    /**
     * Retrieve a boolean configuration property.
     *
     * @param key property name
     * @return parsed boolean value of the property
     */
    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }

    /**
     * Retrieve an integer configuration property.
     *
     * @param key property name
     * @return parsed int value
     */
    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }
}