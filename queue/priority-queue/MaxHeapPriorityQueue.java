/**
 * 基于“大根堆”实现 priority queue
 *
 * @param <T> 泛型
 */
public class MaxHeapPriorityQueue<T extends Comparable<T>> implements Queue<T> {

    private MaxHeap<T> maxHeap;

    @Override
    public int getSize() {
        return maxHeap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enQueue(T elem) {
        maxHeap.addElement(elem);
    }

    @Override
    public T deQueue() {
        return maxHeap.removeMaxElement();
    }

    @Override
    public T getFront() {
        return maxHeap.getMaxElement();
    }

}