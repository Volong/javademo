/**
 * DoubleArrayTrie: Java implementation of Darts (Double-ARray Trie System)
 * 
 * <p>
 * Copyright(C) 2001-2007 Taku Kudo &lt;taku@chasen.org&gt;<br />
 * Copyright(C) 2009 MURAWAKI Yugo &lt;murawaki@nlp.kuee.kyoto-u.ac.jp&gt;
 * Copyright(C) 2012 KOMIYA Atsushi &lt;komiya.atsushi@gmail.com&gt;
 * </p>
 * 
 * <p>
 * The contents of this file may be used under the terms of either of the GNU
 * Lesser General Public License Version 2.1 or later (the "LGPL"), or the BSD
 * License (the "BSD").
 * </p>
 */
package github.io.volong.trie;

import java.util.ArrayList;
import java.util.List;

/**
 *  来源：https://github.com/komiya-atsushi/darts-java
 *
 */
public class DoubleArrayTrie {
	private static final int UNIT_SIZE = 8; // size of int + int

	private static class Node {
	    /**
	     * 字符的编码 (其实真正的值为编码 + 1)
	     */
		int code;
		/**
		 * 深度
		 */
		int depth;
		/**
		 * 该节点在字典中的左边界
		 */
		int left;
		/**
		 * 该节点在字典中的右边界
		 */
		int right;
	};

	/**
	 * 存储每个节点的起始索引位置，可以用来确定该节点是否存在
	 */
	private int check[];
	
	/**
	 * 存储子节点的起始索引位置，可以通过这个找到子节点的位置
	 */
	private int base[];

	/**
	 * 用来记录那个位置被使用
	 */
	private boolean used[];
	
	/**
	 * 分配内存的大小
	 */
	private int allocSize;
	/**
	 * 词典
	 */
	private List<String> key;
	/**
	 * 有多少个词组
	 */
	private int keySize;
	private int length[];
	private int value[];
	
    private int nextCheckPos;
	// boolean no_delete_;
	int error_;

	// int (*progressfunc_) (size_t, size_t);

	// inline _resize expanded
	private int resize(int newSize) {
		int[] base2 = new int[newSize];
		int[] check2 = new int[newSize];
		boolean used2[] = new boolean[newSize];
		if (allocSize > 0) {
			System.arraycopy(base, 0, base2, 0, allocSize);
			System.arraycopy(check, 0, check2, 0, allocSize);
			System.arraycopy(used2, 0, used2, 0, allocSize);
		}

		base = base2;
		check = check2;
		used = used2;

		return allocSize = newSize;
	}

	/**
	 * 获取子节点
	 * @time 2017年11月29日 下午5:40:03
	 * @author Volong
	 *
	 * @param parent
	 * @param siblings
	 * @return
	 */
	private int fetch(Node parent, List<Node> siblings) {
		if (error_ < 0) {
            return 0;
        }

		// 前一个节点
		int prev = 0;
		
		// 将每个词的第一个字放到字典里面
		// 父节点的 left 与 right 表示查询范围
        for (int i = parent.left; i < parent.right; i++) {
		    
		    // 获取当前的词
			String tmp = key.get(i);
			
			// 如果当前词的所有字都放到了字典中 
			if ((length != null ? length[i] : tmp.length()) < parent.depth) {
			    continue;
			}
            
            // 当前节点
			int cur = 0;
			
			// 当 tmp.length = parent.depth 时，即该词已经全部放入词典
			if ((length != null ? length[i] : tmp.length()) != parent.depth) {
			    // 某个节点的父节点的 depth 就代表了这个字在一个词中的位置
			    char charAt = tmp.charAt(parent.depth);
                cur = (int) charAt + 1;
			}
			
			// 前一个节点的位置 是否大于当前节点
			if (prev > cur) {
				error_ = -3;
				return 0;
			}
			
			// cur = prev 时为空节点，即叶子节点
			// 或者是相同的字
			if (cur != prev || siblings.isEmpty()) {
				Node tmpNode = new Node();
				// 子节点的 depth 为父节点 +1
				tmpNode.depth = parent.depth + 1;
				tmpNode.code = cur;
				// 处于第几个兄弟节点，left 就为几
				tmpNode.left = i;
				
				// 如果有兄弟节点，则设置已有兄弟节点中最后一个兄弟节点的 right
				if (!siblings.isEmpty()) {
				 // 最后一个兄弟节点
			        final Node lastSiblingNode = siblings.get(siblings.size() - 1);
				    lastSiblingNode.right = i;
				}

				siblings.add(tmpNode);
			}
			// 将当前节点的位置保存
			prev = cur;
		}

		// 设置兄弟节点中最后一个节点的 right = 父节点的 right
		if (!siblings.isEmpty()) {
		    // 最后一个兄弟节点
	        final Node lastSiblingNode = siblings.get(siblings.size() - 1);
		    lastSiblingNode.right = parent.right;
		}

		return siblings.size();
	}

