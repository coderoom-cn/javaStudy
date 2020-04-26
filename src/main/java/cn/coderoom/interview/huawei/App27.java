package cn.coderoom.interview.huawei;

/**
 *
 * 归并排序（Merge Sort）
 * 数据分段排序，然后数据合并。
 */
public class App27 {

    /**
     *
     * @param arrays
     */
    public static void mergeSort(int[] arrays){
        mergeSort(arrays, 0, arrays.length-1);
    }
    /**
     *
     * @param arrays
     * @param L      指向数组第一个元素
     * @param R      指向数组最后一个元素
     */
    public static void mergeSort(int[] arrays, int L, int R) {

        //如果只有一个元素，那就不用排序了,否则会导致栈溢出的问题
        if (L == R) {
            return;
        } else {
            //取中间的数，进行拆分
            int M = (L + R) / 2;
            //左边的数进行递归拆分
            mergeSort(arrays, L, M);
            //右边的数进行递归拆分
            mergeSort(arrays, M + 1, R);
            //合并
            merge(arrays, L, M + 1, R);

        }
    }


    /**
     * 合并数组
     *
     * @param arrays
     * @param L      指向数组第一个元素
     * @param M      指向数组分隔的元素
     * @param R      指向数组最后的元素
     */
    public static void merge(int[] arrays, int L, int M, int R) {

        //左边数组
        int[] leftArray = new int[M - L];
        //右边数组
        int[] rightArray = new int[R - M + 1];
        //填充数据
        for (int i = L; i < M; i++) {
            leftArray[i - L] = arrays[i];
        }
        for (int i = M; i <= R; i++) {
            rightArray[i - M] = arrays[i];
        }
        int i = 0, j = 0;
        // arrays数组的第一个元素
        int k = L;
        //比较两个数组的值
        while (i < leftArray.length && j < rightArray.length) {

            //谁比较小，谁将元素放入大数组中,移动指针，继续比较下一个
            // 等于的情况是保证“稳定”
            if (leftArray[i] <= rightArray[j]) {
                arrays[k] = leftArray[i];
                i++;
                k++;
            } else {
                arrays[k] = rightArray[j];
                j++;
                k++;
            }
        }

        //如果左边的数组还没比较完，右边的数都已经完了，那么将左边的数抄到大数组中(剩下的都是大数字)
        while (i < leftArray.length) {
            arrays[k] = leftArray[i];
            i++;
            k++;
        }
        //如果右边的数组还没比较完，左边的数都已经完了，那么将右边的数抄到大数组中(剩下的都是大数字)
        while (j < rightArray.length) {
            arrays[k] = rightArray[j];
            k++;
            j++;
        }
    }

}
