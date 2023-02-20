package edu.epn.web.b2022.g6.appweb.chauchera.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Config {
    private final static String CONFIG_FILE = "config.ini";
    private static Properties properties;

    public static void initProperties(){
        properties = new Properties();
        try(FileInputStream fis = new FileInputStream(CONFIG_FILE)){
            properties.load(fis);
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public static Properties getProperties() {
        if(properties==null) initProperties();
        return properties;
    }
    
    public static String getProperty(String key) {
        return getProperties().getProperty(key);
    }
}
