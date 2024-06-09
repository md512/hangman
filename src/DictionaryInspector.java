import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class DictionaryInspector {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("This app read a file 'dictionary.txt' in current directory \n" +
                "and delete all words less than specified length.");
        System.out.println("Enter minimal word length.");

        int minLength = 0;
        String s = scanner.nextLine();
        try {
            minLength = Integer.parseInt(s);
        } catch (Exception e) {
            System.out.println("Cannot parse Int.");
        }
        scanner.close();

        List<String> words = readFile();
        Iterator<String> iterator = words.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().length() <= minLength) {
                iterator.remove();
            }
        }

        try (BufferedWriter writer = new BufferedWriter (new FileWriter("src/dictionary.txt"))) {
            for (String word : words) {
                writer.write(word + "\n");
            }
            System.out.println("Done.");
        } catch (Exception e) {
            System.out.println("Error of writing file");
        }

    }

    private static List<String> readFile() {
        List<String> words = new ArrayList<>();
        try ( BufferedReader reader = new BufferedReader(new FileReader("src/dictionary.txt"))){
            while (reader.ready()) {
                words.add(reader.readLine());
            }
        } catch (Exception e) {
            System.out.println("Error of reading file.");
        }
        return words;
    }
}
