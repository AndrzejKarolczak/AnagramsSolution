import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    /**
     * Groups words into lists of words being anagrams of each other. Anagram is a word that is composed of earranged
     * characters of another word. When determining whether words are anagrams the case of characters is gnored.
     *
     * @param words List of words to be grouped
     * @return List of lists of words from words
     */
    public List<List<String>> findAnagramsWithFunctionalProgramming(List<String> words) {
        var groups = words.stream().map(word -> new Tuple(getWordWithSortedLetters(word), word))
            .collect(
                Collectors.groupingBy(w -> w.key));

        var lists = groups.values().stream().map(
            list -> list.stream().map(
                tuple -> tuple.value
            ).collect(Collectors.toList())
        ).collect(Collectors.toList());

        return lists;
    }

    public List<List<String>> findAnagramsWithLoop(List<String> words) {
        if (words.isEmpty()) return new ArrayList<>();

        Map<String, List<String>> mapOfLists = new HashMap<>();
        String seedWord = words.get(0);
        String sortedSeedWord = getWordWithSortedLetters(seedWord);
        List<String> list = new ArrayList<>();
        list.add(seedWord);
        mapOfLists.put(sortedSeedWord, list);

        for (int i = 1; i < words.size(); i++) {
            String wordToCheck = words.get(i);
            String sortedWordToCheck = getWordWithSortedLetters(wordToCheck);
            var listOrNull = mapOfLists.get(sortedWordToCheck);

            if (Objects.nonNull(listOrNull)) {
                listOrNull.add(wordToCheck);
            } else {
                list = new ArrayList<>();
                list.add(wordToCheck);
                mapOfLists.put(sortedWordToCheck, list);
            }
        }

        return new ArrayList<>(mapOfLists.values());
    }

    private String getWordWithSortedLetters(String word) {
        char[] array = word.toLowerCase().toCharArray();
        Arrays.sort(array);
        return new String(array);
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
    }
}
