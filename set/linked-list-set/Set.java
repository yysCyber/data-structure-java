public interface Set<T> {

    void add(T elem);

    void remove(T elem);

    boolean contains(T elem);

    int getSize();

    boolean isEmpty();

}