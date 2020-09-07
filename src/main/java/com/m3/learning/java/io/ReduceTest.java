package com.m3.learning.java.io;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.Stream;

public class ReduceTest {
    @Test
    public void test()
    {
        Stream<Integer> list = Stream.of(1, 2, 3, 4, 5);
        Optional<Integer> result = list.reduce((x, y) -> x + y);
        System.out.println(result);
    }
}


