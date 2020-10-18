```java
class Solution {
    //二分
    public int maximizeSweetness(int[] sweet, int k) {
        int min = 0, sum = 0;
        for(int x : sweet)
        {
            min = Math.min(min, x);
            sum += x;
        }
        if (k == 0) return sum;
        int l = min, r = sum / k;//这里肯定是下去整，因为我拿最小的
        while(l < r)
        {
            int mid = l + r + 1 >> 1;//切割k次，分成k+1块；
            if (check(mid, sweet, k + 1)) l = mid;
            else r = mid - 1;
        }
        return l;
    }
    private boolean check(int threshold, int[] sweet, int k)//这个是贪心的
    {
        int sum = 0;
        for (int x : sweet)
        {
            sum += x;
            if (sum >= threshold)
            {
                k --;
                sum = 0;
            } 
            if (k == 0) return true;
        }
        return false;
    }
}
```