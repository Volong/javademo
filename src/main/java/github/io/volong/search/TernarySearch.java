package github.io.volong.search;

/**
 *  三元搜索树 
 *
 */
public class TernarySearch {

    public int search(int[] nums, int num) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while(start <= end) {
            int mid1 = start + (end - start)/3;
            int mid2 = mid1 + (end - start)/3;

            if(nums[mid1] == num) {
                return mid1;
            }

            if(nums[mid2] == num) {
                return mid2;
            }

            if(num < nums[mid1]) {
                end = mid1 - 1;
            } else if(num > nums[mid2]) {
                start = mid2 + 1;
            } else {
                start = mid1 + 1;
                end = mid2 - 1;
            }
        }

        return -1;
    }
    
    public static void main(String[] args) {
        
        int[] oddLengthArray = {2, 7, 10, 11, 23, 27, 28};
        int[] evenLengthArray = {-5, -2, 9, 15, 17, 19};
        
        TernarySearch ts = new TernarySearch();
        
        int search = ts.search(oddLengthArray, 23);
        System.out.println(search);
    }
}