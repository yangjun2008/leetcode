package com.aiplayer.learning.leetcode.sn53;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxSubArrayTest {

    private MaxSubArray maxSubArray;

    @BeforeEach
    void setUp() {
        maxSubArray = new MaxSubArray();
    }

    @Test
    void maxSubArray() {
        int[] testArray = {0, 1, 2, -1, 5};
        int result = maxSubArray.maxSubArray(testArray);
        assertEquals(7, result);
    }

    @Test
    void maxSubArray_2() {
        int[] testArray2 = {10};
        int result = maxSubArray.maxSubArray(testArray2);
        assertEquals(10, result);
    }

   @Test
   void maxSubArray_3() {
        int[] testArray2 = {10,-9,-7,3,-2,4,6,-1,-2,4};
        int result = maxSubArray.maxSubArray(testArray2);
       assertEquals(12, result);
    }
}