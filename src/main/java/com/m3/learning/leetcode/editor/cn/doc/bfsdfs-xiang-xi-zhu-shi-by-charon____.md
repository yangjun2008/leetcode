关键点是两步[（参考）](<https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location/discuss/431471/very-clean-c%2B%2B-solution-DFS-%2B-BFS-with-explaination>)：

1. 使用BFS找箱子到终点的最短路径
2. 使用DFS找人是否能将箱子推到对应位置

详细逻辑在代码注释中：

```c++
class Solution {
public:    
    // 人的位置 (sx, sy)
    // 箱子的位置 (bx, by)
    struct Node{ 
        // 用来表示状态
        int sx,sy,bx,by;
    };
    // 去掉 人 箱子 目的地 位置的图
    vector<vector<char>> G_;
    int M_;
    int N_;
    bool valid(int x, int y) {
        return x >= 0 && x < M_ && y >= 0 && y < N_ && G_[x][y] == '.';
    }
    // 记录状态，避免重复访问
    int flag[21][21][21][21];
    void dfs(const Node& cur, vector<Node>& cand, vector<vector<bool>> &vis) {
        // 已经有了下一步的所有可能，返回
        if (cand.size() == 4) return; 
        int sx = cur.sx, sy = cur.sy;
        int bx = cur.bx, by = cur.by;
        vis[sx][sy] = true;
        int d[4][2] = {{0,1},{0,-1},{-1,0},{1,0}};
        for (int k = 0; k < 4; ++k) {
            int nx = sx + d[k][0];
            int ny = sy + d[k][1];
            if (nx == bx && ny == by) {
                // 抵达推箱子的位置
                int pushToX = nx + d[k][0];
                int pushToY = ny + d[k][1];
                if (valid(pushToX, pushToY) && flag[nx][ny][pushToX][pushToY] == 0) {
                    flag[nx][ny][pushToX][pushToY] = 1;
                    cand.push_back(Node{nx, ny, pushToX, pushToY});
                }
            } else if (valid(nx, ny) && !vis[nx][ny]) {
                // 未抵达推箱子位置，继续查找
                dfs(Node{nx, ny, bx, by}, cand, vis);
            }
        }
    }
    
    int minPushBox(vector<vector<char>>& A) {
        memset(flag, 0, sizeof(flag));
        G_ = A;
        M_ = A.size(), N_ = A[0].size();
        int sx,sy,bx,by;
        int tx,ty;
        for (int i = 0; i < M_; ++i) {
            for (int j = 0; j < N_; ++j) {
                if (A[i][j] == 'S') {
                    sx = i, sy = j;
                }
                if (A[i][j] == 'B') {
                    bx = i, by = j;
                }
                if (A[i][j] == 'T') {
                    tx = i, ty = j;
                }
            }
        }
        // 将 人 箱子 目的地置为空，方便后续遍历
        G_[sx][sy] = '.';
        G_[bx][by] = '.';
        G_[tx][ty] = '.';
        
        queue<Node> qu;
        qu.push(Node({sx,sy,bx,by}));
        int depth = 0;
        while (!qu.empty()) {
            int len = (int)qu.size();
            while (len--) {
                // 类似层次遍历，每个循环都要去找下一步可能的方向
                auto nd = qu.front(); qu.pop();
                if (nd.bx == tx && nd.by == ty) {
                    // 抵达目的地
                    return depth;
                }
                vector<Node> cand;
                vector<vector<bool>> vis(M_, vector<bool>(N_));
                // dfs查找，人是否可以到对应的推箱子的方向的位置，可以的话，将方向放入候选方向 cand
                dfs(nd, cand, vis);
                for (auto nd: cand) {
                    qu.push(nd);
                }
            }
            // 完成了一次推动
            depth++;
        }
        return -1;
    }
};
```