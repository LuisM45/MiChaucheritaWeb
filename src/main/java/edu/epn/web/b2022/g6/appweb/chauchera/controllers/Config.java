package edu.epn.web.b2022.g6.appweb.chauchera.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase responsable de cargar la configuración del archivo config.ini
 * @author luism
 */
public abstract class Config {
    private final static String CONFIG_FILE = "config.ini";
    private static Properties properties;

    private static void initProperties(){
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

    private static Properties getProperties() {
        if(properties==null) initProperties();
        return properties;
    }
    
    /**
     * Retorna el valor de la propiedad 'key'
     * @param key Nombre de la propiedad
     * @return Valor de la propieddad
     */
    public static String getProperty(String key) {
        return getProperties().getProperty(key);
    }
}
