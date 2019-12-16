/**
 * 基于“链表”的 Set
 *
 * @param <T> 泛型
 */
public class LinkedListSet<T> implements Set<T> {

    private LinkedList<T> linkedList;

    public LinkedListSet() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void add(T elem) {
        if (!contains(elem)) {
            linkedList.addNodeAtFront(elem);
        }
    }

    @Override
    public void remove(T elem) {
        linkedList.removeNode(elem);
    }

    @Override
    public boolean contains(T elem) {
        return linkedList.contains(elem);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

}