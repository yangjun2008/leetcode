f(i,j) = |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j| 有四种情况(见代码中)
f(i,j) 的最大值就是所有四种情况的最大值;

```java
class Solution {
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        /**
         *  |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j| 有四种情况
         *  arr1[i] - arr1[j] + arr2[i] - arr2[j] + |i - j| -> (arr1[i] + arr2[i]) - (arr1[j] + arr2[j]) + |i - j| (1)
         *  arr1[i] - arr1[j] - arr2[i] + arr2[j] + |i - j| -> (arr1[i] - arr2[i]) - (arr1[j] - arr2[j]) + |i - j| (2)
         * -arr1[i] + arr1[j] + arr2[i] - arr2[j] + |i - j| -> (arr2[i] - arr1[i]) - (arr2[j] - arr1[j]) + |i - j| (3)
         * -arr1[i] + arr1[j] - arr2[i] + arr2[j] + |i - j| -> (-arr1[i] - arr2[i]) - (-arr1[j] - arr2[j]) + |i - j| (4)
         */
        int len = arr1.length;
        int[] num1 = new int[len];
        int[] num2 = new int[len];
        int[] num3 = new int[len];
        int[] num4 = new int[len];
        for (int i = 0; i < len; i++) {
            num1[i] =  arr1[i] + arr2[i];
            num2[i] =  arr1[i] - arr2[i];
            num3[i] = -arr1[i] - arr2[i];
            num4[i] = -arr1[i] + arr2[i];
        }
        int m1 = Math.max(maxAbsValExpr(num1), maxAbsValExpr(num2));
        int m2 = Math.max(maxAbsValExpr(num3), maxAbsValExpr(num4));
        return Math.max(m1, m2);
    }

    // O(N)
    public int maxAbsValExpr(int[] num) {
        // max : num[i] - num[j] + |i - j|
        // (num[i] + i) - (num[j] + j) ; i > j  or
        // (num[i] - i) - (num[j] - j) ; j > i  -->  (j - num[j]) - (i - num[i]); j > i
        int[] num1 = new int[num.length];
        int[] num2 = new int[num.length];
        for (int i = 0; i < num.length; i++) {
            num1[i] = num[i] + i;
            num2[i] = i - num[i];
        }
        return Math.max(max(num1), max(num2));
    }

    // O(N + N) = O(N)
    public int max(int[] num) {
        // max : num[i] - num[j]; i > j
        int[] max = new int[num.length];
        max[num.length - 1] = num[num.length - 1];
        for (int i = num.length - 2; i >= 0; i--) {
            max[i] = Math.max(max[i + 1], num[i]);
        }

        int res = max[1] - num[0];
        for (int j = 1; j < max.length - 1; j++) {
            res = Math.max(res, max[j + 1] - num[j]);
        }
        return res;
    }
}
```
