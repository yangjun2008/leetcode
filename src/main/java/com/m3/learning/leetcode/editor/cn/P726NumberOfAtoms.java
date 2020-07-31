//给定一个化学式formula（作为字符串），返回每种原子的数量。 
//
// 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。 
//
// 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
// 
//
// 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。 
//
// 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。 
//
// 给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数
//量（如果数量大于 1），以此类推。 
//
// 示例 1: 
//
// 
//输入: 
//formula = "H2O"
//输出: "H2O"
//解释: 
//原子的数量是 {'H': 2, 'O': 1}。
// 
//
// 示例 2: 
//
// 
//输入: 
//formula = "Mg(OH)2"
//输出: "H2MgO2"
//解释: 
//原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
// 
//
// 示例 3: 
//
// 
//输入: 
//formula = "K4(ON(SO3)2)2"
//输出: "K4N2O14S4"
//解释: 
//原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
// 
//
// 注意: 
//
// 
// 所有原子的第一个字母为大写，剩余字母都是小写。 
// formula的长度在[1, 1000]之间。 
// formula只包含字母、数字和圆括号，并且题目中给定的是合法的化学式。 
// 
// Related Topics 栈 递归 哈希表 
// 👍 78 👎 0


package com.m3.learning.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class P726NumberOfAtoms {
    public static void main(String[] args) {
        Solution solution = new P726NumberOfAtoms().new Solution();
        /*System.out.println("H2O".equals(solution.countOfAtoms("H2O")));
        System.out.println("H2NgO2".equals(solution.countOfAtoms("Ng(OH)2")));
        System.out.println("K4N2O14S4".equals(solution.countOfAtoms("K4(ON(SO3)2)2")));
        System.out.println("".equals(solution.countOfAtoms("")));
        System.out.println("H".equals(solution.countOfAtoms("H")));
        System.out.println("H2".equals(solution.countOfAtoms("H2")));
        System.out.println("H2".equals(solution.countOfAtoms("(H2)")));
        System.out.println("Be32".equals(solution.countOfAtoms("(Be32)")));*/
        System.out.println("B99N33".equals(solution.countOfAtoms("(NB3)33")));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String countOfAtoms(String formula) {
            Map<String, Integer> countMap = new HashMap<>();
            countOfAtoms(formula, countMap, 1);
            String[] keys = countMap.keySet().toArray(new String[0]);
            Arrays.sort(keys);
            StringBuffer retBuffer = new StringBuffer();
            for(String key : keys) {
                retBuffer.append(key);
                int num = countMap.get(key);
                if(num > 1) retBuffer.append(num);
            }
            return retBuffer.toString();
        }

        private void countOfAtoms(String formula, Map<String,Integer> countMap, int num) {
            int leftBracIdx = formula.indexOf('(');
            if(leftBracIdx >= 0) {
                int rightBracIdx = getPeerRightBracIdx(formula, leftBracIdx);
                countOfAtoms(formula.substring(0, leftBracIdx), countMap, num);
                int numEnd = getNumEnd(formula,rightBracIdx+1);
                countOfAtoms(formula.substring(leftBracIdx+1, rightBracIdx), countMap,
                             numEnd>rightBracIdx? num*Integer.parseInt(formula.substring(rightBracIdx+1, numEnd+1)): num);
                if(numEnd+1 < formula.length()) {
                    countOfAtoms(formula.substring(numEnd+1), countMap, num);
                }
            }
            else {
                //do...
                int start = 0;
                String key;
                for(int i = 0; i < formula.length(); i++) {
                    char curChar = formula.charAt(i);
                    if(isUppperLetter(curChar)) {
                        handleOneAtom(formula.substring(start,i), countMap, num);
                        start = i;
                    }
                    if(i == formula.length()-1) {
                        handleOneAtom(formula.substring(start, i+1), countMap, num);
                    }
                }
            }
        }

        private void handleOneAtom(String oneAtom, Map<String, Integer> countMap, int num) {
            if(oneAtom.length() == 0) {
                return;
            }
            for(int i = 0; i < oneAtom.length(); i++) {
                if (isNumber(oneAtom.charAt(i))) {
                    String key = oneAtom.substring(0, i);
                    countMap.put(key, countMap.getOrDefault(key, 0)+ Integer.parseInt(oneAtom.substring(i))*num);
                    return;
                }
            }
            countMap.put(oneAtom, countMap.getOrDefault(oneAtom, 0)+ num);
        }

        private int getPeerRightBracIdx(String formula, int leftBracIdx) {
            int i = leftBracIdx+1;
            int count = 1;
            while(i < formula.length()) {
                if(formula.charAt(i)=='(') {
                    count++;
                }
                else if(formula.charAt(i) == ')') {
                    count--;
                    if(count == 0) {
                        return i;
                    }
                }
                i++;
            }
            return -1;
        }

        private int getNumEnd(String formula, int startIndex) {
            int i = startIndex+1;
            while(i < formula.length()) {
                if(!isNumber(formula.charAt(i))) {
                    return i-1;
                }
                i++;
            }
            return formula.length()-1;
        }

        private boolean isUppperLetter(char c) {
            return c>='A' && c <= 'Z';
        }

        private boolean isNumber(char c) {
            return c >='0' && c <= '9';
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}