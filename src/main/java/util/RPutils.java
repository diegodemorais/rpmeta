/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Map;

/**
 *
 * @author dm
 */
public final class RPutils {
    
    public static Float getKeyByValue(Map<Float,String> map, final String value) {
        return map.entrySet()
            .stream()
            .filter(e -> e.getValue().equals(value))
            .findFirst()
            .map(Map.Entry::getKey)
            .orElse(null);
    }
}
