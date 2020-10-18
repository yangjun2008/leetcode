### 方法一：按照区间左端点升序排列

```java
class Solution {
    public int findMinArrowShots(int[][] points) {
        int len = points.length; //气球数量
        if(len == 0)    return 0;
        Arrays.sort(points, (a1, a2) -> a1[0] - a2[0]); //按区间左端点升序排序
        int ans = 1; //弓箭数量
        int rightmin = Integer.MAX_VALUE; //当前弓箭能射穿的气球里区间右端点最小值
        for(int i = 0; i < len; i++) {
            if(points[i][0] <= rightmin) //当前气球能被上一支弓箭射穿
                rightmin = Math.min(rightmin, points[i][1]); //更新当前弓箭能射穿的气球里区间右端点的最小值
            else {
                ans++; //不能被上一支弓箭射穿，增加弓箭
                rightmin = points[i][1]; //当前区间右端点作为新弓箭能射穿的气球里区间右端点最小值
            }
        }
        return ans;
    }
}
```

### 方法二：按照区间右端点升序排列
通过方法一，我们发现每次都要维护当前弓箭能射穿的气球里区间右端点的最小值，因为下一个气球的左端点，必须在上一支弓箭能射穿的那堆气球里区间右端点最小值的左边，它才能被上一支弓箭一同射穿。
而按照区间左端点升序排列对这个步骤没有帮助，我们可以对此进行优化。

我们对区间右端点进行升序排列。
然后取第一个气球的区间右端点作为第一支弓箭能射穿的气球里区间右端点的最小值。
只要接下来的气球左端点在这个最小值的左边，它们都能被该弓箭一同射穿。
而由于气球是按照右端点升序排列，所以只要不碰到该弓箭射不穿的气球，这个最小值始终是该弓箭能射穿的这堆气球里区间右端点的最小值。

如果下一个气球的左端点在上一支弓箭能射穿的气球里区间右端点最小值的右边，说明上一支弓箭无法一同射穿该气球，我们新增一支弓箭，然后将该气球的区间右端点设为新弓箭能射穿的气球里区间右端点的最小值。

同理，我们也可以写出按照区间左端点降序排列的做法。


```java
class Solution {
    public int findMinArrowShots(int[][] points) {
        int len = points.length; //气球数量
        if(len == 0)    return 0;
        Arrays.sort(points, (a1, a2) -> a1[1] - a2[1]); //按照区间右端点升序排列
        int ans = 1;
        int rightmin = points[0][1];
        for(int i = 1; i < len; i++) {
            if(points[i][0] > rightmin) {
                ans++;
                rightmin = points[i][1];
            }
        }
        return ans;
    }
}
```

### 方法三：按照区间左端点降序排列

```java
class Solution {
    public int findMinArrowShots(int[][] points) {
        int len = points.length; //气球数量
        if(len == 0)    return 0;
        Arrays.sort(points, (a1, a2) -> a2[0] - a1[0]);
        int ans = 1;
        int leftmost = points[0][0];
        for(int i = 1; i < len; i++) {
            if(points[i][1] < leftmost) {
                ans++;
                leftmost = points[i][0];
            }
        }
        return ans;
    }
}
```