	private int insert(List<Node> siblings) {
		if (error_ < 0) {
		    return 0;
		}

		// 记录字典中哪些位置已经有值了
		int begin = 0;
		
		// 第一个兄弟节点
		final int firstSiblingNode = siblings.get(0).code;
		
		// 实在是没有搞懂为什么 +1 又 -1 
        int pos = ((firstSiblingNode + 1)> nextCheckPos ? (firstSiblingNode + 1): nextCheckPos) - 1;
		
		int first = 0;

        outer: while (true) {
		    
            // 又加了一次 1
			pos++;

			// 不等于 0 则表示当前位置已经被占用了
			if (check[pos] != 0) {
				continue;
			} else if (first == 0) {
			    // 记录下一个需要检查的位置
				nextCheckPos = pos;
				first = 1;
			}
			
			begin = pos - siblings.get(0).code;
			
			if (used[begin]) {
			    continue;
			}

			for (int i = 1; i < siblings.size(); i++) {
			    // 这个位置已经有数据插入进去了
			    if (check[begin + siblings.get(i).code] != 0) {
			        continue outer;
			    }
			}

			break;
		}

		used[begin] = true;
		
		for (int i = 0; i < siblings.size(); i++) {
		    // 为所有的兄弟节点找到了一个开始位置 begin
		    check[begin + siblings.get(i).code] = begin;
		}

		for (int i = 0; i < siblings.size(); i++) {
			List<Node> newSiblings = new ArrayList<>();
			
			// 获取子节点，如果没有子节点，则返回 0 
			if (fetch(siblings.get(i), newSiblings) == 0) {
			    
			    // value 永远为空
			    // 如果某个节点没有子节点，则将该节点对应的 base 设置为负
				base[begin + siblings.get(i).code] = 
				        (value != null) 
				            ? (-value[siblings.get(i).left] - 1) 
				            : (-siblings.get(i).left - 1);
				            
	            if (value != null && (-value[siblings.get(i).left] - 1) >= 0) {
					error_ = -2;
					return 0;
				}

			} else {
			    // 获取下一层的兄弟节点的开始索引
				int childBegin = insert(newSiblings);
				base[begin + siblings.get(i).code] = childBegin;
			}
		}
		return begin;
	}

	public DoubleArrayTrie() {
		check = null;
		base = null;
		used = null;
		allocSize = 0;
		// no_delete_ = false;
		error_ = 0;
	}

	// no deconstructor

	// set_result omitted
	// the search methods returns (the list of) the value(s) instead
	// of (the list of) the pair(s) of value(s) and length(s)

	// set_array omitted
	// array omitted

	void clear() {
		// if (! no_delete_)
		check = null;
		base = null;
		used = null;
		allocSize = 0;
	}

	public int getUnitSize() {
		return UNIT_SIZE;
	}

	public int build(List<String> key) {
		return build(key, null, null, key.size());
	}

	public int build(List<String> _key, int _length[], int _value[], int _keySize) {

	    if (_key == null || _keySize > _key.size()) {
		    return 0;
		}

		// progress_func_ = progress_func;
		key = _key;
		length = _length;
		keySize = _keySize;
		value = _value;

		resize(65536 * 32);
		
		base[0] = 1;
		nextCheckPos = 0;

		// 初始化 root 节点
		Node rootNode = new Node();
		rootNode.left = 0;
		// 右边界的长度等于词组的长度
		rootNode.right = keySize;
		rootNode.depth = 0;

		List<Node> siblings = new ArrayList<>();
		
		fetch(rootNode, siblings);
		
		insert(siblings);

        used = null;
        key = null;
        
		return error_;
	}


	public int exactMatchSearch(String key) {
		return exactMatchSearch(key, 0, 0, 0);
	}

	public int exactMatchSearch(String key, int pos, int len, int nodePos) {
		if (len <= 0)
			len = key.length();
		if (nodePos <= 0)
			nodePos = 0;

		int result = -1;

		char[] keyChars = key.toCharArray();

		int b = base[nodePos];
		int p;

		for (int i = pos; i < len; i++) {
			p = b + (int) (keyChars[i]) + 1;
			if (b == check[p])
				b = base[p];
			else
				return result;
		}

		p = b;
		int n = base[p];
		if (b == check[p] && n < 0) {
			result = -n - 1;
		}
		return result;
	}

	public List<Integer> commonPrefixSearch(String key) {
		return commonPrefixSearch(key, 0, 0, 0);
	}

	public List<Integer> commonPrefixSearch(String key, int pos, int len,
			int nodePos) {
		if (len <= 0)
			len = key.length();
		if (nodePos <= 0)
			nodePos = 0;

		List<Integer> result = new ArrayList<Integer>();

		char[] keyChars = key.toCharArray();

		int b = base[nodePos];
		int n;
		int p;

		for (int i = pos; i < len; i++) {
			p = b;
			n = base[p];

			if (b == check[p] && n < 0) {
				result.add(-n - 1);
			}

			p = b + (int) (keyChars[i]) + 1;
			if (b == check[p])
				b = base[p];
			else
				return result;
		}

		p = b;
		n = base[p];

		if (b == check[p] && n < 0) {
			result.add(-n - 1);
		}

		return result;
	}

}