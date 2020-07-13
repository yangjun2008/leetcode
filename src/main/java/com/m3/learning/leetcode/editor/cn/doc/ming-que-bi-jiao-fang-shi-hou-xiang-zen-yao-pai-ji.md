[TOC]
### 1122. 数组的相对排序

#### 计数排序

- 这一题第一反应是自定义比较函数，然后再进行排序，但是做完之后[参考*^2*](https://leetcode.com/problems/relative-sort-array/discuss/335056/Java-in-place-solution-using-counting-sort)发现，最简单的是用计数排序，充分利用了题目所给提示信息
- 思路也很清晰，先将`arr1`中的数全都记录到数组`count`中，然后遍历`arr2`的同时，将`arr2`中的数置入`arr1`，注意由于`arr2`中的数在`arr1`中也出现了，所以直接从`count`中取出即可
- 处理好`arr2`之后，再处理剩下的数字，一格一格往里填就好了

```Java
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] count = new int[1001];
        // 将 arr1 中的数记录下来
        for (int num1 : arr1) {
            count[num1]++;
        }
        // 先安排 arr2 中的数
        int i = 0;
        for (int num2 : arr2) {
            while (count[num2] > 0) {
                arr1[i++] = num2;
                count[num2]--;
            }
        }
        // 再排剩下的数
        for (int num1 = 0; num1 < count.length; num1++) {
            while (count[num1] > 0) {
                arr1[i++] = num1;
                count[num1]--;
            }
        }
        return arr1;
    }
}
```




#### 自定义比较函数

- 这一题的思路很简单，依照要求，自定义一个比较函数，然后进行排序即可
- 按题目要求我们知道，假设我们按升序排序，对于两个元素`num1`,`num2`，他们的比较有三种情况
  1. 都存在于`arr2`中，按他们的下标进行比较，可类比为靠前的数更小
  2. 其中一个存在于`arr2`，那么存在与`arr2`中的数靠前排，可认为更小
  3. 两个数都不存在于`arr2`，那么很简单，直接比较两个数大小即可
- 根据以上思路很容易想到用一个`HashMap`存储`arr2`中元素和小标的对应关系，便于比较

```Java
Map<Integer, Integer> record = new HashMap<>(arr2.length);
for (int i = 0; i < arr2.length; i++) {
    record.put(arr2[i], i);
}
public boolean less(int num1, int num2) {
    if (record.containsKey(num1) && record.containsKey(num2)) {
        return record.get(num1) < record.get(num2);
    } else if (record.containsKey(num1)) {
        return true;
    } else if (record.containsKey(num2)) {
        return false;
    } else {
        return num1 < num2;
    }
}
```
### 其他排序

- 有了比较函数再进行排序就简单多了，你可以手写其他喜欢的排序方式，这里提供常用的 lambda、***、归并、希尔的写法以供参考

#### lambda表达式

```Java
class Solution {
    Map<Integer, Integer> record;
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        record = new HashMap<>(arr2.length);
        for (int i = 0; i < arr2.length; i++) {
            record.put(arr2[i], i);
        }
        List<Integer> res = Arrays.stream(arr1).boxed().collect(Collectors.toList());

        Collections.sort(res, (num1, num2) -> {
            if (record.containsKey(num1) || record.containsKey(num2)) {
                return record.getOrDefault(num1, 1000) - record.getOrDefault(num2, 1000);
            } else {
                return num1 - num2;
            }
        });
        return res.stream().mapToInt(i -> i).toArray();
    }
}
```

#### 快速排序

```Java
class Solution {
    Map<Integer, Integer> record;
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        record = new HashMap<>(arr2.length);
        for (int i = 0; i < arr2.length; i++) {
            record.put(arr2[i], i);
        }
        quickSort(arr1, 0, arr1.length - 1);
        return arr1;
    }
    public void quickSort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(arr, lo, hi);
        quickSort(arr, lo, j - 1);
        quickSort(arr, j + 1, hi);
    }
    public int partition(int[] arr, int lo, int hi) {
        int temp = arr[hi];
        int j = lo;
        for (int i = lo; i < hi; i++) {
            if (less(arr[i], temp)) {
                swap(arr, i, j++);
            }
        }
        swap(arr, j, hi);
        return j;
    }
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public boolean less(int num1, int num2) {
        if (record.containsKey(num1) && record.containsKey(num2)) {
            return record.get(num1) < record.get(num2);
        } else if (record.containsKey(num1)) {
            return true;
        } else if (record.containsKey(num2)) {
            return false;
        } else {
            return num1 < num2;
        }
    }
}
```

#### 归并排序

```Java
class Solution {
    Map<Integer, Integer> record;
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        record = new HashMap<>(arr2.length);
        for (int i = 0; i < arr2.length; i++) {
            record.put(arr2[i], i);
        }
        mergeSort(arr1, 0, arr1.length - 1);
        return arr1;
    }
    public void mergeSort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo >> 1);
        mergeSort(arr, lo, mid);
        mergeSort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }
    public void merge(int[] arr, int lo, int mid, int hi) {
        int[] temp = new int[hi - lo + 1];
        int i = lo;
        int j = mid + 1;
        for (int k = 0; k < temp.length; k++) {
            if (i > mid) {
                temp[k] = arr[j++];
            } else if (j > hi) {
                temp[k] = arr[i++];
            } else if (less(arr[i], arr[j])) {
                temp[k] = arr[i++];
            } else {
                temp[k] = arr[j++];
            }
        }
        for (int k = 0; k < temp.length; k++) {
            arr[lo + k] = temp[k];
        }
    }
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public boolean less(int num1, int num2) {
        if (record.containsKey(num1) && record.containsKey(num2)) {
            return record.get(num1) < record.get(num2);
        } else if (record.containsKey(num1)) {
            return true;
        } else if (record.containsKey(num2)) {
            return false;
        } else {
            return num1 < num2;
        }
    }
}
```
#### 希尔排序
- 希尔排序写起来最快了，就是运行慢一点`14ms`

```Java
class Solution {
    Map<Integer, Integer> record;
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        record = new HashMap<>(arr2.length);
        for (int i = 0; i < arr2.length; i++) {
            record.put(arr2[i], i);
        }
        shellSort(arr1);
        return arr1;
    }
    public void shellSort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j >= 1 && less(arr[j], arr[j - 1]); j--) {
                swap(arr, j, j - 1);
            }
        }
    }
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public boolean less(int nums1, int nums2) {
        if (record.containsKey(nums1) && record.containsKey(nums2)) {
            return record.get(nums1) < record.get(nums2);
        } else if (record.containsKey(nums1)) {
            return true;
        } else if (record.containsKey(nums2)) {
            return false;
        } else {
            return nums1 < nums2;
        }
    }
}
```


### 参考链接
1. [lambda and stream](https://leetcode.com/problems/relative-sort-array/discuss/335574/Java-Solution-using-Lambda-and-Streams-with-Comments)
2. [counting sort](https://leetcode.com/problems/relative-sort-array/discuss/335056/Java-in-place-solution-using-counting-sort)