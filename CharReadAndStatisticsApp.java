package testbed.review;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class CharReadAndStatisticsApp {
    final static Path path = Paths.get("C:/tmp/logCharStatistics.txt");
    final static int[][] text = new int[256][2];
    static int pivot = -1;
    static int count = 0;
    final static Scanner in = new Scanner(System.in);

    public static void main(String[] args)  {
        try {
            getCharAndSaveService();
            printStatistics();
        } catch (IOException e) {
            System.out.println("Error in I/O");
        }
    }

    public static void getCharAndSaveService() throws IOException, IllegalArgumentException {
        int ch;
        byte[] buf = new byte[4096];
        int n = 0;

        try (FileInputStream fs = new FileInputStream("C:/tmp/inputText.txt");) {
            while ((n = fs.read(buf)) > 0) {
                for (int i = 0; i < n; i++) {
                    if (!saveChar(buf[i])) throw new IllegalArgumentException("Error in save");
                    else {
                        count++;
                    }
                }
            }
        } catch (IOException | IllegalArgumentException e ) {
            log(e);
            throw e;
        }
    }

    public static void printStatistics() {
        int[][] copiedText = Arrays.copyOfRange(text, 0, pivot + 1);

        Arrays.sort(copiedText, Comparator.comparing(a -> a[0]));
        System.out.println("Statistics (Char Ascending)");
        for (int[] ints : copiedText) {
            System.out.printf("%c\t%d\t%.2f%%\n", ints[0], ints[1], ints[1] / (double) count);
        }
    }

    public static boolean saveChar(int ch) {
        int charPosition = -1;
        boolean inserted = false;

        if (isFull(text)) {
            return false;
        }

        charPosition = getCharPosition(ch);

        if (charPosition == -1) {
            pivot++;
            text[pivot][0] = ch;
            text[pivot][1] += 1;
            inserted = true;
        } else {
            text[charPosition][1]++;
            inserted = true;
        }

        return inserted;
    }

    public static int getCharPosition(int ch) {
        for (int i = 0; i <= pivot; i++) {
            if (text[i][0] == ch) {
                return i;
            }
        }

        return -1;
    }

    public static boolean isFull(int[][] text) {
        return pivot == text.length - 1;
    }


    public static void log(Exception e) {
        try (PrintStream ps = new PrintStream(new FileOutputStream(path.toFile(), true))) {
            ps.println(LocalDateTime.now() + "\n" + e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
