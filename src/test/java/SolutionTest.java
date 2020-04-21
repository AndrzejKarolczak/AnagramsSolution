import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    private static String normalizeSolution(List<List<String>> solution) {
        return solution.stream()
            .map(l ->
                l.stream()
                    .sorted()
                    .collect(Collectors.joining(",", "[", "]")))
            .sorted()
            .collect(Collectors.joining(",", "[", "]"));
    }

    @Test
    public void testHandlesEmptyList() {
        assertEquals("[]",
            normalizeSolution(new Solution().findAnagrams(List.of())));
    }

    @Test
    public void testHandlesLowercase() {
        assertEquals("[[abc,bca,cab],[nt,tn],[vwa]]",
            normalizeSolution(new Solution().findAnagrams(List.of("tn", "cab", "vwa", "bca", "nt", "abc"))));
    }

    @Test
    public void testHandlesCase() {
        assertEquals("[[BCA,abc,cab],[Tn,nt],[vwa]]",
            normalizeSolution(new Solution().findAnagrams(List.of("Tn", "cab", "vwa", "BCA", "nt", "abc"))));
    }
}
