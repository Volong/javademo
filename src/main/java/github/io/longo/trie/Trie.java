package github.io.longo.trie;

import org.apache.commons.lang.StringUtils;

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
public class Trie {

    private Trie[] childNode;
    
    private int freq;
    
    private char nodeChar;
    
    private Set<Integer> ids = new HashSet<>();

    public Trie() {
        childNode = new Trie[26];
    }

    public Trie[] getChildNode() {
        return childNode;
    }

    public void setChildNode(Trie[] childNode) {
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
    
    private boolean deleteTrie(String word, int id) {
        if (StringUtils.isBlank(word)) {
            return false;
        }
        return deleteTrie(this, word, id);
    }
    
    private boolean deleteTrie(Trie root, String word, int id) {
        
        if (word.length() == 0) {
            return true;
        }
        
        int index = word.charAt(0) - 'a';
        
        if (root.childNode[index] == null) {
            return false;
        }
        
        // 如果是最后一个单词，且要删除的字符串存在
        if (word.length() == 0 && this.childNode[index].freq > 0) {
            root.childNode[index].freq--;
        }
        
        root.childNode[index].ids.remove(id);
        
        String remainWord = word.substring(1);
        
        return deleteTrie(root.childNode[index], remainWord, id);
    }

    private void addTrieNode(String word, int id) {
        addTrie(this, word, id);
    }

    private void addTrie(Trie root, String word, int id) {
        
        if (word.length() == 0) {
            return;
        }
        
        int index = word.charAt(0) - 'a';
        
        // 如果子节点为null，则生成一个新的子节点
        if (root.childNode[index] == null) { 
            root.childNode[index] = new Trie();
            root.childNode[index].nodeChar = word.charAt(0);
        }
        
        // 将包含该节点的字符串的id记录下来    
        root.childNode[index].getIds().add(id);
        
        // 剩余字符
        String remainWord = word.substring(1);
        
        // 记录该字符串出现几次
        if (remainWord.length() == 0) {
            root.childNode[index].freq++;
        }
        
        addTrie(root.childNode[index], remainWord, id);
    }
    
    private Set<Integer> searchTrie(String word) {
        Set<Integer> idSet = new HashSet<>();
        return searchTrie(this, word, idSet);
    }

    private Set<Integer> searchTrie(Trie root, String word, Set<Integer> idSet) {
        
        if (word.length() == 0) {
            return idSet;
        }
        
        int index = word.charAt(0) - 'a';
        
        if (root.childNode[index] == null) {
            return idSet;
        }
        
        String remainWord = word.substring(1);
        
        // 如果没有剩余字符则获取以该字符串开头的字符串的id
        if (remainWord.length() == 0) {
            idSet = root.childNode[index].getIds();
        }
        
        return searchTrie(root.childNode[index], remainWord, idSet);
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
        
        Trie root = new Trie();
        
        root.addTrieNode("abc", 1);
        root.addTrieNode("b", 2);
        root.addTrieNode("bc", 3);
        root.addTrieNode("ab", 4);
        root.addTrieNode("abcd", 5);
        
        System.out.println(root.deleteTrie("abcde", 3));
        
        Set<Integer> searchTrie = root.searchTrie("bc");
        
        System.out.println(searchTrie);
        
        System.out.println(searchTrie);
        
    }
}
