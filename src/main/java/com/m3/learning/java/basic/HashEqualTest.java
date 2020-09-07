package com.m3.learning.java.basic;

import java.util.HashMap;
import java.util.HashSet;

public class HashEqualTest {

    static class A {
        private int value = 0;

        public A(int val) {
            this.value = val;
        }

        @Override
        public int hashCode() {
            return value;
        }

        @Override
        public String toString() {
            return ""+ new Integer(hashCode()*10);
        }
    }

    public static void main(String[] args) {
        A a = new A(2000);
        A b = new A(2000);
        System.out.println(a.hashCode() == b.hashCode());
        System.out.println(a.equals(b));

        HashMap<A, Integer> map = new HashMap<>();
        map.put(a, 10000);
        map.put(b, 10001);
        System.out.println(map);
        System.out.println(map.get(a));
        System.out.println(map.get(b));

        HashSet<A> set = new HashSet<>();
        set.add(a);
        set.add(b);
        System.out.println(set);
    }
}
