package br.com.fiap.imposto.util;

import java.io.IOException;
import java.util.Properties;

public class AliquotaSingleton {

    private static Properties properties;

    private static final String ARQ = "/resources/conf.properties";
    private AliquotaSingleton(){}

    public static Properties getInstance(){

        if(properties == null){
            properties = new Properties();
            try{
                properties.load(AliquotaSingleton
                        .class.getResourceAsStream(ARQ));
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        return properties;
    }
}