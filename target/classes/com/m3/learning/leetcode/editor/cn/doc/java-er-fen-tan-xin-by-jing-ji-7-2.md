```
class Solution {
    public int maximizeSweetness(int[] sweetness, int K) {
        int sum = 0;
        int min = sweetness[0];
        for (int a:sweetness){
            sum+=a;
            min = Math.min(min,a);
        }
        int left = min,right = sum;
        while (left<right){
            int mid = (left+right)>>>1;
            if (divide(sweetness,mid)>K+1){
                left = mid+1;
            }
            else{
                right = mid;
            }
        }
        return left;
    }
    public int divide(int[] sweetness,int mid){
        int res = 0;
        int count = 1;
        for (int s:sweetness){
            if (res+s<=mid){
                res+=s;
            }
            else{
                count++;
                res = 0;
            }
        }
        return count;
    }
}
```
