### 解题思路
双90+

### 代码

```cpp
class Solution {
private:
    static bool compare(const vector<int>&a,const vector<int>&b){
        return a[0]<b[0];
    }
public:
    int findMinArrowShots(vector<vector<int>>& points) {
        if (points.size() == 0) 
            return 0;
        sort(points.begin(),points.end(),compare);
        int shoot_begin=points[0][0];  //后面的0表示左端点，1表示右端点
        int shoot_end  =points[0][1];
        int arrayer=1;      //起始弓箭手为1；
        for(int i=1;i<points.size();i++){
            if(points[i][0]<=shoot_end){    //比较其他点与射击点的位置，此处为相交
                shoot_begin=points[i][0];
                if(points[i][1]<shoot_end)
                  shoot_end=points[i][1];
            }
            else{       //不相交
                arrayer++;
                shoot_begin=points[i][0];
                shoot_end=points[i][1];
            }
                
        }
        return arrayer;
    }
};
```