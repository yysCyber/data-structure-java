import org.jetbrains.annotations.NotNull;

public class BinarySearchTreeSet<T extends Comparable<T>> implements Set<T> {

    private BinarySearchTree<T> set;

    public BinarySearchTreeSet() {
        set = new BinarySearchTree<>();
    }

    @Override
    public void add(@NotNull T elem) {
        set.addNode(elem);
    }

    @Override
    public void remove(@NotNull T elem) {
        set.removeNode(elem);
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