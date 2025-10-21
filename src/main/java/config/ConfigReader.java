package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigReader
 * ----------------
 * Purpose:
 * This class is responsible for reading key-value pairs from the
 * 'config.properties' file (which lives inside resources folder).

 * Why we need it:
 * - To avoid hardcoding important values (like URLs, browsers, timeouts)
 * - To make the framework flexible and environment-independent
 */
public class ConfigReader {

    // 'Properties' is a Java class that stores data as key=value pairs (like our config file)
    private static Properties properties = new Properties();

    /**
     * 🧩 loadProperties()
     * -------------------
     * This method loads our config.properties file from the resources folder.
     * It reads the file and stores all key=value pairs into memory.
     */
    public static void loadProperties() {
        try {
            // The file is inside 'resources', so we use the class loader to find it.
            InputStream inputStream = ConfigReader.class.getClassLoader()
                    .getResourceAsStream("config.properties");

            if (inputStream == null) {
                throw new RuntimeException("config.properties file not found inside resources folder!");
            }

            // Load all key=value pairs into the 'properties' object
            properties.load(inputStream);

        } catch (IOException e) {
            // If something goes wrong while reading the file, print an error
            System.out.println("Error loading config.properties file: " + e.getMessage());
        }
    }

    /**
     * 🧩 getProperty(String key)
     * --------------------------
     * This method returns the value for a given key from the properties file.
     * Example:
     * getProperty("browser") → returns "chrome"
     */
    public static String getProperty(String key) {
        // If properties are not loaded yet, load them first
        if (properties.isEmpty()) {
            loadProperties();
        }

        // Return the value for the given key
        return properties.getProperty(key);
    }

    /**
     * 🧩 getIntProperty(String key)
     * -----------------------------
     * Sometimes, values are numbers (like wait times).
     * This method converts them from String to int safely.
     */
    public static int getIntProperty(String key) {
        String value = getProperty(key);
        return Integer.parseInt(value);
    }

    /**
     * 🧩 getBooleanProperty(String key)
     * ---------------------------------
     * Sometimes, values are true/false (like headless mode).
     * This method converts them to boolean.
     */
    public static boolean getBooleanProperty(String key) {
        String value = getProperty(key);
        return Boolean.parseBoolean(value);
    }
}
