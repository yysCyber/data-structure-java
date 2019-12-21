import org.jetbrains.annotations.NotNull;

public class LinkedList<T> {

    private Node<T> dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node<>();
        size = 0;
    }

    public void addNode(@NotNull T data, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index is error!");
        }
        Node<T> preNode = dummyHead;
        for (int i = 0; i < index; i++) {
            preNode = preNode.next;
        }
        Node<T> newNode = new Node<>(data, null);
        newNode.next = preNode.next;
        preNode.next = newNode;
        size++;
    }

    public void addNodeAtFront(@NotNull T data) {
        addNode(data, 0);
    }

    public void addNodeAtRear(@NotNull T data) {
        addNode(data, size);
    }

    public T removeNode(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index is error!");
        }
        Node<T> preNode = dummyHead;
        for (int i = 0; i < index; i++) {
            preNode = preNode.next;
        }
        Node<T> deleteNode = preNode.next;
        T result = deleteNode.data;
        preNode.next = deleteNode.next;
        size--;
        deleteNode.next = null;
        return result;
    }

    public T removeNodeAtFront() {
        return removeNode(0);
    }

    public T removeNodeAtRear() {
        return removeNode(size - 1);
    }

    public void removeSpecificNode(@NotNull T data) {
        Node<T> preNode = dummyHead;
        while (preNode.next != null) {
            if (preNode.next.data.equals(data)) {
                Node<T> deleteNode = preNode.next;
                preNode.next = deleteNode.next;
                size--;
                deleteNode.next = null;
            } else {
                preNode = preNode.next;
            }
        }
    }

    public T getDataAtFrontNode() {
        if (dummyHead.next == null) {
            return null;
        }
        return dummyHead.next.data;
    }

    public T getDataAtRearNode() {
        if (dummyHead.next == null) {
            return null;
        }
        Node<T> curNode = dummyHead.next;
        while (curNode.next != null) {
            curNode = curNode.next;
        }
        return curNode.data;
    }

    public boolean contains(@NotNull T data) {
        Node<T> curNode = dummyHead.next;
        while (curNode != null) {
            if (curNode.data.equals(data)) {
                return true;
            }
            curNode = curNode.next;
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        Node<T> curNode = dummyHead.next;
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList: [");
        while (curNode != null) {
            sb.append(curNode.data);
            if (curNode.next != null) {
                sb.append(", ");
            }
            curNode = curNode.next;
        }
        sb.append("]");
        return sb.toString();
    }


    private static class Node<T> {

        T data;
        Node<T> next;

        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        Node(T data) {
            this(data, null);
        }

        Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            if (data != null) {
                return data.toString();
            }
            return super.toString();
        }

    }

}