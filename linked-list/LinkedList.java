public class LinkedList<T> {

    private Node<T> dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node<>();
        size = 0;
    }

    public void addNode(T elem, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Error Index!");
        }
        Node<T> pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = new Node<T>(elem, pre.next);
        size++;
    }

    public void addNodeAtFront(T elem) {
        addNode(elem, 0);
    }

    public void addNodeAtRear(T elem) {
        addNode(elem, size);
    }

    public T removeNode(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Error Index!");
        }
        Node<T> pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node<T> deleteNode = pre.next;
        T result = deleteNode.elem;
        pre.next = deleteNode.next;
        deleteNode.next = null;
        size--;
        return result;
    }

    public T removeNodeAtFront() {
        return removeNode(0);
    }

    public T removeNodeAtRear() {
        return removeNode(size - 1);
    }

    public void removeNode(T elem) {
        Node<T> pre = dummyHead;
        while (pre.next != null) {
            if (pre.next.elem.equals(elem)) {
                break;
            }
            pre = pre.next;
        }
        if (pre.next != null) {
            Node<T> cur = pre.next;
            pre.next = cur.next;
            cur.next = null;
            cur = null;
        }

    }

    public T getElement(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Error Index!");
        }
        Node<T> cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.elem;
    }

    public T getElementAtFront() {
        return getElement(0);
    }

    public T getElementAtRear() {
        return getElement(size - 1);
    }

    public boolean contains(T elem) {
        Node<T> node = dummyHead.next;
        while (node != null) {
            if (node.elem.equals(elem)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList: [");
        Node<T> cur = dummyHead.next;
        while (cur != null) {
            sb.append(cur.elem);
            if (cur.next != null) {
                sb.append(", ");
            }
            cur = cur.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Node
     *
     * @param <T> 泛型
     */
    private static class Node<T> {

        T elem;
        Node<T> next;

        public Node(T elem, Node<T> next) {
            this.elem = elem;
            this.next = next;
        }

        public Node(T elem) {
            this(elem, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return elem.toString();
        }

    }

}