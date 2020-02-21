/**
 *
 * APDPlat - Application Product Development Platform
 * Copyright (c) 2013, 杨尚川, yang-shangchuan@qq.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package github.io.longo.trie;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 双数组前缀树的Java实现
 * 用于查找一个指定的字符串是否在词典中
 * An Implementation of Double-Array Trie: http://linux.thai.net/~thep/datrie/datrie.html
 * @author 杨尚川
 */
public class DoubleArrayDictionaryTrie {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DoubleArrayDictionaryTrie.class);
    
    private AtomicInteger maxLength = new AtomicInteger();
    
    private static final int SIZE = 260_0000;

    private static class Node {
        private int code;
        private int depth;
        private int left;
        private int right;

        @Override
        public String toString() {
            return "Node{" +
                    "code=" + code + "["+ (char)code + "]" +
                    ", depth=" + depth +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    };

    /**
     * 用来存储每个节点的开始位置 
     * check[begin + code] = begin
     */
    private int[] check;
    
    /**
     * 用来确定子节点的开始位置 
     * base[parentBegin + code] = childBegin
     */
    private int[] base;
    
    private boolean[] used;
    
    private int nextCheckPos;

    public DoubleArrayDictionaryTrie(){
        LOGGER.info("初始化词典：" + this.getClass().getName());
    }

    private List<Node> toTree(Node parent, List<String> words) {
        
        List<Node> siblings = new ArrayList<>();
        int prev = 0;

        for (int i = parent.left; i < parent.right; i++) {
            
            // 跳过已经处理的数据
            if (words.get(i).length() < parent.depth) {
                continue;
            }

            String word = words.get(i);

            int cur = 0;
            if (word.length() != parent.depth) {
                cur = (int) word.charAt(parent.depth);
                System.out.println("当前节点:" + word.charAt(parent.depth) + " 的 code 为:" + cur);
            }
            
            // 收集当前所有的兄弟节点
            if (cur != prev || siblings.isEmpty()) {
                Node node = new Node();
                node.depth = parent.depth + 1;
                node.code = cur;
                node.left = i;
                if (!siblings.isEmpty()) {
                    siblings.get(siblings.size() - 1).right = i;
                }
                siblings.add(node);
            }

            prev = cur;
        }

        if (!siblings.isEmpty()) {
            
            // 最后一个兄弟节点的 right 等于父节点的 right，即子节点的最大范围等于父节点的最大范围
            siblings.get(siblings.size() - 1).right = parent.right;
            
            if(LOGGER.isDebugEnabled() && words.size() < 10) {
                LOGGER.debug("************************************************");
                LOGGER.debug("树信息：");
                siblings.forEach(s -> LOGGER.debug(s.toString()));
                LOGGER.debug("************************************************");
            }
        }
        return siblings;
    }

    private int toDoubleArray(List<Node> siblings, List<String> words) {
        int begin = 0;
        int index = (siblings.get(0).code > nextCheckPos) ? siblings.get(0).code : nextCheckPos;
        boolean isFirst = true;

        outer: while (true) {
            
            index++;

            if (check[index] != 0) {
                continue;
            } else if (isFirst) {
                nextCheckPos = index;
                isFirst = false;
            }

            begin = index - siblings.get(0).code;

            // 如果这个位置已经被占用了，则继续往下找
            if (used[begin]) {
                continue;
            }

            for (int i = 1; i < siblings.size(); i++) {
                if (check[begin + siblings.get(i).code] != 0) {
                    continue outer;
                }
            }

            break;
        }

        used[begin] = true;

        for (int i = 0; i < siblings.size(); i++) {
            // 兄弟节点的开始位置都相同
            check[begin + siblings.get(i).code] = begin;
        }

        for (int i = 0; i < siblings.size(); i++) {
            
            List<Node> newSiblings = toTree(siblings.get(i), words);

            if (newSiblings.isEmpty()) {
                // 如果没有子节点了，则将子节点位置的值设为 -1
                base[begin + siblings.get(i).code] = -1;
            } else {
                // 获取子节点的 begin
                int h = toDoubleArray(newSiblings, words);
                base[begin + siblings.get(i).code] = h;
            }
        }
        return begin;
    }
    private void allocate(int size){
        check = null;
        base = null;
        used = null;
        nextCheckPos = 0;

        base = new int[size];
        check = new int[size];
        used = new boolean[size];
        base[0] = 1;
    }
    private void init(List<String> words) {
        if (words == null || words.isEmpty()) {
            return;
        }

        //前缀树的虚拟根节点
        Node rootNode = new Node();
        rootNode.left = 0;
        rootNode.right = words.size();
        rootNode.depth = 0;

        int size = SIZE;
        while (true) {
            try {
                allocate(size);
                List<Node> siblings = toTree(rootNode, words);
                toDoubleArray(siblings, words);
                break;
            } catch (Exception e) {
                size += size/10;
                LOGGER.error("分配空间不够，增加至： " + size);
            }
        }

        words.clear();
        words = null;
        used = null;
    }

    public int getMaxLength() {
        return maxLength.get();
    }

    public boolean contains(String item, int start, int length) {
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("开始查词典：{}", item.substring(start, start + length));
        }
        if (base == null){
            return false;
        }

        //base[0]=1
        int parentBegin = base[0];
        int childBegin;

        for (int i = start; i < start + length; i++) {
            // 确定节点所在的位置
            childBegin = parentBegin + (int) item.charAt(i);
            
            if (childBegin >= check.length || childBegin < 0) {
                return false;
            }
            
            // 如果子节点存在
            if (parentBegin == check[childBegin]) {
                // 确定下一个子节点的开始位置
                parentBegin = base[childBegin];
            } else {
                return false;
            }
        }
        
        childBegin = parentBegin;
        
        if (childBegin >= check.length || childBegin < 0) {
            return false;
        }
        
        if (base[childBegin] < 0 && childBegin == check[childBegin]) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("在词典中查到词：{}", item.substring(start, start + length));
            }
            return true;
        }
        return false;
    }

    public boolean contains(String item) {
        return contains(item, 0, item.length());
    }

    public void addAll(List<String> items) {
        
        if(check!=null){
            throw new RuntimeException("addAll method can just be used once after clear method!");
        }

        items=items.stream()
                   .map(String::trim)
                   .filter(item -> {
                       //统计最大词长
                       int len = item.length();
                       if(len > maxLength.get()){
                           maxLength.set(len);
                       }
                       return len > 0;
                   })
                   .sorted().collect(Collectors.toList());
        
        if(LOGGER.isDebugEnabled()){
            //for debug
            if (items.size()<10){
                items.forEach(item->LOGGER.debug(item));
            }
        }
        init(items);
    }

    public void add(String item) {
        throw new RuntimeException("not yet support, please use addAll method!");
    }

    public void removeAll(List<String> items) {
        throw new RuntimeException("not yet support menthod!");
    }

    public void remove(String item) {
        throw new RuntimeException("not yet support menthod!");
    }

    public void clear() {
        check = null;
        base = null;
        used = null;
        nextCheckPos = 0;
        maxLength.set(0);
    }

    public static void main(String[] args) {
        DoubleArrayDictionaryTrie dictionary = new DoubleArrayDictionaryTrie();

//        List<String> words = Arrays.asList("一举", "一举一动", "一举成名", "一举成名天下知", "万能", "万能胶");
        List<String> words = Arrays.asList("一举");

        //构造词典
        dictionary.addAll(words);

        System.out.println("查找 一举：" + dictionary.contains("一举"));
    }
}