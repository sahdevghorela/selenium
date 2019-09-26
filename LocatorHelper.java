package com.example.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LocatorHelper {

    private static Map<String,By> locatorsMap = new HashMap<>();

    static{
        Properties properties = new Properties();
        ClassLoader classLoader = LocatorHelper.class.getClassLoader();
        try {
            properties.load(classLoader.getResourceAsStream("locators.properties"));
            Enumeration enumeration = properties.propertyNames();
            while(enumeration.hasMoreElements()){
                String name = enumeration.nextElement().toString();
                String value = properties.get(name).toString();
                By by = parse(value);
                locatorsMap.put(name, by);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static By get(String propName){
        return locatorsMap.get(propName);
    }

    private static By parse(String value) {
        String []pair = value.split(":");
        switch (pair[0]){
            case "id" :
                return By.id(pair[1]);
            case "css" :
                return By.cssSelector(pair[1]);
            case "xpath" :
                return By.xpath(pair[1]);
        }
        return null;
    }

}
