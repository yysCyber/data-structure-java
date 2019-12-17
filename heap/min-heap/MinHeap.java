import org.jetbrains.annotations.NotNull;

/**
 * 基于“动态数组”实现 min heap
 *
 * 完全二叉、小根堆：某个结点中的值 不小于 其父结点
 * 索引0使用
 *
 * @param <T> 泛型
 */
public class MinHeap<T extends Comparable<T>> {

    private Array<T> data;

    public MinHeap() {
        data = new Array<>();
    }

    public MinHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MinHeap(T[] array) {
        data = new Array<>(array);
        for (int i = getParentIndex(data.getSize() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public int getSize() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void addElement(@NotNull T elem) {
        data.addElementAtLast(elem);
        siftUp(data.getSize() - 1);
    }

    public T getMinElement() {
        if (data.isEmpty()) {
            throw new IllegalStateException("Heap is empty!");
        }
        return data.getElement(0);
    }

    public T removeMinElement() {
        T result = getMinElement();
        data.swapElement(0, data.getSize() - 1);
        data.removeElementAtLast();
        siftDown(0);
        return result;
    }

    public T replaceMinElement(T newElem) {
        T result = getMinElement();
        data.setElement(newElem, 0);
        siftDown(0);
        return result;
    }

    private int getParentIndex(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("Index is error!");
        }
        return (index - 1) / 2;
    }

    private int getLeftChildIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index is error!");
        }
        return index * 2 + 1;
    }

    private int getRightChildIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index is error!");
        }
        return index * 2 + 2;
    }

    private void siftUp(int index) {
        while (index > 0 && data.getElement(index).compareTo(data.getElement(getParentIndex(index))) < 0) {
            // 索引 index 处的元素比其父结点对应索引处的元素还要小，需要调整，即交换
            data.swapElement(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    private void siftDown(int index) {
        while (getLeftChildIndex(index) < data.getSize()) {
            int tempIndex = getLeftChildIndex(index);
            if (tempIndex + 1 < data.getSize() && data.getElement(tempIndex).compareTo(data.getElement(tempIndex + 1)) > 0) {
                tempIndex = getRightChildIndex(index);
                // 等价于 tempIndex = tempIndex + 1;
            }
            // data.get(tempIndex) 是 leftChild 与 rightChild 中较小的
            if (data.getElement(index).compareTo(data.getElement(tempIndex)) <= 0) {
                // 索引 index 处的元素比左、右孩子结点中较小元素都小或相等，无需调整
                break;
            } else {
                data.swapElement(index, tempIndex);
                index = tempIndex;
            }
        }
    }

}