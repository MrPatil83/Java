// Q1
/*  Write a Java code to read your resume txt file (or for matter any given text file with minimum 100 words) and implement the logic to print the data as shown below
 
Please note: given below is sample data based on the file we read,
Max User Alphabet
Max User Number
Min Used Alphabet
Min Used Number
Max Used Word
2nd Max used Word
Min Used Word
2nd Min used Word
A
9
Z
6
The
Is
Sandra
Bandra   */

import java.io.FileInputStream;
import java.io.IOException;

public class ResumeAnalyzer {

    public static void main(String[] args) {
        // text file path
        String filePath = "C:\\Users\\Avinash\\Downloads\\Avinash Patil.txt";
        try {
            String content = readFile(filePath);
            analyzeText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        byte[] buffer = new byte[1024];
        int bytesRead;
        StringBuilder contentBuilder = new StringBuilder();

        while ((bytesRead = fis.read(buffer)) != -1) {
            contentBuilder.append(new String(buffer, 0, bytesRead, "UTF-8"));
        }

        fis.close();
        return contentBuilder.toString().trim();
    }

    private static void analyzeText(String text) {
        if (countWords(text) < 100) {
            System.out.println("The file must contain at least 100 words.");
            return;
        }

        int[] alphabetCount = new int[26];
        int[] numericCount = new int[10]; 

        String[][] wordCount = new String[1000][2]; 
        int uniqueWordCount = 0;

        String[] words = splitWords(text);
        for (String word : words) {
            word = cleanWord(word).toLowerCase(); 
            if (word.isEmpty()) continue;

            char firstChar = word.charAt(0);
            if (firstChar >= 'a' && firstChar <= 'z') {
                alphabetCount[firstChar - 'a']++;
            } else if (firstChar >= '0' && firstChar <= '9') {
                numericCount[firstChar - '0']++;
            }

            boolean found = false;
            for (int i = 0; i < uniqueWordCount; i++) {
                if (wordCount[i][0].equals(word)) {
                    wordCount[i][1] = String.valueOf(Integer.parseInt(wordCount[i][1]) + 1);
                    found = true;
                    break;
                }
            }
            if (!found) {
                wordCount[uniqueWordCount][0] = word;
                wordCount[uniqueWordCount][1] = "1";
                uniqueWordCount++;
            }
        }

        int maxUsedAlphabetIndex = -1;
        int minUsedAlphabetIndex = -1;
        for (int i = 0; i < 26; i++) {
            if (alphabetCount[i] > 0) {
                if (maxUsedAlphabetIndex == -1 || alphabetCount[i] > alphabetCount[maxUsedAlphabetIndex]) {
                    maxUsedAlphabetIndex = i;
                }
                if (minUsedAlphabetIndex == -1 || alphabetCount[i] < alphabetCount[minUsedAlphabetIndex]) {
                    minUsedAlphabetIndex = i;
                }
            }
        }

        int maxUsedNumberIndex = -1;
        int minUsedNumberIndex = -1;
        for (int i = 0; i < 10; i++) {
            if (numericCount[i] > 0) {
                if (maxUsedNumberIndex == -1 || numericCount[i] > numericCount[maxUsedNumberIndex]) {
                    maxUsedNumberIndex = i;
                }
                if (minUsedNumberIndex == -1 || numericCount[i] < numericCount[minUsedNumberIndex]) {
                    minUsedNumberIndex = i;
                }
            }
        }

        String maxUsedWord = null;
        String secondMaxUsedWord = null;
        String minUsedWord = null;
        String secondMinUsedWord = null;
        int maxUsedWordCount = 0;
        int secondMaxUsedWordCount = 0;
        int minUsedWordCount = Integer.MAX_VALUE;
        int secondMinUsedWordCount = Integer.MAX_VALUE;

        for (int i = 0; i < uniqueWordCount; i++) {
            int count = Integer.parseInt(wordCount[i][1]);
            if (count > maxUsedWordCount) {
                secondMaxUsedWord = maxUsedWord;
                secondMaxUsedWordCount = maxUsedWordCount;
                maxUsedWord = wordCount[i][0];
                maxUsedWordCount = count;
            } else if (count > secondMaxUsedWordCount) {
                secondMaxUsedWord = wordCount[i][0];
                secondMaxUsedWordCount = count;
            }

            if (count < minUsedWordCount) {
                secondMinUsedWord = minUsedWord;
                secondMinUsedWordCount = minUsedWordCount;
                minUsedWord = wordCount[i][0];
                minUsedWordCount = count;
            } else if (count < secondMinUsedWordCount) {
                secondMinUsedWord = wordCount[i][0];
                secondMinUsedWordCount = count;
            }
        }

        System.out.println("Max Used Alphabet: " + (char) (maxUsedAlphabetIndex + 'a') + " (" + alphabetCount[maxUsedAlphabetIndex] + ")");
        System.out.println("Min Used Alphabet: " + (char) (minUsedAlphabetIndex + 'a') + " (" + alphabetCount[minUsedAlphabetIndex] + ")");
        System.out.println("Max Used Number: " + (maxUsedNumberIndex) + " (" + numericCount[maxUsedNumberIndex] + ")");
        System.out.println("Min Used Number: " + (minUsedNumberIndex) + " (" + numericCount[minUsedNumberIndex] + ")");
        System.out.println("Max Used Word: " + maxUsedWord + " (" + maxUsedWordCount + ")");
        System.out.println("2nd Max Used Word: " + secondMaxUsedWord + " (" + secondMaxUsedWordCount + ")");
        System.out.println("Min Used Word: " + minUsedWord + " (" + minUsedWordCount + ")");
        System.out.println("2nd Min Used Word: " + secondMinUsedWord + " (" + secondMinUsedWordCount + ")");
    }

    private static int countWords(String text) {
        int count = 0;
        boolean isWord = false;
        int endOfLine = text.length() - 1;

        for (int i = 0; i < text.length(); i++) {
            if (isLetter(text.charAt(i)) && i != endOfLine) {
                isWord = true;
            } else if (!isLetter(text.charAt(i)) && isWord) {
                count++;
                isWord = false;
            } else if (isLetter(text.charAt(i)) && i == endOfLine) {
                count++;
            }
        }
        return count;
    }

    private static String[] splitWords(String text) {
        // [1000 is words 
        String[] words = new String[1000]; 
        int wordCount = 0;
        StringBuilder wordBuilder = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (isLetterOrDigit(c)) {
                wordBuilder.append(c);
            } else {
                if (wordBuilder.length() > 0) {
                    words[wordCount++] = wordBuilder.toString();
                    wordBuilder.setLength(0); 
                }
            }
        }

        if (wordBuilder.length() > 0) {
            words[wordCount++] = wordBuilder.toString();
        }

        String[] result = new String[wordCount];
        System.arraycopy(words, 0, result, 0, wordCount);
        return result;
    }

    private static String cleanWord(String word) {
        StringBuilder cleaned = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (isLetter(c) || (c >= '0' && c <= '9')) {
                cleaned.append(c);
            }
        }
        return cleaned.toString();
    }

    private static boolean isLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    private static boolean isLetterOrDigit(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }
}
