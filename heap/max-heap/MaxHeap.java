/**
 * 基于“动态数组”实现 max heap
 *
 * 完全二叉、大根堆：某个结点中的值 不大于 其父结点
 * 索引0正常使用即索引0表示根，
 * 当然，也可以索引0不使用，自索引1开始使用，相关的 getParentIndex、getLeftChildIndex、getRightChildIndex 也要调整
 *
 * @param <T> 泛型
 */
public class MaxHeap<T extends Comparable<T>> {

    private Array<T> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    /**
     * 将任意数组调整成堆
     *
     * 自最后一个“非叶子结点”所对应的索引向前遍历，逐一“下沉”调整，最终得到堆
     * 当然，对于将“任意数组调整为堆”也可以逐一使用 addElement 方法
     *
     * @param array 任意数组
     */
    public MaxHeap(T[] array) {
        data = new Array<>(array);
        for (int i = getParentIndex(data.getSize() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int getSize() {
        return data.getSize();
    }

    /**
     * 向堆中添加元素
     *
     * @param elem 元素
     */
    public void addElement(T elem) {
        // 先将元素插入到末尾
        data.addElementAtLast(elem);
        // 使用 siftUp 方法根据堆的性质调整新插入元素的位置
        siftUp(data.getSize() - 1);
    }

    /**
     * 获取堆中最大的元素
     *
     * @return 堆中最大的元素 或 抛出异常
     */
    public T getMaxElement() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty!");
        }
        return data.getElement(0);
    }

    /**
     * 删除堆中最大的元素
     *
     * @return 堆中最大的元素
     */
    public T removeMaxElement() {
        T result = getMaxElement();
        // 1、因为根结点中的元素即索引0处的元素是最大的元素，所以需要删除索引0处的元素，为了保证堆的结构，先采取下面的方法
        data.swapElement(0, data.getSize() - 1);
        data.removeElementAtLast();
        // 2、根据堆的性质调整索引0处新的元素的位置
        siftDown(0);
        return result;
    }

    /**
     * 替换堆中的最大元素并维持堆结构
     *
     * 第一种实现：先删除、再添加
     *
     * @param newElem 新插入堆的元素
     * @return 插入新元素之前堆中的最大元素
     */
    public T replaceMaxElementOne(T newElem) {
        T result = getMaxElement();
        removeMaxElement();
        addElement(newElem);
        return result;
    }

    /**
     * 替换堆中的最大元素并维持堆结构
     *
     * 第二种实现：使用 Array 中的 setElement 更新根结点中元素的值，然后再“下沉”调整
     *
     * @param newElem 新插入堆的元素
     * @return 插入新元素之前堆中的最大元素
     */
    public T replaceMaxElementTwo(T newElem) {
        T result = getMaxElement();
        data.setElement(newElem, 0);
        siftDown(0);
        return result;
    }

    /**
     * 获取其父结点在 array 中所对应的索引
     *
     * 索引0使用
     *
     * @param index 索引
     * @return 其父结点在 array 中所对应的索引
     */
    private int getParentIndex(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("Index is error!");
        }
        return (index - 1) / 2;
    }

    /**
     * 获取其左孩子结点在 array 中所对应的索引
     *
     * 索引0使用
     *
     * @param index 索引
     * @return 其左孩子结点在 array 中所对应的索引
     */
    private int getLeftChildIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index is error!");
        }
        return index * 2 + 1;
    }

    /**
     * 获取其右孩子结点在 array 中所对应的索引
     *
     * 索引0使用
     *
     * @param index 索引
     * @return 其右孩子结点在 array 中所对应的索引
     */
    private int getRightChildIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index is error!");
        }
        return index * 2 + 2;
    }

    /**
     * 调整给定索引处的元素在堆中的位置，上浮
     *
     * 关键！！！
     *
     * @param index 索引
     */
    private void siftUp(int index) {
        while (index > 0 && data.getElement(index).compareTo(data.getElement(getParentIndex(index))) > 0) {
            // 索引 index 处的元素比其父结点中的元素大，需要调整，即交换
            data.swapElement(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    /**
     * 调整给定索引处的元素在堆中的位置，下沉
     *
     * 关键！！！
     *
     * @param index 索引
     */
    private void siftDown(int index) {
        while (getLeftChildIndex(index) < data.getSize()) {
            int tempIndex = getLeftChildIndex(index);
            if (tempIndex + 1 < data.getSize() && data.getElement(tempIndex).compareTo(data.getElement(tempIndex + 1)) < 0) {
                tempIndex = getRightChildIndex(index);
                // 等价于 tempIndex = tempIndex + 1;
            }
            // data.get(tempIndex) 是 leftChild 与 rightChild 中较大的
            if (data.getElement(index).compareTo(data.getElement(tempIndex)) >= 0) {
                // 索引 index 处的元素比左、右结点中较大元素都大或相等，无需调整
                break;
            } else {
                data.swapElement(index, tempIndex);
                index = tempIndex;
            }
        }
    }

}