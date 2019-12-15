import org.jetbrains.annotations.NotNull;
import java.util.*;

/**
 * 二分（叉）搜索（排序）树
 *
 * @param <T> 泛型
 * @author Yuan
 */
public class BinarySearchTree<T extends Comparable<T>> {

    /**
     * 根结点
     */
    private Node<T> rootNode;

    /**
     * 树的结点数，可以维护也可以不维护
     */
    private int size;

    /**
     * 构造方法
     * 
     * 一个“空树”
     */
    public BinarySearchTree() {
        rootNode = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向该二分搜索树中插入结点
     * 
     * 非递归实现
     *
     * @param data 要插入的结点中的数据
     */
    public void addNodeNonRecursion(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null!");
        }
        if (rootNode == null) {
            rootNode = new Node<>(data);
            size++;
        } else {
            Node<T> preNode = null;
            Node<T> curNode = rootNode;
            while (curNode != null) {
                preNode = curNode;
                if (curNode.data.compareTo(data) > 0) {
                    curNode = curNode.leftChildNode;
                } else if (curNode.data.compareTo(data) < 0) {
                    curNode = curNode.rightChildNode;
                } else {
                    break;
                }
            }
            if (preNode.data.compareTo(data) > 0) {
                preNode.leftChildNode = new Node<>(data);
                size++;
            } else if (preNode.data.compareTo(data) < 0) {
                preNode.rightChildNode = new Node<>(data);
                size++;
            }
        }
    }

