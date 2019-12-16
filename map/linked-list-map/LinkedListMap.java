import org.jetbrains.annotations.NotNull;

/**
 * 基于“链表”的 Map
 *
 * @param <K> 键
 * @param <V> 值
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    private Node<K, V> dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node<>();
        size = 0;
    }

    /**
     * 添加
     *
     * key 已存在的时候，可以考虑抛出异常或采取更新 key 所对应的 value
     * 这里采用“更新 key 所对应的 value”
     *
     * @param key 键
     * @param value 值
     */
    @Override
    public void add(@NotNull K key, V value) {
        Node<K, V> node = getNodeByKey(key);
        if (node != null) {
            node.value = value;
            // 或直接 throw new IllegalArgumentException("Key has existed!");
        } else {
            dummyHead.next = new Node<>(key, value, dummyHead.next);
            // 等价于下面的写法：
            //     Node<K, V> newNode = new Node<>(key, value);
            //     newNode.next = dummyHead.next;
            //     dummyHead.next = newNode;
            size++;
        }

    }

    /**
     * 删除
     *
     * @param key 键
     * @return 被删除的“键值对”中“值” 或 null
     */
    @Override
    public V remove(@NotNull K key) {
        Node<K, V> preNode = dummyHead;
        while (preNode.next != null) {
            if (preNode.next.key.equals(key)) {
                break;
            }
            preNode = preNode.next;
        }
        if (preNode.next != null) {
            Node<K, V> deleteNode = preNode.next;
            V result = deleteNode.value;
            preNode.next = deleteNode.next;
            deleteNode.next = null;
            size--;
            return result;
        }
        return null;
    }

    /**
     * 判断是否存在
     *
     * @param key 键
     * @return 是否存在
     */
    @Override
    public boolean contains(@NotNull K key) {
        return getNodeByKey(key) != null;
    }

    /**
     * 获取
     *
     * @param key 键
     * @return 对应的值（存在时返回） 或 null（不存在时返回）
     */
    @Override
    public V get(@NotNull K key) {
        Node<K, V> node = getNodeByKey(key);
        return node == null ? null : node.value;
    }

    /**
     * 更新/修改
     *
     * @param key 键
     * @param newValue 新的值
     */
    @Override
    public void set(@NotNull K key, V newValue) {
        Node<K, V> node = getNodeByKey(key);
        if (node == null) {
            throw new IllegalArgumentException("Key doesn't exist!");
        }
        node.value = newValue;
    }

    /**
     * 获取 Map 大小
     *
     * @return 大小
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 判断是否为空的 Map
     *
     * @return 是否为一个空的 Map
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取“键”对应的 Node
     *
     * @param key 键
     * @return 对应的 Node
     */
    private Node<K, V> getNodeByKey(@NotNull K key) {
        Node<K, V> curNode = dummyHead.next;
        while (curNode != null) {
            if (curNode.key.equals(key)) {
                return curNode;
            }
            curNode = curNode.next;
        }
        return null;
    }

    /**
     * Node
     *
     * @param <K> 键
     * @param <V> 值
     */
    private static class Node<K, V> {

        private K key;
        private V value;
        private Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        Node(K key, V value) {
            this(key, value, null);
        }

        Node() {
            this(null, null, null);
        }

    }

}