import java.util.*;

/**
 * - LeetCode : https://leetcode.com/problems/3sum/
 * - clarification:
 *   The array is empty or have less than 3 elements.
 *   There are duplicated elements and may get the same result.
 *
 * - solutions:
 *   1. We can use map to store the value of -a-b so we can find c(which equals to -a-b) in a very quick way.
 *      if we didn't find any element that equals to -a-b then it means there no c meet the condition of a+b+c=0.
 *      In order to get rid of duplicated results we can use set to store them or we can do pruning.
 *      Time complexity: O(n^2)
 *      Space complexity: O(n)
 *   2. Different from the above, we can use two pointers to find the two elements of which the sum is -nums[i] based on the sorted array.
 *      We can use one pointer to point at the front(the first element behind the nums[i]), and another to point at the end.
 *      when the sum is bigger than the -nums[i], it means we have to drop the nums[end]
 *      because the sum of the smallest element in the current(nums[front]) and it can not fit the condition, there will not be any other elements matching with it.
 *      So we move the pointer in the end backwards(end--).
 *      The situation is the same with that the sum is smaller than the -nums[i], we move pointer in the front forwards(front++).
 *      During the process, we have to deduplicate the result by moving the pointers forwards or backwards to jump over the duplicated elements.
 *      Time complexity: O(n^2)
 *      Space complexity: 0(n)
 *
 * - Test cases:
 *   1. nums = []
 *   2. nums = [2,-2]
 *   3. nums = [-1,0,-1,1]           // [-1,0,1]
 *   4. nums = [2,-1,-1,0,1,4,-3]    // [2,-1,-1] [2,1,-3] [-1,0,1] [-1,-3,4]
 *   5. nums = [0,0,0,0,0,0]         // [[0,0,0]]
 *
 * - Important key:
 *   Use set to remove the repetitive elements.
 *   Use hash or two pointers(both sorted) or binary search(one sorted) to speed up the search process.
 *
 * - Related problems:
 *   1,
 */
public class lc015ThreeSum {
/* Solution 1: Out of tim， Because the sort spend two much time.
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) return Collections.emptyList();
        Set<List<Integer>> result = new LinkedHashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {
            Map<Integer, Integer> hashMap = new HashMap<>(nums.length - i);
            for (int j = i + 1; j < nums.length; j++) {
                int value = -nums[i] - nums[j];
                Integer exist = hashMap.get(value);
                if (exist != null) {
                    List<Integer> list = Arrays.asList(nums[i], nums[j], exist);
                    list.sort(Comparator.naturalOrder());
                    result.add(list);
                }else hashMap.put(nums[j],nums[j]);
            }
        }
        return new ArrayList<>(result);
    }
 */

/* Solution 1: We don't need set to deduplicate. We can do pruning based the sort.
    // It doesn't matter if the nums[j](exist) that is saved in the map but not put into the result list is duplicated
    // because we only get the value from it. But We have to deduplicate the last elements
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new LinkedList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // If the first element is bigger than 0, it means all the elements will bigger than 0.
            // Then there is no elements meet the condition (a+b+c = 0)
            if (nums[i] > 0) return result;
            // We have to jump over the same nums[i], because they will get the same result.
            if (i > 0 && nums[i] == nums[i-1]) continue;
            Map<Integer, Integer> hashMap = new HashMap<>(nums.length - i);
            for (int j = i + 1; j < nums.length; j++) {
                int v = -nums[i] - nums[j];
                Integer exist = hashMap.get(v);
                if (exist != null) {
                    result.add(Arrays.asList(nums[i], exist, nums[j]));
                    // We have to finish the matching first, then we can jump over those are the same with the nums[j].
                    // Because this kind of situation has already been put into the result.
                    while (j + 1 < nums.length && nums[j] == nums[j+1]) j++;
                } else {
                    // When there is no element matching with it, it has to wait in the map for other elements to match it;
                    hashMap.put(nums[j], nums[j]);
                }
            }
        }
        return result;
    }
 */

/* Solution 2:
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) return result;
            if (i > 0 && nums[i-1] == nums[i]) continue;

            int low = i + 1;
            int high = nums.length - 1;
            int target = -nums[i];
            while (low < high) {
                if (nums[low] + nums[high] == target) {
                    result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    while(low < high && nums[low] == nums[low + 1]) low++;
                    while(high - 1 > low && nums[high] == nums[high -1]) high--;
                    low++;
                    high--;
                }else if (nums[low] + nums[high] < target) {
                    while(low + 1 < high && nums[low] == nums[low + 1]) low++;
                    low++;
                } else {
                    while(high - 1 > low && nums[high] == nums[high -1]) high--;
                    high--;
                }
            }
        }
        return result;
    }

 */

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new LinkedList<>();

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                return result;
            }
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int head = i + 1;
            int tail = nums.length - 1;
            while (head < tail) {
                int sum = -(nums[head] + nums[tail]);
                if (sum == nums[i]) {
                    result.add(Arrays.asList(nums[i], nums[head], nums[tail]));
                    while (head < tail && nums[head] == nums[head + 1]) {
                        head++;
                    }
                    while (head < tail && nums[tail] == nums[tail - 1]) {
                        tail--;
                    }
                }
                if (sum <= nums[i]) {
                    tail--;
                } else {
                    head++;
                }
            }
        }
        return result;
    }

        public static void main (String[]args){
            int[] nums = {-3, -1, -1, 0, 1, 3, 4};
            lc015ThreeSum s = new lc015ThreeSum();
            List<List<Integer>> result = s.threeSum(nums);
            for (int i = 0; i < result.size(); i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(result.get(i).get(j) + " ");
                }
                System.out.println(" ");
            }
        }
}
