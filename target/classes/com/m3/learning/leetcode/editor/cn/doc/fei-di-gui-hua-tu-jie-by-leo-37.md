### 解题思路
递归思路就没啥好讲的，分而治之，来看看非递归，结合图和官方题解的x的77次方看
 [8cd437593c7a05c776de07ad0a11348064c60b48517e977559e3fca7ee039329-b1d5a04f251199a36e8225924370d42.jpg](https://pic.leetcode-cn.com/a5edb62c58db9649ab0252e6bf699389d4ecf5a3fad0fcfd04a2e204671a8a55-8cd437593c7a05c776de07ad0a11348064c60b48517e977559e3fca7ee039329-b1d5a04f251199a36e8225924370d42.jpg)



### 代码

```java
class Solution {
    public double myPow(double x, int n) {
       if (x ==0) {
            return x;
        }

        //样例输入太长了
        long times = n;

        if (times<0){
            times*=-1;
        }

        double result = 1;
        double contr = x;
        while (times>0){
            if (times%2==1){
                result *=contr;
            }
            contr *=contr;
            times /=2;
        }

        return n>0?result:1.0/result;
    }
}
```