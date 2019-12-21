import org.jetbrains.annotations.NotNull;

/**
 * 线段（或“区间”）树
 *
 * 线段树是一种基于原生数据而生成的树，父结点中的数据是其直接左、右子结点中的数据的一种“综合”，这种“综合”另一层意思就是归并成一个“区间”了
 * 具体的“综合”的策略是根据业务需求来确定的
 *
 * 索引0可以使用
 *
 * @param <T> 泛型
 */
public class SegmentTree<T> {

    /**
     * 线段树，这里使用“数组”表示！
     *
     * “线段数”按照一个“满二叉树”来处理，所以表示“线段树”的数组的容量一般选取的是实际数组长度的4倍，其中没有数据的地方为 null
     */
    private T[] tree;

    /**
     * 实际的数组，基于实际的数组以及业务需求构建出线段树
     */
    private T[] data;

    /**
     * 合并规则的制定者
     */
    private SegmentTreeMerger<T> merger;

    /**
     * 构造方法
     *
     * @param data 数组，要求数组长度与实际内容相符，不要有多余的空间
     */
    public SegmentTree(@NotNull T[] data, @NotNull SegmentTreeMerger<T> merger) {
        this.data = (T[]) new Object[data.length];
        System.arraycopy(data, 0, this.data, 0, data.length);
        tree = (T[]) new Object[4 * data.length];
        this.merger = merger;
        buildSegmentTree(0, 0, data.length - 1);
    }

    public int getSize() {
        return data.length;
    }

    public T getElement(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is error!");
        }
        return data[index];
    }

    /**
     * 创造线段树
     *
     * 递归实现
     *
     * 在 tree 这一成员变量的 segmentTreeRootIndex 的位置 创建表示区间 [leftIndex...rightIndex] 的线段树
     * 难！！！
     *
     * @param segmentTreeRootIndex 数组 tree 的索引
     * @param leftIndex 数组 data 的索引
     * @param rightIndex 数组 data 的索引
     */
    private void buildSegmentTree(int segmentTreeRootIndex, int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex) {
            tree[segmentTreeRootIndex] = data[leftIndex];
            return;
        }

        int segmentTreeLeftChildIndex = getLeftChildIndex(segmentTreeRootIndex);
        int segmentTreeRightChildIndex = getRightChildIndex(segmentTreeRootIndex);

        int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
        buildSegmentTree(segmentTreeLeftChildIndex, leftIndex, middleIndex);
        buildSegmentTree(segmentTreeRightChildIndex, middleIndex + 1, rightIndex);

        tree[segmentTreeRootIndex] = merger.merge(tree[segmentTreeLeftChildIndex], tree[segmentTreeRightChildIndex]);
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SegmentTree: [");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                sb.append(tree[i]);
            } else {
                sb.append("null");
            }
            if (i + 1 != tree.length) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 获取左孩子结点在数组表示中所对应的索引
     *
     * @param index 索引
     * @return 左孩子结点在数组表示中所对应的索引 或 抛出异常
     */
    private int getLeftChildIndex(int index) {
        if (index < 0 || index >= tree.length) {
            throw new IllegalArgumentException("Index is error!");
        }
        return 2 * index + 1;
    }

    /**
     * 获取右孩子结点在数组表示中所对应的索引
     *
     * @param index 索引
     * @return 右孩子结点在数组表示中所对应的索引 或 抛出异常
     */
    private int getRightChildIndex(int index) {
        if (index < 0 || index >= tree.length) {
            throw new IllegalArgumentException("Index is error!");
        }
        return 2 * index + 2;
    }

}