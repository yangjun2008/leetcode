看了下数组长度，就1000， 那直接暴力O(n^2)就行了。
唯一注意就是用滑动窗口去计算值， 不要重复去计算总和，每次滑动只要减去第一个，加上最后一个就行了。

```java
class Solution {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int lRight = L-1;
        int lSum = 0, mInitSum = 0;;
        
        for (int i = 0; i <L-1;i++) {
            lSum += A[i];
        }
        for (int i = 0; i <M-1;i++) {
            mInitSum += A[i];
        }

        int result = -1;
        for (int lLeft = 0;lRight<A.length;lLeft++, lRight++) {
            lSum += A[lRight];
            int mRight = M-1;
            int mSum = mInitSum;
            for (int mLeft = 0;mRight<A.length;mLeft++, mRight++) {
                mSum += A[mRight];
                if (mRight < lLeft || mLeft > lRight) {
                    if (mSum + lSum > result) {
                        result = mSum + lSum;
                    }
                }
                mSum -= A[mLeft];
            }    
            lSum -= A[lLeft];
        }
        return result;
    }
}
```