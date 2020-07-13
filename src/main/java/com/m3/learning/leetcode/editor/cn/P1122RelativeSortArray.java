//给你两个数组，arr1 和 arr2， 
//
// 
// arr2 中的元素各不相同 
// arr2 中的每个元素都出现在 arr1 中 
// 
//
// 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末
//尾。 
//
// 
//
// 示例： 
//
// 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
// 
//
// 
//
// 提示： 
//
// 
// arr1.length, arr2.length <= 1000 
// 0 <= arr1[i], arr2[i] <= 1000 
// arr2 中的元素 arr2[i] 各不相同 
// arr2 中的每个元素 arr2[i] 都出现在 arr1 中 
// 
// Related Topics 排序 数组 
// 👍 61 👎 0


package com.m3.learning.leetcode.editor.cn;

import java.util.*;
import java.util.stream.Collectors;

public class P1122RelativeSortArray {
    public static void main(String[] args) {
        Solution solution = new P1122RelativeSortArray().new Solution();
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19}, arr2 = {2,1,4,3,9,6};
        int[] pOut = {2,2,2,1,4,3,3,9,6,7,19};
        System.out.println(Arrays.equals(pOut, solution.relativeSortArray(arr1, arr2)));

        Solution2 solution2 = new P1122RelativeSortArray().new Solution2();
        System.out.println(Arrays.equals(pOut, solution2.relativeSortArray(arr1, arr2)));

        Solution3 solution3 = new P1122RelativeSortArray().new Solution3();
        System.out.println(Arrays.equals(pOut, solution3.relativeSortArray(arr1, arr2)));

        Solution4 solution4 = new P1122RelativeSortArray().new Solution4();
        System.out.println(Arrays.equals(pOut, solution4.relativeSortArray(arr1, arr2)));

        Solution5 solution5 = new P1122RelativeSortArray().new Solution5();
        System.out.println(Arrays.equals(pOut, solution5.relativeSortArray(arr1, arr2)));

