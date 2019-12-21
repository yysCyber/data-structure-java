import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> rootNode;
    private int size;

    public BinarySearchTree() {
        rootNode = null;
        size = 0;
    }

    public void addNode(@NotNull T data) {
        if (rootNode == null) {
            rootNode = new Node<>(data);
            size++;
        } else {
            Node<T> preNode = rootNode;
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
            if (curNode == null) {
                Node<T> newNode = new Node<>(data);
                if (preNode.data.compareTo(data) > 0) {
                    preNode.leftChildNode = newNode;
                } else {
                    preNode.rightChildNode = newNode;
                }
                size++;
            }
        }
    }

    public void removeNode(@NotNull T data) {
        if (rootNode == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }
        Node<T> curNode = rootNode;
        Node<T> preNode = rootNode;
        while (curNode != null) {
            if (curNode.data.compareTo(data) == 0) {
                break;
            } else if (curNode.data.compareTo(data) > 0) {
                preNode = curNode;
                curNode = curNode.leftChildNode;
            } else {
                preNode = curNode;
                curNode = curNode.rightChildNode;
            }
        }
        if (curNode == null) {
            throw new IllegalArgumentException("No target data!");
        }

        // 1、待删除的结点左、右子树均为空
        if (curNode.leftChildNode == null && curNode.rightChildNode == null) {
            if (preNode == curNode) {
                // 1.1、要删除的结点为“根结点”
                rootNode = null;
            } else {
                // 1.2、要删除的结点为“非根结点”
                if (preNode.data.compareTo(data) > 0) {
                    preNode.leftChildNode = null;
                } else {
                    preNode.rightChildNode = null;
                }
            }
        }

        // 2、待删除的结点左子树不为空，右子树为空
        if (curNode.leftChildNode != null && curNode.rightChildNode == null) {
            if (preNode == curNode) {
                rootNode = rootNode.leftChildNode;
            } else {
                if (preNode.data.compareTo(data) > 0) {
                    preNode.leftChildNode = curNode.leftChildNode;
                    curNode.leftChildNode = null;
                } else {
                    preNode.rightChildNode = curNode.leftChildNode;
                    curNode.leftChildNode = null;
                }
            }
        }

        // 3、待删除的结点右子树不为空，左子树为空
        if (curNode.leftChildNode == null && curNode.rightChildNode != null) {
            if (preNode == curNode) {
                rootNode = rootNode.rightChildNode;
            } else {
                if (preNode.data.compareTo(data) > 0) {
                    preNode.leftChildNode = curNode.rightChildNode;
                    curNode.rightChildNode = null;
                } else {
                    preNode.rightChildNode = curNode.rightChildNode;
                    curNode.rightChildNode = null;
                }
            }
        }

        // 4、待删除的结点左、右子树均不为空
        if (curNode.leftChildNode != null && curNode.rightChildNode != null) {
            Node<T> tempCurNode = curNode.rightChildNode;
            Node<T> tempPreNode = curNode.rightChildNode;
            while (tempCurNode.leftChildNode != null) {
                tempPreNode = tempCurNode;
                tempCurNode = tempCurNode.leftChildNode;
            }
            curNode.data = tempCurNode.data;
            if (tempPreNode == tempCurNode) {
                curNode.rightChildNode = tempCurNode.rightChildNode;
            } else {
                tempPreNode.leftChildNode = tempCurNode.rightChildNode;
            }
        }

        size--;
    }

    public T removeMaxDataNode() {
        if (rootNode == null) {
            return null;
        }
        Node<T> curNode = rootNode;
        Node<T> preNode = rootNode;
        while (curNode.rightChildNode != null) {
            preNode = curNode;
            curNode = curNode.rightChildNode;
        }
        T maxData;
        if (curNode == preNode) {
            maxData = rootNode.data;
            rootNode = rootNode.leftChildNode;
        } else {
            maxData = curNode.data;
            preNode.rightChildNode = curNode.leftChildNode;
            curNode.leftChildNode = null;
        }
        size--;
        return maxData;
    }

    public T removeMinDataNode()  {
        if (rootNode == null) {
            return null;
        }
        Node<T> curNode = rootNode;
        Node<T> preNode = rootNode;
        while (curNode.leftChildNode != null) {
            preNode = curNode;
            curNode = curNode.leftChildNode;
        }
        T minData;
        if (preNode == curNode) {
            minData = rootNode.data;
            rootNode = rootNode.rightChildNode;
        } else {
            minData = curNode.data;
            preNode.leftChildNode = curNode.rightChildNode;
            curNode.rightChildNode = null;
        }
        size--;
        return minData;
    }

    public List<T> getPreorderTraversalResultList() {
        List<T> list = new ArrayList<>();
        getPreorderTraversalResultList(rootNode, list);
        return list;
    }

    private void getPreorderTraversalResultList(Node<T> rootNode, List<T> resultList) {
        if (rootNode == null) {
            return;
        }
        resultList.add(rootNode.data);
        getPreorderTraversalResultList(rootNode.leftChildNode, resultList);
        getPreorderTraversalResultList(rootNode.rightChildNode, resultList);
    }

    public List<T> getInorderTraversalResultList() {
        List<T> list = new ArrayList<>();
        getInorderTraversalResultList(rootNode, list);
        return list;
    }

    private void getInorderTraversalResultList(Node<T> rootNode, List<T> resultList) {
        if (rootNode == null) {
            return;
        }
        getInorderTraversalResultList(rootNode.leftChildNode, resultList);
        resultList.add(rootNode.data);
        getInorderTraversalResultList(rootNode.rightChildNode, resultList);
    }

    public List<T> getPostorderTraversalResultList() {
        List<T> list = new ArrayList<>();
        getPostorderTraversalResultList(rootNode, list);
        return list;
    }

    private void getPostorderTraversalResultList(Node<T> rootNode, List<T> resultList) {
        if (rootNode == null) {
            return;
        }
        getPostorderTraversalResultList(rootNode.leftChildNode, resultList);
        getPostorderTraversalResultList(rootNode.rightChildNode, resultList);
        resultList.add(rootNode.data);
    }

    public List<T> getLevelTraversalResultList() {
        List<T> list = new ArrayList<>();
        Queue<Node<T>> queue = new LinkedList<>();
        if (rootNode != null) {
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
        }
        return list;
    }

    public T getMaxData() {
        if (rootNode == null) {
            return null;
        }
        Node<T> curNode = rootNode;
        while (curNode.rightChildNode != null) {
            curNode = curNode.rightChildNode;
        }
        return curNode.data;
    }

    public T getMinData() {
        if (rootNode == null) {
            return null;
        }
        Node<T> curNode = rootNode;
        while (curNode.leftChildNode != null) {
            curNode = curNode.leftChildNode;
        }
        return curNode.data;
    }

    public boolean contains(@NotNull T data) {
        if (rootNode == null) {
            return false;
        }
        Node<T> curNode = rootNode;
        while (curNode != null) {
            if (curNode.data.compareTo(data) == 0) {
                break;
            } else if (curNode.data.compareTo(data) > 0) {
                curNode = curNode.leftChildNode;
            } else {
                curNode = curNode.rightChildNode;
            }
        }
        return curNode != null;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private static class Node<T> {

        private T data;
        private Node<T> leftChildNode;
        private Node<T> rightChildNode;

        Node(T data) {
            this.data = data;
            this.leftChildNode = null;
            this.rightChildNode = null;
        }

    }

}