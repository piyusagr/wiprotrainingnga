import java.util.*;

class TargetSum {
    static int[] twoSum(int n, int[] nums, int target) {
        // HashMap to store number and its index
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // Check each number
        for(int i = 0; i < n; i++) {
            // Find what number we need
            int complement = target - nums[i];
            
            // If we've seen this complement before, we found our pair
            if(map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            
            // Store current number and its index
            map.put(nums[i], i);
        }
        
        // If no solution found (though per problem constraints, we'll always find one)
        return new int[]{-1, -1};
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            int n = in.nextInt();
            int arr[] = new int[n];
            for(int i = 0; i < n; i++)
                arr[i] = in.nextInt();
            int target = in.nextInt();
            int[] ans = twoSum(n, arr, target);
            System.out.println("(" + ans[0] + " , " + ans[1] + ")");
        } finally {
            in.close();
        }
    }
}