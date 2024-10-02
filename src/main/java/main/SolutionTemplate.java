package main;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class SolutionTemplate {
    public static void main(String[] args) {
        // Use getResourceAsStream to load the input file from resources
        InputStream inputStream = SolutionTemplate.class.getResourceAsStream("/input.txt");
        if (inputStream == null) {
            System.err.println("Input file not found!");
            return;
        }
        FastReader reader = new FastReader(inputStream);

        // Example 1: Reading a single integer
        int singleInt = reader.nextInt();
        System.out.println("Single Integer: " + singleInt);

        // Example 2: Reading a space-separated array of integers
        int arraySize = reader.nextInt();
        int[] intArray = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            intArray[i] = reader.nextInt();
        }
        System.out.println("Integer Array: " + Arrays.toString(intArray));

        // Example 3: Reading space-separated strings as a List
        int numOfStrings = reader.nextInt();
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < numOfStrings; i++) {
            stringList.add(reader.next());
        }
        System.out.println("String List: " + stringList);

        // Example 4: Reading a matrix (2D array)
        int rows = reader.nextInt();
        int cols = reader.nextInt();
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = reader.nextInt();
            }
        }
        System.out.println("Matrix:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        // Example 5: Using Java 8 Streams to read and print a list of integers
        List<Integer> integerList = Arrays.stream(reader.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        System.out.println("Integer List from Stream: " + integerList);

        // Example 6: Reading multiple test cases and storing pairs
        int testCases = reader.nextInt();
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < testCases; i++) {
            int a = reader.nextInt();
            int b = reader.nextInt();
            pairs.add(new Pair<>(a, b));
        }
        System.out.println("Pairs:");
        pairs.forEach(pair -> System.out.println("Pair: " + pair.getKey() + ", " + pair.getValue()));
    }

    // FastReader class for efficient input handling
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        // Constructor to read from file
        public FastReader(InputStream inputStream) {
            br = new BufferedReader(new InputStreamReader(inputStream));
        }

        // Reads next word
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        // Reads next integer
        int nextInt() {
            return Integer.parseInt(next());
        }

        // Reads next long
        long nextLong() {
            return Long.parseLong(next());
        }

        // Reads next double
        double nextDouble() {
            return Double.parseDouble(next());
        }

        // Reads complete line
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
