package com.m3.learning.java.basic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DeleteCollection {
    public static void main(String[] args) {
        //test_1();
        //test_1_1();
        /*test_2();
        test_3();
        test_4();
        test_5();
        test_6();
        test_7();
        test_8();
        test_9();
        test_10();
        test_11();*/
        test_11_1();
        /*test_12();
        test_13();
        test_14();
        test_15();*/
    }

    //ConcurrentModificationException
    private static void test_1() {
        try {
            List<String> list = new LinkedList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
            for (String str : list) {
                if (str.equals("c"))
                    list.remove(str);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //ConcurrentModificationException
    private static void test_1_1() {
        try {
            List<String> list = new LinkedList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
            for (String str : list) {
                if (str.equals("c")) {
                    list.remove(str);
                    break;
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //OK
    private static void test_2() {
        try {
            List<String> list = new LinkedList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
            for (String str : list) {
                if (str.equals("d"))
                    list.remove(str);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //OK
    private static void test_3() {
        try {
            List<String> list = new LinkedList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
            for (String str : list) {
                if (str.equals("e"))
                    list.remove(str);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    //ConcurrentModificationException
    private static void test_4() {
        try {
            List<String> list = new ArrayList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
            for (String str : list) {
                if (str.equals("c"))
                    list.remove(str);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //OK
    private static void test_5() {
        try {
            List<String> list = new ArrayList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
            for (String str : list) {
                if (str.equals("d"))
                    list.remove(str);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //ConcurrentModificationException
    private static void test_6() {
        try {
            List<String> list = new ArrayList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
            for (String str : list) {
                if (str.equals("e"))
                    list.remove(str);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void test_7() {
        try {
            List<String> list = new ArrayList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals("d"))
                    list.remove(list.get(i));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void test_8() {
        try {
            List<String> list = new ArrayList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals("e"))
                    list.remove(list.get(i));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void test_9() {
        try {
            List<String> list = new ArrayList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals("c"))
                    list.remove(list.get(i));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void test_10() {
        try {
            List<String> list = new ArrayList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
            for (int i = 0; i < list.size(); i++) {
                list.remove(list.get(i));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void test_11() {
        try {
            List<String> list = new ArrayList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i);
                list.remove(i);
                System.out.println(list);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void test_11_1() {
        try {
            List<String> list = new ArrayList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i);
                list.remove(i);
                i--;
                System.out.println(list);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void test_12() {
        try {
            List<String> list = new LinkedList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i);
                list.remove(i);
                System.out.println(list);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void test_13() {
        try {
            List<String> list = new LinkedList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
            for (int i = list.size()-1; i >=0; i--) {
                System.out.println(i);
                list.remove(i);
                System.out.println(list);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //使用迭代器遍历，却用集合的方法删除元素，报ConcurrentModificationException
    private static void test_14() {
        try {
            List<String> list = new LinkedList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
            Iterator<String> iter = list.iterator();
            while(iter.hasNext()) {
                String str = iter.next();
                if("c".equals(str)) {
                    list.remove(str);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //迭代器遍历，并且使用迭代器的删除方法是开发推荐的
    private static void test_15() {
        try {
            List<String> list = new LinkedList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
            Iterator<String> iter = list.iterator();
            while(iter.hasNext()) {
                String str = iter.next();
                System.out.println(str);
                iter.remove();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


}
