package google;

public class MinNumberFlipsBinaryAlt {
    public int minFlips(String s) {
        int n = s.length();
        int maxEnd = 2 * n;

        int min = Integer.MAX_VALUE;
        int startZero = 0;//01010101...
        int startOne = 0; //101010101...
        StringBuilder sb = new StringBuilder(s);
        int start = 0;
        for (int end = 0; end < maxEnd; end++) {
            if (end % 2 == 0) {
                if (sb.charAt(end) == '1') {
                    startZero++;
                } else {
                    startOne++;
                }
            } else {
                if (sb.charAt(end) == '1') {
                    startOne++;
                } else {
                    startZero++;
                }
            }
            if (end - start + 1 == n) {
                min = Math.min(min, Math.min(startOne, startZero));
                char st = sb.charAt(start);
                if (start % 2 == 0) {
                    if (sb.charAt(start) == '1') {
                        startZero--;
                    } else {
                        startOne--;
                    }
                } else {
                    if (sb.charAt(start) == '1') {
                        startOne--;
                    } else {
                        startZero--;
                    }
                }
                sb.append(st);
                start++;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        String s = "111000";
        MinNumberFlipsBinaryAlt mF = new MinNumberFlipsBinaryAlt();
        System.out.println(mF.minFlips(s));

    }
}
