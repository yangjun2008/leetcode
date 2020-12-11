### 解题思路
最初没打算用前缀树来做，想用hashmap把字典里的单词最短的前缀放进去，然后遍历sentence用indexOf来判断是否需要替换，但感觉上，可能时间预处理上会很慢，因为要一一比较，复杂度O(n2)，后来觉得可能还是前缀树要快些，所以就改用了这个解法，解法不难，主要是需要了解前缀树的数据结构，后面就很好做了。

### 代码

```java
class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        if(dict.size() == 0) {
			return sentence;
		}

        // 预处理
		Trie t = new Trie();
		for(String d : dict) {
			t.insert(d);
		}

		String[] sentenceArr = sentence.split(" ");
		StringBuilder replace = new StringBuilder();
		for(int i=0;i<sentenceArr.length;++i) {
			TrieNode node = t.root;
			boolean needReplace = false;
			for(char c : sentenceArr[i].toCharArray()) {
				// 如果isEnd，说明这是题目要求的最短的前缀的最后一个node节点，直接跳出
				if(node.isEnd) {
					needReplace = true;
					break;
				}
                // 有子节点再往下走，同时加入replace，用于最后替换
                if(node.children.containsKey(c)) {
                    replace.append((c));
					node = node.children.get(c);
                // 没有子节点，说明这个单词在字典里没有前缀，那就不需要替换，直接跳出
				} else {
                    break;
                }
			}
            // 需要替换再替换
			if(needReplace) {
				sentenceArr[i] = replace.toString();
			}
            // 重置
			replace.setLength(0);
		}

		StringBuilder ans = new StringBuilder();
		for(String s : sentenceArr) {
			ans.append(s);
			ans.append(" ");
		}
		ans.deleteCharAt(ans.length() - 1);
		return ans.toString();
    }
}

// 定义前缀树节点结构
class TrieNode {

    // 结束标志
	boolean isEnd;
    // 子节点map
	Map<Character, TrieNode> children;

	TrieNode() {
		this.isEnd = false;
		this.children = new HashMap<>();
	}
}

// 定义前缀树结构
class Trie {

	TrieNode root;

	Trie() {
		this.root = new TrieNode();
	}

    // 用于插入dict
	public void insert(String word) {
		TrieNode node = root;
		for(char c : word.toCharArray()) {
			if(node.isEnd) {
				break;
			}
			if(!node.children.containsKey(c)) {
				node.children.put(c, new TrieNode());
			}
			node = node.children.get(c);
		}
		node.isEnd = true;
	}
}
```