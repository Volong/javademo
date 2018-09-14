package github.io.volong.trie;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Trie 树的实现 <br>
 * 参考：http://www.cnblogs.com/huangxincheng/archive/2012/11/25/2788268.html <br>
 * 
 * 26叉trie树
 * @time 2017年11月24日 下午4:34:29
 * @author Volong
 *
 */
public class TrieNode {

    private TrieNode[] childNode;
    
    private int freq;
    
    private char nodeChar;
    
    private Set<Integer> ids = new HashSet<>();

    public TrieNode() {
        childNode = new TrieNode[26];
    }

    public TrieNode[] getChildNode() {
        return childNode;
    }

    public void setChildNode(TrieNode[] childNode) {
        this.childNode = childNode;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public char getNodeChar() {
        return nodeChar;
    }

    public void setNodeChar(char nodeChar) {
        this.nodeChar = nodeChar;
    }

    public Set<Integer> getIds() {
        return ids;
    }

    public void setIds(Set<Integer> ids) {
        this.ids = ids;
    }

    private void addTrieNode(TrieNode root, String word, int id) {
        
        if (word.length() == 0) {
            return;
        }
        
        int index = word.charAt(0) - 'a';

        System.out.println(word);
        
        // 如果子节点为null，则生成一个新的子节点
        if (root.childNode[index] == null) { 
            root.childNode[index] = new TrieNode();
            root.childNode[index].nodeChar = word.charAt(0);
        }
        
        // 将包含该节点的字符串的id记录下来    
        root.childNode[index].getIds().add(id);
        
        // 剩余字符
        String remainWord = word.substring(1);
        
        // 记录该单词出现几次
        if (remainWord.length() == 0) {
            root.childNode[index].freq++;
        }
        
        addTrieNode(root.childNode[index], remainWord, id);
    }
    
    private Set<Integer> searchTrie(String word) {
        Set<Integer> idSet = new HashSet<>();
        return SearchTrie(this, word, idSet);
    }

    private Set<Integer> SearchTrie(TrieNode root, String word, Set<Integer> idSet) {
        
        if (word.length() == 0) {
            return idSet;
        }
        
        int index = word.charAt(0) - 'a';
        
        String remainWord = word.substring(1);
        
        // 如果没有剩余字符则获取以该字符串开头的字符串的id
        if (remainWord.length() == 0) {
            idSet = root.childNode[index].getIds();
        }
        
        return SearchTrie(root.childNode[index], remainWord, idSet);
    }
    
    public static void main(String[] args) throws IOException {
        
        /*File f = new File("C:\\Users\\Volong\\Desktop\\1.txt");
        
        BufferedReader reader = new BufferedReader(new FileReader(f));
        
        String str = null;
        
        while ((str = reader.readLine()) != null) {
            String[] words = str.split(" ");
            root.addTrieNode(root, words[1].trim().toLowerCase(), Integer.parseInt(words[0]));
        }
        
        reader.close();*/
        
        TrieNode root = new TrieNode();
        
        root.addTrieNode(root, "abc", 1);
        root.addTrieNode(root, "b", 2);
        root.addTrieNode(root, "bc", 3);
        root.addTrieNode(root, "ab", 4);
        root.addTrieNode(root, "abcd", 5);
        
        Set<Integer> searchTrie = root.searchTrie("a");
        
        System.out.println(searchTrie);
    }
}
