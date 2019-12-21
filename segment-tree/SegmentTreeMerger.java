/**
 * 线段树合并规则接口
 *
 * @param <T>
 */
public interface SegmentTreeMerger<T> {

    /**
     * a 与 b 该怎么合并的逻辑，合并规则
     *
     * @param a a
     * @param b b
     * @return a 与 b 按照一定规则合并后的结果
     */
    T merge(T a, T b);

}