### 解题思路
此题仍然借鉴了 题解 中一位大佬的思想：
> 1. 先假设都去了A城市
> 2. 用一个数组保存每个人 **（去B城市的费用-去A城市的费用）**
> 3. 按照 **从小到大** 的顺序，给 **费用差数组 排序**
> 4. 将**费用差最小**的**一半**数组的值，加给总费用

### 运行结果
 [image.png](https://pic.leetcode-cn.com/9c662c479f29e859509976c47fbaac4976d32fdb45718f9c70d67cba92503a31-image.png)


### 代码

```java
class Solution {
    public int twoCitySchedCost(int[][] costs) {
        int res = 0;
        int[] temp = new int[costs.length];

        for (int i = 0; i < costs.length; i++) {
            temp[i] = costs[i][1] - costs[i][0];
            res += costs[i][0];
        }
        Arrays.sort(temp);
        for (int i = 0; i < temp.length/2; i++) {
            res += temp[i];
        }

        return res;
    }
}
```
打卡第9天，加油！！！