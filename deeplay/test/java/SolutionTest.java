import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

    @Test
    void getResult() {
        Assertions.assertEquals(10, Solution.getResult("inputFile.txt"));
        IllegalArgumentException numberFieldsException = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Solution.getResult("moreFields.txt");
        }, "IllegalArgumentException was expected");
        Assertions.assertEquals("There only 16 fields", numberFieldsException.getMessage());

        IllegalArgumentException raceException = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Solution.getResult("notRace.txt");
        }, "IllegalArgumentException was expected");
        Assertions.assertEquals("This race is missing", raceException.getMessage());

        IllegalArgumentException typeFieldException = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Solution.getResult("notTypeField.txt");
        }, "IllegalArgumentException was expected");
        Assertions.assertEquals("only 4 type of field: s, w, t, p", typeFieldException.getMessage());
    }
}