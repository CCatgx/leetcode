package easy;

import java.util.HashSet;

/**
 * @ClassName easy026
 * @Description TODO
 * @Author CcatGX
 * @Date 2020/4/16 14:20
 * @Version 1.0
 **/
public class easy026 {

    /**
      *给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     *  
     *
     * 示例 1:
     *
     * 给定数组 nums = [1,1,2],
     *
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:
     *
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     *
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     *  
     *
     * 说明:
     *
     * 为什么返回数值是整数，但输出的答案是数组呢?
     *
     * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     *
     * 你可以想象内部操作如下:
     *
     * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
     * int len = removeDuplicates(nums);
     *
     * // 在函数里修改输入数组对于调用者是可见的。
     * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
     * for (int i = 0; i < len; i++) {
     *     print(nums[i]);
     * }
     *
    **/

    // 因为是要原地操作一个数组，所以肯定是只能在一个for循环里面解决问题
    public int removeDuplicates(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int flag = 0;
        for (int i=0;i<nums.length;i++){
            if(set.size()==0){
                set.add(nums[i]);
                flag++;
            }
            if(!set.contains(nums[i])){
                set.add(nums[i]);
                nums[flag] = nums[i];
                flag++;
            }
        }
        return set.size();
    }

    /**
      *解题思路：
     * 解法： 双指针
     *
     * 首先注意数组是有序的，那么重复的元素一定会相邻。
     *
     * 要求删除重复元素，实际上就是将不重复的元素移到数组的左侧。
     *
     * 考虑用 2 个指针，一个在前记作 p，一个在后记作 q，算法流程如下：
     *
     * 1.比较 p 和 q 位置的元素是否相等。
     *
     * 如果相等，q 后移 1 位
     * 如果不相等，将 q 位置的元素复制到 p+1 位置上，p 后移一位，q 后移 1 位
     * 重复上述过程，直到 q 等于数组长度。
     *
     * 返回 p + 1，即为新数组长度。
     *
    **/

    public int removeDuplicates1(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;
        while(q < nums.length){
            if(nums[p] != nums[q]){
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }



    public static void main(String[] args){
        easy026 a = new easy026();
        int[] nums1 = {1,1,2};
        int[] nums2= {0,0,1,1,1,2,2,3,3,4};
        System.out.println(a.removeDuplicates(nums2));
        for(int i=0;i<nums2.length;i++){
            System.out.print(nums2[i]+" ");
        }
    }

}
