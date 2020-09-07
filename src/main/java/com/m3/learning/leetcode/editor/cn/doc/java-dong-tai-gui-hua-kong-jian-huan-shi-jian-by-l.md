### 解题思路
此解法借鉴了题解区一位大佬的思想
首先，根据提示：
 [image.png](https://pic.leetcode-cn.com/74d09d01c9f3b57959cbc36d6a3cce09576db0297a9a06bd710ebfe8d4d468ca-image.png)
new两个int型数组：
>- 一个用于存放 每日股票价格
>- 一个用于存放 每日股票价格跨度

在范围内，当天股票价格大于等于数组中当前元素时，
让结果增加当前股票价格跨度，并使得下标前移 股票价格跨度 个单元(跟比当前股票大的数值比较)
得出结果，记录两个数组中，即可！


### 运行结果
 [image.png](https://pic.leetcode-cn.com/2b1f062eee4fa62bbfe8007a24ed7fe3f4068ee14c8aec6a699a4668b059db2d-image.png)


### 代码

```java
class StockSpanner {
    private int[] stockPrice;
    private int[] stockWeight;

    private int curIndex = -1;

    public StockSpanner() {
        stockPrice = new int[10000];
        stockWeight = new int[10000];
    }

    public int next(int price) {
        int res = 1;

        int i = curIndex;
        while (i >= 0 && price >= stockPrice[i]) {
            res += stockWeight[i];
            i -= stockWeight[i];
        }
        stockPrice[++curIndex] = price;
        stockWeight[curIndex] = res;

        return res;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
```
打卡第14天，fightting！