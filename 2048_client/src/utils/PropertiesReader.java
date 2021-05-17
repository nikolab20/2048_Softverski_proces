/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author nikolab
 */
public class PropertiesReader {

    private static PropertiesReader instance;
    private Properties properties;

    private PropertiesReader() throws IOException {
        loadProperties();
    }

    public synchronized static PropertiesReader getInstance() {
        try {
            if (instance == null) {
                instance = new PropertiesReader();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return instance;
    }

    private void loadProperties() throws IOException {
        properties = new Properties();
        properties.load(PropertiesReader.class.getResourceAsStream("/props/app.properties"));
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
