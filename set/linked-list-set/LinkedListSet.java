import org.jetbrains.annotations.NotNull;

public class LinkedListSet<T> implements Set<T> {

    private LinkedList<T> set;

    public LinkedListSet() {
        set = new LinkedList<>();
    }

    @Override
    public void add(@NotNull T elem) {
        if (!set.contains(elem)) {
            set.addNodeAtRear(elem);
        }
    }

    @Override
    public void remove(@NotNull T elem) {
        set.removeSpecificNode(elem);
    }

    @Override
    public boolean contains(@NotNull T elem) {
        return set.contains(elem);
    }

    @Override
    public int getSize() {
        return set.getSize();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

}