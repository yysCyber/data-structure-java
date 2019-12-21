import java.util.TreeMap;

/**
 * 字典树（或“前缀树”）
 *
 * 可以使用泛型，以达到可以面向不同的自然语言的情况，这里就暂时不考虑那么复杂了
 */
public class Trie {

    private Node rootNode;
    private int size;

    public Trie() {
        rootNode = new Node();
        size = 0;
    }

    /**
     * 向“字典树”中添加一个“单词”结点
     *
     * 非递归实现
     *
     * @param word 单词
     */
    public void addNode(String word) {
        Node curNode = rootNode;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);

            if (!curNode.next.containsKey(c) || curNode.next.get(c) == null) {
                Node node = new Node(c);
                curNode.next.put(c, node);
            }
            curNode = curNode.next.get(c);
        }

        if (!curNode.isWord) {
            curNode.isWord = true;
            size++;
        }
    }

    /**
     * 判断在“字典树”中是否存在这一单词
     *
     * @param word 单词
     * @return 在“字典树”中是否存在这一单词
     */
    public boolean contains(String word) {
        Node curNode = rootNode;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (!curNode.next.containsKey(c) || curNode.next.get(c) == null) {
                return false;
            }
            curNode = curNode.next.get(c);
        }
        return curNode.isWord;
    }

    /**
     * 判断在“字典树”中是否存在这一前缀
     *
     * @param prefix 前缀
     * @return 在“字典树”中是否存在这一前缀
     */
    public boolean containsPrefix(String prefix) {
        Node curNode = rootNode;
        for (int i = 0; i < prefix.length(); i++) {
            Character c = prefix.charAt(i);
            if (!curNode.next.containsKey(c) || curNode.next.get(c) == null) {
                return false;
            }
            curNode = curNode.next.get(c);
        }
        return true;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private static class Node {

        /**
         * 实际上，这个 Character 可以不需要，因为在 TreeMap 中已经“暴露”了这个信息
         */
        private Character character;

        /**
         * “是否是单词”的标识
         *
         * 因为很多单词会出现一样的“前缀”，所以设置这个标识
         */
        private boolean isWord;

        /**
         * 这个数据结构在结点中在开始的理解上会有一定的困难
         * 假设这个“字典树”是面向“英语（只考虑小写）”的，那么每一个结点按道理就应该有26个 next 也就是说整个“字典树”将会是一个26叉树
         * 但是为了使这个“字典树”的设计相对灵活一些，所以就使用 TreeMap 将这些“叉”进行统一的组织和管理
         */
        private TreeMap<Character, Node> next;

        Node(Character character, boolean isWord) {
            this.character = character;
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        Node(Character character) {
            this(character, false);
        }

        Node() {
            this(null, false);
        }

    }

}