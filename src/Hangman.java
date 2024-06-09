import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    private static List<String> dictionary;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите [1] для начала новой игры, [2] для выхода.");
            String s = scanner.nextLine();
            if (s.equals("1") || s.equals("e")) {
                Game game = new Game(getRandomWord());
                while (game.isActive()) {
                    game.checkLetter(readCharacter());
                }
            } else if (s.equals("2")) {
                break;
            }
        }
        scanner.close();
    }

    private static String getRandomWord() {
        if (dictionary == null) {
            dictionary = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("src/dictionary.txt"))) {
                while (reader.ready()) {
                    dictionary.add(reader.readLine());
                }
            } catch (Exception e) {
                System.out.println("Error of reading dictionary.");
            }
        } else {

        }
        Random random = new Random();
        return dictionary.get(random.nextInt(dictionary.size()));
    }

    private static String readCharacter() {
        String input = null;
        while (true) {
            input = scanner.nextLine();
            if (input.length() != 1 || !input.matches("[а-яА-Я]")) {
                System.out.println("Нужно ввести одну букву русского алфавита. Попробуйте снова.");
            } else {
                input = input.toLowerCase();
                break;
            }
        }
        return input;
    }
}