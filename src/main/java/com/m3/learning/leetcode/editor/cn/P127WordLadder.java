//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索 
// 👍 427 👎 0


package com.m3.learning.leetcode.editor.cn;

import java.util.*;

public class P127WordLadder {
    public static void main(String[] args) {
        Solution solution = new P127WordLadder().new Solution();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"});
        System.out.println(solution.ladderLength(beginWord, endWord, wordList));

        wordList = Arrays.asList(new String[]{"hot","dot","doe","lom","cog"});
        System.out.println(solution.ladderLength(beginWord, endWord, wordList));

    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
            Set<String> wordSet = new HashSet<>(wordList);
            if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
                return 0;
            }

            // 第 2 步：图的广度优先遍历，必须使用队列和表示是否访问过的 visited 哈希表
            Queue<String> queue = new LinkedList();
            queue.offer(beginWord);
            Set<String> visited = new HashSet<>();
            visited.add(beginWord);

            // 第 3 步：开始广度优先遍历，包含起点，因此初始化的时候步数为 1
            int step = 1;
            while (!queue.isEmpty()) {
                int currentSize = queue.size();
                for (int i = 0; i < currentSize; i++) {
                    String currentWord = queue.poll();
                    // 如果 currentWord 能够修改 1 个字符与 endWord 相同，则返回 step + 1
                    if (changeWordEveryOneLetter(currentWord, endWord, queue, visited, wordSet)) {
                        return step + 1;
                    }
                }
                step++;
            }
            return 0;
        }

        private boolean changeWordEveryOneLetter(String currentWord, String endWord, Queue<String> queue, Set<String> visited, Set<String> wordSet) {
            char[] charArray = currentWord.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                char curChar = charArray[i];
                for (char repChar = 'a'; repChar <= 'z'; repChar++) {
                    if (repChar == curChar) {
                        continue;
                    }
                    charArray[i] = repChar;
                    String nextWord = String.valueOf(charArray);
                    if (wordSet.contains(nextWord)) {
                        if (nextWord.equals(endWord)) {
                            return true;
                        }
                        if (!visited.contains(nextWord)) {
                            queue.add(nextWord);
                            visited.add(nextWord);
                        }
                    }
                }
                charArray[i] = curChar;
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}