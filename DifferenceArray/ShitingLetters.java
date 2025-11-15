/*
 * https://leetcode.com/problems/shifting-letters/description/?envType=problem-list-v2&envId=2fdm7o03
 * try this
"bad"
shifts[] ={10,20,30};
 */
public class ShitingLetters {
    public String shiftingLetters(String s, int[] shifts) {
        // create the difference array
        int n = s.length();
        int[] diffArr = new int[n];

        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = i;
            // Take modulo 26 for each shift value to prevent overflow
            int val = shifts[i] % 26;
            diffArr[left] += val;
            if (right + 1 < n)
                diffArr[right + 1] -= val;
        }

        // find cumulative sum
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += diffArr[i];
            diffArr[i] = sum;
        }

        // find the resultant string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int shift = diffArr[i];
            char ch = s.charAt(i);
            // Handle negative shifts by adding 26
            if (shift < 0)
                shift += 26;
            char newChar = (char) ((ch - 'a' + shift) % 26 + 'a');
            sb.append(newChar);
        }
        return sb.toString();
    }
}
