import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于“二分搜索树”的 Map
 *
 * @param <K> 键
 * @param <V> 值
 */
public class BinarySearchTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Node<K, V> rootNode;
    private int size;

    public BinarySearchTreeMap() {
        rootNode = null;
        size = 0;
    }

    @Override
    public void add(@NotNull K key, V value) {
        if (rootNode == null) {
            rootNode = new Node<>(key, value);
            size++;
        } else {
            Node<K, V> preNode = null;
            Node<K, V> curNode = rootNode;
            while (curNode != null) {
                preNode = curNode;
                if (curNode.key.compareTo(key) > 0) {
                    curNode = curNode.leftChildNode;
                } else if (curNode.key.compareTo(key) < 0) {
                    curNode = curNode.rightChildNode;
                } else {
                    break;
                }
            }
            if (preNode.key.compareTo(key) > 0) {
                preNode.leftChildNode = new Node<>(key, value);
                size++;
            } else if (preNode.key.compareTo(key) < 0) {
                preNode.rightChildNode = new Node<>(key, value);
                size++;
            } else {
                preNode.value = value;
            }
        }
    }

    @Override
    public V remove(@NotNull K key) {
        Node<K, V> node = getNodeByKey(key);
        if (node == null) {
            return null;
        }
        Node<K, V> preNode = rootNode;
        Node<K, V> curNode = rootNode;
        while (curNode != null && curNode.key.compareTo(key) != 0) {
            preNode = curNode;
            if (curNode.key.compareTo(key) > 0) {
                curNode = curNode.leftChildNode;
            } else {
                curNode = curNode.rightChildNode;
            }
        }
        // 不存在相应的 key
        if (curNode == null) {
            return null;
        }

        // 存在相应的 key 的情况下，curNode 为要删除的结点

        V result = curNode.value;

        // 要删除的结点为“叶子结点”
        if (curNode.leftChildNode == null && curNode.rightChildNode == null) {
            if (curNode == preNode) {
                // 要删除的结点是“根结点”（只有一个结点的树）
                rootNode = null;
            } else {
                // 要删除的结点是“其他结点”
                if (preNode.key.compareTo(key) > 0) {
                    preNode.leftChildNode = null;
                } else {
                    preNode.rightChildNode = null;
                }
            }
        }

        // 要删除的结点只有“左子树”
        if (curNode.leftChildNode != null && curNode.rightChildNode == null) {
            if (curNode == preNode) {
                // 要删除的结点是“根结点”（只有“左子树”的树）
                rootNode = curNode.leftChildNode;
                // 等价于 rootNode = rootNode.leftChildNode;
            } else {
                if (preNode.key.compareTo(key) > 0) {
                    preNode.leftChildNode = curNode.leftChildNode;
                } else {
                    preNode.rightChildNode = curNode.leftChildNode;
                }
            }
        }

        // 要删除的结点只有“右子树”
        if (curNode.leftChildNode == null && curNode.rightChildNode != null) {
            if (curNode == preNode) {
				// 要删除的结点是“根结点”（只有“右子树”的树）
                rootNode = curNode.rightChildNode;
            } else {
                if (preNode.key.compareTo(key) > 0) {
                    preNode.leftChildNode = curNode.rightChildNode;
                } else {
                    preNode.rightChildNode = curNode.rightChildNode;
                }
            }
        }

        // 要删除的结点既有“左子树”，又有“右子树”
		// 这个逻辑要格外注意！！！
        if (curNode.leftChildNode != null && curNode.rightChildNode != null) {
            Node<K, V> tempPreNode = curNode.rightChildNode;
            Node<K, V> tempCurNode = curNode.rightChildNode;
            while (tempCurNode.leftChildNode != null) {
                tempPreNode = tempCurNode;
                tempCurNode = tempCurNode.leftChildNode;
            }
            if (tempCurNode == tempPreNode) {
                curNode.key = tempCurNode.key;
                curNode.rightChildNode = tempCurNode.rightChildNode;
            } else {
                curNode.key = tempCurNode.key;
                tempPreNode.leftChildNode = tempCurNode.rightChildNode;
            }
        }
        size--;
        return result;
    }

    @Override
    public boolean contains(@NotNull K key) {
        return getNodeByKey(key) != null;
    }

    @Override
    public V get(@NotNull K key) {
        Node<K, V> node = getNodeByKey(key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(@NotNull K key, V newValue) {
        Node<K, V> node = getNodeByKey(key);
        if (node == null) {
            throw new IllegalArgumentException("Key doesn't exist!");
        }
        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public List<K> getInorderTraversalList() {
        List<K> list = new ArrayList<>();
        getInorderTraversalList(rootNode, list);
        return list;
    }

    private void getInorderTraversalList(Node<K, V> rootNode, List<K> list) {
        if (rootNode == null) {
            return;
        }
        getInorderTraversalList(rootNode.leftChildNode, list);
        list.add(rootNode.key);
        getInorderTraversalList(rootNode.rightChildNode, list);
    }

    private Node<K, V> getNodeByKey(@NotNull K key) {
        if (rootNode == null) {
            return null;
        }
        Node<K, V> curNode = rootNode;
        while (curNode != null) {
            if (curNode.key.compareTo(key) == 0) {
                return curNode;
            } else if (curNode.key.compareTo(key) > 0) {
                curNode = curNode.leftChildNode;
            } else {
                curNode = curNode.rightChildNode;
            }
        }
        return null;
    }

    private static class Node<K, V> {

        private K key;
        private V value;
        private Node<K, V> leftChildNode;
        private Node<K, V> rightChildNode;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.leftChildNode = null;
            this.rightChildNode = null;
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }

    }

}