    /**
     * 向该二分搜索树中插入结点
     * 
     * 基于 addNodeRecursionOne 方法递归实现
     *
     * @param data 要插入的结点中的数据
     */
    public void addNodeRecursionOne(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null!");
        }
        if (rootNode == null) {
            rootNode = new Node<>(data);
            size++;
        } else {
            addNodeRecursionOne(rootNode, data);
        }
    }

    /**
     * “向该二分搜索树中插入结点”的第一种递归方法
     *
     * @param rootNode 根结点
     * @param data 要插入的结点中的数据
     */
    private void addNodeRecursionOne(@NotNull Node<T> rootNode, @NotNull T data) {
        if (rootNode.data.compareTo(data) == 0) {
            return;
        } else {
            if (rootNode.data.compareTo(data) > 0 && rootNode.leftChildNode == null) {
                rootNode.leftChildNode = new Node<>(data);
                size++;
                return;
            }
            if (rootNode.data.compareTo(data) < 0 && rootNode.rightChildNode == null) {
                rootNode.rightChildNode = new Node<>(data);
                size++;
                return;
            }
        }
        if (rootNode.data.compareTo(data) > 0) {
            addNodeRecursionOne(rootNode.leftChildNode, data);
        } else if (rootNode.data.compareTo(data) < 0) {
            addNodeRecursionOne(rootNode.rightChildNode, data);
        }
    }

    /**
     * 向该二分搜索树中插入结点
     * 
     * 基于 addNodeRecursionTwo 方法递归实现
     * 难！！！
     *
     * @param data 要插入的结点中的数据
     */
    public void addNodeRecursionTwo(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null!");
        }
        rootNode = addNodeRecursionTwo(rootNode, data);
    }

    /**
     * “向该二分搜索树中插入结点”的第二种递归方法
     * 
     * 难！！！
     *
     * @param rootNode 根结点
     * @param data 要插入的结点中的数据
     * @return Node
     */
    private Node<T> addNodeRecursionTwo(Node<T> rootNode, @NotNull T data) {
        if (rootNode == null) {
            size++;
            return new Node<>(data);
        }
        if (rootNode.data.compareTo(data) > 0) {
            rootNode.leftChildNode = addNodeRecursionTwo(rootNode.leftChildNode, data);
        } else if (rootNode.data.compareTo(data) < 0) {
            rootNode.rightChildNode = addNodeRecursionTwo(rootNode.rightChildNode, data);
        }
        return rootNode;
    }

    /**
     * 中序遍历
     * 
     * 基于 inorderTraversalRecursion 方法递归实现
     *
     * @return List
     */
    public List<T> inorderTraversalRecursion() {
        List<T> list = new ArrayList<>();
        inorderTraversalRecursion(rootNode, list);
        return list;
    }

    /**
     * 递归将中序遍历结果存到 List 中
     *
     * @param rootNode 根结点
     * @param list     List
     */
    private void inorderTraversalRecursion(Node<T> rootNode, @NotNull List<T> list) {
        if (rootNode == null) {
            return;
        }
        inorderTraversalRecursion(rootNode.leftChildNode, list);
        list.add(rootNode.data);
        inorderTraversalRecursion(rootNode.rightChildNode, list);

    }

    /**
     * 先（前）序遍历
     * 
     * 基于 preorderTraversalRecursion 方法递归实现
     *
     * @return List
     */
    public List<T> preorderTraversalRecursion() {
        List<T> list = new ArrayList<>();
        preorderTraversalRecursion(rootNode, list);
        return list;
    }

    /**
     * 递归将先（前）序遍历结果存到 List 中
     *
     * @param rootNode 根结点
     * @param list     List
     */
    private void preorderTraversalRecursion(Node<T> rootNode, @NotNull List<T> list) {
        if (rootNode == null) {
            return;
        }
        list.add(rootNode.data);
        preorderTraversalRecursion(rootNode.leftChildNode, list);
        preorderTraversalRecursion(rootNode.rightChildNode, list);
    }

    /**
     * 后序遍历
     * 
     * 基于 postorderTraversalRecursion 方法递归实现
     *
     * @return List
     */
    public List<T> postorderTraversalRecursion() {
        List<T> list = new ArrayList<>();
        postorderTraversalRecursion(rootNode, list);
        return list;
    }

    /**
     * 递归将后序遍历结果存到 List 中
     *
     * @param rootNode 根结点
     * @param list     List
     */
    private void postorderTraversalRecursion(Node<T> rootNode, @NotNull List<T> list) {
        if (rootNode == null) {
            return;
        }
        postorderTraversalRecursion(rootNode.leftChildNode, list);
        postorderTraversalRecursion(rootNode.rightChildNode, list);
        list.add(rootNode.data);
    }

    /**
     * 先（前）序遍历
     * 
     * 非递归遍历
     *
     * @return List
     */
    public List<T> preorderTraversalNonRecursion() {
        List<T> list = new ArrayList<>();
        if (rootNode == null) {
            return list;
        }
        Stack<Node<T>> stack = new Stack<>();
        stack.push(rootNode);
        while (!stack.empty()) {
            Node<T> curNode = stack.pop();
            list.add(curNode.data);
            if (curNode.rightChildNode != null) {
                stack.push(curNode.rightChildNode);
            }
            if (curNode.leftChildNode != null) {
                stack.push(curNode.leftChildNode);
            }
        }
        return list;
    }

    /**
     * 层次遍历
     *
     * @return List
     */
    public List<T> levelTraversal() {
        List<T> list = new ArrayList<>();
        if (rootNode == null) {
            return list;
        }
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(rootNode);
        while (!queue.isEmpty()) {
            Node<T> curNode = queue.remove();
            list.add(curNode.data);
            if (curNode.leftChildNode != null) {
                queue.add(curNode.leftChildNode);
            }
            if (curNode.rightChildNode != null) {
                queue.add(curNode.rightChildNode);
            }
        }
        return list;
    }

    /**
     * 判断二分搜索树中是否存在要找的数据
     * 
     * 非递归实现
     *
     * @param data 要查找的数据
     * @return 是否存在要查找的数据
     */
    public boolean containsNonRecursion(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null!");
        }
        Node<T> curNode = rootNode;
        while (curNode != null) {
            if (curNode.data.compareTo(data) > 0) {
                curNode = curNode.leftChildNode;
            } else if (curNode.data.compareTo(data) < 0) {
                curNode = curNode.rightChildNode;
            } else {
                break;
            }
        }
        return curNode != null;
    }

    /**
     * 判断二分搜索树中是否存在要找的数据
     * 
     * 基于 containsRecursion 方法递归实现
     *
     * @param data 要查找的数据
     * @return 是否存在要查找的数据
     */
    public boolean containsRecursion(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null!");
        }
        return containsRecursion(rootNode, data);
    }

    /**
     * “判断二分搜索树中是否存在要找的数据”的递归方法
     *
     * @param rootNode 根节点
     * @param data     要查找的数据
     * @return 是否存在要查找的数据
     */
    private boolean containsRecursion(Node<T> rootNode, @NotNull T data) {
        if (rootNode == null) {
            return false;
        }
        if (rootNode.data.compareTo(data) == 0) {
            return true;
        } else if (rootNode.data.compareTo(data) > 0) {
            return containsRecursion(rootNode.leftChildNode, data);
        } else {
            return containsRecursion(rootNode.rightChildNode, data);
        }
    }

    /**
     * 获取最大的数据
     * 
     * 非递归实现
     *
     * @return 最大的数据 或 null（当前为空树的情况下）
     */
    public T getMaxDataNonRecursion() {
        if (rootNode == null) {
            return null;
        }
        Node<T> curNode = rootNode;
        while (curNode.rightChildNode != null) {
            curNode = curNode.rightChildNode;
        }
        return curNode.data;
    }

    /**
     * 获取最大的数据
     * 
     * 基于 getMaxDataNodeRecursion 方法递归实现
     *
     * @return 最大的数据 或 null（当前为空树的情况下）
     */
    public T getMaxDataRecursion() {
        if (rootNode == null) {
            return null;
        }
        return getMaxDataNodeRecursion(rootNode).data;
    }

    private Node<T> getMaxDataNodeRecursion(@NotNull Node<T> rootNode) {
        if (rootNode.rightChildNode == null) {
            return rootNode;
        }
        return getMaxDataNodeRecursion(rootNode.rightChildNode);
    }

    /**
     * 获取最小的数据
     * 
     * 非递归实现
     *
     * @return 最小的数据 或 null（当前为空树的情况下）
     */
    public T getMinDataNonRecursion() {
        if (rootNode == null) {
            return null;
        }
        Node<T> curNode = rootNode;
        while (curNode.leftChildNode != null) {
            curNode = curNode.leftChildNode;
        }
        return curNode.data;
    }

    /**
     * 获取最小的数据
     * 
     * 基于 getMinDataNodeRecursion 方法递归实现
     *
     * @return 最小的数据 或 null（当前为空树的情况下）
     */
    public T getMinDataRecursion() {
        if (rootNode == null) {
            return null;
        }
        return getMinDataNodeRecursion(rootNode).data;
    }

    private Node<T> getMinDataNodeRecursion(@NotNull Node<T> rootNode) {
        if (rootNode.leftChildNode == null) {
            return rootNode;
        }
        return getMinDataNodeRecursion(rootNode.leftChildNode);
    }

    /**
     * 删除最大的数据的结点并返回这个最大数据
     * 
     * 非递归实现
     *
     * @return 最大的数据 或 null（当前为空树的情况下）
     */
    public T removeMaxDataNodeNonRecursion() {
        if (rootNode == null) {
            return null;
        }
        Node<T> curNode = rootNode;
        Node<T> preNode = rootNode;
        while (curNode.rightChildNode != null) {
            preNode = curNode;
            curNode = curNode.rightChildNode;
        }
        if (curNode == preNode) {
            rootNode = null;
        } else {
            preNode.rightChildNode = curNode.leftChildNode;
        }
        size--;
        return curNode.data;
    }


    /**
     * 删除最小的数据的结点并返回
     * 
     * 非递归实现
     *
     * @return 最小的数据 或 null（当前为空树的情况下）
     */
    public T removeMinDataNodeNonRecursion() {
        if (rootNode == null) {
            return null;
        }
        Node<T> curNode = rootNode;
        Node<T> preNode = rootNode;
        while (curNode.leftChildNode != null) {
            preNode = curNode;
            curNode = curNode.leftChildNode;
        }
        if (curNode == preNode) {
            rootNode = null;
        } else {
            preNode.leftChildNode = curNode.rightChildNode;
        }
        size--;
        return curNode.data;
    }

    /**
     * 删除特定的结点
     *
     * 非递归实现
     * 策略：选择被删结点的“右子树”中的“最小的数据”对应的结点来替换即将被删结点
     * 另外的策略：也可以选择被删结点的“左子树”中的“最大的数据”对应的结点来替换即将被删结点
     *
     * @param data 要删除的结点中的数据
     */
    public void removeSpecificNodeNonRecursion(T data) {
        // 1、判断是否存在
        //     1.1、data == null 会抛异常
        //     1.2、rootNode == null 会抛异常（因为在 containsNonRecursion 方法的逻辑中，rootNode 为 null 返回值一定是 false）
        //     1.3、“不存在”会抛异常
        if (!containsNonRecursion(data)) {
            throw new IllegalArgumentException("Data does not exist!");
        }

        // 2、下面的逻辑是结点存在
        // 定位要删除结点的位置
        Node<T> preNode = rootNode;
        Node<T> curNode = rootNode;
        // 循环过后，curNode 即指向要被删除的结点
        while (curNode.data.compareTo(data) != 0) {
            preNode = curNode;
            if (curNode.data.compareTo(data) > 0) {
                curNode = curNode.leftChildNode;
            } else {
                curNode = curNode.rightChildNode;
            }
        }

        // 要删除的是“根结点”
        if (preNode == curNode) {
            if (curNode.leftChildNode == null && curNode.rightChildNode == null) {
                rootNode = null;
            }
            if (curNode.leftChildNode == null && curNode.rightChildNode != null) {
                rootNode = curNode.rightChildNode;
            }
            if (curNode.leftChildNode != null && curNode.rightChildNode == null) {
                rootNode = curNode.leftChildNode;
            }
            if (curNode.leftChildNode != null && curNode.rightChildNode != null) {
                Node<T> tempCurNode = curNode.rightChildNode;
                Node<T> tempPreNode = curNode.rightChildNode;
                while (tempCurNode.leftChildNode != null) {
                    tempPreNode = tempCurNode;
                    tempCurNode = tempCurNode.leftChildNode;
                }
                if (tempPreNode == tempCurNode) {
                    curNode.data = tempCurNode.data;
                    curNode.rightChildNode = null;
                } else {
                    curNode.data = tempCurNode.data;
                    tempPreNode.leftChildNode = null;
                }
            }
        } else {
            // 要删除的是其他结点
            // 要删除的结点为“叶子”结点
            if (curNode.leftChildNode == null && curNode.rightChildNode == null) {
                if (preNode.data.compareTo(data) > 0) {
                    preNode.leftChildNode = null;
                } else {
                    preNode.rightChildNode = null;
                }
            }
            // 要删除的结点只有“左子树”
            if (curNode.leftChildNode != null && curNode.rightChildNode == null) {
                if (preNode.data.compareTo(data) > 0) {
                    preNode.leftChildNode = curNode.leftChildNode;
                } else {
                    preNode.rightChildNode = curNode.leftChildNode;
                }
            }
            // 要删除的结点只有“右子树”
            if (curNode.leftChildNode == null && curNode.rightChildNode != null) {
                if (preNode.data.compareTo(data) > 0) {
                    preNode.leftChildNode = curNode.rightChildNode;
                } else {
                    preNode.rightChildNode = curNode.rightChildNode;
                }
            }
            // 要删除的结点既有“左子树”又有“右子树”
            if (curNode.leftChildNode != null && curNode.rightChildNode != null) {
                // 1、定位要被删除结点的“右子树”中的“最小的数据”对应的结点
                Node<T> tempPreNode = curNode.rightChildNode;
                Node<T> tempCurNode = curNode.rightChildNode;
                while (tempCurNode.leftChildNode != null) {
                    tempPreNode = tempCurNode;
                    tempCurNode = tempCurNode.leftChildNode;
                }
                if (tempPreNode == tempCurNode) {
                    curNode.data = tempCurNode.data;
                    curNode.rightChildNode = null;
                } else {
                    curNode.data = tempCurNode.data;
                    tempPreNode.leftChildNode = null;
                }
            }
        }
        size--;
    }

    /**
     * Node
     *
     * @param <T> 泛型
     */
    private static class Node<T> {

        private T data;
        private Node<T> leftChildNode;
        private Node<T> rightChildNode;

        Node(T data) {
            this.data = data;
            leftChildNode = null;
            rightChildNode = null;
        }

    }

}