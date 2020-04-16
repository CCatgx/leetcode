package easy;

import java.util.Arrays;

/**
 * @ClassName easy088
 * @Description TODO
 * @Author CcatGX
 * @Date 2020/4/16 11:01
 * @Version 1.0
 **/
public class easy088 {

    /**
     *给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     *
     *  
     *
     * 说明:
     *
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     *示例:
     *
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     *
     * 输出: [1,2,2,3,5,6]
    **/

    //我的解法，暴力解法
    //生成一个m+n的数组，因为传递过来的数组是有序数组，那么暴力比较两个数组，依次放入临时数组中
    //或者先放入一个数组，然后在一个数组内，使用排序算法,官方也是这个思路，但是可以用jdk自带的算法

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[m+n];
        for(int i=0;i<m+n;i++){
            if(i!=m){
                temp[i]=nums1[i];
            } else{
                for(int j=0;j<n;j++){
                    temp[i] = nums2[j];
                    i++;
                }
                break;
            }
        }
        for(int i=0;i<temp.length;i++){
            for(int j=i+1;j<=temp.length-1;j++){
                if(temp[i]>temp[j]){
                    int tmp = temp[i];
                    temp[i] = temp[j];
                    temp[j] = tmp;
                }
            }
        }
        for(int i=0;i<temp.length;i++){
            nums1[i] = temp[i];
        }
    }

    //时间复杂度 : O((n + m)\log(n + m))O((n+m)log(n+m))。
    //空间复杂度 : O(1)O(1)。
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    /**
     *直觉
     *
     * 一般而言，对于有序数组可以通过 双指针法 达到O(n + m)O(n+m)的时间复杂度。
     *
     * 最直接的算法实现是将指针p1 置为 nums1的开头， p2为 nums2的开头，在每一步将最小值放入输出数组中。
     *
     * 由于 nums1 是用于输出的数组，需要将nums1中的前m个元素放在其他地方，也就需要 O(m)O(m) 的空间复杂度。
     *
    **/
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int [] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);
        int p1 = 0;
        int p2 = 0;

        int p = 0;

        // Compare elements from nums1_copy and nums2
        // and add the smallest one into nums1.
        while ((p1 < m) && (p2 < n))
            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];

        // if there are still elements to add
        if (p1 < m)
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        if (p2 < n)
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
    }

    /**
     *直觉
     *
     * 方法二已经取得了最优的时间复杂度O(n + m)O(n+m)，但需要使用额外空间。这是由于在从头改变nums1的值时，需要把nums1中的元素存放在其他位置。
     *
     * 如果我们从结尾开始改写 nums1 的值又会如何呢？这里没有信息，因此不需要额外空间。
     *
     * 这里的指针 p 用于追踪添加元素的位置。
     *
    **/
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        // two get pointers for nums1 and nums2
        int p1 = m - 1;
        int p2 = n - 1;
        // set pointer for nums1
        int p = m + n - 1;

        // while there are still elements to compare
        while ((p1 >= 0) && (p2 >= 0))
            // compare two elements from nums1 and nums2 
            // and add the largest one in nums1 
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];

        // add missing elements from nums2
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    public static void main(String[] args){
        int[] num1 = {1,2,3,0,0,0};
        int[] num2 = {2,5,6};
        //int[] num1 = {2,0};
        //int[] num2 = {1};
        easy088 a = new easy088();
        a.merge3(num1,3,num2,3);
        for(int i=0;i<num1.length;i++){
            System.out.print(num1[i]+" ");
        }
    }

}
