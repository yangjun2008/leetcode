package com.m3.learning.java.basic;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 构造方法中accessOrder为true,可以当做LRU(least recently used)缓存使用
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size()>5;
            }
        };

        for(int i = 0; i < 10; i++) {
            map.put(i, i);
        }
        System.out.println(map.keySet());
    }
}
