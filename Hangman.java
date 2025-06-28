import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {

        String path = "src/Words.txt";

        ArrayList<String> words = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;
            while((line = reader.readLine())!= null){
                words.add(line.trim());
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found!!");
        }
        catch(IOException e){
            System.out.println("Something went wrong!!");
        }

        Random random = new Random();

        String word = words.get(random.nextInt(words.size()));

        Scanner scanner = new Scanner(System.in);

        ArrayList<Character> characters = new ArrayList<>();

        int wrongGuess =0 ;


        for(int i = 0;i<word.length();i++){
            characters.add('_');
        }
        System.out.println("Let's Play HANGMAN game!!");



        while((wrongGuess <6)){

            System.out.print("Word:");
            for(char c:characters){
                System.out.print(c + " ");
            }
            System.out.println();

            System.out.print("Guess a letter:");
            char guess = scanner.next().toUpperCase().charAt(0);

            if(word.indexOf(guess) >=0){
                System.out.println("Correct Guess!!");
                for(int i=0;i<word.length();i++){
                    if(word.charAt(i)==guess){
                        characters.set(i,guess);
                    }
                }
                if(!characters.contains('_')){
                    System.out.print(HangmanStates(wrongGuess));
                    System.out.println("You Win !!!");
                    System.out.println("The Word: "+word);
                    break;
                }
            }
            else{
                System.out.println("Wrong guess!");
                wrongGuess++;
                System.out.println(HangmanStates(wrongGuess));
            }
            if(wrongGuess==6){
                System.out.println(HangmanStates(6));
                System.out.println("GAME OVER!!");
                System.out.println("The word:"+word);
            }
        }
    }
    static String HangmanStates(int wrongGuess){

        return switch(wrongGuess){
            case 0 -> " ";
            case 1 -> """
                       O
                      
                      
                      """;
            case 2 -> """
                       O
                       |
                      
                      """;
            case 3 -> """
                       O
                      /|
                       
                      """;
            case 4 -> """
                       O
                      /|\\
                       
                      """;
            case 5 -> """
                       O
                      /|\\
                      /
                      """;
            case 6 -> """
                       O
                      /|\\
                      / \\
                      """;
            default -> """ 
                       """;
        };
    }
}
