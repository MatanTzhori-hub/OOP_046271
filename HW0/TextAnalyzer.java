import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * A program that analyzes a text file and prints the following statistics about the file:
 * - Amount of words in file.
 * - Average words per line.
 * - The fraction of the total words for words with length 1-15.
 */
public class TextAnalyzer {
    private static final String DELIMITERS = " ;?{}[]=-+_!@#$%^&*():',.";
    private static final int MIN_LEN = 1;
    private static final int MAX_LEN = 15;

    /**
     * @requires lineCount and wordCount is not zero.
     * @effects prints statistics about the file.
     * @modifies wordsLengthsCount
     * @param wordsCount is the amount of words in the text file.
     * @param linesCount is the amount of lines in the text file.
     * @param wordsLengthsCount is a Histogram array with amount of words with length i in the cell i. (cell 0 is unused)
     */
    private static void printStatistics(int wordsCount, int linesCount, double wordsLengthsCount[]){
        double avgLineWords = 0;

        if(linesCount > 0)
            avgLineWords = (double)wordsCount / linesCount;

        if (wordsCount > 0) {
            for (int i = MIN_LEN; i <= MAX_LEN; i++) {
                wordsLengthsCount[i] /=  wordsCount;
            }
        }

        System.out.println("Total words in the file: " + wordsCount);
        System.out.println("Total lines in the file: " + linesCount);
        System.out.printf("Average words per line: %.2f\n", avgLineWords);
        for (int i = MIN_LEN; i <= MAX_LEN; i++) {
            System.out.printf("Words with %d characters: %.2f%%\n", i, wordsLengthsCount[i] * 100);
        }
    }

    /**
     * @requires filePath is a valid file-path and is not null.
     * @effects Prints statistics about the file. Prints an error if file does not exist.
     * @modifies Nothing.
     * @param filePath is a string path to the file to analyze.
     */
    private static void analyzeTextFile(String filePath){
        File textFile = new File(filePath);
        if (!textFile.exists()) {
            System.out.println("File does not exist!");
            return;
        }

        int wordsCount = 0;
        int linesCount = 0;
        double[] linesCounwordsLengthsCountt = new double[MAX_LEN+1];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }
                linesCount++;

                // Split line using the given delimiters
                StringTokenizer tokenizer = new StringTokenizer(line, DELIMITERS);
                while (tokenizer.hasMoreTokens()) {
                    String word = tokenizer.nextToken();
                    if (word.length() >= MIN_LEN && word.length() <= MAX_LEN) {
                        linesCounwordsLengthsCountt[word.length()]++;
                    }
                    wordsCount++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        printStatistics(wordsCount, linesCount, linesCounwordsLengthsCountt);
    }

    public static void main(String[] args) {
        if (args == null || args.length != 1) {
            System.err.println("Error: Invalid amount of arguments.");
            return;
        }
        
        analyzeTextFile(args[0]);
    }
}


