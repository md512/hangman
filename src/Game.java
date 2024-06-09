import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Game {
    private boolean isActive;
    private int closedLetters;
    private int mistake;
    private final char[] question; //full word
    private final char[] answer;  //template with *
    private final Set<String> usedLetters = new HashSet<>();

    public Game(String word) {
        question = word.toCharArray();
        answer = new char[question.length];
        Arrays.fill(answer, '*');
        closedLetters = question.length;
        isActive = true;
        System.out.println("Новая игра. Угадайте слово.");
        printStatus();
    }

    public boolean isActive() {
        return isActive;
    }

    public void checkLetter(String s) {
        if (usedLetters.contains(s)) {
            System.out.println("Вы уже использовали эту букву.");
            return;
        }

        char c = s.charAt(0);
        boolean isCorrectAnswer = false;
        usedLetters.add(String.valueOf(c));
        for (int i = 0; i < question.length; i++) {
            if (c == question[i]) {
                answer[i] = c;
                closedLetters--;
                isCorrectAnswer = true;
            }
        }
        if (!isCorrectAnswer) {
            mistake++;
        }
        printStatus();
        checkResult();
    }

    private void printStatus() {
        PrintHelper.printHangman(mistake);
        System.out.println(new String(answer));
    }

    private void checkResult() {
        if (closedLetters == 0 && mistake < 6) {
            System.out.println("Вы победили!");
            isActive = false;
        } else if (mistake == 6 && closedLetters > 0) {
            System.out.println("Вы проиграли. Было загадано слово \"" + String.valueOf(question) + "\".");
            isActive = false;
        }
    }
}
