import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner keyboard = new Scanner(System.in);
        System.out.println(">> How many players? (1 or 2)");
        String players = keyboard.nextLine();

        String word = "blank";
        if(players.equals("1")){
            Scanner scanner = new Scanner(new File("/Users/maku/Documents/Java/Hangman/words.txt"));
            List<String> words = new ArrayList<>();
            while(scanner.hasNext()){
                words.add(scanner.nextLine());
            }

            Random random = new Random();
            word = words.get(random.nextInt(words.size()));
        }else{
            System.out.println(">> Player 1 please write the word!");
            word = keyboard.nextLine();
            System.out.println("\n\n\n\n\n\n");
            System.out.println(">> Player 2 please guess the word!");
        }

        //System.out.println(word);
        
        List<Character> playerGuesses = new ArrayList<>();

        int wrongCount = 0;
        while(true) {

            printHangman(wrongCount);

            if(wrongCount >= 6){
                System.out.println(">> You lose! The word was: " + word);
                break;
            }
            printWordState(word, playerGuesses);

            if(!getPlayerGuess(keyboard, playerGuesses, word)){
                wrongCount++;
            }

            if(printWordState(word, playerGuesses)){
                System.out.println(">> You found the word!");
                break;
            }
            System.out.println(">> Guess the word!");
            if(keyboard.nextLine().equals(word)){
                System.out.println(">> You guessed the word!");
                break;
            }else{
                System.out.println(">> Thats not the word!");
            }
        }

    }

    private static void printHangman(int wrongCount) {
        System.out.println("     *********");
        System.out.println("     |        ");
        if(wrongCount >= 1) {
            System.out.println("    ( xx)");
        }else{
            System.out.println();
        }
        if(wrongCount >= 2) {
            System.out.print("    /");
            if(wrongCount == 2)
                System.out.println();
            if (wrongCount >= 3)
                System.out.println(" \\");
        }else{
            System.out.println();
        }
        if(wrongCount >= 4) {
            System.out.println("     |");
        }else{
            System.out.println();
        }
        if(wrongCount >= 5) {
            System.out.print("    /");
            if(wrongCount == 5)
                System.out.println();
            if (wrongCount >= 6)
                System.out.println(" \\");
        }else{
            System.out.println();
        }
        System.out.println("**************");
    }

    private static boolean getPlayerGuess(Scanner keyboard, List<Character> playerGuesses, String word) {
        System.out.println(">> Enter a letter!");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));
        return word.contains(letterGuess);
    }

    private static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for(int i = 0; i < word.length(); i++){
            if(playerGuesses.contains(word.charAt(i))){
                System.out.print(word.charAt(i));
                correctCount++;
            }else{
                System.out.print("_");
            }
        }
        System.out.println();
        return word.length() == correctCount;
    }
}