        Solution6 solution6 = new P1122RelativeSortArray().new Solution6();
        System.out.println(Arrays.equals(pOut, solution6.relativeSortArray(arr1, arr2)));

    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    //方案1：计数排序
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] count = new int[1001];
        for(int num1 : arr1) {
            count[num1]++; //技巧1：用arr1的数值作为下表，计数为值。一举两得：数据下标天然排序，同时统计了计数。
        }

        int index = 0;
        for(int num2 : arr2) {
            while(count[num2] > 0) { // 技巧2：通过自减计数，添加重复数值。
                arr1[index++] = num2; //技巧3：直接使用arr1数组排序，节省空间；@Bug：修改了入参arr1，可能引入潜在错误
                count[num2]--;
            }
        }

        for(int i = 0; i < count.length; i++) {
            while(count[i] > 0) {
                arr1[index++] = i;
                count[i]--;
            }
        }

        return arr1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    //leetcode submit region begin(Prohibit modification and deletion)
    //方案2：自定义比较函数
    class Solution2 {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            Integer[] newArr1 = Arrays.stream(arr1).boxed().toArray(Integer[]::new);
            Map<Integer, Integer> arr2Map = new HashMap<>();
            for (int i = 0; i < arr2.length; i++) {
                arr2Map.put(arr2[i], i);
            }
            Arrays.sort(newArr1, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (arr2Map.containsKey(o1) && arr2Map.containsKey(o2)) {
                        return arr2Map.get(o1) - arr2Map.get(o2);
                    } else if (arr2Map.containsKey(o1)) {
                        return -1;
                    } else if (arr2Map.containsKey(o2)) {
                        return 1;
                    } else {
                        return o1 - o2;
                    }
                }
            });

            return Arrays.stream(newArr1).mapToInt(Integer::valueOf).toArray();
        }
    }

    //方案3：自定义比较函数，并使用lambda
    class Solution3 {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            Map<Integer, Integer> arr2Map = new HashMap<>();
            for (int i = 0; i < arr2.length; i++) {
                arr2Map.put(arr2[i], i);
            }
            List<Integer> newArr1 = Arrays.stream(arr1).boxed().collect(Collectors.toList());
            Collections.sort(newArr1, (num1, num2) -> {
                if(arr2Map.containsKey(num1) || arr2Map.containsKey(num2)) {
                    return arr2Map.getOrDefault(num1, 1000) - arr2Map.getOrDefault(num2, 1000);
                }
                else {
                    return (num1 - num2);
                }
            });
            return newArr1.stream().mapToInt(Integer::valueOf).toArray();
        }
    }

    //方案4：快速排序
    class Solution4 {

        Map<Integer, Integer> arr2Map;

        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            arr2Map = new HashMap<>();
            for (int i = 0; i < arr2.length; i++) {
                arr2Map.put(arr2[i], i);
            }

            quickSort(arr1, 0, arr1.length-1);
            return arr1;
        }

        private void quickSort(int[] arr, int lo, int hi) {
            if(lo >= hi) return;
            int j = partition(arr, lo, hi);
            quickSort(arr, lo, j - 1);
            quickSort(arr, j + 1, hi);
        }

        private int partition(int[] arr, int lo, int hi) {
            int temp = arr[hi];
            int j = lo;
            for(int i = lo; i < hi; i++) {
                if(less(arr[i], temp)) {
                    swap(arr, i, j++);
                }
            }
            swap(arr, j, hi);
            return j;
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        private boolean less(int num1, int num2) {
            if (arr2Map.containsKey(num1) && arr2Map.containsKey(num2)) {
                return arr2Map.get(num1) < arr2Map.get(num2);
            } else if (arr2Map.containsKey(num1)) {
                return true;
            } else if (arr2Map.containsKey(num2)) {
                return false;
            } else {
                return num1 < num2;
            }
        }
    }

    //方案5：希尔排序
    class Solution5 {

        Map<Integer, Integer> arr2Map;

        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            arr2Map = new HashMap<>();
            for (int i = 0; i < arr2.length; i++) {
                arr2Map.put(arr2[i], i);
            }

            shellSort(arr1);
            return arr1;
        }

        private void shellSort(int[] arr) {
            int length = arr.length;
            for(int i = 1; i < length; i++) {
                for(int j = i; j >=1 & less(arr[j], arr[j-1]); j--) {
                    swap(arr, j, j -1);
                }
            }
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        private boolean less(int num1, int num2) {
            if (arr2Map.containsKey(num1) && arr2Map.containsKey(num2)) {
                return arr2Map.get(num1) < arr2Map.get(num2);
            } else if (arr2Map.containsKey(num1)) {
                return true;
            } else if (arr2Map.containsKey(num2)) {
                return false;
            } else {
                return num1 < num2;
            }
        }
    }

    //方案6：归并排序
    class Solution6 {

        Map<Integer, Integer> arr2Map;

        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            arr2Map = new HashMap<>();
            for (int i = 0; i < arr2.length; i++) {
                arr2Map.put(arr2[i], i);
            }

            mergeSort(arr1, 0, arr1.length-1);
            return arr1;
        }

        private void mergeSort(int[] arr, int lo, int hi) {
            if(lo >= hi) return;
            int mid = lo + (hi - lo>>1);
            mergeSort(arr, lo, mid);
            mergeSort(arr, mid+1, hi);
            merge(arr, lo, mid, hi);
        }

        private void merge(int[] arr, int lo, int mid, int hi) {
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

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        private boolean less(int num1, int num2) {
            if (arr2Map.containsKey(num1) && arr2Map.containsKey(num2)) {
                return arr2Map.get(num1) < arr2Map.get(num2);
            } else if (arr2Map.containsKey(num1)) {
                return true;
            } else if (arr2Map.containsKey(num2)) {
                return false;
            } else {
                return num1 < num2;
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)
}