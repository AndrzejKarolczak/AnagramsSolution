import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    /**
     * Groups words into lists of words being anagrams of each other. Anagram is a word that is composed of earranged
     * characters of another word. When determining whether words are anagrams the case of characters is gnored.
     *
     * @param words List of words to be grouped
     * @return List of lists of words from words
     */
    public List<List<String>> findAnagrams(List<String> words) {

        var groups = words.stream().map(w -> {
                var array = w.toLowerCase().toCharArray();
                Arrays.sort(array);
                return new Tuple(new String(array), w);
            }
        ).collect(Collectors.groupingBy(
            w -> w.key));

        var lists = groups.values().stream().map(
            list -> list.stream().map(
                tuple -> tuple.value
            ).collect(Collectors.toList())
        ).collect(Collectors.toList());

        return lists;
    }

    private static class Tuple {
        private String key;
        private String value;

        public Tuple(String k, String v) {
            key = k;
            value = v;
        }
    }

    public static void main(String[] args) {
       // JUnitCore.main("Solution");
    }
}
