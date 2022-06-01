import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        System.out.println(getResult("inputFile.txt"));
    }

    public static int getResult(String filePath) {
        String fields = null;
        String race = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            fields = reader.readLine();
            race = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (race == null || fields == null) {
            throw new IllegalArgumentException("race and fields should be not null");
        }
        if (!(race.equals("Human") || race.equals("Swamper") || race.equals("Forester"))) {
            throw new IllegalArgumentException("This race is missing");
        }
        if (fields.length() != 16) {
            throw new IllegalArgumentException("There only 16 fields");
        }
        if ((fields.matches(".*[^swtp].*"))) {
            throw new IllegalArgumentException("only 4 type of field: s, w, t, p");
        }

        Map<String, Map<Character, Integer>> moveCost = new HashMap<>(3);
        moveCost.put("Human", Map.of('s', 5, 'w', 2, 't', 3, 'p', 1));
        moveCost.put("Swamper", Map.of('s', 2, 'w', 2, 't', 5, 'p', 2));
        moveCost.put("Forester", Map.of('s', 3, 'w', 3, 't', 2, 'p', 2));
        char[][] fieldsType = new char[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fieldsType[i][j] = fields.charAt(i * 4 + j);
            }
        }

        int[][] minFieldsCost = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                minFieldsCost[i][j] = moveCost.get(race).get(fieldsType[i][j]);
                if (i > 0 && j > 0) {
                    minFieldsCost[i][j] += Math.min(minFieldsCost[i - 1][j], minFieldsCost[i][j - 1]);
                } else {
                    if (i > 0) {
                        minFieldsCost[i][j] += minFieldsCost[i - 1][j];
                    } else if (j > 0) {
                        minFieldsCost[i][j] += minFieldsCost[i][j - 1];
                    }
                }
            }
        }

        return minFieldsCost[3][3] - minFieldsCost[0][0];
    }
}
