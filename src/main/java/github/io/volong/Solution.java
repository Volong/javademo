package github.io.volong;

class Solution {

    public boolean isPalindrome(String s) {
        
        if (s == null) {
            return true;
        }
        
        s = s.toUpperCase();

        int i = 0;
        int j = s.length() - 1;


        while (i <= j) {
            
            char c1 = s.charAt(i);
            char c2 = s.charAt(j);
            
            if (isNumStr(c1) && isNumStr(c2) && c1 != c2) {
                return false;
            }

            if (!isNumStr(c1)) {
                i++;
            } else if (!isNumStr(c2)) {
                j--;
            } else {
                i++;
                j--;
            }
            
        }

        return true;
    }

    private boolean isNumStr(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z');
    }

    public static void main(String[] args) {

        String s = "race a car";
//        String s = "0P";
        Solution solution = new Solution();
        boolean palindrome = solution.isPalindrome(s);
        System.out.println(palindrome);

        System.out.println((int)'A');
        System.out.println((int)'0');
        System.out.println((int)'P');
        System.out.println((int)'Z');

        System.out.println(solution.isNumStr('0'));
    }
}