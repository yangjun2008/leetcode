package com.aiplayer.learning.leetcode.s152;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxProductTest {

    MaxProduct mp;
    @BeforeEach
    void setUp() {
        mp = new MaxProduct();
    }

    @Test
    void maxProduct() {
        int [] input = {1, 2, 3, -1, 2};
        assertEquals(6, mp.maxProduct(input));
    }

    @Test
    void maxProduct_2() {
        int [] input = {1, 2, 3, -1, 2, 2, 4};
        assertEquals(16, mp.maxProduct(input));
    }

    @Test
    void maxProduct_3() {
        int [] input = {1, 2, 3, -1, 2, 2, -1};
        assertEquals(24, mp.maxProduct(input));
    }
}