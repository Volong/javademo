package trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
        
        File f = new File("C:\\Users\\Volong\\Desktop\\trie测试\\1.txt");
        
        BufferedReader reader = new BufferedReader(new FileReader(f));
        
        String str = null;
        TrieNode root = new TrieNode();
        
        while ((str = reader.readLine()) != null) {
            String[] words = str.split(" ");
            root.addTrieNode(root, words[1].trim().toLowerCase(), Integer.parseInt(words[0]));
        }
        
        reader.close();
        
        Set<Integer> searchTrie = root.searchTrie("go");
        
        System.out.println(searchTrie);
    }
}
