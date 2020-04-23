import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    private Solution solution = new Solution();
    private static List<TestContainer> testCases;

    @BeforeAll
    private static void prepareTestCases() {
        testCases = new ArrayList<>();
        testCases.add(new TestContainer("lower-case anagrams should be found correctly",
            "[[abc,bca,cab],[nt,tn],[vwa]]", List.of("tn", "cab", "vwa", "bca", "nt", "abc")));
        testCases.add(new TestContainer("empty list should not throw an exception", "[]", List.of()));
        testCases.add(new TestContainer("case-insensitive anagrams should be found correctly",
            "[[BCA,abc,cab],[Tn,nt],[vwa]]", List.of("Tn", "cab", "vwa", "BCA", "nt", "abc")));
    }

    @TestFactory
    Collection<DynamicTest> testForFunctionalProgrammingSolution() {
        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        testCases.forEach(testCase -> {

            Executable executable = () -> {
                assertEquals(testCase.expectedResult,
                    normalizeSolution(
                        solution.findAnagramsWithFunctionalProgramming(testCase.givenArgument)));
            };

            DynamicTest dynamicTest = DynamicTest.dynamicTest(testCase.name, executable);
            dynamicTests.add(dynamicTest);
        });
        return dynamicTests;
    }

    @TestFactory
    Collection<DynamicTest> testForLoopBasedSolution() {
        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        testCases.forEach(testCase -> {

            Executable executable = () -> {
                assertEquals(testCase.expectedResult,
                    normalizeSolution(
                        solution.findAnagramsWithLoop(testCase.givenArgument)));
            };

            DynamicTest dynamicTest = DynamicTest.dynamicTest(testCase.name, executable);
            dynamicTests.add(dynamicTest);
        });
        return dynamicTests;
    }

    private static String normalizeSolution(List<List<String>> solution) {
        return solution.stream()
            .map(l ->
                l.stream()
                    .sorted()
                    .collect(Collectors.joining(",", "[", "]")))
            .sorted()
            .collect(Collectors.joining(",", "[", "]"));
    }

    private static class TestContainer {
        private String name;
        private String expectedResult;
        private List<String> givenArgument;

        public TestContainer(String name, String expectedResult, List<String> given) {
            this.name = name;
            this.expectedResult = expectedResult;
            this.givenArgument = given;
        }
    }
}
