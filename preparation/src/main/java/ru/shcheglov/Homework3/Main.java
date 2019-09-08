package ru.shcheglov.Homework3; /**
 * Java 1. Homework3. Guess the number and guess the word games.
 *
 * @author Alexey Shcheglov
 * @version dated Nov 29, 2017
 * @link https://github.com/Pharmazon
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();    
    
    public static void main(String[] args) {
        while (true) {
            System.out.print("Choose the game:\n" + 
                    "1. Guess the number\n2. Guess the word\n3. Exit\n");
            switch (sc.next()) {
                case "1":
                    guessTheNumber(10, 3);
                    break;
                case "2":
                    guessTheWord(Objects.requireNonNull(readFromFileByLines("inputwords.txt")));
                    break;
                default:
                    return;
            }
        }
    }
    
    //Task 1. Guess the number GAME
    public static void guessTheNumber(int limit, int times) {
        int uservar = 0;
        int val = 0;
        int flag = 1;
        
        //Print welcome words to console
        System.out.println("========== GUESS THE NUMBER GAME ==========");
        System.out.println("");
        
        //infinite main cycle intil user decide not to play again
        while (flag == 1) {
            val = rand.nextInt(limit);
            System.out.println("I chose the number between 0 and " + (limit - 1) + ". Guess it! You have only " + times +" attempts.");
            
            //comparing the user number with computer
            for (int i = 1; i <= times; i++) {
                System.out.println("Enter the number: ");
                uservar = sc.nextInt();
                if (uservar == val) {
                    System.out.println("Congratulations! You WON! You guessed the number '" + val + "'."); 
                    break;                  
                } else if (uservar > val) {
                    System.out.println("Your number is GREATER. You have " + (times - i)+ " attempts left.");
                } else {
                    System.out.println("Your number is LESS. You have " + (times - i)+ " attempts left.");                
                } 
                if (i == times) System.out.println("Unfortunately, you LOST! I guessed number '" + val + "'."); 
            }      
            
            //Play again?
            System.out.println("Play again? (1 - yes / 0 - no)"); 
            flag = sc.nextInt();    
            if (flag == 0) break;
        }
    }
    
    //Task 2. Guess the word GAME
    public static void guessTheWord(String[] arr) {  
        String userword;
        boolean play = true;
        String compword = new String(arr[rand.nextInt(arr.length - 1)].toLowerCase());
        char[] hide = new char[15];
        hide = "###############".toCharArray();
        int minsym = 0;  
        String convertToStr;
        
        //Print welcome words to console
        System.out.println("========== GUESS THE WORD GAME ==========");        
        System.out.println("I guessed the word.  Guess it!");

        //infinite main cycle intil user guess the word
        while (play) {
            
            //Ask user to enter the word in console
            System.out.println("Enter the word:");
            userword = sc.next().toLowerCase();
            
            //search of string with minimum count of symbols
            if (userword.length() > compword.length()) minsym = compword.length();
            else if (userword.length() < compword.length()) minsym = userword.length();
            else minsym = compword.length();

            //comparing of two string char by char for minsym times
            for (int i = 0; i < minsym; i++) {
                if (userword.charAt(i) == compword.charAt(i) && hide[i] == '#') {
                    hide[i] = userword.charAt(i);
                }
            }

            //check hide array if contains compword. If not, print to console the guessed characters 
            //and print congratulations in case of user guess the word or guessed the last character
            convertToStr = String.valueOf(hide);
            if (!convertToStr.contains(compword))
                System.out.println("You guessed the following characters: " + convertToStr);
            else if (userword.equalsIgnoreCase(compword) || convertToStr.contains(compword)) {
                System.out.println("Congratulations! You guessed the word '" + compword + "'.");
                play = false;                
            }
        }        
    }
    
    public static String[] readFromFileByLines (String filename) {
        try {
            int linescount = 0;
            int count = 0;
            String line; 
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(
                new FileInputStream(filename), StandardCharsets.UTF_8));
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(
                new FileInputStream(filename), StandardCharsets.UTF_8));                
            while (reader1.readLine() != null) {
                linescount++;
            }
            String[] inwords = new String[linescount];
            while ((line = reader2.readLine()) != null) {
                inwords[count] = line.trim();
                count++;
            }
            return inwords;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}