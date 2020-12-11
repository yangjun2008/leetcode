//给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。 
//
// 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0,
// 2, 5]。 
//
// 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。 
//
// 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？ 
//
// 
//
// 示例： 
//
// 输入: words = ["time", "me", "bell"]
//输出: 10
//说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 2000 
// 1 <= words[i].length <= 7 
// 每个单词都是小写字母 。 
// 
// 👍 195 👎 0


package com.m3.learning.jiuyang.dicttree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P820ShortEncodingOfWords {
    public static void main(String[] args) {
        Solution solution = new P820ShortEncodingOfWords().new Solution();
        System.out.println(solution.minimumLengthEncoding(new String[]{"time", "me", "bell"}));
        System.out.println(solution.minimumLengthEncoding(new String[]{"ti", "me", "imed", "med","im"}));
        System.out.println(solution.minimumLengthEncoding(new String[]{"me", "time"}));
        System.out.println(solution.minimumLengthEncoding(new String[]{"time", "atime","btime"}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution2 {
    public int minimumLengthEncoding(String[] words) {
        Map<Character, List<String>> dict = new HashMap<>();
        int count=0;
        for(String word : words) {
            char key = word.charAt(word.length()-1);
            List<String> dictList = dict.getOrDefault(key, new ArrayList<>());
            boolean found = false;
            for(int i = 0; i < dictList.size(); i++) {
                String str = dictList.get(i);
                if(word.endsWith(str)) {
                    found=true;
                    dictList.set(i, word);
                    count += (word.length()-str.length());
                }
                else if(str.endsWith(word)) {
                    found = true;
                }
            }
            if(found == false) {
                dictList.add(word);
                count += (word.length()+1);
            }
            dict.put(key, dictList);
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minimumLengthEncoding(String[] words) {
            Trie root = new Trie();
            for(String word : words) {
                root.insert(new StringBuffer(word).reverse().toString());
            }
            int sum = root.allLeafSize();
            return sum;
        }
    }

    class Trie {
        Trie[] next = new Trie[26];
        private boolean isEnd = false;

        public void insert(String word) {
            Trie curTrie = this;
            for(char c : word.toCharArray()) {
                if( curTrie.next[c-'a'] == null) {
                    curTrie.next[c-'a'] = new Trie();
                }
                curTrie.isEnd = false;
                curTrie = curTrie.next[c-'a'];
            }
            curTrie.isEnd = true;
        }

        public boolean startsWith(String prefix) {
            Trie curTrie = this;
            for(char c : prefix.toCharArray()) {
                if( curTrie.next[c-'a'] == null) {
                    return false;
                }
                curTrie = curTrie.next[c-'a'];
            }
            return true;
        }

        public boolean search(String word) {
            Trie curTrie = this;
            for(char c : word.toCharArray()) {
                if( curTrie.next[c-'a'] == null) {
                    return false;
                }
                curTrie = curTrie.next[c-'a'];
            }
            return curTrie.isEnd;
        }

        public int countNext() {
            int count = 0;
            for(Trie trie : next) {
                if(trie != null)
                    count++;
            }
            return count;
        }

        public int size() {
            int size = 1;
            for(Trie trie : next) {
                if(trie != null)
                    size+=trie.size();
            }
            return size;
        }

        public int allLeafSize() {
            int sum = countDeep(1, this);
            return sum;
        }

        private int countDeep(int curLayer, Trie trie) {
            int sum = 0;
            boolean hasChild = false;
            for (Trie child : trie.next) {
                if (child != null) {
                    hasChild = true;
                    sum += countDeep(curLayer + 1, child);
                }
            }
            if(hasChild == false) {
                return curLayer;
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}