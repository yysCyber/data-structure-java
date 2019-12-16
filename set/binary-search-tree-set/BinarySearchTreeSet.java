import org.jetbrains.annotations.NotNull;

/**
 * 基于“二分搜索树”的 Set
 *
 * @param <T> 泛型
 */
public class BinarySearchTreeSet<T extends Comparable<T>> implements Set<T> {

    private BinarySearchTree<T> tree;

    public BinarySearchTreeSet() {
        tree = new BinarySearchTree<>();
    }

    @Override
    public void add(@NotNull  T elem) {
        tree.addNodeNonRecursion(elem);
    }

    @Override
    public void remove(@NotNull  T elem) {
        tree.removeSpecificNodeNonRecursion(elem);
    }

    @Override
    public boolean contains(T elem) {
        return tree.containsNonRecursion(elem);
    }

    @Override
    public int getSize() {
        return tree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

}