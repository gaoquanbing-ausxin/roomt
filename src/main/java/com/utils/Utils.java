package com.utils;

import java.util.Map;
import java.util.function.Function;

/**
 * User: brucegao
 * Date: 2018-08-14
 */
public class Utils {
    private Utils() {
    }

    private static class SingleTon {
        static Utils singleTon = new Utils();
    }

    public static Utils g() {
        return SingleTon.singleTon;
    }


    public String replaceMapKey(Map<String, String> map, String str) {
        Function<String, String> combined = map.entrySet().stream()
                .reduce(
                        Function.identity(),
                        (f, e) -> s -> f.apply(s).replaceAll(e.getKey(), e.getValue()),
                        Function::andThen
                );

        return combined.apply(str);
    }

}
