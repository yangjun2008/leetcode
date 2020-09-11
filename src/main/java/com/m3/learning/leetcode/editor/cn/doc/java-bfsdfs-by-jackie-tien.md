### 解题思路
这个题目如果只需要箱子自己移动，而不需要人去推，那么就是一个简单的BFS就可以搞定。

多了人去推之后，我们需要多考虑两个因素，
- 箱子处于(x, y)处时，人的坐标是什么(peopleX, peopleY)；
- 箱子如果想要向左移动(即箱子想从(x, y)移动到(x, y-1)时)，人能否从当前位置移动到箱子的右边去推它(即人能否从(peopleX, peopleY)移动到(x, y+1))

处于上面的考虑
- 我们的Point类不仅需要保存箱子的位置(x, y)，还需要保存人当前的位置(peopleX, peopleY)
- visited数组用于记录该坐标是否访问过，两维也不够，需要三维，[n][m][4]，第三维去记录访问的方向，即如果visited[x][y][0]=true，只能表示箱子从右往左被推到过(x, y)，若此时visited[x][y][1]=false，则表示箱子还能从左往右被推到(x, y)处
- 最后，我们在canReach方法中，我们封装了“人能否从当前位置到达推箱子的位置”的判断逻辑，其实就是一个DFS的过程


### 代码

```java
class Solution {
     private static final int[][] directions = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

  public int minPushBox(char[][] grid) {
    int targetX = -1;
    int targetY = -1;
    int startX = -1;
    int startY = -1;
    int peopleX = -1;
    int peopleY = -1;
    int n = grid.length;
    int m = grid[0].length;
    boolean[][][] visited = new boolean[n][m][4];
    Queue<Point> queue = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 'T') {
          targetX = i;
          targetY = j;
        } else if (grid[i][j] == 'B') {
          startX = i;
          startY = j;
        } else if (grid[i][j] == 'S') {
          peopleX = i;
          peopleY = j;
        }
      }
    }

    grid[startX][startY] = '#';
    for (int i = 0; i < directions.length;  i++) {
      int[] direction = directions[i];
      int x = startX + direction[0];
      int y = startY + direction[1];
      if (canReach(grid, n, m, peopleX, peopleY, x, y)) {
        queue.add(new Point(startX, startY, x, y));
        visited[startX][startY][i] = true;
      }
    }
    grid[startX][startY] = 'B';
    int step = 0;
    while (!queue.isEmpty()) {
      for (int i = queue.size(); i > 0; i--) {
        Point point = queue.poll();
        if (point.x == targetX && point.y == targetY) {
          return step;
        }
        grid[point.x][point.y] = '#';
        // up
        if (point.x - 1 >= 0 && grid[point.x - 1][point.y] != '#' && !visited[point.x - 1][point.y][2]
            && canReach(grid, n, m, point.peopleX, point.peopleY, point.x + 1, point.y)) {
          queue.add(new Point(point.x - 1, point.y, point.x, point.y));
          visited[point.x - 1][point.y][2] = true;
        }
        // down
        if (point.x + 1 < n && grid[point.x + 1][point.y] != '#' && !visited[point.x + 1][point.y][3]
            && canReach(grid, n, m, point.peopleX, point.peopleY, point.x - 1, point.y)) {
          queue.add(new Point(point.x + 1, point.y, point.x, point.y));
          visited[point.x + 1][point.y][3] = true;
        }
        // left
        if (point.y - 1 >= 0 && grid[point.x][point.y - 1] != '#' && !visited[point.x][point.y - 1][1]
            && canReach(grid, n, m, point.peopleX, point.peopleY, point.x, point.y + 1)) {
          queue.add(new Point(point.x, point.y - 1, point.x, point.y));
          visited[point.x][point.y - 1][1] = true;
        }
        // right
        if (point.y + 1 < m && grid[point.x][point.y + 1] != '#' && !visited[point.x][point.y + 1][0]
            && canReach(grid, n, m, point.peopleX, point.peopleY, point.x, point.y - 1)) {
          queue.add(new Point(point.x, point.y + 1, point.x, point.y));
          visited[point.x][point.y + 1][0] = true;
        }
        grid[point.x][point.y] = '.';
      }

      step++;
    }
    return -1;
  }

  private boolean canReach(char[][] grid, int n, int m, int startX, int startY, int targetX,
      int targetY) {
    if (targetX < 0 || targetX >= n || targetY < 0 || targetY >= m) {
      return false;
    }
    boolean[][] visited = new boolean[n][m];
    visited[startX][startY] = true;
    Stack<Point> stack = new Stack<>();
    stack.push(new Point(startX, startY));
    while (!stack.isEmpty()) {
      Point point = stack.pop();
      if (point.x == targetX && point.y == targetY) {
        return true;
      }
      for (int[] direction : directions) {
        int x = point.x + direction[0];
        int y = point.y + direction[1];
        if (x >= 0 && x < n && y >= 0 && y < m && grid[x][y] != '#' && !visited[x][y]) {
          visited[x][y] = true;
          stack.add(new Point(x, y));
        }
      }
    }
    return false;
  }


  private static class Point {

    int x;
    int y;
    int peopleX;
    int peopleY;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public Point(int x, int y, int peopleX, int peopleY) {
      this.x = x;
      this.y = y;
      this.peopleX = peopleX;
      this.peopleY = peopleY;
    }
  }
}   
```