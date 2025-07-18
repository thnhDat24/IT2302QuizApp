/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltd.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class FlyweightFactory {
    private static Map<String, List> cachedData = new HashMap<>();
    
    public static <E> List<E> getData(BaseServices s, String key) throws SQLException {
        if (cachedData.containsKey(key) == true)
            return cachedData.get(key);
        else {
            List results = s.list();
            cachedData.put(key, results);
            
            return results;
        }
    }
}
