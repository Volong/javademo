package github.io.volong.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestDoubleArrayTrie {

    public static void main(String[] args) throws IOException {

        String line;
        InputStream resourceAsStream = TestDoubleArrayTrie.class.getClassLoader().getResourceAsStream("small.dic");
        List<String> words = new ArrayList<String>();
        Set<Character> charset = new HashSet<Character>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream, "utf-8"));
        while ((line = reader.readLine()) != null) {
            words.add(line);
            // 制作一份码表debug
            for (char c : line.toCharArray()) {
                charset.add(c);
            }
        }
        reader.close();
        // 这个字典如果要加入新词必须按字典序，参考下面的代码
        // Collections.sort(words);
        // BufferedWriter writer = new BufferedWriter(new
        // FileWriter("./data/sorted.dic", false));
        // for (String w : words)
        // {
        // writer.write(w);
        // writer.newLine();
        // }
        System.out.println("字典词条：" + words.size());

        String infoCharsetValue = "";
        String infoCharsetCode = "";
        for (Character c : charset) {
            infoCharsetValue += c.charValue() + "           ";
            infoCharsetCode += (int) c.charValue() + " ";
        }
        infoCharsetValue += '\n';
        infoCharsetCode += '\n';
        System.out.print(infoCharsetValue);
        System.out.print(infoCharsetCode);
        
        System.out.println("-----------------");
        
        DoubleArrayTrie dat = new DoubleArrayTrie();
        System.out.println("是否错误: " + dat.build(words));
        
        List<Integer> integerList = dat.commonPrefixSearch("一举一动");
        for (int index : integerList) {
            System.out.println(words.get(index));
        }
        
    }
}
