public interface Queue<T> {

    int getSize();

    boolean isEmpty();

    void enQueue(T elem);

    T deQueue();

    T getFront();

}