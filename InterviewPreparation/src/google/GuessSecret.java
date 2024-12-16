package google;

public class GuessSecret {

    public static String getWordleColors(String secret, String guess) {
        int n = secret.length();
        char[] result = new char[n];
        boolean[] matchedInSecret = new boolean[n];  // To track letters matched in SECRET for 'G' or 'Y'

        // Step 1: First pass to assign 'G' (Green) for exact matches
        for (int i = 0; i < n; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                result[i] = 'G';
                matchedInSecret[i] = true;  // Mark this letter as matched in SECRET
            } else {
                result[i] = 'B';  // Default to 'B', will update later if found in SECRET
            }
        }

        // Step 2: Second pass to assign 'Y' (Yellow) for letters in wrong positions
        for (int i = 0; i < n; i++) {
            if (result[i] == 'B') {  // Only consider positions that are still marked as 'B'
                for (int j = 0; j < n; j++) {
                    if (!matchedInSecret[j] && secret.charAt(j) == guess.charAt(i)) {
                        result[i] = 'Y';  // Mark as 'Y'
                        matchedInSecret[j] = true;  // Mark this position as used in SECRET
                        break;  // Move to the next character in GUESS
                    }
                }
            }
        }

        return new String(result);
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(getWordleColors("acdz", "cxdz"));  // Expected: YBGG
        System.out.println(getWordleColors("hello", "helix"));  // Expected: GGGBB
        System.out.println(getWordleColors("ax", "aa"));  // Expected: GB
        System.out.println(getWordleColors("hello", "eaaae"));  // Expected: YBBBB
        System.out.println(getWordleColors("ab", "bb"));  // Expected: BG
    }
}


