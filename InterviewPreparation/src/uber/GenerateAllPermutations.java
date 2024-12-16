package uber;
import java.util.*;

public class GenerateAllPermutations {
    public List<String> generateWords(String input, char mandatoryChar) {
        Set<String> words = new HashSet<>();
        List<String> result = new ArrayList<>();
        recursive(input, new ArrayList<>(), words);

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                String newWord = word.substring(0, i) + mandatoryChar + word.substring(i);
                if (newWord.length() >= 4) result.add(newWord);
            }
        }
        return result;
    }

    private void recursive(String input, List<Character> temp, Set<String> words) {
        if (temp.size() >= 3 && temp.size() <= 6) {
            words.add(temp.stream()
                    .map(Object::toString)
                    .reduce((acc, e) -> acc + e)
                    .get());
        }
        if (temp.size() > 6) return;

        for (int i = 0; i < input.length(); i++) {
            if (temp.contains(input.charAt(i))) continue;
            temp.add(input.charAt(i));
            recursive(input, temp, words);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<String> res = new GenerateAllPermutations().generateWords("TWADLN", 'I');
        res.sort(Comparator.comparing(String::length));
        System.out.println(res);
        System.out.println(res.contains("WAIT"));
        System.out.println(res.contains("LIT"));
        System.out.println(res.contains("WANT"));
        System.out.println(res.contains("ITWA"));
        System.out.println(res.contains("FISH"));
    }
}
