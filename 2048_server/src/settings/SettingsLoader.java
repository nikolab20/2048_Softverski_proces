/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author nikolab
 */
public class SettingsLoader {

    private static SettingsLoader instance;
    private Properties properties;

    private SettingsLoader() throws IOException {
        loadProperties();
    }

    public synchronized static SettingsLoader getInstance() {
        try {
            if (instance == null) {
                instance = new SettingsLoader();
            }
            return instance;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void loadProperties() throws IOException {
        properties = new Properties();
        properties.load(SettingsLoader.class.getResourceAsStream("/props/settings.properties"));
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
